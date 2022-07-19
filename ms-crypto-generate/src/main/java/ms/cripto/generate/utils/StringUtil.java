package ms.cripto.generate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtil {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);


    public static String sha256(String value) {
        log.info("ACTIVE SHA256 :" + value);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            log.error("ERROR IN STRING UTIL: sha256: SISTEM ERROR" + e.getMessage());
        }
        return null;
    }
}
