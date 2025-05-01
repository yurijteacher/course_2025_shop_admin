package ua.com.kneu.course.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {

    public boolean logicXLS(String file){
        String pattern = "[_A-Za-z0-9-]+\\.(xls)";
        Matcher matcher = Pattern.compile(pattern).matcher(file);
        return (matcher.matches()) ? true : false;
    }

    public boolean logicXLSX(String file){
        String pattern = "[A-Za-z0-9_-]+\\.(xlsx)";
        Matcher matcher = Pattern.compile(pattern).matcher(file);
        return (matcher.matches()) ? true : false;
    }

}