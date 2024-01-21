package com.digiwin.aqs_dwcc0130.service.impl;

import com.digiwin.app.json.gson.DWGsonProvider;
import com.digiwin.app.service.DWEAIResult;


import com.digiwin.aqs_dwcc0130.pojo.CreateParam;
import com.digiwin.aqs_dwcc0130.service.IMyEaiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

public class MyEaiService implements IMyEaiService {
    private static Log log = LogFactory.getLog(MyEaiService.class);

    @Override
    public DWEAIResult create(Map<String, Object> header, String messageBody) throws Exception {
        log.warn(messageBody);
        CreateParam param = DWGsonProvider.getGson().fromJson(messageBody, CreateParam.class);
        // Do some business logic
        return new DWEAIResult("0", "0", "OK", new HashMap<String, Object>());
    }

    @Override
    public DWEAIResult createPojo(Map<String, Object> header,
                                  @Validated CreateParam messageBody) throws Exception {
        log.warn(DWGsonProvider.getGson().toJson(messageBody));
        // Do some business logic
        return new DWEAIResult("0", "0", "OK", new HashMap<String, Object>());
    }
}
