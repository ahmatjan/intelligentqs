package com.example.app.hnust_qa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Devilsen on 2014/7/30 0030.
 */
public class LoginActivity extends Activity {

    private EditText et_username;
    private EditText et_password;
    private Button bu_login;
    private Button bu_register;

    private String username;
    private String password;
    private String userId;
    
    private SharedPreferences sp;

    private String reurl = NetTool.web + "loginMobileServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bu_login = (Button) findViewById(R.id.bu_login);
        bu_register = (Button) findViewById(R.id.bu_register);
        
        autoLoging();

        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString().trim();
                NetTool.username = username;
                password = et_password.getText().toString().trim();

                new URLConnect(LoginActivity.this).execute();
            }
        });
        
    }
    
    private boolean autoLoging(){
    	boolean flag = false;
    	
    	String spUsername = sp.getString("username", "");
    	String spPassword = sp.getString("password", "");
    	NetTool.userId = sp.getString("userId", "");
    	if( !spUsername.isEmpty() || !spPassword.isEmpty() ){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("username",spUsername);
            startActivity(intent);
            finish();
            
            flag = true;
    	}
    	
    	return flag;
    }

    class URLConnect extends AsyncTask<String, Integer, String> {

        String message;
        String sessionid;
        String status = "null";

        private Context mContext;
        private ProgressDialog dialog;

        public URLConnect(Context mContext) {
            this.mContext = mContext;
            initDialog();
        }

        //初始化进度条框
        private void initDialog() {
            dialog = new ProgressDialog(mContext);
            dialog.setMax(100);
//            dialog.setCancelable(false);//返回键不可取消
 //           dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMessage("正在登陆");

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                HttpClient httpClient = new DefaultHttpClient();
                httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,8000);//核心连接名称。。。
                httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,5000);

                HttpPost httpPost = new HttpPost(reurl);

                List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair("user_name", username));
                valuePairs.add(new BasicNameValuePair("user_password", password));

                httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                if( httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK ){
                    //获取sessionid
                    String session = httpResponse.getFirstHeader("Set-Cookie").toString();
                    NetTool.sessionId = session.substring(13,session.indexOf(";"));
                    Log.e("session",session);
//                    Log.e("sessionid",sessionid);

                    HttpEntity httpEntity = httpResponse.getEntity();
                    String json = EntityUtils.toString(httpEntity, "utf-8");
                    Log.e("json",json);
                    JSONObject object = new JSONObject(json);
                    message = object.getString("message");
                    userId = object.getString("userId");
                    NetTool.userId = userId;

                    status = "true";
                }else{
                    status = "连接错误";
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setProgress(values[0]);//如果是进度条的话，这里来改变进度条的进度。需要在doInBackground方法里添加   publishProgress((int) ((count / (float) length) * 100)); 方法，就是按比例来加进度。同时设置progress的style为dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(status.equals("true")){
                if(message.equals("true")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("sessionid",sessionid);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    dialog.dismiss();
                    //保存用户名、密码
                    Editor editor = sp.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putString("userId", userId);
                    editor.commit();
                    
                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("用户名或密码出错了哦(⊙o⊙)");
                    builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }
            }else if(status.equals("null")){
                dialog.dismiss();
                Toast.makeText(LoginActivity.this,"服务器无响应",Toast.LENGTH_SHORT).show();
            }else{
                dialog.dismiss();
                Toast.makeText(LoginActivity.this,status,Toast.LENGTH_SHORT).show();
            }


        }
    }

}
