package com.example.srms.util.userextractor;

import com.example.srms.Model.Student;

public interface LoggedUser {

    UserInfo getInfo();

    Student getUser();
}
