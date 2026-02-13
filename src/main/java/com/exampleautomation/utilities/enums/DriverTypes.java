package com.exampleautomation.utilities.enums;

import org.openqa.selenium.WebDriver;

public enum DriverTypes {
    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    EDGE("edge");

    String text;

    DriverTypes(String text) {
        this.text = text;
    }

    public static DriverTypes fromString(String s){
        for(DriverTypes type : DriverTypes.values()){
            if (type.text.equalsIgnoreCase(s)){
                return type;
            }
        }
        throw new IllegalArgumentException("No driver found for: " + s);
    }
}
