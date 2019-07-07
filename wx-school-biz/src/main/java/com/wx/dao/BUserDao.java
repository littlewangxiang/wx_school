package com.wx.dao;

import com.wx.entity.po.AUser;
import com.wx.entity.po.BUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BUserDao {

    AUser getTicketByTicketId(@Param("username") String username);

    int insertAUser(BUser bUser);
}