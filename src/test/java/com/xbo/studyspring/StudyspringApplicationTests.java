package com.xbo.studyspring;

import com.xbo.studyspring.biz.entity.User;
import com.xbo.studyspring.biz.mapper.UserMapper;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyspringApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
    }
    //引入 ContiPerf 进行性能测试
    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    //10个线程 执行10次
    @PerfTest(invocations = 100,threads = 10)
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
