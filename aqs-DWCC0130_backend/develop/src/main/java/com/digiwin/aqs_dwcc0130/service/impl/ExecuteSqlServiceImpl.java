package com.digiwin.aqs_dwcc0130.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.digiwin.app.dao.DWDao;
import com.digiwin.app.data.DWDataSetOperationOption;
import com.digiwin.app.service.DWServiceContext;

import com.digiwin.aqs_dwcc0130.service.ExecuteSqlService;
import com.digiwin.utils.DWTenantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

public class ExecuteSqlServiceImpl implements ExecuteSqlService {

    @Autowired
    @Qualifier("dw-dao")
    private DWDao dao;

    private String getTenantSid(){
        DWServiceContext context = DWServiceContext.getContext();
        String tenantSid = String.valueOf(context.getProfile().get("tenantSid"));
        return tenantSid;
    }

    @Override
    public List<Map<String, Object>> getInfo() {
        DWDataSetOperationOption option = new DWDataSetOperationOption();
        StringBuilder sql = new StringBuilder();
        sql.append(DWTenantUtils.getTenantIgnoreTagByColumnName())
                .append("select * from example where id = ?");
        List<Map<String, Object>> select = this.dao.select(option, sql.toString(), "1");

        return select;
    }

    @Override
    public String insertInfo() {
        //创建链接
        DWDataSetOperationOption options = new DWDataSetOperationOption();
        String tenantSid = getTenantSid();
        if(StringUtils.isNotEmpty(tenantSid)){
            tenantSid="匿名用户";
        }
        String sql = DWTenantUtils.getTenantIgnoreTagByColumnName()+
                "insert into example (name,message,tenantsid) values(?,?,?)";
        //执行sql
        System.out.println(sql);
        int update = this.dao.update(options,sql,"fangchao","第一次插入",tenantSid);
        return String.valueOf(update);
    }

    @Override
    public String updateInfo() {
        //创建链接
        DWDataSetOperationOption options = new DWDataSetOperationOption();
        String tenantSid = getTenantSid();
        if(StringUtils.isNotEmpty(tenantSid)){
            tenantSid="匿名用户";
        }
        String sql = DWTenantUtils.getTenantIgnoreTagByColumnName()+
                "update  example set name=?,message=?,tenantsid=? where id = ?";
        //执行sql
        System.out.println(sql);
        int update = this.dao.update(options,sql,"fangchao","第一次更新",tenantSid,1);
        return String.valueOf(update);
    }

    @Override
    public String deleteInfo() {
        //创建链接
        DWDataSetOperationOption options = new DWDataSetOperationOption();
        String tenantSid = getTenantSid();
        if(StringUtils.isNotEmpty(tenantSid)){
            tenantSid="匿名用户";
        }
        String sql = DWTenantUtils.getTenantIgnoreTagByColumnName()+
                "delete from example  where id = ?";
        //执行sql
        System.out.println(sql);
        int update = this.dao.update(options,sql,1);
        return String.valueOf(update);
    }




}
