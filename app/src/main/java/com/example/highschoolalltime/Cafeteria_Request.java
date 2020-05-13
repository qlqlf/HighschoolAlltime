package com.example.highschoolalltime;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Cafeteria_Request extends StringRequest {
    //서버 URL설정 (PHP파일연동)
    final static private  String URL = "http://highschool.dothome.co.kr/Cafeteria.php";
    //string배열로 저장
    private Map<String, String> map;

    public Cafeteria_Request(int WhatMonth, int WhatDate, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
    //map에 저장
        map = new HashMap<>();
        map.put("WhatMonth",WhatMonth + "");
        map.put("WhatDate",WhatDate + "");
    }
    //map리턴
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
