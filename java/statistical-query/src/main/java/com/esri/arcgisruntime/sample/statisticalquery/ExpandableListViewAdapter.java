package com.esri.arcgisruntime.sample.statisticalquery;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

  private Context context;
  private List<String> mGroupList;
  private TreeMap<String, List<String>> mStatList;

  public ExpandableListViewAdapter(Context context, TreeMap<String, List<String>> statList) {
    this.context = context;
    this.mGroupList = new ArrayList<>(statList.keySet());
    this.mStatList = statList;
  }

  @Override
  public Object getChild(int groupListPosition, int statListPosition) {
    return this.mStatList.get(this.mGroupList.get(groupListPosition)).get(statListPosition);
  }

  @Override
  public long getChildId(int groupPosition, int statListPosition) {
    return statListPosition;
  }

  @Override
  public View getChildView(int groupListPosition, final int statListPosition, boolean isLastChild, View convertView,
      ViewGroup parent) {
    final String expandedListText = (String) getChild(groupListPosition, statListPosition);
    if (convertView == null) {
      LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
    }
    TextView expandedListTextView = convertView.findViewById(android.R.id.text1);
    expandedListTextView.setText(expandedListText);
    return convertView;
  }

  @Override
  public int getChildrenCount(int listPosition) {
    return this.mStatList.get(this.mGroupList.get(listPosition)).size();
  }

  @Override
  public Object getGroup(int listPosition) {
    return this.mGroupList.get(listPosition);
  }

  @Override
  public int getGroupCount() {
    return this.mGroupList.size();
  }

  @Override
  public long getGroupId(int listPosition) {
    return listPosition;
  }

  @Override
  public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    String listTitle = (String) getGroup(listPosition);
    if (convertView == null) {
      LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
    }
    TextView listTitleTextView = convertView.findViewById(android.R.id.text1);
    listTitleTextView.setTypeface(null, Typeface.BOLD);
    listTitleTextView.setText(listTitle);
    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int listPosition, int expandedListPosition) {
    return true;
  }
}