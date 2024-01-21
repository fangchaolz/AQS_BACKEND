package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.AllowAnonymous;
import com.digiwin.app.service.DWService;

import java.util.List;
import java.util.Map;

public interface ExecuteSqlService extends DWService {

    @AllowAnonymous
    public List<Map<String,Object>> getInfo();

    @AllowAnonymous
    public String insertInfo();

    @AllowAnonymous
    public String updateInfo();

    @AllowAnonymous
    public String deleteInfo();
}
