package com.example.app.hnust_qa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.hnust_qa.Bean.AnswerBean;
import com.example.app.hnust_qa.Bean.DiscussBean;
import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;

/**
 * Created by Administrator on 2014/7/28 0028.
 */
public class QuestionDetail2 extends SwipeBackActivity {

	private ImageView portrait;
	private TextView askUsername;
	private TextView title;
	private TextView summary;
	private Button bu_addAnwser;
	private Button bu_addWatch;
	private String summaryString;
	private String url = NetTool.web + "getDetilQuestionMobile?question_id=";

	private List<AnswerBean> list;
	private List<DiscussBean> listDiscuss;
	private String questionId;

	private mAdapter commentAdapter;
	private TextView title_name;

	private ListView mianLayout;
	private PullToRefreshLayout mPullToRefreshLayout;
	private Thread listThread;

	private SwipeBackLayout mSwipeBackLayout;
	private LinearLayout questionLinearLayout;

	private RelativeLayout noAnwserFoot;
	
	private PopupWindow mpopuWindow;
	private int flag = 0;
	private int prepraiseFlag = 0;
	private int treadasFlag = 0; 
	
	private Button bu_agree;
	private Button bu_opposition;
	private Button bu_comments;
	
	private Animation textUpAnimation; 
	private Animation textDownAnimation;
	
	ViewHolder holder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_detail_main_layout);

		questionLinearLayout = (LinearLayout) findViewById(R.id.question_detail_linearLayout);
		mPullToRefreshLayout = new PullToRefreshLayout(this);

		// 设置swipebacklayout
		mSwipeBackLayout = getSwipeBackLayout();
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

		mianLayout = (ListView) findViewById(R.id.question_detail_layout);
		RelativeLayout question_content = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.question_detail_head, null);
		noAnwserFoot = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.no_anwser_foot, null);

		mianLayout.addHeaderView(question_content, null, false);

		// 设置自定义标题栏
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.title_layout);
		title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("问题");

		// 设置进入画效果(退出效果在onpause里面)
		overridePendingTransition(R.anim.enter_animation, R.anim.back_animation);
		//设置动画
		textUpAnimation = AnimationUtils.loadAnimation(this, R.anim.text_up);
		textDownAnimation = AnimationUtils.loadAnimation(this, R.anim.text_drop);
		
		list = new ArrayList<AnswerBean>();
		listDiscuss = new ArrayList<DiscussBean>();

		portrait = (ImageView) question_content
				.findViewById(R.id.detail_portrait);
		askUsername = (TextView) question_content
				.findViewById(R.id.detail_username);
		title = (TextView) question_content.findViewById(R.id.detail_title);
		summary = (TextView) question_content.findViewById(R.id.detail_summary);
		bu_addAnwser = (Button) question_content.findViewById(R.id.add_anwser);
		bu_addWatch = (Button) question_content.findViewById(R.id.add_watch);

		Intent intent = getIntent();
		askUsername.setText(intent.getStringExtra("question_username"));
		title.setText(intent.getStringExtra("question_title"));
		summary.setText(intent.getStringExtra("question_summary"));
		questionId = intent.getStringExtra("question_id");
		url = url + questionId;

		pullToRefresh();
		popuWindow();

		listThread = new Thread(runnable);
		listThread.start();

		// 添加答案
		bu_addAnwser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent1 = new Intent(QuestionDetail2.this,
						AddAnwserActivity.class);
				intent1.putExtra("questionId", questionId + "");
				startActivity(intent1);

			}
		});
		// 收藏问题
		bu_addWatch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new starTast().execute();
			}
		});

	}

	//popuwindow
	private void popuWindow(){
		View popLayout = getLayoutInflater().inflate(R.layout.popuwindow, null);
		mpopuWindow = new PopupWindow(popLayout,380,90);
		//点击空白消失
		mpopuWindow.setTouchable(true);
		mpopuWindow.setOutsideTouchable(true);
		mpopuWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
		
		bu_agree = (Button) popLayout.findViewById(R.id.agree);
		bu_opposition = (Button) popLayout.findViewById(R.id.opposition);
		bu_comments = (Button) popLayout.findViewById(R.id.comments);
		
	}
	
	
	// 下拉刷新
	private void pullToRefresh() {

		ActionBarPullToRefresh
				.from(QuestionDetail2.this)
				.insertLayoutInto(questionLinearLayout)
				.theseChildrenArePullable(mianLayout)
				// We need to mark the ListView and it's Empty View as pullable
				// This is because they are not direct children of the
				.options(
						Options.create()
								.refreshingText("正在加载...")
								.pullText("下拉刷新")
								.releaseText("放开我>_<")
								.titleTextColor(R.color.topbartext)
								.headerBackgroundColor(R.color.topbar)
								.progressBarStyle(
										Options.PROGRESS_BAR_STYLE_INSIDE) // or
																			// Options.PROGRESS_BAR_STYLE_OUTSIDE
								.build()).listener(new OnRefreshListener() {

					@Override
					public void onRefreshStarted(View view) {

						new AsyncTask<Void, Void, Void>() {

							@Override
							protected Void doInBackground(Void... params) {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								return null;
							}

							@Override
							protected void onPostExecute(Void result) {
								super.onPostExecute(result);

								getQuestionDetail(questionId);
								commentAdapter.notifyDataSetChanged();
								mPullToRefreshLayout.setRefreshComplete();

							}
						}.execute();
					}
				}).setup(mPullToRefreshLayout);

	}

	// 加载
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {
				commentAdapter = new mAdapter();
				mianLayout.setAdapter(commentAdapter);
				mianLayout.setOnItemClickListener(new listViewItemListener());
				summary.setText(summaryString);

				if (list.size() == 0) {
					mianLayout.addFooterView(noAnwserFoot, null, false);
				}
				if (list.size() > 0) {
					mianLayout.removeFooterView(noAnwserFoot);
				}

			} else {
				Toast.makeText(QuestionDetail2.this, "没有取到信息(>_<)",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	Runnable runnable = new Runnable() {
		@Override
		public void run() {

			if (list.isEmpty()) {
				getQuestionDetail(questionId);
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}
	};

	// 联网，取得数据
	private void getQuestionDetail(String id) {

		try {

			String urlString = url;
			HttpGet httpGet = new HttpGet(urlString);
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				list.removeAll(list);
				HttpEntity httpEntity = httpResponse.getEntity();
				String json = EntityUtils.toString(httpEntity, "UTF-8");
				JSONObject jsonObject = new JSONObject(json);
				JSONObject question = jsonObject.getJSONObject("question");
				summaryString = question.getJSONObject("questionBean")
						.getString("question_description");

				JSONArray listAnswer = jsonObject.getJSONArray("listAnswer");
				for (int i = 0; i < listAnswer.length(); i++) {
					JSONObject answer = listAnswer.getJSONObject(i)
							.getJSONObject("answerBean");
					AnswerBean bean = new AnswerBean();

					bean.setAnswer_best(answer.getInt("answer_best"));
					bean.setAnswer_description(answer
							.getString("answer_description"));
					bean.setAnswer_id(answer.getInt("answer_id"));
					bean.setAnswer_mark(answer.getInt("answer_mark"));
					bean.setAnswer_time(answer.getString("answer_time"));
					bean.setAnswer_user_id(answer.getInt("answer_user_id"));
					bean.setQuestion_id(answer.getInt("question_id"));
					bean.setUserName(listAnswer.getJSONObject(i).getString(
							"userName"));

					list.add(bean);

				}

			} else {
				Looper.prepare();
				Toast.makeText(QuestionDetail2.this, "服务器粗事了>_<",
						Toast.LENGTH_SHORT).show();
				Looper.loop();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// 设置退出动画效果
		overridePendingTransition(R.anim.enter_animation, R.anim.back_animation);
	}


	// listview适配器
	class ViewHolder {
		TextView comment;
		TextView commentUserName;
		TextView zanNumber;
		TextView addOne;
		TextView deleteOne;
	}
	class mAdapter extends BaseAdapter {
		
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
			int position = i;

			if (view == null) {
				view = getLayoutInflater().inflate(
						R.layout.question_detail_listview, null);

				holder = new ViewHolder();
				holder.comment = (TextView) view.findViewById(R.id.question_detail_comment_summary);
				holder.commentUserName = (TextView) view.findViewById(R.id.question_detail_comment_username);
				holder.zanNumber = (TextView) view.findViewById(R.id.answer_comment_count);
				holder.addOne = (TextView) view.findViewById(R.id.addone);
				holder.deleteOne = (TextView) view.findViewById(R.id.deleteone);

				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			holder.comment.setText(list.get(position).getAnswer_description());
			holder.commentUserName.setText(list.get(position).getUserName());
			holder.zanNumber.setText(list.get(position).getAnswer_mark() + "");

			return view;
		}

	}

	// listView点击事件
	class listViewItemListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
			final int position = i - 1;
			final int mark = list.get(position).getAnswer_mark();

			int popWidth = mpopuWindow.getWidth();
            int screenWidth = getResources().getDisplayMetrics().widthPixels ;
            int xoff = (screenWidth  - popWidth)/2 ;
            int yoff = mpopuWindow.getHeight() + view.getHeight() / 2 + 10;
			mpopuWindow.showAsDropDown(view,xoff,-yoff);
			//评论按钮
			bu_comments.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(QuestionDetail2.this,DiscussActivity.class);

					// 传递一些必要的数据
					intent.putExtra("answer_id", list.get(position).getAnswer_id() + "");
					intent.putExtra("question_id", questionId);

					startActivity(intent);
					mpopuWindow.dismiss();
				}
			});
			//赞按钮
			bu_agree.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new PreprasiseTask().execute("praiseasMobile","answerid_" + list.get(position).getAnswer_id() + "");
					String textagree = bu_agree.getText().toString().trim();
					if( textagree.equals("同意")){
						bu_agree.setText("已同意");
						bu_opposition.setText("反对");
						holder.zanNumber.setText(mark + 1 +"");
						holder.addOne.startAnimation(textUpAnimation);
					}else{
						bu_agree.setText("同意");
						holder.zanNumber.setText(mark +"");
					}
					mpopuWindow.dismiss();
				}
			});
			//踩按钮
			bu_opposition.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new PreprasiseTask().execute("treadasMobile",list.get(position).getAnswer_id() + "");
					String textopposition = bu_opposition.getText().toString().trim();
					if( textopposition.equals("反对")){
						bu_opposition.setText("已反对");
						bu_agree.setText("同意");
						holder.zanNumber.setText(mark - 1 +"");
						holder.deleteOne.startAnimation(textDownAnimation);
					}else{
						bu_opposition.setText("反对");
						holder.zanNumber.setText(mark +"");
					}
					mpopuWindow.dismiss();
				}
			});
			
		}
	}

	class getAnswerTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object[] objects) {
			return null;
		}

		@Override
		protected void onPostExecute(Object o) {
			super.onPostExecute(o);
			getQuestionDetail(questionId);
			commentAdapter.notifyDataSetChanged();
		}
	}

	//收藏问题task
	class starTast extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			String text = "";
			String bu_text = "";
			
			try {
				String url = NetTool.web + "starMobile";
				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);

				HttpPost post = new HttpPost(url);

				List<NameValuePair> valuePair = new ArrayList<NameValuePair>();
				valuePair.add(new BasicNameValuePair("question_id", questionId));
				valuePair.add(new BasicNameValuePair("userid", NetTool.userId));
				post.setEntity(new UrlEncodedFormEntity(valuePair));

				HttpResponse response = client.execute(post);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					flag++;
				}

				if((flag & 1)==0){
					text = "取消收藏";
					bu_text = "收藏";
				}else{
					text = "收藏成功";
					bu_text = "已收藏";
				}
				Toast.makeText(QuestionDetail2.this, text, Toast.LENGTH_LONG).show();
				bu_addWatch.setText(bu_text);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	//赞问题Task
	class PreprasiseTask extends AsyncTask<String, Integer, Boolean>{

		String urlLocation;
		String id;
		
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			urlLocation = params[0];
			id = params[1];
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				String url = NetTool.web + urlLocation;
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				
				List<BasicNameValuePair> nameValuePair = new ArrayList<BasicNameValuePair>();
				nameValuePair.add(new BasicNameValuePair("id", id));
				nameValuePair.add(new BasicNameValuePair("userid", NetTool.userId));
		
				post.setEntity(new UrlEncodedFormEntity(nameValuePair));
				
				HttpResponse response = client.execute(post);
				
				if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ){
					prepraiseFlag++;
				}
				if((prepraiseFlag & 1)==0){
					
				}else{
					
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
