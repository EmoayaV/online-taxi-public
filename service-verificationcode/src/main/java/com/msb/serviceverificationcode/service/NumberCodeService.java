package com.msb.serviceverificationcode.service;

import org.springframework.stereotype.Service;

/**
 * ClassName: NumberCodeService
 * Package: com.msb.serviceverificationcode.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/25 16:04
 * @Version 1.0
 */
@Service
public class NumberCodeService {
    /**
     * 返回一个位数为size 的验证码
     * @param size
     * @return
     */
    public int getRandom(int size) {
        return (int) ((Math.random() * 9 + 1) * (Math.pow(10, size - 1)));
    }

}
