package com.freedom.backend.demo;

import com.freedom.backend.demo.service.AfterSaleServiceImpl;
import com.freedom.backend.demo.service.LeaveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author rick
 * @Date 2022/4/11 12:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoTest {
    @Resource
    private AfterSaleServiceImpl afterSaleService;
    @Resource
    private LeaveServiceImpl leaveService;

    @Rollback(false)
    @Test
    public void runLeaveDemo(){
        leaveService.run();
    }
}
