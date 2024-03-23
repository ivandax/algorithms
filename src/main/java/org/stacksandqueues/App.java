package org.stacksandqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;

public class App {
    public static void main(String[] args) {
        System.out.println("Stacks and queues");

        boolean shouldRunClient = true;

        while(shouldRunClient) {
            StdOut.println("Running client");

            String input = StdIn.readString();

            if(input.equals("exit")){
                System.out.println("Exiting...");
                shouldRunClient = false;
            }

            if(input.equals("stack")){
                String[] arrayOfStrings = {"test0", "test1", "test2"};
                StackOfStrings myStackOfStrings = new StackOfStrings();
                for(String str : arrayOfStrings){
                    myStackOfStrings.push(str);
                }
                myStackOfStrings.printAsArray();
            }
        }
    }
}
