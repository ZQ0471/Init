package com.baimi.init.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class AliyunSmsUtil {
    @Value("${aliyun.sms.sms-access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.sms-access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sms-sign-nam}")
    private String signName;

    @Value("${aliyun.sms.sms-template-cod}")
    private String templateCode;

    @Value("${aliyun.sms.sms-endpoint}")
    private String endpoint;
    public boolean sendSms(String phoneNumber, HashMap<String, Object> templateParams) {
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumber);
            request.setSignName(signName);
            request.setTemplateCode(templateCode);
            // 将HashMap转化为JSON字符串
            String templateParam = JSON.toJSONString(templateParams);
            request.setTemplateParam(templateParam);

            SendSmsResponse response = client.getAcsResponse(request);

            return "OK".equals(response.getCode());
        } catch (ClientException e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }
}

