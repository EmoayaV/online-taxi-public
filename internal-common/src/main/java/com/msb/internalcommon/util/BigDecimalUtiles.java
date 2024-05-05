package com.msb.internalcommon.util;

import java.math.BigDecimal;

/**
 * ClassName: BigDecimalUtiles
 * Package: com.msb.internalcommon.util
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 16:27
 * @Version 1.0
 */
public class BigDecimalUtiles {

    //加法
    public static double add(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    //减法
    public static double substract(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    //乘法
    public static double multiply(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }




    //除法
    public static double divide(int v1, int v2){
        if(v2<=0){
            throw new IllegalArgumentException("除数非法");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }


}
