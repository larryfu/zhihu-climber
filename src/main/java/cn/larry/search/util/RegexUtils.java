package cn.larry.search.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fugz on 2016/6/23.
 */
public class RegexUtils {

    public static List<String> getMatch(String regex, String content) {
        Matcher matcher = Pattern.compile(regex).matcher(content);
        List<String> result = new ArrayList<>();
        while (matcher.find())
            result.add(matcher.group());
        return result;
    }

    public static String getFirstMatch(String regex, String content) {
        if (regex == null || content == null)
            return null;
        List<String> result = getMatch(regex, content);
        return result.size() == 0 ? null : result.get(0);
    }
}
