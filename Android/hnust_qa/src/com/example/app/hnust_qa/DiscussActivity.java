package com.example.app.hnust_qa;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.hnust_qa.Bean.DiscussBean;
import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/8/6 0006.
 */
public class DiscussActivity extends SwipeBackActivity {

    public static final int HTTP_REQUEST_SUCCESS = -1;
    public static final int HTTP_REQUEST_ERROR = 0;

    private List<DiscussBean> list;
    private String url = NetTool.web + "getDetilQuestionMobile?question_id=";
    private String addDiscussUrl = NetTool.web + "AddDiscussMobileServlet";

    private int anwserId;
    private String questionId;

    private ListView listView;
    private EditText discuss_comment;
    private Button discuss_button;
    private String comment;
    
    //SwipeBackActivity相关组件
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discuss_layout);
        //设置swipeLayout
        mSwipeBackLayout  = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        
        //设置进入画效果(退出效果在onpause里面)
        overridePendingTransition(R.anim.enter_animation,R.anim.back_animation );
        

        Intent intent = getIntent();
        questionId = intent.getStringExtra("question_id");
        anwserId = Integer.parseInt(intent.getStringExtra("answer_id"));

        list = new ArrayList<DiscussBean>();
        listView = (ListView) findViewById(R.id.discuss_listView);
        discuss_comment = (EditText) findViewById(R.id.discuss_comment);
        discuss_button = (Button) findViewById(R.id.discuss_button);

        listView.setAdapter(new DiscussAdapter());

        new GetDiscussNet().execute();

        discuss_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("添加评论","成功");
                comment = "";
                comment = discuss_comment.getText().toString().trim();
                new addCommentTask().execute();
            }
        });
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
        //设置退出动画效果
        overridePendingTransition(R.anim.enter_animation,R.anim.back_animation );
    }

    class DiscussAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.discuss_listview, null);

                TextView username = (TextView) view.findViewById(R.id.discuss_username);
                TextView time = (TextView) view.findViewById(R.id.discuss_time);
                TextView summary = (TextView) view.findViewById(R.id.discuss_summary);
                TextView zanNumber = (TextView) view.findViewById(R.id.discuss_zan_number);

                username.setText(list.get(i).getAnswer_username());
                time.setText(list.get(i).getTime().substring(0, 9));
                summary.setText(list.get(i).getContent());
//                zanNumber.setText(list.get(i).get);

            }
            return view;
        }
    }

    class GetDiscussNet extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            getQuestionDetail(questionId);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }

        //联网，取得数据
        private void getQuestionDetail(String id) {

            try {
                url = url + id;
                HttpGet httpGet = new HttpGet(url);
                HttpClient httpClient = new DefaultHttpClient();
                HttpResponse httpResponse = httpClient.execute(httpGet);

                if (httpResponse.getStatusLine().getStatusCode() == 200) {

                    HttpEntity httpEntity = httpResponse.getEntity();
                    String json = EntityUtils.toString(httpEntity, "UTF-8");
                    JSONObject jsonObject = new JSONObject(json);

                    JSONArray listAllDiscuss = jsonObject.getJSONArray("listAllDiscuss");
                    for (int j = 0; j < listAllDiscuss.length(); j++) {
                        JSONObject discuss = listAllDiscuss.getJSONObject(j).getJSONObject("discussBean");

                        int answer_id = discuss.getInt("answer_id");
                        if (answer_id != anwserId) {
                            continue;
                        }
                        DiscussBean bean = new DiscussBean();
                        bean.setAnswer_id(discuss.getInt("answer_id"));
                        bean.setContent(discuss.getString("content"));
                        bean.setDiscuss_id(discuss.getInt("discuss_id"));
                        bean.setTime(discuss.getString("time"));
                        bean.setUser_id(discuss.getInt("user_id"));
                        bean.setAnswer_username(listAllDiscuss.getJSONObject(j).getString("userName"));

                        list.add(bean);
                    }

                } else {
                    Looper.prepare();
                    Toast.makeText(DiscussActivity.this, "服务器粗事了>_<", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    //添加评论
    class addCommentTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... strings) {
            if (NetTool.isWifiConnected(DiscussActivity.this)) {
                try {
                    Thread.sleep(1000);
                    return HTTP_REQUEST_SUCCESS;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return HTTP_REQUEST_ERROR;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            switch (result) {
                case HTTP_REQUEST_SUCCESS:
                    addAnswer();
                    break;
                case HTTP_REQUEST_ERROR:
                    Toast.makeText(DiscussActivity.this, "请检查网络", Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }

        private void addAnswer() {
            String message;
            try {
                HttpClient httpClient = new DefaultHttpClient();
                httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
                HttpPost httpPost = new HttpPost(addDiscussUrl);

                List<BasicNameValuePair> valuePairs = new ArrayList<BasicNameValuePair>();
                valuePairs.add(new BasicNameValuePair("discuss_content",comment));
                valuePairs.add(new BasicNameValuePair("answer_id",anwserId + ""));
                valuePairs.add(new BasicNameValuePair("question_id",questionId));
                valuePairs.add(new BasicNameValuePair("user_id",NetTool.userId));

                httpPost.setEntity(new UrlEncodedFormEntity(valuePairs,"utf-8"));
                HttpResponse httpResponse = httpClient.execute(httpPost);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    HttpEntity entity = httpResponse.getEntity();
                    String json = EntityUtils.toString(entity,"utf-8");
                    JSONObject object = new JSONObject(json);
                    message = object.getString("message");
                    if(message.equals("true")){
                        Toast.makeText(DiscussActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(DiscussActivity.this,MainActivity.class);
//                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(DiscussActivity.this,"没有发布成功呦，再发一次试试吧",Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
