public class WordNumber implements Comparable<WordNumber> {
    private String word;
    private double number;

    public WordNumber(String word, double number) {
        this.word = word;
        this.number = number;
    }

    @Override
    public int compareTo(WordNumber other) {
        if (this.number < other.number) {
            return 1;
        } else if (this.number > other.number) {
            return -1;
        }
        return this.word.compareTo(other.word);
    }

    public String getWord() {
        return word;
    }

    public double getNumber() {
        return number;
    }

}
