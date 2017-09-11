package com.app.security;

import java.util.regex.Pattern;

public class XssAttacks {

	public final static String tagStart = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";

    public final static String tagEnd = "\\</\\w+\\>";

    public final static String tagSelfClosing = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";

    public final static String htmlEntity = "&[a-zA-Z][a-zA-Z0-9]+;";

    public final static Pattern htmlPattern = Pattern.compile("(" + tagStart + ".*" + tagEnd + ")|(" + tagSelfClosing
            + ")|(" + htmlEntity + ")", Pattern.DOTALL);

    public static boolean isHtml(String s) {
        boolean ret = false;
        if (s != null) {
            ret = htmlPattern.matcher(s).find();
        }
        return ret;
    }

}
