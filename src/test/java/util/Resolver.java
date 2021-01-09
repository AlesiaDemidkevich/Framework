package util;

public class Resolver {
    public static String resolveTemplate(String template, String data){
        return String.format(template, data);
    }
}
