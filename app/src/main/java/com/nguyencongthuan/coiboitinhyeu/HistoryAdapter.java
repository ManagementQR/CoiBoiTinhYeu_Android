package com.nguyencongthuan.coiboitinhyeu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Attribute_history> historyList;

    public HistoryAdapter(Context context, int layout, List<Attribute_history> historyList) {
        this.context = context;
        this.layout = layout;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Returns the result for each line on item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);

        // mapping
        TextView txtMyName = (TextView) convertView.findViewById(R.id.history_myName);
        TextView txtYourName = (TextView) convertView.findViewById(R.id.history_yourName);
        TextView txtResult = (TextView) convertView.findViewById(R.id.history_result);

        // set value
        Attribute_history attribute_history = historyList.get(position);

        txtMyName.setText(attribute_history.getMyName());
        txtYourName.setText(attribute_history.getYourName());
        txtResult.setText(attribute_history.getResult());

        return convertView;
    }
}
