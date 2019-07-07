package com.wx.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AUser {

    private String username;
    private Date date;

    public AUser(String username, Date date){
        this.username = username;
        this.date = date;
    }
}
