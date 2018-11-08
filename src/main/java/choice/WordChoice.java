package choice;

public abstract class WordChoice {
    public String chosenWord;
    public boolean negativeMatch = false;
    public boolean caseSensitive = true;

    public abstract Boolean match(String word);
}
