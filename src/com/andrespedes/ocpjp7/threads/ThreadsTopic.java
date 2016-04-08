package com.andrespedes.ocpjp7.threads;

/**
 * Created by Andres on 28/01/2016.
 */
public class ThreadsTopic {

    public static void main (String[] args){
        // Default Options for a Thread
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority()); //Max priority is 10
        System.out.println(Thread.currentThread().getState());
    }
}
