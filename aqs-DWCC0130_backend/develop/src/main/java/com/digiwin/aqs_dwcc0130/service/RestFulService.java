package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.AllowAnonymous;
import com.digiwin.app.service.DWService;

public interface RestFulService extends DWService {
    @AllowAnonymous
    public String getMessage(String name);
}
