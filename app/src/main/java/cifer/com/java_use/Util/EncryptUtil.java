package cifer.com.java_use.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    private static final String securty1 = "asd1h2bhbujihu3in1k2jn4l123njknklzxcnon12j3nu12nh3unkzxcdi12nk31m2knjusda";

    private static final String securty2 = "aasjdnwn21nmizoqwldasdi2jzmmksdi2msad5455asdpqwe2o9sxmz92m34mdkanwd82jmiozxcu1j112amzxcnb213111wernm2nzlcjasiwqlwenmm1234f";


    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }

    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("please input something");
        }

        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }
}
