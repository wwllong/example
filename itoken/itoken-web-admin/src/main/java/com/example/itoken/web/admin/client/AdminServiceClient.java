package com.example.itoken.web.admin.client;

import com.example.itoken.common.constants.EnumHttpStatus;
import com.example.itoken.common.dto.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-admin", fallback = AdminServiceClient.AdminServiceClientFallback.class)
public interface AdminServiceClient {

    @GetMapping("login")
    BaseResult login(@RequestParam(value = "loginCode") String loginCode, @RequestParam(value = "password") String password);


    @Component
    public class AdminServiceClientFallback implements AdminServiceClient {

        @Override
        public BaseResult login(String loginCode, String password) {
            return EnumHttpStatus.toBaseResult(EnumHttpStatus.BAD_GATEWAY);
        }

    }

}


