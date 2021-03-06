import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WordFrequencyGame {

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
        return wordInfoList.stream().sorted
                (Comparator.comparingInt(WordInfo::getWordCount)
                        .reversed()).collect(toList());
    }

    private List<WordInfo> addFrequencyToWordInfo(String stringOfWords) {
        Map<String, Long> wordFrequencyMap = calculateFrequency(stringOfWords);
        return wordFrequencyMap.keySet().stream().map(word ->
                new WordInfo(word, wordFrequencyMap.get(word).intValue()))
                .collect(toList());
    }

    private Map<String, Long> calculateFrequency(String stringOfWords) {
        final String WHITE_SPACES = "\\s+";
        List<String> wordList = Arrays.asList(stringOfWords.split(WHITE_SPACES));
        return wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private String buildWordInfo(List<WordInfo> wordInfos) {
        return wordInfos.stream().map(wordInfo -> format("%s %d", wordInfo.getValue(),
                wordInfo.getWordCount()))
                .collect(joining("\n"));
    }
}
