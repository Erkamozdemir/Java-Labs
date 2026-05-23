public class Text {
    private int year;
    private WordList words;

    public Text(int year) {
        this.year = year;
        this.words = new WordList();
    }

    public int getYear() {
        return year;
    }

    public WordList getWords() {
        return words;
    }
}
