package com.wx.utils.sign;

import java.util.Base64;

public class Base64Util {

    public static byte[] decode(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(str);
    }

    public static String encode(byte[] byteStr) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(byteStr);
    }

    public static void main(String[] args) {
        String str = "test";
        byte[] byteTest = decode(str);
        System.out.println(encode(byteTest));

        byte x = -88;
        System.out.println(Integer.toBinaryString(x));
    }

}
