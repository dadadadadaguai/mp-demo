package com.itheima.mp.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;

public interface IUserService  extends IService<User> {
    void deductUserBalanceById(Long id, Integer money);
}
