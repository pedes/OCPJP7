package com.andrespedes.ocpjp7.threads;

import java.util.concurrent.Semaphore;

/**
 * Created by Andres on 07/04/2016.
 */
public class ATMRoom {

        public static void main(String []args) {
            // assume that only two ATM machines are available in the ATM room
            Semaphore machines = new Semaphore(1);

            // list of people waiting to access the machine
            new Person(machines, "Mickey");
            new Person(machines, "Donald");
            new Person(machines, "Tom");
            new Person(machines, "Jerry");
            new Person(machines, "Casper");
        }


}
