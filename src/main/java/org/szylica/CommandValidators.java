package org.szylica;

public class CommandValidators {


    //Helper class, which validate command inputs

    //Checks if time is between 0 and 10^11
    public static void timeValidation(long day) {
        if (day < 0 || day > Math.pow(10, 11)) {
            throw new InvalidInputException("Invalid input. Day have to be between 1 and 365");
        }
    }

    //Checks if command have 3 parameters
    public static void threeElementValidation(String[] commandLine) {
        if (commandLine.length != 4) {
            throw new InvalidInputException("Invalid input. Command have to have 3 parameters after prefix!");
        }
    }

    //Checks if command have 2 parameters
    public static void twoElementValidation(String[] commandLine) {
        if (commandLine.length != 3) {
            throw new InvalidInputException("Invalid input. Command have to have 2 parameters after prefix!");
        }
    }

    //Checks if capacity is between 0 and 1000
    public static void capacityValidation(int capacity) {
        if (capacity < 0 || capacity > 1000) {
            throw new InvalidInputException("Invalid input. Capacity have to be between 0 and 1000");
        }
    }

    //Checks if all parameters after prefix are numbers
    public static void isNumbersValidation(String[] commandLine) {

        for (int i = 1; i < commandLine.length; i++) {
            try {
                Integer.parseInt(commandLine[i]);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid input. Please enter only integer numbers after the prefix!");
            }
        }
    }

    //Checks if plane number is between 1 and number of planes
    public static void planeListRangeValidation(int plane) {
        if (plane < 1 || plane > AllPlanes.size()) {
            throw new InvalidInputException("Invalid input. Number have to be between 1 and " + AllPlanes.size());
        }
    }

    //Validation Q commands
    public static void validateQ(String[] QCommandLine){

        threeElementValidation(QCommandLine);
        isNumbersValidation(QCommandLine);
        planeListRangeValidation(Integer.parseInt(QCommandLine[1]));
        planeListRangeValidation(Integer.parseInt(QCommandLine[2]));

        if (Integer.parseInt(QCommandLine[1]) > Integer.parseInt(QCommandLine[2])) {
            throw new InvalidInputException("Invalid input in Q command. First number parameter have to be smaller or equal than second number parameter!");
        }
    }

    //Validation C commands
    public static void validateC(String[] CCommandLine){

        twoElementValidation(CCommandLine);
        isNumbersValidation(CCommandLine);
        timeValidation(Long.parseLong(CCommandLine[2]));
        planeListRangeValidation(Integer.parseInt(CCommandLine[1]));

    }

    //Validation P commands
    public static void validateP(String[] PCommandLine){

        threeElementValidation(PCommandLine);
        isNumbersValidation(PCommandLine);
        timeValidation(Long.parseLong(PCommandLine[3]));
        capacityValidation(Integer.parseInt(PCommandLine[2]));
        planeListRangeValidation(Integer.parseInt(PCommandLine[1]));

    }

    //Validation A commands
    public static void validateA(String[] ACommandLine){

        threeElementValidation(ACommandLine);
        isNumbersValidation(ACommandLine);
        timeValidation(Long.parseLong(ACommandLine[3]));
        capacityValidation(Integer.parseInt(ACommandLine[2]));
        planeListRangeValidation(Integer.parseInt(ACommandLine[1]));
    }
}
