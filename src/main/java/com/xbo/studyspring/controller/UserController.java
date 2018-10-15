package com.xbo.studyspring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xbo.studyspring.biz.entity.User;
import com.xbo.studyspring.biz.service.IUserService;
import com.xbo.studyspring.exception.CommonException;
import com.xbo.studyspring.model.UserReq;
import com.xbo.studyspring.model.UserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层 简单演示增删改查及分页
 *
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户API")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("add")
    @ApiOperation(value="用户新增")
    //正常业务时， 需要在user类里面进行事务控制，控制层一般不进行业务控制的。
    //@Transactional(rollbackFor = Exception.class)
    public Map<String,String> addUser(@Valid @RequestBody UserReq userReq){

        User user = new User();
        user.setName(userReq.getName());
        //由于设置了主键策略 id可不用赋值 会自动生成
        //user.setId(0L);
        userService.save(user);
        Map<String,String> result = new HashMap<String,String>();
        result.put("respCode", "01");
        result.put("respMsg", "新增成功");
        //事务测试
        //System.out.println(1/0);
        return result;
    }

    @PostMapping("update")
    @ApiOperation(value="用户修改")
    @CacheEvict(value = "xbo", key = "#userReq.id")
    public Map<String,String> updateUser(@Valid @RequestBody UserReq userReq){

        if(userReq.getId() == null || "".equals(userReq.getId())) {
            throw new CommonException("0000", "更新时ID不能为空");
        }
        User user = new User();
        user.setName(userReq.getName());
        user.setId(Long.parseLong(userReq.getId()));
        userService.updateById(user);
        Map<String,String> result = new HashMap<String,String>();
        result.put("respCode", "01");
        result.put("respMsg", "更新成功");
        return result;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value="用户查询(ID)")
    @ApiImplicitParam(name="id",value="查询ID",required=true)
    @Cacheable(value = "xbo", key = "#id")
    public Map<String,Object> getUser(@PathVariable("id") String id){
        //查询
        User user = userService.getById(id);
        if(user == null) {
            throw new CommonException("0001", "用户ID：" + id + "，未找到");
        }
        UserResp resp = UserResp.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01");
        result.put("respMsg", "成功");
        result.put("data", resp);
        return result;
    }

    @GetMapping("/page")
    @ApiOperation(value="用户查询(分页)")
    public Map<String,Object> pageUser(int current, int size){
        //分页
        Page<User> page = new Page<>(current, size);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01");
        result.put("respMsg", "成功");
        result.put("data", userService.page(page,
                new QueryWrapper<User>().orderByDesc("age")));

        return result;
    }

}