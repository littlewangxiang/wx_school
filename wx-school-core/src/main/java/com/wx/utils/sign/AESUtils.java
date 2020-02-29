package com.wx.utils.sign;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    // 加密
    public static String encrypt(String sSrc, String sKey) {
        try {
            if (sSrc == null)
                return null;
            if (sKey == null) {
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                return null;
            }
            byte[] raw = sKey.getBytes();
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());

            return Base64Util.encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Throwable e) {
            return null;
        }
    }

    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64Util.decode(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Throwable th) {
            return null;
        }
    }

    public static void main(String[] args) {
        int lenght = "aa".length();
        int lenght1 = "afadsfadsfsadfasdfasdfasdfsadfasdfasdfasdfsdafasdfasdfasdfasdfasdfasdfa".length();
        System.out.println(lenght);
    }
}
