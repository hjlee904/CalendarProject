package com.example.calendarproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainCellAdapter extends RecyclerView.Adapter<MainCellAdapter.MainCellViewHolder> {

    ArrayList<String> dayList;

    public MainCellAdapter(ArrayList<String> dayList) {
        this.dayList = dayList;
    }

    public int getItemCount() {
        return dayList.size();
    }

    @NonNull
    @Override
    // 화면을 연결 해주는 메소드
    public MainCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.main_cell, parent, false);

        return new MainCellViewHolder(view);
    }

    // 데이터를 연결 해주는 메소드
    public void onBindViewHolder(MainCellViewHolder holder, int position) {

        // 날짜 적용
        holder.main_cell_day.setText(dayList.get(position));
    }

    class MainCellViewHolder extends  RecyclerView.ViewHolder {

        // 초기화
        TextView main_cell_day;

        public  MainCellViewHolder(View itemView) {
            super(itemView);

            main_cell_day = itemView.findViewById(R.id.main_cell_day);
        }
    }
}
