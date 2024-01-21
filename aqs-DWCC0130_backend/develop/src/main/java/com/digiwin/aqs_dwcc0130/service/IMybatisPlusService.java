package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.DWService;


import java.util.List;
import java.util.Map;



public interface IMybatisPlusService extends DWService {

    public List<Map<String, Object>> getInfo();

    public String insertInfo(String name, String message);

    public String updateInfo(Integer id, String message);

    public String deleteInfo(Integer id,String name);

}
