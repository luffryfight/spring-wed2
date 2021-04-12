package com.kobe.service.Impl;

import com.kobe.dao.MiddlerMapper;
import com.kobe.pojo.Middle;
import com.kobe.service.MiddleService;
import com.kobe.vo.SignInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作用：
 * 2020/11/25
 */
@Service
public class MiddleServiceImpl implements MiddleService {
    @Autowired
    MiddlerMapper middlerMapper;

    @Override
    public void addMiddle(int idc, int idu, int ids) {
        middlerMapper.addMiddle(idc,idu,ids);
    }
}
