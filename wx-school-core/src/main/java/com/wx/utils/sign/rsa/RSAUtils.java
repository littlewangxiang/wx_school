package com.wx.utils.sign.rsa;


import com.wx.utils.sign.Base64Util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    private static String publicKey1 =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDGnzEhL1DhiDKfXetvGX17JwuNEp6+GntPqBR+nbvp61xgacHB3J425Dd//S21hI5+4VKFTQdJtqujGk3FjT6S8+nkwokSYAz2Rj95cobuQn2TGQa5dzCsDB+a7nb3vmBVJmqgKud93E56YgD0X18q/8M3rrvW82mmS38Fx2mxRwIDAQAB";

    private static String privateKey1 =
            "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMafMSEvUOGIMp9d628ZfXsnC40Snr4ae0+oFH6du+nrXGBpwcHcnjbkN3/9LbWEjn7hUoVNB0m2q6MaTcWNPpLz6eTCiRJgDPZGP3lyhu5CfZMZBrl3MKwMH5rudve+YFUmaqAq533cTnpiAPRfXyr/wzeuu9bzaaZLfwXHabFHAgMBAAECgYEAv7MjwuidAp/mZARM2DTc9EDSGD1xcv+yJkaOSUpJ9NJRnUhVVpttJIONwX0FYd1nfrRQZhHbR90DuW8IwvO5AH79JSuoVTJDRZ4sMK1q1BD6MnwhinVtagGTUtes3iXOZg9ahUSeAqXEAqSmQGbHyfPEEGSoswbvaLb8KcQo33kCQQDm/w21bXegnJERiLCrNEIdmr2napM2vzHjuRIUazSoSBmZNECBse6qK6uE/X36uOADbMQesl4kW8H9/PDtYSINAkEA3B8JYSFpsV98iEtXxOaQjxOSonrykZEPYSpveLT8ZYLsJEkOqLK5I2a/VeBr2I7EdAbROqCzymsc/7qR3ItPowJBAN1VzoDlHxcabjZqzz18mU1clBTynZR7pwYYsyY1gwMr8rtFwfmD0LflmDeZ3Th8JMmjUlhHeMVF1p/T2dbal5ECQC9g1sn7QR6WaN2FKf0Ni2vgUJ18TR2b1W2ajqeyfOj0hxNATlFtNoAyK1i2lz/jatF2bi5qTIhzCajZZW/M5HUCQQC7junR6Su4j3GxcmXb/4IGfEnAHiAqu+3MD33ZXljxYQZs/+aYz3Q8sHJfaVUMXZtD7D1O70C/SwabJx81jImT";

    private static String publicKey2 =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCLJVD7gGxlbxFTx8/6eFZk9ic60/jsKruLl88n0hH8YkJQOfMKUPlGm/xvy9q+EFxGuKc9GksKkDPaFNEth1fvNcq3vuJQdZyqHuXVhG8DmqjT4dvIOvmE7N+LcE9kM96k3XFYpK/P8MKAzYfYXNiWMcnoKLcF7xcmflz/jI6dwIDAQAB";
    private static String privateKey2 =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIIslUPuAbGVvEVPHz/p4VmT2JzrT+Owqu4uXzyfSEfxiQlA58wpQ+Uab/G/L2r4QXEa4pz0aSwqQM9oU0S2HV+81yre+4lB1nKoe5dWEbwOaqNPh28g6+YTs34twT2Qz3qTdcVikr8/wwoDNh9hc2JYxyegotwXvFyZ+XP+Mjp3AgMBAAECgYAWlHUv21Ef5MFLpHXfOEqGp0ajsY2iCTsdJZ4PffGjyRnkKQYMO/wy+WsBnBA1leqEHA3C/AJiTtH/KDGJca2WczmA8j4aI19UFbUtqc/uZvZIuxRs6WPCzxAgwGHZPAs19EMqJActwRgf1BujXTbXfl9qt+dWht/ZGXGcIb+zAQJBAPqe5kmE4TLUp/I7uj4TpfFcU0rzyB3NAd7YdxlLsN4wBb6KqX6jMjaZEQ0OEe7TunGR5enlX2cyfUUVMjLOe1ECQQCE99vUW+UZEAlK2UeWg+/D9DJxGBD+D6nz7wdtweYaHJ51l193c+yxHbOBssicAnRxDQ6+zQQGsG0VmKWGTddHAkAiMvK7z40ncYtla68Tb3zyfaXEEZYaMvJxZVqDMif1edoe0S8l38EnYCXKgUQDMhM8EiusiidFnscnWSgbdL+xAkAS5Snu/nQK4LWBVmkO4YbT4MK+QX5ArDcpe+EhXrl1InX+HdmqHuYWsTLODnrleI/VdNaQlI+q561IsV1sPWmHAkAW2HRwgB9O8AN8dkJXYRDX6XMfQriPeIHoRfp266wVzbnS6hJX+JHd+HJoJsEHK+UGEm6jyKxuX3BwxY3sO8U+";

    //公钥加密
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64Util.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64Util.encode(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64Util.decode(str);
        //base64编码的私钥
        byte[] decoded = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static void main(String[] arga) throws Exception {
        String str = "hahahaha";
        String encryStr = encrypt(str, publicKey1);
        String deStr = decrypt(encryStr, privateKey2);
        System.out.println(deStr);
    }

}
