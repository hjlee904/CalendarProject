package com.example.calendarproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    ArrayList<String> dayList;

    public CalendarAdapter(ArrayList<String> dayList) {
        this.dayList = dayList;
    }

    @NonNull
    @Override
    // 화면을 연결 해주는 메소드
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.month_cell, parent, false);

        return new CalendarViewHolder(view);
    }

    // 데이터를 연결 해주는 메소드
    public void onBindViewHolder(CalendarViewHolder holder, int position) {

        // 날짜 적용
        holder.month_cell_day.setText(dayList.get(position));
    }

    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends  RecyclerView.ViewHolder{

        // 초기화
        TextView month_cell_day;

        public  CalendarViewHolder(View itemView) {
            super(itemView);

            month_cell_day = itemView.findViewById(R.id.month_cell_day);
        }
    }
}
