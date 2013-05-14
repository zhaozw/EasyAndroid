package test.activity;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.easyandroid.R;
import test.MyBaseActivity;
import test.activity.animation.AnimationListActivity;
import test.activity.custom.CustomListActivity;
import test.activity.graphics.GraphicsListActivity;
import test.activity.media.MediaListActivity;
import test.activity.other.OtherListActivity;
import test.activity.views.ViewListActivity;
import test.adapter.ActivityAdapter;
import test.adapter.ActivityAdapter.ActivityItem;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 主页
 */
public class MainActivity extends MyBaseActivity{
	private List<ActivityItem> activityItemList;
	private ListView listView;
	
	@Override
	public void onInitLayout(Bundle savedInstanceState) {
		setContentView(R.layout.activity_simple_list_rebound);
		listView = (ListView) findViewById(android.R.id.list);
	}

	@Override
	public void onInitListener(Bundle savedInstanceState) {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				startActivity(activityItemList.get(arg2 - listView.getHeaderViewsCount()).getAction());
			}
		});
	}

	@Override
	public void onInitData(Bundle savedInstanceState) {
		activityItemList = new ArrayList<ActivityItem>();
		activityItemList.add(new ActivityItem(getString(R.string.animationList_title), AnimationListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.graphicsList_title), GraphicsListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.mediaList_title), MediaListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.viewsList_title), ViewListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.customList_title), CustomListActivity.class));
		activityItemList.add(new ActivityItem(getString(R.string.otherList_title), OtherListActivity.class));
		
		listView.setAdapter(new ActivityAdapter(getBaseContext(), activityItemList));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.comm, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected boolean isEnableBackHome() {
		return false;
	}

	@Override
	public boolean isEnableDoubleClickBackButtonExitApplication() {
		return true;
	}
}