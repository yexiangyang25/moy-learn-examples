package org.moy.spring.captcha;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        double a1 = -2.33d;
        double a2 = 1.33d;
        System.out.println(a1 + a2);
        System.out.println(add(a1 , a2));
        System.out.println(subtract(a2 , a1));
    }

    public static Double subtract(Double d1, Double d2) {
        BigDecimal b1 = new BigDecimal(d1.toString());
        if (d2==null)return b1.doubleValue();
        BigDecimal b2 = new BigDecimal(d2.toString());
        return b1.subtract(b2).doubleValue();
    }

    /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }
}
