package com.wx.utils.sign.sha3;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;

public class SHA3Util {

    public static String sha3_256(String baseString) {
        return sha3_256(baseString.getBytes());
    }

    public static String sha3_256(byte[] bytes) {
        Digest digest = new SHA3Digest(256);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    public static void main(String[] args) {
        System.out.println(sha3_256("test"));
    }

}
