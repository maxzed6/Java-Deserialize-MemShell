package sample.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

public class EncodeUtils {
    public static String encodeExp(String encodeType, byte[] expBytes) throws UnsupportedEncodingException {
        if (encodeType.equals("base64")){
            return new String(Base64.getEncoder().encode(expBytes));
        } else if (encodeType.equals("URLEncode")){
            return URLEncoder.encode(new String(expBytes), "utf-8");
        } else if (encodeType.equals("hex")){
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < expBytes.length; i++){
                stringBuffer.append(String.format("%02x", expBytes[i]));
            }
            return stringBuffer.toString();
        }
        return "";
    }
}
