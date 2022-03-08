package com.asleepyfish;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhoujh42045
 * @Date: 2022/3/7 16:43
 * @Description: TODO
 */
@SpringBootTest
public class DailyTest {
    @Test
    public void testToUpperCase() {
        System.out.println("10k".toUpperCase());
        System.out.println("rRk".toUpperCase());
    }

    @Test
    public void testStringBuildLength() {
        StringBuilder sb = new StringBuilder();
        sb.append(",1").append("sd");
        System.out.println(sb.toString().length());
    }
}
