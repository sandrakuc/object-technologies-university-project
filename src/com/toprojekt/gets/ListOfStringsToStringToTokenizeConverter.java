package com.toprojekt.gets;

import java.util.List;

public enum ListOfStringsToStringToTokenizeConverter {
    INSTANCE;

    public String convert(List<String> list){
        String stringToTokenize = "";
        for(int i = 0; i < list.size(); i++){
            stringToTokenize += list.get(i);
            if(i != list.size()-1){
                stringToTokenize += ",";
            }
        }
        return stringToTokenize;
    }
}
