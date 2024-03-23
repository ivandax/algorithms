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
                System.out.println("\n");

                String[] arrayOfStrings2 = {"arr1", "arr2", "arr3"};
                StackOfStringsArray myStackOfStringsArray = new StackOfStringsArray();
                myStackOfStringsArray.printAsArray();
                System.out.println("\n");
                for(String str : arrayOfStrings2){
                    myStackOfStringsArray.push(str);
                }
                myStackOfStringsArray.pop();
                myStackOfStringsArray.printAsArray();
                System.out.println("\n");

                myStackOfStringsArray.pop();
                myStackOfStringsArray.printAsArray();
                System.out.println("\n");
            }

            if(input.equals("queue")){
                String[] myStrings = {"q1", "q2", "q3"};
                QueueOfStrings queueOfStrings = new QueueOfStrings();
                for(String str : myStrings){
                    queueOfStrings.enqueue(str);
                }
                queueOfStrings.printAsArray();
                System.out.println("\n");
                queueOfStrings.dequeue();
                queueOfStrings.printAsArray();
                System.out.println("\n");
            }
        }
    }
}
