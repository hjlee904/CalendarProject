package com.example.calendarproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonthBtnListAdapter extends RecyclerView.Adapter<MonthBtnListAdapter.MonthBtnListHolder> {
    ArrayList<String> monthList;

    // 생성자를 통해 데이터 리스트 context를 받음
    public MonthBtnListAdapter(ArrayList<String> monthList) { this.monthList = monthList; }

    public int getItemCount() { return monthList.size(); }

    @NonNull
    @Override
    public MonthBtnListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.month_list_item, parent, false);

        return new MonthBtnListHolder(view);
    }

    public void onBindViewHolder(MonthBtnListHolder holder, int position) {
        holder.textMonth.setText(monthList.get(position));
    }

    class MonthBtnListHolder extends RecyclerView.ViewHolder {
        TextView textMonth;

        public MonthBtnListHolder(View itemview) {
            super(itemview);

            textMonth = itemview.findViewById(R.id.textMonth);
        }
    }

}
