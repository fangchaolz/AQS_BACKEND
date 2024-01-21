package com.digiwin.aqs_dwcc0130.service.impl;


import com.digiwin.aqs_dwcc0130.service.RestFulService;

public class RestFulServiceImpl implements RestFulService {
    @Override
    public String getMessage(String name) {
        return String.format("hello,[%s] 好啊",name);
    }
}
