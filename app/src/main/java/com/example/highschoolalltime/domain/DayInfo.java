package com.example.highschoolalltime.domain;
/*하루의 날짜정보를 저장하는 클래스이다.*/
public class DayInfo
{
    private String day;
    private boolean inMonth;
    //날짜반환
    public String getDay()
    {
        return day;
    }
    //날짜저장
    public void setDay(String day)
    {
        this.day = day;
    }
    //이번달 날짜인지 확인
    public boolean isInMonth()
    {
        return inMonth;
    }
    //이번달 날짜저장
    public void setInMonth(boolean inMonth)
    {
        this.inMonth = inMonth;
    }

}