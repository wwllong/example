package com.example.itoken.web.admin.client;

import com.example.itoken.common.constants.EnumHttpStatus;
import com.example.itoken.common.dto.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-sso", fallback = AuthServiceClient.AuthServiceClientFallback.class)
public interface AuthServiceClient {

    @GetMapping("/auth/token/usr")
    BaseResult getTbSysUserByToken(@RequestParam("token") String token);

    @Component
    public class AuthServiceClientFallback implements AuthServiceClient {

        @Override
        public BaseResult getTbSysUserByToken(String token) {
            return EnumHttpStatus.toBaseResult(EnumHttpStatus.BAD_GATEWAY);
        }

    }

}
