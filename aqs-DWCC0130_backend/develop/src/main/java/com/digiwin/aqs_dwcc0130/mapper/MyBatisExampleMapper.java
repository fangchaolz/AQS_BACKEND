package com.digiwin.aqs_dwcc0130.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
public interface MyBatisExampleMapper  {
    public void updateInfo(@Param("parameter") Map<String, Object> parameter);
    public void deleteInfo(@Param("parameter") Map<String, Object> parameter);
    public void insertInfo(@Param("parameter") Map<String, Object> parameter);
    public List<Map<String, Object>> getInfo();

}
