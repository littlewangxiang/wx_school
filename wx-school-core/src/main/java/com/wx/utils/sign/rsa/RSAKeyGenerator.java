package com.wx.utils.sign.rsa;


import com.wx.utils.sign.Base64Util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeyGenerator {

    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64Util.encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64Util.encode((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        System.out.println("公钥：" + publicKeyString);  //0表示公钥
        System.out.println("私钥：" + privateKeyString);  //1表示私钥
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        genKeyPair();
    }
}
