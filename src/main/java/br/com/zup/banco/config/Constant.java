package br.com.zup.banco.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {
    private static final String DATE_FORMAT_STRING= "dd/MM/yyyy";
    private static final SimpleDateFormat DATE_FORMAT= new SimpleDateFormat(DATE_FORMAT_STRING);

    public static String getDateFormatString() {
        return DATE_FORMAT_STRING;
    }

    public static SimpleDateFormat getDateFormat() {
        return DATE_FORMAT;
    }

}
