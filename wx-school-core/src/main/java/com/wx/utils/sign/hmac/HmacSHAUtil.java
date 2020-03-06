package com.wx.utils.sign.hmac;


import com.wx.utils.sign.Base64Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

public class HmacSHAUtil {
    //HmacMD5
    //HmacSHA1
    //HmacSHA256
    private static String CHARSET_UTF8 = "utf8";


    //第四步 按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
    public static String hmacSHA1Signature(String secret, String baseString) {
        return Base64Util.encode(hmacSHASignature(secret, baseString, "HmacSHA1"));
    }

    public static byte[] hmacSHASignature(String secret, String baseString, String algorithmName) {

        try {
            Mac mac = Mac.getInstance(algorithmName);
            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), ALGORITHM);
            mac.init(keySpec);
            return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(hmacSHA1Signature("1234", "test"));
    }

}
