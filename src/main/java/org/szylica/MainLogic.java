package org.szylica;

import java.util.*;

public class MainLogic {



    //Takes user input until user types "stop" or press Ctrl+D/Ctrl+Z
    public static StringBuilder getInput() {

        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        System.out.println("Paste the text and press Enter. Finish by pressing Ctrl+D (Unix) or Ctrl+Z (Windows), or by typing \"stop\" on a new line:");

        int lineCount = 0;


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                continue;
            }
            if (line.equalsIgnoreCase("Stop")){
                break;
            }

            input.append(line).append("\n");
            lineCount++;

        }

        return input;
    }

    //Validates first two lines of input
    public static void validateInput(StringBuilder input) {

        String text = input.toString();
        String[] lines = text.split("\n");

        //check if first line contain exactly two integer numbers
        String[] firstLineArray = lines[0].split(" ");
        String[] secondLineArray = lines[1].split(" ");

        //check if first line contain exactly two integer numbers
        class firstLineValidation{
            public static int numberOfTraces = 0;
            public static int numberOfCommands = 0;
            public void validate(){

                if (firstLineArray.length != 2) {
                    throw new InvalidInputException("Invalid input. There is more than two numbers in first line.\nPlease enter two integer numbers in first line!");
                }

                try{

                    numberOfTraces = Integer.parseInt(firstLineArray[0]);
                    numberOfCommands = Integer.parseInt(firstLineArray[1]);

                } catch (NumberFormatException e){
                    throw new RuntimeException(e);
                }

                if (numberOfTraces < 1 || numberOfTraces > 10000000 || numberOfCommands < 1 || numberOfCommands > 10000000) {
                    throw new InvalidInputException("Each number must be between 1 and 10^7!");
                }

            }

        }


        firstLineValidation firstLineValidation = new firstLineValidation();
        firstLineValidation.validate();

        //check if second line contain exactly numberOfTraces integer numbers
        class secondLineValidation{

            public void validate(){

                if (secondLineArray.length != firstLineValidation.numberOfTraces) {
                    System.err.println("Invalid input. You provided different amount of traces than declared in first parameter in first line.\nPlease enter equal amount of traces as declared in first parameter in first line!");
                }
                for (String s : secondLineArray) {
                    int a = -1;

                    try {
                        a = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please enter only integer numbers!");
                        throw new RuntimeException(e);
                    }

                    if (a < 0 || a > 1000) {
                        throw new InvalidInputException("Invalid input. Each number must be between 0 and 1000!");
                    }

                }

            }

        }

        secondLineValidation secondLineValidation = new secondLineValidation();
        secondLineValidation.validate();


        //check if declared number of commands in first line is equal real number of commands
        if (lines.length-2 != firstLineValidation.numberOfCommands) {
            throw new InvalidInputException("Invalid input. You provided different amount of commands than declared in second parameter in first line.\nPlease enter equal amount of commands as declared in second parameter in first line!");
        }

    }

    //Interprets input
    public static List<Long> interpretInput(StringBuilder input) {

        String text = input.toString();
        String[] lines = text.split("\n");
        String[] secondLine = lines[1].split(" ");
        List<Long> results = new ArrayList<>();

        //Adding each plane to list
        for (String planeCapacity : secondLine) {
            AllPlanes.addPlane(new Plane(Integer.parseInt(planeCapacity)));
        }

        //Interpreting commands
        for (int i = 2; i < lines.length; i++) {

            String[] commandLine = lines[i].split(" ");

            switch (commandLine[0]) {
                case "Q" -> {

                    CommandValidators.validateQ(commandLine);

                    long sum = 0;

                    //Iterating through all planes from range(i, j)
                    for (int j = Integer.parseInt(commandLine[1])-1; j <= Integer.parseInt(commandLine[2])-1; j++) {
                        try {

                            //sum capacity from every plane
                            sum += AllPlanes.getPlane(j).sumCapacity(0, Long.parseLong(commandLine[3]));

                        } catch (NumberFormatException e) {
                            throw new InvalidInputException("Invalid input. Please enter only integer numbers after the prefix!");
                        }
                    }
                    results.add(sum);
                }

                case "C" -> {

                    CommandValidators.validateC(commandLine);
                    AllPlanes.getPlane(Integer.parseInt(commandLine[1])-1).clear(Long.parseLong(commandLine[2]));
                }

                case "P" -> {

                    CommandValidators.validateP(commandLine);
                    AllPlanes.getPlane(Integer.parseInt(commandLine[1])-1).update(Long.parseLong(commandLine[3]), Integer.parseInt(commandLine[2]));
                }

                case "A" -> {
                    CommandValidators.validateA(commandLine);
                    AllPlanes.getPlane(Integer.parseInt(commandLine[1])-1).update(Long.parseLong(commandLine[3]), Integer.parseInt(commandLine[2]));
                }

                default -> {
                    throw new InvalidInputException("Invalid prefix");
                }
            }


        }

        return results;

    }


}
