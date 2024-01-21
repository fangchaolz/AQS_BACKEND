package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.DWEAIResult;
import com.digiwin.app.service.DWService;
import com.digiwin.app.service.eai.EAIService;
import com.digiwin.aqs_dwcc0130.pojo.CreateParam;


import java.util.Map;

public interface IMyEaiService extends DWService {

    @EAIService(id = "demo.eai.create")
    DWEAIResult create(Map<String, Object> header, String messageBody) throws Exception;

    @EAIService(id = "demo.eai.create.pojo")
    DWEAIResult createPojo(Map<String, Object> header, CreateParam messageBody) throws Exception;
}
