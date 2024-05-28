package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.service.IUserService;
import com.itheima.mp.domain.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private  final IUserService userService;

    @ApiOperation("新增用户接口")
    @PostMapping
    public void saverUser(@RequestBody  UserFormDTO userFormDTO){
        User user= BeanUtil.copyProperties(userFormDTO,User.class);
        userService.save(user);
    }
    @ApiOperation("删除用户接口")
    @DeleteMapping("/{id}")
    public void deleteUserById(@ApiParam("用户id") @PathVariable("id")  Long id){
        userService.removeById(id);
    }
    @ApiOperation("根据id查询用户接口")
    @GetMapping("/{id}")
    public UserVO getUserById(@ApiParam("用户id") @PathVariable("id")  Long id){
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }
    @ApiOperation("根据id批量查询用户接口")
    @GetMapping()
    public List<UserVO> getBatchUserById(@ApiParam("用户id") @RequestParam List<Long> ids){
        List<User> userslist = userService.listByIds(ids);
        List<UserVO> userVOList = BeanUtil.copyToList(userslist, UserVO.class);
        return userVOList;
    }
    @ApiOperation("减少用户余额")
    @PutMapping("{id}/deduction/{money}")
    public void deductUserBalanceById(@ApiParam("用户id") @PathVariable Long id, @ApiParam("金额") @PathVariable Integer money){
        userService.deductUserBalanceById(id,money);
    }
    @ApiOperation("根据复杂条件查询用户接口")
    @GetMapping("/list")
    public List<UserVO> queryUsers(UserQuery userQuery){
        List<User> users = userService.quertUsers(userQuery.getName(),userQuery.getMaxBalance(),userQuery.getMinBalance(),userQuery.getStatus());
        return BeanUtil.copyToList(users,UserVO.class);
    }
}
