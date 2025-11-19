package com.exampleautomation.interfaces;

import com.exampleautomation.utilities.CommonUtilities;
import org.openqa.selenium.By;

public interface PageElements {

    By by();

    default By by(Object... args){
        if(args.length <1){
            return by();
        }
        return CommonUtilities.getDynamicLocator(by(), args);
    }

    /**
     * @return locator string as-is (without resolving any placeholders)
     */
    default String getLocatorString(){
        return CommonUtilities.getLocatorString(by());
    }

    /**
     * @param args arguments for placeholders
     *
     * @return locator string with placeholders resolved (substituted with arguments)
     */
    default String getLocatorString(Object... args){
        return CommonUtilities.getLocatorString(by(args));
    }
}
