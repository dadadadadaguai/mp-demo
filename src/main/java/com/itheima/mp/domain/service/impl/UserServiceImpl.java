package com.itheima.mp.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.service.IUserService;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void deductUserBalanceById(Long id, Integer money) {
        User user = getById(id);
        if (user.getId()==null || user.getStatus()==2){
            throw new RuntimeException("用户不正常");
        }
        if (user.getBalance()<money){
            throw new RuntimeException("用户金额不足");
        }
        //减少金额
        baseMapper.deductBalance(id,money);
    }
}