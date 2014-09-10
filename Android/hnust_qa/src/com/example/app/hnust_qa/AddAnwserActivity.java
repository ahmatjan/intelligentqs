package com.example.app.hnust_qa;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static com.example.app.hnust_qa.Tools.LayoutTool.setCustomTitle;

/**
 * Created by Administrator on 2014/8/11 0011.
 */
public class AddAnwserActivity extends SwipeBackActivity {

    private TextView title_name;
    private TextView title_center;
    private TextView title_right;
    private EditText et_add_anwser;

    private String addAnswerUrl;
    private String comment;
    private String questionId;
    
    //SwipeBackActivity相关组件
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_anwser_layout);
        //设置swipeBackLayout
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        
        Intent intent = getIntent();
        questionId = intent.getStringExtra("questionId");

        //设置标题栏
        setCustomTitle(AddAnwserActivity.this,R.layout.title_layout);
        title_name = (TextView) findViewById(R.id.title_name);
        title_center = (TextView) findViewById(R.id.title_middle);
        title_right = (TextView) findViewById(R.id.title_right);
        title_name.setText("添加答案");
        title_center.setText("");
        title_right.setText("发布");

        et_add_anwser = (EditText) findViewById(R.id.et_add_anwser);

        //设置进入画效果(退出效果在onpause里面)
        overridePendingTransition(R.anim.enter_animation,R.anim.back_animation );

        title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = et_add_anwser.getText().toString().trim();
                new AddAnswerTask().execute();
                finish();
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


    //评论
    class AddAnswerTask extends AsyncTask {

        private int statuCode = 0;

        @Override
        protected Object doInBackground(Object[] objects) {
            addAnswerUrl = NetTool.web + "addAnswerInfoMobile?answer_description=";
            addAnswerUrl = addAnswerUrl + comment + "&question_id=" + questionId + "&userId=" + NetTool.userId;

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(addAnswerUrl);
                httpGet.setHeader("Cookie", NetTool.sessionId);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                statuCode = httpResponse.getStatusLine().getStatusCode();

                Log.e("url", addAnswerUrl);
                Log.e("comment",comment);
                Log.e("questionId",questionId);

                HttpEntity httpEntity = httpResponse.getEntity();
                String string = EntityUtils.toString(httpEntity, "utf-8");

                Log.e("string",string);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(statuCode == 200){
                Toast.makeText(AddAnwserActivity.this, "评论成功", Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(AddAnwserActivity.this,"评论有点小问题，再试一次吧",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
