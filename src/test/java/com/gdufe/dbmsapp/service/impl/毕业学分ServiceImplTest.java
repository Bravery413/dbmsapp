package com.gdufe.dbmsapp.service.impl;

import com.gdufe.dbmsapp.entity.毕业学分;
import com.gdufe.dbmsapp.service.毕业学分Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class 毕业学分ServiceImplTest {
    @Resource
    毕业学分Service 毕业学分Service;
    @Test
    public void test(){
        List<毕业学分> list = 毕业学分Service.list(null);
        System.out.println(111);
        System.out.println(list);
    }

}