package br.inpe.app.commons;

import com.google.common.base.*;
import org.apache.commons.lang3.StringUtils;


import java.util.*;


/**
 * @author Heitor
 * @since 30/04/2016
 */
public class GuavaString {
    public static void main(String[] args) {
        //Joiner Class
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1, 2, 3, 4, 5, null, 6)));

        //Splitter Class
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog."));


        //CharMatcher Class
        System.out.println(CharMatcher.DIGIT.retainFrom("mahesh123")); // only the digits
        System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom("     Mahesh     Parashar ", ' '));

        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom("mahesh123", "*")); // star out all digits
        // eliminate all characters that aren't digits or lowercase
        System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("mahesh123"));

        //CaseFormat Class
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));

        /**
         * Test
         */
        System.out.println("\nTEST\n");
        List<String> dataName = Arrays.asList("vegtype_2000.shp", "vegtype_2005.shp", "vegtype_2010.shp", "vegtype_2015.shp",
                "vegtype_2020.shp", "vegtype_2025.shp", "vegtype_2030.shp", "vegtype_2035.shp", "vegtype_2040.shp",
                "vegtype_2045.shp", "vegtype_2050.shp");

        Map<String, List<String>> datasetGroup = new HashMap<>();

        dataName.forEach(data -> {
            List<String> datasetGroupIterator = Splitter.on("_")
                    .trimResults()
                    .omitEmptyStrings()
                    .splitToList(data);

            if (datasetGroupIterator.size()!= 2)
                return;

            String name = datasetGroupIterator.get(0);
            String year = datasetGroupIterator.get(1);

            if(datasetGroup.containsKey(name))
                datasetGroup.get(name).add(year);
            else{
                List<String> years = new ArrayList<>();
                years.add(year);
                datasetGroup.put(name,years);
            }
        });

        System.out.println(datasetGroup);
    }
}
