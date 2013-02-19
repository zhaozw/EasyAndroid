package test.activity;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.androidlibrary.R;
import me.xiaopan.androidlibrary.widget.ReboundListView;
import test.MyBaseActivity;
import test.adapter.ActivityAdapter;
import test.adapter.ActivityAdapter.ActivityItem;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 自定义组件或特效界面
 * @author xiaopan
 *
 */
public class CustomWidgetActivity extends MyBaseActivity{

	private List<ActivityItem> activityItemList;
	private ReboundListView reboundListView;
	
	@Override
	public void onInitLayout(Bundle savedInstanceState) {
		setContentView(R.layout.comm_simple_rebound_list);
		reboundListView = (ReboundListView) findViewById(android.R.id.list);
	}

	@Override
	public void onInitListener(Bundle savedInstanceState) {
		reboundListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				startActivity(activityItemList.get(arg2 - reboundListView.getHeaderViewsCount()).getAction());
			}
		});
	}

	@Override
	public void onInitData(Bundle savedInstanceState) {
		activityItemList = new ArrayList<ActivityItem>();
		activityItemList.add(new ActivityItem(getString(R.string.pullDownRefreshList_title), PullDownRefreshListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.clickLoadMoreList_title), ClickLoadMoreListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.slideTab_title), SlideTabActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.customCamera_title), CustomCameraActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.picturePlayer_title), PicturePlayerActivity.class));
		
		reboundListView.setAdapter(new ActivityAdapter(getBaseContext(), activityItemList));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.comm, menu);
		return super.onCreateOptionsMenu(menu);
	}
}