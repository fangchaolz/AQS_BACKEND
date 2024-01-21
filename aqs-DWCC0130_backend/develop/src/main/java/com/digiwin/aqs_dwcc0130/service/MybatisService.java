package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.DWService;

import java.util.List;
import java.util.Map;

public interface MybatisService extends DWService {

    public List<Map<String, Object>> getInfo();

    public String insertInfo(String name, String message);

    public String updateInfo(String id, String message);

    public String deleteInfo(String id);

}
