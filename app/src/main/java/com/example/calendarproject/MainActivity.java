package com.example.calendarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    Button year_year_btn;
    Button year_jan_btn;
    LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 변수 초기화
        year_year_btn = findViewById(R.id.year_year_btn);
        year_jan_btn = findViewById(R.id.year_jan_btn);

        // 현재 날짜
        selectedDate = LocalDate.now();

        // 월 버튼을 클릭하면 month 레이아웃으로 화면 전환
        year_jan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MonthActivity.class);
                startActivity(intent);
            }
        });

    }

}