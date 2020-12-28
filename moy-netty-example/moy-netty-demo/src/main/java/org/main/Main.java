package org.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 10;
        log.info("main a={},b={}", a, b);
        swap(a, b);
        log.info("main a={},b={}", a, b);
    }

    public static void swap(Integer aa, Integer bb) {
        Integer temp = aa;
        aa = bb;
        bb = temp;
        log.info("swap a={},b={}", aa, bb);
    }
}
