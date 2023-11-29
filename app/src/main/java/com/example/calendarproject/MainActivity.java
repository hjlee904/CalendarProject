package com.example.calendarproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button year_year_btn;
    Button year_jan_btn;
    private LocalDate currentDate;
    int currentYear;
    RecyclerView jan_recycler_view;
    RecyclerView feb_recycler_view;
    RecyclerView mar_recycler_view;
    RecyclerView apr_recycler_view;
    RecyclerView may_recycler_view;
    RecyclerView jun_recycler_view;
    RecyclerView jul_recycler_view;
    RecyclerView aug_recycler_view;
    RecyclerView sep_recycler_view;
    RecyclerView oct_recycler_view;
    RecyclerView nov_recycler_view;
    RecyclerView dec_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 변수 초기화
        year_year_btn = findViewById(R.id.year_year_btn);
        year_jan_btn = findViewById(R.id.year_jan_btn);

        jan_recycler_view = findViewById(R.id.jan_recycler_view);
        feb_recycler_view = findViewById(R.id.feb_recycler_view);
        mar_recycler_view = findViewById(R.id.mar_recycler_view);
        apr_recycler_view = findViewById(R.id.apr_recycler_view);
        may_recycler_view = findViewById(R.id.may_recycler_view);
        jun_recycler_view = findViewById(R.id.jun_recycler_view);
        jul_recycler_view = findViewById(R.id.jul_recycler_view);
        aug_recycler_view = findViewById(R.id.aug_recycler_view);
        sep_recycler_view = findViewById(R.id.sep_recycler_view);
        oct_recycler_view = findViewById(R.id.oct_recycler_view);
        nov_recycler_view = findViewById(R.id.nov_recycler_view);
        dec_recycler_view = findViewById(R.id.dec_recycler_view);

        // 현재 날짜
        currentDate = LocalDate.now();
        currentYear = currentDate.getYear();

        HashMap<Integer, RecyclerView> map = new HashMap<Integer, RecyclerView>();
        map.put(1, jan_recycler_view);
        map.put(2, feb_recycler_view);
        map.put(3, mar_recycler_view);
        map.put(4, apr_recycler_view);
        map.put(5, may_recycler_view);
        map.put(6, jun_recycler_view);
        map.put(7, jul_recycler_view);
        map.put(8, aug_recycler_view);
        map.put(9, sep_recycler_view);
        map.put(10, oct_recycler_view);
        map.put(11, nov_recycler_view);
        map.put(12, dec_recycler_view);

        for (int i = 1; i <= 12; i++) {
            int currentMonth = i;
            LocalDate currentDate1 = LocalDate.of(currentYear, currentMonth, 1);

            setMonthView(currentDate1, map.get(i));

        }

        // 월 버튼을 클릭 시 month 레이아웃으로 화면 전환
        year_jan_btn.setOnClickListener(new View.OnClickListener() {
            int currentMonth = 1;
            LocalDate currentDate1 = LocalDate.of(currentYear, currentMonth, 1);

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MonthActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setMonthView(LocalDate date, RecyclerView recyclerView) {
        ArrayList<String> dayList = daysInMonthArray(date);

        MainCellAdapter adapter = new MainCellAdapter(dayList);

        // 레이아웃 설정 (열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        // 레이아웃 적용
        recyclerView.setLayoutManager(manager);

        // 어댑터 적용
        recyclerView.setAdapter(adapter);
    }

    // 월 달력에 날짜를 생성하는 메소드
    public ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        // 해당 월 마지막 날짜 가져오기
        int lastDay = yearMonth.lengthOfMonth();

        // 해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = date.withDayOfMonth(1);

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