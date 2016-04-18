package com.andrespedes.ocpjp7.localization;

import java.util.Locale;

/**
 * Created by Andres on 17/04/2016.
 */
public class ExampleLocalBuilder {

    public void buildLocales(){
        Locale l = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        Locale l2 = Locale.forLanguageTag("en-US");
        Locale l3 = Locale.GERMAN;
        Locale l4 = Locale.getDefault();
        Locale l5 = new Locale("fr", "CA");

        System.out.println(l.toString());
        System.out.println(l2.toString());
        System.out.println(l3.toString());
        System.out.println(l4.toString());
        System.out.println(l5.toString());

    }

    public static void main(String[] args) {
        new ExampleLocalBuilder().buildLocales();
    }
}
