package com.lardi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leo on 20.04.2016.
 */
public class Main {
    public static void main(String[] args){
        List<String> phoneNumbers = new ArrayList();

        phoneNumbers.add("+38.066.1234567");
        phoneNumbers.add("+38(066.1234567");
        phoneNumbers.add("(123)-456-7890");
        phoneNumbers.add("+38(123)4567890");

       // String regex = "^\\+38\\(\\[0-9]{4,6}.\\d{7}$";
        String regex = "^\\+38\\(\\d{3}\\)\\d{7}";

        Pattern pattern = Pattern.compile(regex);

        for(String email : phoneNumbers)
        {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
        }
    }
}
