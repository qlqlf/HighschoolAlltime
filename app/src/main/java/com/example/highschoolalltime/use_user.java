//login클래스에서 가져온 회원정보를 저장하는 class
package com.example.highschoolalltime;

import android.app.Application;
//현재 이 프로젝트에 login 클래스가 없으므로 임의의 값을 저장함.
public class use_user extends Application {
    private  String userID = "IDID";
    private  String userSchool = "시흥고등학교";
    private  String userName = "이시형";
    private  String userEmail = "IDID.com";
    private  String userGrade = "2학년";
    private  String userPassword = "1234";
    public String getUserID(){
        return userID;
    }
    public String getUserSchool() { return userSchool;}
    public String getUserName() { return userName;}
    public String getUserEmail() { return userEmail;}
    public String getUserGrade() { return userGrade;}
    public String getUserPassword() {return userPassword;}
    /*public void setUser(String userID, String userSchool, String userName, String userEmail, String userGrade){
        this.userID = userID;
        this.userSchool = userSchool;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGrade = userGrade;
    }*/
}