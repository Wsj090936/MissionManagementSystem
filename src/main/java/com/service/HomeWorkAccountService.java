package com.service;


import com.pojo.HomeWorkAccount;

public interface HomeWorkAccountService {
    boolean insertAccount(String studentId,Integer taskId,String workUrl);
}
