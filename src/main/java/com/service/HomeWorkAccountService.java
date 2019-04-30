package com.service;


import com.pojo.HomeWorkAccount;
import com.pojo.dto.HomeWorkAccountDto;

import java.util.List;

public interface HomeWorkAccountService {
    boolean insertAccount(Long studentId,Integer taskId,String workUrl);

    List<HomeWorkAccountDto> getuploadDetailListByTaskId(Integer taskId);
}
