package com.wx.utils.sign.message_digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final char HEX_DIGITS[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMd5Str(String str) {
        //MD5
        //SHA-1
        //SHA-256
        return getMD5Str(str.getBytes());

    }

    public static String getMD5Str(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();//128位（16字节） bit特定编码 32个表示16进制的数字或者是字母的字符形式
            return bufferToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    //这里的写法和SHAUtils中那个一样
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = HEX_DIGITS[(bt & 0xf0) >>> 4];// 取字节中高 4 位的数字转换
        char c1 = HEX_DIGITS[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static void main(String[] args) {
        System.out.println(getMd5Str("testfasdkjfadkfjka"));
    }
}
