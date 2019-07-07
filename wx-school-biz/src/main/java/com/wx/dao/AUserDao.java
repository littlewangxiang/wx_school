package com.wx.dao;

import com.wx.entity.po.AUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AUserDao {

    AUser getTicketByTicketId(@Param("username") String username);

    int insertAUser(AUser aUser);
}
