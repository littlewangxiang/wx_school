package com.wx.dao;

import com.wx.entity.po.AEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ADao {

    @Select("select * from tb_a where id = #{id}")
    List<AEntity> selectOne(Integer id);

}
