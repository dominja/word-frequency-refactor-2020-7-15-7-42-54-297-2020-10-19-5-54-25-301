import java.util.Objects;

class WordInfo {
    private String value;
    private int count;

    WordInfo(String word, int count) {
        this.value = word;
        this.count = count;
    }

    String getValue() {
        return this.value;
    }

    int getWordCount() {
        return this.count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordInfo wordInfo = (WordInfo) o;
        return Objects.equals(value, wordInfo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
