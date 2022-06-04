package org.stop.util;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.ReUtil;

public class TestPhone {
    public static void main(String[] args) {
        System.out.println(ReUtil.isMatch("^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", "19407980798"));

    }
}
