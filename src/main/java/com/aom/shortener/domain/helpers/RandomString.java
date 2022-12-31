package com.aom.shortener.domain.helpers;

public class RandomString {

    public static String random(int size)
    {
        String AlphaNumericString = "0123456789abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
