package me.tomasito.bot;

public class IsInteger {
    public static Boolean isNumber (String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            System.out.println(d);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
