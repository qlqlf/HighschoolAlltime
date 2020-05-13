package com.example.highschoolalltime;

import java.util.ArrayList;
import java.util.Calendar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.highschoolalltime.adapter.cafeteria_Adapter;
import com.example.highschoolalltime.domain.DayInfo;
//import com.example.highschoolalltime.domain.ExToday;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

//각 날짜에 대한 클릭이벤트와 버튼의 클릭이벤트를 구현하기위한 implements
public class Cafeteria extends Activity implements OnItemClickListener, OnClickListener{
    public static int SUNDAY = 1;
    public static int MONDAY = 2;
    public static int TUESDAY = 3;
    public static int WEDNSESDAY = 4;
    public static int THURSDAY = 5;
    public static int FRIDAY = 6;
    public static int SATURDAY = 7;
    //cafeteria xml의 그리드뷰와 textview를 가져온다.
    private GridView mGvCalendar;
    private TextView mTvCalendarTitle;
    //adapter와 날짜리스트를 가져온다.
    private ArrayList<DayInfo> mDayList;
    private cafeteria_Adapter mCalendarAdapter;

    Calendar mThisMonthCalendar;
    Calendar mLastMonthCalendar;
    Calendar mNextMonthCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);
        //버튼을 가져온다.
        Button bLastMonth = (Button)findViewById(R.id.gv_calendar_activity_b_last);
        Button bNextMonth = (Button)findViewById(R.id.gv_calendar_activity_b_next);
        //gridview와 textview를 가져온다.
        mGvCalendar = (GridView) findViewById(R.id.gv_calendar_activity_gv_calendar);
        mTvCalendarTitle = (TextView) findViewById(R.id.cafe_month);
        //날짜클릭이벤트와 버튼클릭이벤트를 위한 설정
        mGvCalendar.setOnItemClickListener(this);
        bLastMonth.setOnClickListener(this);
        bNextMonth.setOnClickListener(this);
        //배열생성
        mDayList = new ArrayList<DayInfo>();
    }
    //background에서 다시 돌아왔을때 이번달 달력을 띄움
    @Override
    protected void onResume() {
        super.onResume();
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
    }
    //달력 생성
    private void getCalendar(Calendar calendar) {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        mDayList.clear();
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //지난달은 -1
        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH) + "");

        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //이번달은 +1
        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH) + "");
        //한 주가 지날 경우 +7
        if (dayOfMonth == SUNDAY) {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth - 1) - 1;
        //textview설정
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");

        DayInfo day;

        Log.e("DayOfMonth", dayOfMonth + "");
        //날짜구현
        for (int i = 0; i < dayOfMonth - 1; i++) {
            int date = lastMonthStartDay + i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);

            mDayList.add(day);
        }
        for (int i = 1; i <= thisMonthLastDay; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);

            mDayList.add(day);
        }
        for (int i = 1; i < 42 - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);
        }
        //cafeteriaAdapter애 넣어주기
        initCafeteriaAdapter();
    }
    //지난달구현
    private Calendar getLastMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }
    //다음달구현
    private Calendar getNextMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }
    //어뎁터에 넣어주기
    private void initCafeteriaAdapter() {
        mCalendarAdapter = new cafeteria_Adapter(this, R.layout.cafeteria_day, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
    //날짜 클릭 이벤트 구현
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
        //다이얼로그 창 구현
        final int WhatDate = position;
        final int WhatMonth = mThisMonthCalendar.get(Calendar.MONTH)+1;
        final AlertDialog.Builder calendar_cafeteria = new AlertDialog.Builder(Cafeteria.this);
        calendar_cafeteria.setIcon(R.mipmap.ic_launcher);
        calendar_cafeteria.setTitle("오늘의 급식");
        //DB와 연동하여 저장된 Text보여주기(실패)
        Response.Listener<String> reponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success) {
                        calendar_cafeteria.setMessage(jsonObject.getString("Menu"));
                    }else {
                        Toast.makeText(getApplicationContext(), "급식이없습니다.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //volley를 사용해 서버로 요청
        Cafeteria_Request cafeteria_request = new Cafeteria_Request(WhatMonth, WhatDate, reponseListener);
        RequestQueue queue = Volley.newRequestQueue(Cafeteria.this);
        queue.add(cafeteria_request);
        //급식추가하는 edittext구현
        final EditText edit_cafeteria = new EditText(Cafeteria.this);
        calendar_cafeteria.setView(edit_cafeteria);
        //추가버튼 눌렀을때 DB에 값 저장
        calendar_cafeteria.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                //Menu에 String값으로 EditText저장
                String Menu = edit_cafeteria.getText().toString();
                //DB에 값 넣어주기
                Response.Listener responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                Toast.makeText(getApplicationContext(),"급식을 추가하였습니다.",
                                        Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getApplicationContext(),"실패했습니다.",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //Volley를 이용해 서버로 요청
                Register_Cafe register_cafe = new Register_Cafe(WhatMonth, WhatDate, Menu, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Cafeteria.this);
                queue.add(register_cafe);
            }
        });
        //취소했을때
        calendar_cafeteria.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        calendar_cafeteria.show();
    }
    //버튼클릭이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //지난달 버튼구현
            case R.id.gv_calendar_activity_b_last:
                mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
                //다음달 버튼구현
            case R.id.gv_calendar_activity_b_next:
                mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
        }
    }
}