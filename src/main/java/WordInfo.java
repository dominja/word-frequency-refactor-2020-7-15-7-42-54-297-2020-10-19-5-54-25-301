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


}
