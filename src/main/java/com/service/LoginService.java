package com.service;

import com.resultResponse.ResultMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    boolean studentLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response);

    boolean teacherLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response);

}
