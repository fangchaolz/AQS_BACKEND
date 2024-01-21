package com.digiwin.aqs_dwcc0130.service.impl;

import com.digiwin.app.dao.DWDao;
import com.digiwin.app.dao.DWDataSetSqlInfo;
import com.digiwin.app.dao.DWSQLExecutionResult;
import com.digiwin.app.data.DWDataSet;
import com.digiwin.app.data.DWDataSetOperationOption;
import com.digiwin.app.data.DWDataTable;

import com.digiwin.aqs_dwcc0130.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DataSetServiceImpl implements DataSetService {
    @Autowired
    @Qualifier("dw-dao")
    DWDao dao;
    @Override
    public String insertInfo() {
        DWDataSetOperationOption option = new DWDataSetOperationOption();

        DWDataSet ds = new DWDataSet();
        DWDataTable dt = ds.newTable("example");
        dt.newRow().set("name","fangchao")
                .set("message","DataSet message")
                .set("$state","C");
        DWDataSetSqlInfo sqlInfo = dao.getDialect().parse(ds,option);

        DWSQLExecutionResult executionResult = dao.execute(ds,option);
        return "插入成功";


    }

    @Override
    public String updateInfo() {
        return null;
    }

    @Override
    public String deleteInfo() {
        return null;
    }
}
