package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updateBalanceByIds(@Param("ew")LambdaQueryWrapper<User> wrapper, @Param("amount")int amount);

    @Update("update tb_user set balance=balance-#{money} where id=#{id}")
    void deductBalance(@Param("id") Long id, @Param("money") Integer money);
}
