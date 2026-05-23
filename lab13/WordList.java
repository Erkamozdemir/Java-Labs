import java.util.ArrayList;

public class WordList {
    private ArrayList<String> wordsAL;
    private ArrayList<Integer> countOfTheWords;

    public WordList() {
        wordsAL = new ArrayList<String>();
        countOfTheWords = new ArrayList<Integer>();
    }

    public void add(String word) {
        wordsAL.add(word);
    }

    public void sortAndCount() {
        if (wordsAL.size() == 0) {
            return;
        }

        String[] arrayOfWords = wordsAL.toArray(new String[0]);
        SortByQuickSort(arrayOfWords, 0, arrayOfWords.length - 1);

        wordsAL.clear();
        countOfTheWords.clear();

        wordsAL.add(arrayOfWords[0]);
        int count = 1;
        for (int i = 1; i < arrayOfWords.length; i++) {
            if (arrayOfWords[i].equals(arrayOfWords[i - 1])) {
                count++;
            } else {
                countOfTheWords.add(count);
                wordsAL.add(arrayOfWords[i]);
                count = 1;
            }
        }
        countOfTheWords.add(count);
    }

    public static <Sorting extends Comparable<Sorting>> void SortByQuickSort(Sorting[] items, int lowest, int highest) {
        if (lowest < highest) {
            int pivot = part(items, lowest, highest);
            SortByQuickSort(items, lowest, pivot - 1);
            SortByQuickSort(items, pivot + 1, highest);
        }
    }

    private static <Sorting extends Comparable<Sorting>> int part(Sorting[] items, int lowest, int highest) {
        Sorting key = items[highest];
        int index = lowest - 1;
        for (int j = lowest; j < highest; j++) {
            if (items[j].compareTo(key) <= 0) {
                index++;
                Sorting tmp = items[index];
                items[index] = items[j];
                items[j] = tmp;
            }
        }
        Sorting tmp = items[index + 1];
        items[index + 1] = items[highest];
        items[highest] = tmp;
        return index + 1;
    }

    public int findWordByBinarySearch(String wordText) {
        int lowest = 0;
        int highest = wordsAL.size() - 1;
        while (lowest <= highest) {
            int middle = (lowest + highest) / 2;
            int compare = wordsAL.get(middle).compareTo(wordText);
            if (compare == 0) {
                return countOfTheWords.get(middle);
            } else if (compare < 0) {
                lowest = middle + 1;
            } else {
                highest = middle - 1;
            }
        }
        return 0;
    }

    public int size() {
        return wordsAL.size();
    }

    public String getWord(int index) {
        return wordsAL.get(index);
    }

    public int getWordCount(int index) {
        return countOfTheWords.get(index);
    }

    public int totalNumber() {
        int totalNumber = 0;
        for (int numberOfThatWord : countOfTheWords) {
            totalNumber += numberOfThatWord;
        }
        return totalNumber;
    }
}