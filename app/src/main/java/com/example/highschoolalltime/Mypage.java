package com.example.highschoolalltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//mypage탭. 정보수정과 로그아웃 회원탈퇴를 할수 있는 곳
public class Mypage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Button btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Login.class);
                startActivity(intent); //액티비티 이동
            }
        });

        Button btn_withdraw = findViewById(R.id.btn_withdraw);

        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, withdraw.class);
                startActivity(intent); //액티비티 이동
            }
        });

        Button btn_change = findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, ChangeInformation.class);
                startActivity(intent); //액티비티 이동
            }
        });
    }
}
