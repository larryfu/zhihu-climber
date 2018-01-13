package cn.larry.search.controller;

import cn.larry.search.util.NetUtils;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by larryfu on 2015/12/16.
 */

/**
 * @author larryfu
 */
public class ControllerUtils {

    static final Logger logger = LogManager.getLogger();

    public static void setDownloadHeads(String filename, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("octets/stream");
        String name = NetUtils.urlEncodeWithUTF8(filename);
        response.addHeader("Content-Disposition", "attachment;filename=" + name + ".html");
    }

    public static void setDownloadErrorHeads(HttpServletResponse response, Object resuponseData) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Gson gson = new Gson();
        String responseStr = gson.toJson(resuponseData);
        byte[] bytes = responseStr.getBytes();
        try (OutputStream os = response.getOutputStream();) {
            os.write(bytes);
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
