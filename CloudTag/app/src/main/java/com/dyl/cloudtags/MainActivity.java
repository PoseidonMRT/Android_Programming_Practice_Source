package com.dyl.cloudtags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private KeywordsFlow keywordsFlow;
	private String[] keywords;
	public static final String SEARCH_HISTORY = "search_history";
	private ArrayList<SearchDataPojo> searchItem;
	private String longhistory;
	private SharedPreferences sp;
	private ArrayList<String> history;
	private EditText world_shopping_search_input;
	private TextView world_city_refresh, clear_history;
	private ImageView toSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initSearchHistory();
		refreshTags();
	}

	private void initView() {
		world_shopping_search_input = (EditText) findViewById(R.id.world_shopping_search_input);
		keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);

		world_city_refresh = (TextView) findViewById(R.id.world_city_refresh);
		world_city_refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				refreshTags();
			}
		});

		clear_history = (TextView) findViewById(R.id.clear_history);
		clear_history.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				clearSearchHistory();
			}
		});

		toSearch = (ImageView) findViewById(R.id.toSearch);
		toSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				saveSearchHistory();
				refreshTags();
			}
		});
	}

	private void refreshTags() {
		initSearchHistory();
		keywordsFlow.setDuration(800l);
		keywordsFlow.setOnItemClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String keyword = ((TextView) v).getText().toString();// ��õ���ı�ǩ
				world_shopping_search_input.setText(keyword);
			}
		});
		// ���
		feedKeywordsFlow(keywordsFlow, keywords);
		keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
	}

	private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
		Random random = new Random();
		for (int i = 0; i < KeywordsFlow.MAX; i++) {
			int ran = random.nextInt(arr.length);
			String tmp = arr[ran];
			keywordsFlow.feedKeyword(tmp);
		}
	}

	/**
	 * ��ȡ��ʷ������¼
	 */
	private void initSearchHistory() {
		sp = getSharedPreferences(MainActivity.SEARCH_HISTORY, 0);
		longhistory = sp.getString(MainActivity.SEARCH_HISTORY, "");
		if (!longhistory.equals("")) {
			keywords = longhistory.split(",");
			searchItem = new ArrayList<SearchDataPojo>();
			for (int i = 0; i < keywords.length; i++) {
				searchItem.add(new SearchDataPojo().setContent(keywords[i]));
			}
		} else {// ���SharedPreferencesû��ֵ�û�������ʾĬ�ϵ�����
			keywords = new String[] { "��ζϺ", "����ţ��", "������", "�湦��", "�ձ�����",
					"��������", "��ɿͷ�", "���Ȱ�˹", "��ζϺ", "����ţ��", "������", "�湦��" };
		}
	}

	/*
	 * ����������¼
	 */
	private void saveSearchHistory() {
		String text = world_shopping_search_input.getText().toString().trim();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		if (!text.equals("") && text != null) {
			if (text.length() < 1) {
				return;
			}
			sp = getSharedPreferences(SEARCH_HISTORY, 0);
			String longhistory = sp.getString(SEARCH_HISTORY, "");
			String[] tmpHistory = longhistory.split(",");
			history = new ArrayList<String>(Arrays.asList(tmpHistory));
			if (history.size() > 0) {
				int i;
				for (i = 0; i < history.size(); i++) {
					if (text.equals(history.get(i))) {
						history.remove(i);
						break;
					}
				}
				history.add(0, text);
			}
			if (history.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < history.size(); i++) {
					sb.append(history.get(i) + ",");
				}
				sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
			} else {
				sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
			}
			clear_history.setVisibility(View.VISIBLE);
		}
	}

	// �����ʷ����
	private void clearSearchHistory() {
		searchItem.removeAll(searchItem);
		sp.edit().clear().commit();
		Toast.makeText(this, "�����ʷ��¼", Toast.LENGTH_LONG).show();
	}
}
