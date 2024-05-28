package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.service.IUserService;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Transactional
    public void deductUserBalanceById(Long id, Integer money) {
        User user = getById(id);
        if (user.getId()==null || user.getStatus()==2){
            throw new RuntimeException("用户不正常");
        }
        if (user.getBalance()<money){
            throw new RuntimeException("用户金额不足");
        }
        //减少金额
//        baseMapper.deductBalance(id,money);
        int remainBalance = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance,remainBalance)
                .set(remainBalance==0,User::getStatus,2)
                .eq(User::getId,id)
                .eq(User::getBalance,user.getBalance())   //乐观锁
                .update();
    }


    @Override
    public List<User> quertUsers(String name, Integer maxBalance, Integer minBalance, Integer status) {
        return lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .lt(maxBalance != null, User::getBalance, maxBalance)
                .gt(minBalance != null, User::getBalance, minBalance)
                .list();

    }
}
