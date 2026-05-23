import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static double TF(String word, Text text) {
        int frequencyOfWord = text.getWords().findWordByBinarySearch(word);
        int totalWordsInTheYear = text.getWords().totalNumber();
        if (totalWordsInTheYear == 0) {
            return 0;
        }
        return (double) frequencyOfWord / totalWordsInTheYear;
    }

    public static double IDF(String word, ArrayList<Text> allTexts) {
        int yearsContainingTheWord = 0;
        for (Text text : allTexts) {
            if (text.getWords().findWordByBinarySearch(word) > 0) {
                yearsContainingTheWord++;
            }
        }
        int totalYears = allTexts.size();
        return Math.log((double) (totalYears + 1) / (yearsContainingTheWord + 1));
    }

    public static double keyness(String word, Text currentText, ArrayList<Text> allTexts) {
        int frequencyOfWordInTheYear = currentText.getWords().findWordByBinarySearch(word);
        int frequencyOfWordInOtherYears = 0;
        for (Text text : allTexts) {
            if (text != currentText) {
                frequencyOfWordInOtherYears += text.getWords().findWordByBinarySearch(word);
            }
        }
        return (double) frequencyOfWordInTheYear / (frequencyOfWordInOtherYears + 30);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String str = in.next();
        File file = new File(str);
        in = new Scanner(file);

        ArrayList<Text> texts = new ArrayList<Text>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.trim().isEmpty())
                continue;
            String[] words = line.split(" ");
            String yearStr = words[0].substring(6, 10);
            int year = Integer.parseInt(yearStr);

            Text currentText = null;
            for (Text text : texts) {
                if (text.getYear() == year) {
                    currentText = text;
                    break;
                }
            }

            if (currentText == null) {
                currentText = new Text(year);
                texts.add(currentText);
            }

            for (int i = 1; i < words.length; i++) {
                currentText.getWords().add(words[i]);
            }
        }

        texts.sort((a, b) -> Integer.compare(a.getYear(), b.getYear()));

        for (Text text : texts) {
            text.getWords().sortAndCount();
        }

        int topWhatNumberToShow = 5;

        for (Text text : texts) {
            System.out.println("Year: " + text.getYear());
            int uniqueWordNumber = text.getWords().size();

            WordNumber[] TFIDFNumbers = new WordNumber[uniqueWordNumber];
            WordNumber[] keynessNumbers = new WordNumber[uniqueWordNumber];

            for (int i = 0; i < uniqueWordNumber; i++) {
                String word = text.getWords().getWord(i);

                double TF = TF(word, text);
                double IDF = IDF(word, texts);
                double TFIDF = TF * IDF;
                double keyness = keyness(word, text, texts);

                TFIDFNumbers[i] = new WordNumber(word, TFIDF);
                keynessNumbers[i] = new WordNumber(word, keyness);
            }

            WordList.SortByQuickSort(TFIDFNumbers, 0, TFIDFNumbers.length - 1);
            WordList.SortByQuickSort(keynessNumbers, 0, keynessNumbers.length - 1);

            String format = "%d %-15s %.6f\n";

            System.out.println("Top " + topWhatNumberToShow + " TF-IDF:");
            for (int i = 0; i < Math.min(topWhatNumberToShow, TFIDFNumbers.length); i++) {
                System.out.printf(format, i + 1, TFIDFNumbers[i].getWord(),
                        TFIDFNumbers[i].getNumber());
            }

            System.out.println("Top " + topWhatNumberToShow + " keyness:");
            for (int i = 0; i < Math.min(topWhatNumberToShow, keynessNumbers.length); i++) {
                System.out.printf(format, i + 1, keynessNumbers[i].getWord(),
                        keynessNumbers[i].getNumber());
            }
        }

        in.close();
    }
}
