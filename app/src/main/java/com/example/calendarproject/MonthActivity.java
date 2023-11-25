package com.example.calendarproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MonthActivity extends Activity {

    Button month_year_btn;
    Button month_month_btn;
    LocalDate selectedDate;

    RecyclerView recyclerView;

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

        // 화면 설정
        setMonthView();
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

    // 날짜 생성 메소드
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
