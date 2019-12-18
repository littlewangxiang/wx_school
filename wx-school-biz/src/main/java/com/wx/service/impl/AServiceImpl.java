package com.wx.service.impl;

import com.wx.dao.ADao;
import com.wx.entity.po.AEntity;
import com.wx.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AServiceImpl implements AService {

    @Autowired
    private ADao aDao;

    @Override
    public AEntity selectOne() {
        List<AEntity> result = aDao.selectOne(1);
        if (result == null || result.size() <= 0) {
            return null;
        }
        return result.get(0);
    }
}
