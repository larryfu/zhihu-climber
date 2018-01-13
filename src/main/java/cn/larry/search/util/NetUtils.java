package cn.larry.search.util;


import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Thinkpad on 2015/11/27.
 */

/**
 * @author larryfu
 */
public class NetUtils {

    private NetUtils() {
    }


    public static String urlEncodeWithUTF8(String str) throws IllegalArgumentException {
        try {
            if(str == null )
                return "";
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("编码格式UTF-8不被支持");
        }
    }

    private static boolean isIpInvalid(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String[] ipHeads = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String ip = null;
        for (String head : ipHeads)
            if (isIpInvalid(ip))
                ip = request.getHeader(head);
            else break;

        if (isIpInvalid(ip))
            ip = request.getRemoteAddr();
        return ip;
    }
}
