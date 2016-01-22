package com.andrespedes.ocpjp7.enhancements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andres on 21/01/2016.
 */
public class DiamondOperator {

    private List<Object> listOfAnyObjects;
    private List<Number> listOfNumber;
    private List<? super Number> listOfAnyNumber;


    public void init(){
        //Its not necessary to put generic type on declaration, it takes from reference type
        listOfAnyObjects = new ArrayList<>();

        listOfNumber = new ArrayList<>();
        //listOfNumber = new ArrayList<Integer>();, although Integer extends Number, generics aren't covariant

        listOfAnyNumber = new ArrayList<>();
        Long number = new Long(1); //with wildcard ?, can set Long that extends Number
        Integer numberB = new Integer(1); //with wildcard ?, can set Long that extends Number
        listOfAnyNumber.add(number);
        listOfAnyNumber.add(numberB);
    }

    public DiamondOperator(){
    }
}
