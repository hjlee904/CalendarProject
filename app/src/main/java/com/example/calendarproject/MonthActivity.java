package com.example.calendarproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MonthActivity extends Activity {

    Button month_year_btn;
    Button month_month_btn;
    LocalDate selectedDate;

    // 월 달력 생성하는 리사이클러 뷰
    RecyclerView recyclerView;
    // 월 버튼 클릭 시 커스텀 다이얼로그로 뜨는 1~12월 리사이클러 뷰
    RecyclerView monthBtnListRecyclerView;
    // 월 버튼 클릭 시 뜨는 커스텀 다이얼로그
    Dialog monthListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);

        // 변수 초기화
        month_year_btn = findViewById(R.id.month_year_btn);
        month_month_btn = findViewById(R.id.month_month_btn);
        recyclerView = findViewById(R.id.recyclerView);

        // 현재 날짜
        selectedDate = LocalDate.now();

        // 월 달력 화면 설정
        setMonthView();

        // 월 버튼 클릭 시 커스텀 다이얼로그가 뜸
        month_month_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDefaultDialog();
            }
        });
    }

    // 월 버튼 클릭 시 동작 - 1~12월이 적힌 리사이클러 뷰가 커스텀 다이얼로그로 뜸
    public void showDefaultDialog() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        monthListDialog = new Dialog(this);

        display.getRealSize(size);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        LayoutInflater inflater = getLayoutInflater();
        View diaView = inflater.inflate(R.layout.month_list_dialog, null);

        lp.copyFrom(monthListDialog.getWindow().getAttributes());
        int width = size.x;
        int height = size.y;
        lp.width = width * 60 / 100;    // 사용자 화면의 60%
        lp.height = height * 40 / 100;  // 사용자 화면의 40%

        monthListDialog.setContentView(diaView);
        monthListDialog.setCanceledOnTouchOutside(true);
        monthListDialog.getWindow().setAttributes(lp);

        ArrayList<String> monthList = new ArrayList<String>();

        for (int i = 1; i <= 12; i++) {
            String string = Integer.toString(i);
            string = string + "월";
            monthList.add(string);
        }

        monthBtnListRecyclerView = (RecyclerView) diaView.findViewById(R.id.monthBtnListRecyclerView);;
        MonthBtnListAdapter adapter = new MonthBtnListAdapter(monthList);

        monthBtnListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        monthBtnListRecyclerView.setAdapter(adapter);

        monthListDialog.show();
    }

    // 날짜 타입 설정 - 년
    private String yearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년");
        return date.format(formatter);
    }
    // 날짜 타입 설정 - 월
    private String monthFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월");
        return date.format(formatter);
    }

    // 화면 설정
    private void setMonthView() {
        month_year_btn.setText(yearFromDate(selectedDate));
        month_month_btn.setText(monthFromDate(selectedDate));

        ArrayList<String> dayList = daysInMonthArray(selectedDate);

        CalendarAdapter adapter = new CalendarAdapter(dayList);

        // 레이아웃 설정 (열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        // 레이아웃 적용
        recyclerView.setLayoutManager(manager);

        // 어댑터 적용
        recyclerView.setAdapter(adapter);
    }

    // 월 달력에 날짜를 생성하는 메소드
    private  ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        // 해당 월 마지막 날짜 가져오기
        int lastDay = yearMonth.lengthOfMonth();

        // 해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = selectedDate.withDayOfMonth(1);

        // 첫 번째 날 요일 가져오기
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i < 42; i++) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add("");
            }
            else {
                dayList.add(String.valueOf(i - dayOfWeek));
            }
        }

        return dayList;
    }

}
