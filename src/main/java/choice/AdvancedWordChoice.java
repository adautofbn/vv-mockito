package choice;

public class AdvancedWordChoice extends WordChoice {

    private static String CASE_SENSITIVE = "-cs";
    private static String CASE_INSENSITIVE = "-ci";
    private static String NEGATIVE = "-n";

    public AdvancedWordChoice(String[] inputs) {
        this.chosenWord = inputs[0];
        String firstConfig = inputs[1];
        checkValidConfig(firstConfig);

        if (firstConfig.equals(CASE_SENSITIVE) || firstConfig.equals(CASE_INSENSITIVE)) {
            this.caseSensitive = firstConfig.equals(CASE_SENSITIVE);

            if (inputs.length > 2) {
                String secondConfig = inputs[2];
                checkValidConfig(secondConfig);
                this.negativeMatch = true;
            }
        } else {
            this.negativeMatch = true;

            if (inputs.length > 2) {
                String secondConfig = inputs[2];
                checkValidConfig(secondConfig);
                this.caseSensitive = firstConfig.equals(CASE_SENSITIVE);
            }
        }
    }

    private void checkValidConfig(String configInput) {
        if (CASE_SENSITIVE.equals(configInput) |
                CASE_INSENSITIVE.equals(configInput) |
                NEGATIVE.equals(configInput)) {
            // do nothing
        } else {
            System.out.println("Wrong configuration");
            System.exit(1);
        }
    }

    @Override
    public Boolean match(String word) {
        String transformedWordChoice = caseSensitive ? chosenWord : chosenWord.toLowerCase();

        if (negativeMatch) {
            return !transformedWordChoice.equals(word);
        } else {
            return transformedWordChoice.equals(word);
        }
    }

    public boolean Equals(AdvancedWordChoice other) {
        return this.chosenWord == other.chosenWord;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
