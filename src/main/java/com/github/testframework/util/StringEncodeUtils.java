package com.github.testframework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * StringEncodeUtils
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-22
 */
public class StringEncodeUtils {

    private StringEncodeUtils() {
    }

    /**
     * 用'*'替换掉字符串中指定索引和长度的字符
     * (索引越界时取小于指定索引的最大索引,有剩余的字符时剩余字符追加到末尾)
     *
     * @param str
     * @param startReplaceIndex 起始索引(包含)
     * @param replaceLength
     * @return
     */
    public static String replaceInIndex(String str, int startReplaceIndex, int replaceLength) {
        return replaceInIndex(str, startReplaceIndex, replaceLength, '*');
    }

    /**
     * 用指定字符替换掉字符串中指定索引和长度的字符
     * (索引越界时取小于指定索引的最大索引,有剩余的字符时剩余字符追加到末尾)
     *
     * @param string
     * @param startReplaceIndex 起始索引(包含)
     * @param replaceLength
     * @param replacer
     * @return
     */
    public static String replaceInIndex(String string, int startReplaceIndex, int replaceLength, char replacer) {
        if (startReplaceIndex < 0 || replaceLength < 0) {
            throw new IllegalArgumentException("illegal args: startReplaceIndex = " + startReplaceIndex + ", replaceLength = " + replaceLength);
        }
        if (replaceLength == 0 || StringUtils.isBlank(string)) {
            return StringUtils.isBlank(string) ? "" : string.trim();
        }

        // 目标字符串分3段生成: 1-前缀保留字符, 2-目标替换字符, 3-后缀保留字符
        // 索引越界时取小于指定索引的最大索引
        string = string.trim();
        int prefixLength = startReplaceIndex > (string.length() - 1) ? (startReplaceIndex - string.length() + 1) : startReplaceIndex;
        int tailKeepLength = string.length() > (startReplaceIndex + replaceLength) ? (string.length() - startReplaceIndex - replaceLength) : 0;

        int strMaxIndex = string.length() - 1, len1 = prefixLength, len2 = len1 + replaceLength, len3 = len2 + tailKeepLength;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len3; i++) {
            if (i < len1) {
                builder.append(i <= strMaxIndex ? string.charAt(i) : replacer);
            } else if (i < len2) {
                builder.append(replacer);
            } else if (i < len3) {
                builder.append(string.charAt(i));
            }
        }

        return builder.toString();
    }
}
