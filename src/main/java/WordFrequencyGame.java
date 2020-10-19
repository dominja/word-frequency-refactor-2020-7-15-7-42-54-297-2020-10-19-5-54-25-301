import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String stringOfWords) {

        try {
            List<WordInfo> wordInfoList = addFrequencyToWordInfo(stringOfWords);
            List<WordInfo> wordInfos = sortMapToDescending(wordInfoList);
            return buildWordInfo(wordInfos);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> sortMapToDescending(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .sorted((wordInfo1, wordInfo2) ->
                        wordInfo2.getWordCount() - wordInfo1.getWordCount())
                .collect(Collectors.toList());
    }

    private List<WordInfo> addFrequencyToWordInfo(String stringOfWords) {
        Map<String, Long> wordFrequencyMap = calculateFrequency(stringOfWords);
        return wordFrequencyMap.keySet().stream().map(word ->
                new WordInfo(word, wordFrequencyMap.get(word).intValue()))
                .collect(Collectors.toList());
    }

    private Map<String, Long> calculateFrequency(String stringOfWords) {
        List<String> wordList = Arrays.asList(stringOfWords.split(WHITE_SPACES));
        return wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private String buildWordInfo(List<WordInfo> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : inputList) {
            String wordLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
            joiner.add(wordLine);
        }
        return joiner.toString();
    }
}
