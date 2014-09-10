package com.example.app.hnust_qa;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/8/4 0004.
 */
public class AskQestionActivity extends SwipeBackActivity {

    private String url = NetTool.web + "askQuestionMobileServlet";

    private EditText et_ask_title;
    private EditText et_ask_description;
    private EditText et_ask_label;
    private Button ask_button;

    private String title;
    private String description;
    private String label;
    
    //SwipeBackActivity相关组件
    private SwipeBackLayout mSwipeBackLayout;

	private TextView title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_question_layout);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        
        //设置自定义标题栏
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_layout);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("? 提问");

        et_ask_title = (EditText) findViewById(R.id.et_ask_title);
        et_ask_description = (EditText) findViewById(R.id.et_ask_description);
        et_ask_label = (EditText) findViewById(R.id.et_ask_label);
        ask_button = (Button) findViewById(R.id.ask_button);

        ask_button.setOnClickListener(new AskQuestion());
        
        //设置进入画效果(退出效果在onpause里面)
        overridePendingTransition(R.anim.enter_animation,R.anim.back_animation );
        


    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
        //设置退出动画效果
        overridePendingTransition(R.anim.enter_animation,R.anim.back_animation );
    }

    class AskQuestion implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            title = et_ask_title.getText().toString().trim();
            description = et_ask_description.getText().toString().trim();
            label = et_ask_label.getText().toString().trim();

            new AskQuestionNet().execute();
        }
    }

    class AskQuestionNet extends AsyncTask {

        String status = "null";

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("question_title", title));
                list.add(new BasicNameValuePair("question_description", description));
                list.add(new BasicNameValuePair("question_tags", label));
                list.add(new BasicNameValuePair("userId", NetTool.userId));

//                Log.e("title",title);
//                Log.e("description",description);
//                Log.e("label",label);

                httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));

                HttpResponse httpResponse = httpClient.execute(httpPost);

                if( httpResponse.getStatusLine().getStatusCode() == 200){

                    HttpEntity httpEntity = httpResponse.getEntity();
                    String json = EntityUtils.toString(httpEntity,"utf-8");
                    Log.e("html", json);
                    JSONObject object = new JSONObject(json);
                    status = object.getString("message");

                }else{
                    status = "false";
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(status.equals("true")){
                Toast.makeText(AskQestionActivity.this,"提问成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AskQestionActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(AskQestionActivity.this,"没有发布成功呦，再发一次试试吧",Toast.LENGTH_SHORT).show();
            }

        }
    }


}
