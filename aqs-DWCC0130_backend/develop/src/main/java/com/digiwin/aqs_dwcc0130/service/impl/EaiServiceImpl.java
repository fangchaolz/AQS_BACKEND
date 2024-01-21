package com.digiwin.aqs_dwcc0130.service.impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.digiwin.app.service.DWEAIResult;
import com.digiwin.aqs_dwcc0130.service.EaiService;


import java.util.Map;

public class EaiServiceImpl implements EaiService {
    private static Log log = LogFactory.getLog(EaiServiceImpl.class);

    @Override
    public DWEAIResult getMessage(Map<String, String> headers, String messageBody) {
        // 解析入参
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(messageBody));
        JSONObject body = jsonObject.getJSONObject("std_data").getJSONObject("parameter");
        // 设置返回结果
        DWEAIResult result = new DWEAIResult();
        result.setCode("0");
        result.getParameter().put("messageBody内容为：", body);
        return result;
    }

}
