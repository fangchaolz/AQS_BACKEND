package com.digiwin.aqs_dwcc0130.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digiwin.app.service.DWServiceContext;

import com.digiwin.aqs_dwcc0130.mapper.MyBatisPlusMapper;
import com.digiwin.aqs_dwcc0130.pojo.UserInfo;
import com.digiwin.aqs_dwcc0130.service.IMybatisPlusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MybatisPlus implements IMybatisPlusService {

    @Autowired
    MyBatisPlusMapper mapper;

    @Override
    public List<Map<String, Object>> getInfo() {
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",1);
        List<UserInfo> userInfos = mapper.selectList(queryWrapper);
        for (UserInfo userInfo : userInfos) {
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("id", userInfo.getId());
            map1.put("name", userInfo.getName());
            map1.put("message", userInfo.getMessage());
            map1.put("tenantsid",userInfo.getTenantsid());
            map.add(map1);
        }
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo.toString());
        }
        return map;
    }

    @Override
    public String insertInfo(String name, String message) {
        DWServiceContext context = DWServiceContext.getContext();
        String tenantSid = String.valueOf(context.getProfile().get("tenantSid"));
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setMessage(message);
        userInfo.setTenantsid(tenantSid);
        int insert = mapper.insert(userInfo);
        if (insert > 0) {
        return "新增成功";
        }else {
            return "新增失败";
        }
    }



    @Override
    public String updateInfo(Integer id, String message) {

        UserInfo userInfo = new UserInfo();
        userInfo.setMessage(message);
        userInfo.setId(id);
        int update = mapper.updateById(userInfo);
        if (update > 0) {
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    @Override
    public String deleteInfo(Integer id,String name) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("id",id);

        int delete = mapper.deleteByMap(map);

        if (delete > 0) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
}
