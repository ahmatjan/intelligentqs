package com.example.app.hnust_qa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.hnust_qa.Bean.QuestionAllInfoBean;
import com.example.app.hnust_qa.Bean.QuestionBean;
import com.example.app.hnust_qa.Tools.NetTool;
import com.example.hnust.R;
import com.slidinglayer.SlidingLayer;


public class MainActivity extends Activity {

	public static final int QUESTION_LIST_SUCCESS = 1;
	
	private SharedPreferences sp;

	private TextView title_name;
	private TextView title_center;
	private TextView title_right;

	private SlidingLayer  slidingLayer;
	private ListView slidingListview;
	private List<String> slidingList;
	private mSlidingAdapter slidingAdapter;
	// 退出组件
	private Timer timer;
	private boolean isExit = false;
	private TimerTask timerTask = null;
	// 问题列表组件
	private String url = NetTool.web + "disscussQuestionMobile?currentPage=";
	private static int currentPage = 0;
	private List<QuestionAllInfoBean> questionList;
	private String responseStatus;
	private PullToRefreshLayout mPullToRefreshLayout;
	private ListView questionListView;
	private LinearLayout question_linearLayout;
	private QuestionAdapter mquestionAdapter;

	private Intent mainIntent;
	//判断是否到达最后一条数据，自动加载
	private boolean lodingFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() 
				.penaltyLog().build());
		// 严苛模式
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		NetTool.userId = sp.getString("userId", "");
		
		// 设置标题栏
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.title_layout);
		title_name = (TextView) findViewById(R.id.title_name);
		title_center = (TextView) findViewById(R.id.title_middle);
		title_right = (TextView) findViewById(R.id.title_right);
		
		title_name.setText("≡首页");
		title_center.setText("");
		title_right.setText("");

		mainIntent = getIntent();
		currentPage = 0;
		timer = new Timer();

		questionList = new LinkedList<QuestionAllInfoBean>();

		slidingList = new ArrayList<String>();

		slidingList.add("首页");
		slidingList.add("提问");

		
		questionListView = (ListView) findViewById(R.id.question_listViews);
		questionListView.setOnItemClickListener(new questionItemListener());
		question_linearLayout = (LinearLayout) findViewById(R.id.question_linearLayout);
		mPullToRefreshLayout = new PullToRefreshLayout(MainActivity.this);
		pullToRefresh();
		
		slidingMenu();
		
		lodingMore();

		new Thread(runQuestionList).start();
		
		
		
		title_name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!slidingLayer.isOpened()){
					slidingLayer.openLayer(true);
					title_name.setText("≡问答");
				}else{
					slidingLayer.closeLayer(true);
					title_name.setText("≡首页");
				}
				
			}
		});
		

	}
	
	//下拉刷新
	private void pullToRefresh(){
		
	    ActionBarPullToRefresh.from(MainActivity.this)
	    .insertLayoutInto(question_linearLayout)
        .theseChildrenArePullable(questionListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
        .options(Options.create()
                .refreshingText("正在加载...")
                .pullText("下拉刷新")
                .releaseText("放开我>_<")
                .titleTextColor(R.color.topbartext)
                .headerBackgroundColor(R.color.topbar)
                .progressBarStyle(Options.PROGRESS_BAR_STYLE_INSIDE) // or Options.PROGRESS_BAR_STYLE_OUTSIDE
                .build())
        .listener(new OnRefreshListener() {

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
	                    
	        			currentPage = 0;
	        			questionList.removeAll(questionList);
	        			getQuestionList();
	        			mquestionAdapter.notifyDataSetChanged();
	                    mPullToRefreshLayout.setRefreshComplete();

	                }
	            }.execute();
			} 
         })
        .setup(mPullToRefreshLayout);
	    
	}
	
	//底端自动加载
	private void lodingMore(){
		
		View footLayout;
		
		footLayout = this.getLayoutInflater().inflate(R.layout.footview, null);
		
		questionListView.addFooterView(footLayout);
		
		questionListView.setOnScrollListener(new OnScrollListener() {
			
			int lastItemIndex;//当前ListView中最后一个Item的索引  
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE  && lastItemIndex == mquestionAdapter.getCount() - 1) {  
                    //加载数据
					currentPage = currentPage + 1;
					getQuestionList();
					
					mquestionAdapter.notifyDataSetChanged();
					mPullToRefreshLayout.setRefreshComplete();
                }  
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if( lodingFlag = true){
					lastItemIndex = firstVisibleItem + visibleItemCount - 1 -1;  
				}else{
					Toast.makeText(MainActivity.this, "已经到了最后一条了哦", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
	
	// 设置抽屉菜单
	public void slidingMenu() {

	     slidingLayer = (SlidingLayer) findViewById(R.id.slidingLayer1);
	
	     slidingLayer.setShadowWidthRes(R.dimen.shadow_width);
	     slidingLayer.setOffsetWidth(10);
	     slidingLayer.setShadowDrawable(R.drawable.sidebar_shadow);
	     slidingLayer.setStickTo(SlidingLayer.STICK_TO_LEFT);
	     slidingLayer.setCloseOnTapEnabled(true);
	     
	    View slidingView = getLayoutInflater().inflate(R.layout.sliding_menu, null);

		slidingAdapter = new mSlidingAdapter();
		slidingListview = (ListView) slidingView.findViewById(R.id.slidingListView);
		TextView tv_username = (TextView) slidingView.findViewById(R.id.tv_username);
		tv_username.setText(NetTool.username);
		slidingListview.setAdapter(slidingAdapter);
		slidingListview.setOnItemClickListener(new slidingItemClickListener());
		
		slidingLayer.addView(slidingView);

	}
	
	public void click(View view){
		slidingLayer.openLayer(true);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {

				mquestionAdapter = new QuestionAdapter();
				questionListView.setAdapter(mquestionAdapter);
				
			} else {
				Toast.makeText(MainActivity.this, "没有取到信息(>_<)",Toast.LENGTH_SHORT).show();
			}
		}
	};

	Runnable runQuestionList = new Runnable() {
		@Override
		public void run() {

			getQuestionList();
			Message msg = new Message();
			msg.what = QUESTION_LIST_SUCCESS;
			handler.sendMessage(msg);
			
		}
	};


	public void getQuestionList() {
		try {
			// get方式连接
			HttpGet httpGet = new HttpGet(url + currentPage);
			// 相当于一个小型浏览器
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 得到的数据
			HttpResponse httpResponse = httpClient.execute(httpGet);
			// 判断是否连接成功
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 得到连接实体（就是把代码从网上拿下来，当然需要一个东西装）
				HttpEntity httpEntity = httpResponse.getEntity();
				// json方式传递，当然要转成json了
				String json = EntityUtils.toString(httpEntity, "utf-8");
				// new一个json对象，为什么是array不是object后面会说
				JSONArray jsonArray = new JSONArray(json);
				JSONArray rows = jsonArray.getJSONObject(0).getJSONArray("rows");
				for (int i = 0; i < rows.length(); i++) {
					// 对照json来看才能看的懂。。只要明白前几步，后面就好懂了
					JSONObject question = rows.getJSONObject(i).getJSONObject(
							"questionBean");
					JSONObject questioninfo = rows.getJSONObject(i);

					Log.e("question", question + "");
					Log.e("count:", questioninfo.getInt("countOfAnswers") + "");
					QuestionAllInfoBean questionAllInfoBean = new QuestionAllInfoBean();

					questionAllInfoBean.setBestAnswer(questioninfo
							.getString("bestAnswer"));
					questionAllInfoBean.setCountOfAnswers(questioninfo
							.getInt("countOfAnswers"));
					questionAllInfoBean.setQuestionUserName(questioninfo
							.getString("questionUserName"));
					questionAllInfoBean.setVpOfQuestion(Integer
							.parseInt(questioninfo.getString("vpOfQuestion")));
					QuestionBean questionBean = new QuestionBean();
					questionBean.setQuestion_description(question
							.getString("question_description"));
					questionBean.setQuestion_id(Integer.parseInt(question
							.getString("question_id")));
					questionBean.setQuestion_mark(Integer.parseInt(question
							.getString("question_mark")));
					questionBean.setQuestion_tags(question
							.getString("question_tags"));
					questionBean.setQuestion_time(question
							.getString("question_time"));
					questionBean.setQuestion_title(question
							.getString("question_title"));
					questionBean.setQuestion_user_id(Integer.parseInt(question
							.getString("question_user_id")));

					questionAllInfoBean.setQuestionBean(questionBean);

					questionList.add(questionAllInfoBean);
					
					//判断下拉刷新到达最后一个
					if( question.getString("question_id").equals("1")){
						lodingFlag = false;
					}
				}

			} else {
				responseStatus = "服务器连接异常，请重试";
				Toast.makeText(MainActivity.this, responseStatus,Toast.LENGTH_SHORT).show();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void onBackPressed() {
		if (isExit) {
			finish();
		} else {
			isExit = true;
			Toast.makeText(MainActivity.this, "再按一次退出问答", Toast.LENGTH_SHORT)
					.show();
			timerTask = new TimerTask() {
				@Override
				public void run() {
					isExit = false;
				}
			};
			timer.schedule(timerTask, 2000);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		menu.add(0, 1, 1, "登出");
		menu.add(0, 2, 2, "关于");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == 1) {
			logOut();
			return true;
		} else if (id == 2) {
			Toast.makeText(MainActivity.this, "关于正在建设中", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}
	//登出
	private void logOut(){
		Editor editor = sp.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.commit();
        finish();
        Intent outIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(outIntent);
        
	}

	// 问题listview适配器
	class QuestionAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return questionList.size();
		}

		@Override
		public Object getItem(int i) {
			return questionList.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View convertView, ViewGroup viewGroup) {
			View view;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.qustion_list, null);
			} else {
				view = convertView;
			}

			TextView title = (TextView) view.findViewById(R.id.question_title);
			ImageView portrait = (ImageView) view.findViewById(R.id.question_portrait);
			TextView count = (TextView) view.findViewById(R.id.answer_count);
			TextView summary = (TextView) view.findViewById(R.id.answer_summary);

			title.setText(questionList.get(i).getQuestionBean().getQuestion_title());
			portrait.setImageResource(R.drawable.git);
			count.setText(questionList.get(i).getCountOfAnswers() + "");
			String summaryString = questionList.get(i).getQuestionBean().getQuestion_description();
			int summaryLength = summaryString.length();
			if (summaryLength > 90) {
				summaryString = summaryString.substring(0, 90) + "..";
			}
			summary.setText(summaryString);

			return view;
		}
	}

	// 抽屉菜单适配器
	class mSlidingAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return slidingList.size();

		}

		@Override
		public Object getItem(int i) {
			return slidingList.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View convertView, ViewGroup viewGroup) {
			View view;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.sliding_listview,null);
			} else {
				view = convertView;
			}
			TextView title = (TextView) view.findViewById(R.id.shouye);

			title.setText(slidingList.get(i));

			return view;
		}
	}

	// 抽屉菜单动作监听
	class slidingItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i,
				long l) {
			// int position = i - 1;
			switch (i) {
			case 0:
				slidingLayer.closeLayer(true);
				title_name.setText("≡首页");
				break;
			case 1:
				Intent intent = new Intent(MainActivity.this,AskQestionActivity.class);
				startActivity(intent);
				break;
			
			}

		}
	}

	// 问题菜单监听，打开单个listview中的数据
	class questionItemListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
			int position = i ;
			Intent intent = new Intent(MainActivity.this, QuestionDetail2.class);
			// 传递一些必要的数据
			intent.putExtra("question_id", questionList.get(position).getQuestionBean().getQuestion_id()+ "");
			intent.putExtra("question_title", questionList.get(position).getQuestionBean().getQuestion_title());
			intent.putExtra("question_summary", questionList.get(position).getQuestionBean().getQuestion_description());
			intent.putExtra("question_username", questionList.get(position).getQuestionUserName());

			startActivity(intent);

		}
	}

}
