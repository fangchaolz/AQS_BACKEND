package com.digiwin.aqs_dwcc0130.service;

import com.digiwin.app.service.AllowAnonymous;
import com.digiwin.app.service.DWService;
import com.digiwin.app.service.DWServiceResult;

public interface IDemoHelloService extends DWService {

    @AllowAnonymous
    String sayHello(String name) throws Exception;
    DWServiceResult getHello(String name) throws Exception;
    DWServiceResult getHello2(String name) throws Exception;

}
