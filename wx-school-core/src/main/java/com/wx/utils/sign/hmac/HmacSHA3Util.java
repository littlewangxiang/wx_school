package com.wx.utils.sign.hmac;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class HmacSHA3Util {
    //        224:
//        case 256:
//        case 384:
//        case 512:

    public static String sha3_512(String secret, String baseString) {
        Digest digest = new SHA3Digest(512);
        Mac mac = new HMac(digest);
        mac.init(new KeyParameter(secret.getBytes()));
        mac.update(baseString.getBytes(), 0, baseString.getBytes().length);
        byte[] macV = new byte[mac.getMacSize()];
        int res = mac.doFinal(macV, 0);
        return Hex.toHexString(macV);
    }

    public static void main(String[] args) {
        System.out.println(sha3_512("123", "test"));
    }
}
