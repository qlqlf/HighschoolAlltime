package com.example.highschoolalltime;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Register_Cafe extends StringRequest {
    //서버 URL설정 (PHP파일연동)
    final static private  String URL = "http://highschool.dothome.co.kr/Register_Cafe.php";
    //string배열로 저장
    private Map<String, String> map;

    public Register_Cafe(int WhatMonth, int WhatDate, String Menu, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        //map에 값넣기
        map = new HashMap<>();
        map.put("WhatMonth",WhatMonth+"");
        map.put("WhatDate",WhatDate+"");
        map.put("Menu",Menu);
    }
    //map리턴
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
