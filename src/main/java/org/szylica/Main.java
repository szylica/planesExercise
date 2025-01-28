package org.szylica;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        StringBuilder input = MainLogic.getInput();


        MainLogic.validateInput(input);
        System.out.println("Your output:");
        List<Long> output = MainLogic.interpretInput(input);

        for (Long day : output) {
            System.out.println(day);
        }
    }
}

