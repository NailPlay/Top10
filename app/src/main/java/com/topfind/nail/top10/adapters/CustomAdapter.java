package com.topfind.nail.top10.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.topfind.nail.top10.R;
import com.topfind.nail.top10.entity.Item;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> implements View.OnClickListener {

    private ArrayList<Item> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvLink;
        TextView tvSnippet;
    }

    public CustomAdapter(ArrayList<Item> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Item dataModel=(Item) object;
        switch (v.getId())
        {
            case R.id.snippetText:
                Toast.makeText(getContext(),dataModel.getLink() + " - " + dataModel.getSnippet() +
                        " - " + dataModel.getTitle(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.titleText);
            viewHolder.tvSnippet = (TextView) convertView.findViewById(R.id.snippetText);
            viewHolder.tvLink = (TextView) convertView.findViewById(R.id.linkText);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;
        viewHolder.tvLink.setText(dataModel.getLink());
        viewHolder.tvTitle.setText(dataModel.getTitle());
        viewHolder.tvSnippet.setText(dataModel.getSnippet());
        return convertView;
    }
}