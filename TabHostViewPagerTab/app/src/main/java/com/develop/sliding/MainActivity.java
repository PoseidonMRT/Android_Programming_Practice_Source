package com.develop.sliding;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

public class MainActivity extends Activity {
	private TabHost tabHost; 						// 选项卡
	private List<View> listViews; 					// 页面集合
	private Context context = null; 				// 上下文,当前对象
	private LocalActivityManager manager = null; 	// 布局管理器
	private ViewPager pager = null; 				// 滑动组件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
		pager = (ViewPager) findViewById(R.id.viewpager);
		listViews = new ArrayList<View>();
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);

		tabHost = (TabHost) findViewById(R.id.tabhost);

		Intent intent = new Intent(context, Page1Activity.class);
		listViews.add(getView("A", intent));

		intent = new Intent(context, Page2Activity.class);
		listViews.add(getView("B", intent));

		intent = new Intent(context, Page3Activity.class);
		listViews.add(getView("C", intent));

		tabHost.setup();
		tabHost.setup(manager);

		Intent tmpIntent = new Intent(context, EmptyActivity.class);	//使用一个空页面,即点击选项卡的时候,页面不会显示任何东西
		tabHost.addTab(tabHost.newTabSpec("A").setIndicator("选项一").setContent(tmpIntent));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator("选项二").setContent(tmpIntent));
		tabHost.addTab(tabHost.newTabSpec("C").setIndicator("选项三").setContent(tmpIntent));

		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				updateTabStyle(tabHost);
				if ("A".equals(tabId)) {
					pager.setCurrentItem(0);
				} else if ("B".equals(tabId)) {
					pager.setCurrentItem(1);
				} else if ("C".equals(tabId)) {
					pager.setCurrentItem(2);
				}
			}
		});

		updateTabStyle(tabHost);
	}

	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	/**
	 * 更新菜单选项的背景颜色和背景图片
	 * 
	 */
	public void updateTabStyle(TabHost tabHost) {
		TabWidget tabWidget = tabHost.getTabWidget(); 			// 获取TabHost的头部		
		
		for (int i = 0; i < tabWidget.getChildCount(); i++) { 	// 循环每个tabView
			View view = tabWidget.getChildAt(i); 				// 获取当前tabView项
			//每个tabHost 是一个View对象, 它是由一个ImageView对象和一个TextView对象组成
			//此处可以循环出所有选项,并可以对所有选项设置不同的图标和文字
			
			//view.setBackgroundResource(R.drawable.tabhost); 	// 设置背景以后重新设置背景图片

			
			
			//--------------------  图标变化示列   -------------------------------
			ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);	//获取当前选择的图标
			switch (i) {
				case 0:{
					imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_slideshow)); 	//改变我们需要的图标
					break;
				}				
				case 1:{
					imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_call)); 		
					break;
				}				
				case 2:{
					imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_compass)); 	
					break;
				}			
										
			}
			
						
			
			/*
			--------------------  文字变化示列   -------------------------------
			TextView textView = (TextView) view.findViewById(android.R.id.title); 	// 获取textView控件
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();

			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0); 					// 取消文字底边对齐
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); 	// 设置TextView的文字居中

			textView.getPaint().setFakeBoldText(true); 								// 字体加粗
			if (tabHost.getCurrentTab() == i) { 									// 如果当前的标签为选中,则将字体放大,并改变其颜色
				textView.setTextSize(16);
				textView.setTextColor(Color.parseColor("#FFFFFF")); 				// 选中时的字体颜色
			} else {
				textView.setTextSize(14);
				textView.setTextColor(Color.parseColor("#FFFFFF"));
			}
			*/
		}
	}

	private class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(View view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
}
