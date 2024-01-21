package com.digiwin.aqs_dwcc0130.service.impl;

import com.digiwin.app.container.exceptions.DWArgumentException;
import com.digiwin.app.dao.DWServiceResultBuilder;
import com.digiwin.app.service.DWServiceResult;
import com.digiwin.aqs_dwcc0130.service.IDemoHelloService;


public class DemoHelloServiceImpl implements IDemoHelloService {
    @Override
    public String sayHello(String name) throws Exception {
        return String.format("Hello! %s", name);
    }

    @Override
    public DWServiceResult getHello(String name) throws Exception {
        if(name==null || name.length()==0) {
            throw new DWArgumentException("name","name is null or empty");
        }
        String data = String.format("Hello! %s", name);
        return DWServiceResultBuilder.build(data);
    }

    @Override
    public DWServiceResult getHello2(String name) throws Exception {
        if(name==null || name.length()==0) {
            return DWServiceResultBuilder.build(false,"name is null or empty");
        }
        String data = String.format("Hello! %s", name);
        return DWServiceResultBuilder.build(true,"done!",data);
    }
}
