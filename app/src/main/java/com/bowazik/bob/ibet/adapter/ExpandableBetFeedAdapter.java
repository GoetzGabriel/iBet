package com.bowazik.bob.ibet.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.data.iBet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by bob on 17.07.17.
 */

public class ExpandableBetFeedAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<iBet>> _listDataChild;

    public ExpandableBetFeedAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<iBet>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //Get the child data
        iBet tempIbet = _listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
        final String childTitle = tempIbet.getTitle();
        final String childDescription = tempIbet.getDescription();
        final String childContender = tempIbet.getContenderName();
        final String childValue = Integer.toString(tempIbet.getValue());

        //Inflate the child layout
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.bet_feed_child_item, null);
        }

        //Get the textview references
        TextView txtTitleView = (TextView) convertView
                .findViewById(R.id.bet_item_title);
        TextView txtDescriptionView = (TextView) convertView
                .findViewById(R.id.bet_item_description);
        TextView txtContenderView = (TextView) convertView
                .findViewById(R.id.bet_item_contender);
        TextView txtStakeView = (TextView) convertView
                .findViewById(R.id.bet_item_stake);

        //Set the textview values
        txtTitleView.setText(childTitle);
        txtDescriptionView.setText(childDescription);
        txtContenderView.setText(childContender);
        txtStakeView.setText(childValue);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //Get the group elements data
        String headerTitle = (String) getGroup(groupPosition);
        int groupElementCount = _listDataChild.get(_listDataHeader.get(groupPosition)).size();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.bet_feed_group, null);
        }
        //Get the group textview references
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        TextView groupElementCounter = (TextView) convertView
                .findViewById(R.id.bet_feed_group_counter);
        //Set the group data
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        groupElementCounter.setTypeface(null, Typeface.BOLD);
        groupElementCounter.setText(Integer.toString(groupElementCount));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
