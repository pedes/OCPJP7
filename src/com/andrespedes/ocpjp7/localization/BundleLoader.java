package com.andrespedes.ocpjp7.localization;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Created by Andres on 18/04/2016.
 */
public class BundleLoader {

    public static void main(String[] args) {

        // ResourceBundle class will use SystemMessages.properties file
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "com.andrespedes.ocpjp7.localization.Bundle", Locale.ENGLISH);
        System.out.println((resourceBundle.getString("first_name") + ": John"));
        System.out.println((resourceBundle.getString("last_name") + ": Smith"));

        // ResourceBundle class will use SystemMessages_es.properties file
        resourceBundle = ResourceBundle.getBundle("com.andrespedes.ocpjp7.localization.Bundle",
                Locale.forLanguageTag("es"));
        System.out.println((resourceBundle.getString("first_name") + ": Andres"));
        System.out.println((resourceBundle.getString("last_name") + ": Pedes"));

    }
}
