package com.digiwin.aqs_dwcc0130.service.impl;

import com.digiwin.app.service.DWServiceContext;

import com.digiwin.aqs_dwcc0130.mapper.MyBatisExampleMapper;
import com.digiwin.aqs_dwcc0130.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MybatisServiceImpl implements MybatisService {
    @Autowired
    MyBatisExampleMapper exampleMapper;
    @Override
    public List<Map<String, Object>> getInfo() {
        List<Map<String, Object>> info = exampleMapper.getInfo();
        return info;
    }

    @Override
    public String insertInfo(String name, String message) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", name);
        parameter.put("message", message);

        DWServiceContext context = DWServiceContext.getContext();
        String tenantSid = String.valueOf(context.getProfile().get("tenantSid"));
        parameter. put("tenantsid", tenantSid);
        exampleMapper. insertInfo(parameter);
        return"新增成功";

    }

    @Override
    public String updateInfo(String id, String message) {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("id", id);
        parameters.put("message", message);
        exampleMapper.updateInfo(parameters);
        return "更新成功";
    }

    @Override
    public String deleteInfo(String id) {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("id", id);
        exampleMapper.deleteInfo(parameters);
        return "删除成功";
    }
}
