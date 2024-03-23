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
                String[] arrayOfStrings = {"linked1", "linked2", "linked3"};
                StackOfStrings myStackOfStrings = new StackOfStrings();
                for(String str : arrayOfStrings){
                    myStackOfStrings.push(str);
                }
                myStackOfStrings.printAsArray();

                String[] arrayOfStrings2 = {"arr1", "arr2", "arr3"};
                StackOfStringsArray myStackOfStringsArray = new StackOfStringsArray(3);
                for(String str : arrayOfStrings2){
                    myStackOfStringsArray.push(str);
                }
                myStackOfStringsArray.printAsArray();
            }
        }
    }
}
