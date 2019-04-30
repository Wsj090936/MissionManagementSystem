package com.dao;

import com.pojo.HomeWorkAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeWorkAccountMapper {

    int insertAccount(HomeWorkAccount account);

    List<HomeWorkAccount> selectAccountByTaskId(@Param("taskId") Integer taskId);

}
