//mypage 구현 class
package com.example.highschoolalltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//mypage탭. 정보수정과 로그아웃 회원탈퇴를 할수 있는 곳
public class Mypage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //activity_mypage애서 txtview와 button을 가져온다.
        TextView txt_showID = findViewById(R.id.TextView_Mypage_ShowID);
        Button btn_logout = findViewById(R.id.Button_Mypage_Logout);
        Button btn_withdraw = findViewById(R.id.Button_Mypage_Withdraw);
        Button btn_change = findViewById(R.id.Button_Mypage_Change);
        //use_user 클래스에서 저장된 user의 값을 불러온다.
        String userID = new use_user().getUserID();
        String userName = new use_user().getUserName();
        txt_showID.setText(userName+"("+userID+")");
        //login페이지 합칠경우 login페이지 이동(현재는 급식페이지로 대체)
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Cafeteria.class);
                startActivity(intent); //액티비티 이동
            }
        });
        //회원탈퇴 페이지로 이동
       btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, withdraw.class);
                startActivity(intent); //액티비티 이동
            }
        });
       //정보수정 페이지로 이동
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, ChangeInformation.class);
                startActivity(intent); //액티비티 이동
            }
        });
        //DB에 저장된 글 listview는 차후 개발예정
    }
}
