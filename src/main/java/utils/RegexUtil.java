package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * получение значения из текста по регулярному выражению
     */
    public static String getMatchValueByGroupNumber(String sentence, String regex, int groupNumber) {
        if (sentence != null && regex != null) {
            sentence = sentence.trim();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sentence);
            if (matcher.find()) {
                return matcher.group(groupNumber);
            }
        }
        return null;
    }

    /**
     * совпадение в тексте по регулярному выражению
     */
    public static Boolean getMatch(String sentence, String regex) {
        if (sentence != null && regex != null) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sentence);
            return matcher.find();
        }
        return null;
    }

}

