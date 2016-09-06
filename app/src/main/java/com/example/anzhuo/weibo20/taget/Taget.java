package com.example.anzhuo.weibo20.taget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anzhuo on 2016/8/29.
 */
public class Taget {
    private static final String REGEX_HTML = "<[^>]+>";
    public static String delHTMLTag(String htmlStr){
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
      return htmlStr.trim();
    }

}
