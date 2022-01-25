package com.example.itoken.web.admin.service.impl;

import com.example.itoken.service.api.client.AdminServiceClient;
import com.example.itoken.web.admin.service.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizServiceImpl implements UserBizService {

    @Autowired
    private AdminServiceClient adminServiceClient;


}
