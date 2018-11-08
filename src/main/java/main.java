import choice.AdvancedWordChoice;
import choice.SimpleWordChoice;
import choice.WordChoice;
import counter.FileCounter;
import fileService.FileService;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

    public static void main(String args[]) throws FileNotFoundException {
        if (args.length == 1) {
            FileCounter fileCounter = new FileCounter(args[0], FileService.getInstance());
        }
        FileCounter fileCounter = new FileCounter("./src/main/resources/bourne.txt", FileService.getInstance());

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            WordChoice choice = parseInput(input);
            System.out.println("Number of words: " + fileCounter.count(choice));
        }
    }

    private static WordChoice parseInput(String input) {
        String[] inputs = input.split("\\s");

        if (inputs.length == 1) {
            return new SimpleWordChoice(inputs[0]);
        }

        if (inputs.length == 2 || inputs.length == 3) {
            return new AdvancedWordChoice(inputs);
        }

        return null;
    }
}
