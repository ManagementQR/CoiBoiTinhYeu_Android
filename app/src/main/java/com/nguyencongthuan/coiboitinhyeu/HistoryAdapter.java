package com.nguyencongthuan.coiboitinhyeu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyencongthuan.coiboitinhyeu.Model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<History> historyList;

    public HistoryAdapter(Context context, int layout, List<History> historyList) {
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
        History history = historyList.get(position);

        txtMyName.setText(history.getFullname());
        txtYourName.setText(history.getInfor());
        txtResult.setText(history.getResult());




        return convertView;
    }
}
