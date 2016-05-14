package br.inpe.app.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Heitor
 * @since 30/04/2016
 */
public class CommonsString {
    public static void main(String[] args) {
        String text = "I will come and meet you at the woods 123woods and all the woods";

        List<String> tokens = new ArrayList<>();
        tokens.add("123woods");
        tokens.add("woods");

        String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
