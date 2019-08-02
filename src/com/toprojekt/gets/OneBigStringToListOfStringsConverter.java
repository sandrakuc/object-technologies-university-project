package com.toprojekt.gets;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public enum OneBigStringToListOfStringsConverter {
    INSTANCE;

    public List<String> convert(String tokenizableString){
        List<String> strings = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(tokenizableString, ",");
        while (st.hasMoreTokens()){
            strings.add(st.nextToken());
        }
        return strings;
    }
}
