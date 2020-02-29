package com.wx.utils.sign.message_digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = new byte[0];

        try {
            bt = strSrc.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var6) {
            return null;
        }

        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }

            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
            return strDes;
        } catch (NoSuchAlgorithmException var7) {
            return null;
        }
    }

    //这里的写法和MD5写法一样
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;

        for (int i = 0; i < bts.length; ++i) {
            tmp = Integer.toHexString(bts[i] & 255);
            if (tmp.length() == 1) {
                des = des + "0";
            }

            des = des + tmp;
        }

        return des;
    }
}