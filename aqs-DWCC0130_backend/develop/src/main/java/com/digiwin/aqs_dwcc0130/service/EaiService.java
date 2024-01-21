package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.DWEAIResult;
import com.digiwin.app.service.DWService;
import com.digiwin.app.service.eai.EAIService;

import java.util.Map;

public interface EaiService extends DWService {

    @EAIService(id = "test.basic.eaiService.getMessage")
    public DWEAIResult getMessage(Map<String, String> headers, String messageBody);

}
