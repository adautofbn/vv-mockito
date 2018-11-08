package choice;

public class SimpleWordChoice extends WordChoice {

    public SimpleWordChoice(String word) {
        this.chosenWord = word;
    }

    @Override
    public Boolean match(String word) {
        return word.equals(chosenWord);
    }

    @Override
    public boolean equals(Object obj) {
        if (SimpleWordChoice.class == obj.getClass()) {
            return chosenWord.equals(((SimpleWordChoice) obj).chosenWord);
        }
        return false;
    }
}
