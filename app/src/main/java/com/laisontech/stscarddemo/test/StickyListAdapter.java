package com.laisontech.stscarddemo.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;

public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

	private ArrayList<String> list;
	private Context mContext;
	public void init(Context context,ArrayList<String> list) {
		this.list = list;
		this.mContext = context;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String s = list.get(position);
		TextView itemView = new TextView(mContext);
		itemView.setText(s);
		itemView.setTextSize(20);
		return itemView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		String s = list.get(position);
		TextView headView = new TextView(mContext);
		headView.setText(s);
		headView.setTextSize(24);
		headView.setTextColor(new Color().RED);
		return headView;
	}

	@Override
	public long getHeaderId(int position) {
		return position/10;
	}
}
