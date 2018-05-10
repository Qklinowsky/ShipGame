package com.kondzio.ships;

import java.util.ArrayList;
import java.util.List;

public class ChoiceTranslator {
    public Point translate(String string) {
        String xChoice = string.substring(0, 1);
        String yChoice = string.substring(1, string.length());

        int xValue = getAlphabetOrder(xChoice) - 1;
        int yValue = Integer.parseInt(yChoice) - 1;

        return Point.point(xValue,yValue);

    }

    public int getAlphabetOrder(String string) {
        string = string.toLowerCase();
        int temp = (int) string.charAt(0);
        int temp_integer = 96; //for lower case
        if (temp <= 122 & temp >= 97) {
            return temp - temp_integer;
        } else {
            throw new IllegalArgumentException(string);
        }
    }

    public String[] createRowHeaders(int size){
        List<String> rowHeaders = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String s = Character.toString((char) (97 + i));
            rowHeaders.add(s);
        }
        return rowHeaders.toArray(new String[]{});
    }
    public String[] createColumnHeaders(int size){
        List<String> columnHeaders = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            columnHeaders.add(i+ "");
        }
        return columnHeaders.toArray(new String[]{});
    }

}
