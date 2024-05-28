package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;

import java.util.List;

public interface IUserService  extends IService<User> {
    void deductUserBalanceById(Long id, Integer money);

    List<User> quertUsers(String name, Integer maxBalance, Integer minBalance, Integer status);

}
