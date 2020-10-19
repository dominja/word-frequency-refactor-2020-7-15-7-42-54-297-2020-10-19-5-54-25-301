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
            List<WordInfo> wordInfoList = calculateFrequency(stringOfWords);
            List<WordInfo> wordInfos = wordInfoList.stream()
                    .sorted((wordInfo1, wordInfo2) ->
                            wordInfo2.getWordCount() - wordInfo1.getWordCount())
                    .collect(Collectors.toList());
            return buildWordInfo(wordInfos);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> calculateFrequency(String stringOfWords) {
        List<String> wordList = Arrays.asList(stringOfWords.split(WHITE_SPACES));
        Map<String, Long> wordFrequencyList = wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return wordFrequencyList.keySet().stream().map(word -> new WordInfo(word, wordFrequencyList
                .get(word).intValue()))
                .collect(Collectors.toList());
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
