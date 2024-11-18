package org.stock.trading.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestUtil {
    public static void main(String[] args) {
        String str = "Raghu";
       Map<Character,Long> grp = str.chars().mapToObj(as -> Character.valueOf((char)as))
        .collect(Collectors.groupingBy
                (Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(grp.entrySet().stream().filter(as -> as.getValue() == 1).findFirst());

    }
}
