package com.mahallem.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingPatternLayout extends PatternLayout {


    private String patternsProperty;

    public String getPatternsProperty() {
        return patternsProperty;
    }

    public void setPatternsProperty(String patternsProperty) {
        this.patternsProperty = patternsProperty;
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);

        if (patternsProperty != null) {
            String[] patterns = patternsProperty.split("\\|");
            for (int i = 0; i < patterns.length; i++) {
                Pattern pattern = Pattern.compile(patterns[i]);

                Matcher matcher = pattern.matcher(event.getMessage());
                if (matcher.find()) {
                    message = matcher.replaceAll("********************");
                }
            }
        } else {

        }

        return message;
    }

}