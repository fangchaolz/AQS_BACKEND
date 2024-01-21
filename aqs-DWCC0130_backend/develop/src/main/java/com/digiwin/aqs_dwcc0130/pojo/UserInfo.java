package com.digiwin.aqs_dwcc0130.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@TableName("example")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String message;
    private String tenantsid;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}