import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String stringOfWords) {

        try {
            List<WordInfo> wordInfoList = calculateFrequency(stringOfWords);
            Map<String, List<WordInfo>> wordInfoMap = getListMap(wordInfoList);

            List<WordInfo> wordInfos = new ArrayList<>();
            for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
                WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                wordInfos.add(input);
            }
            wordInfoList = wordInfos;
            wordInfoList.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());

            return buildWordInfo(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> calculateFrequency(String stringOfWords) {
        List<String> wordList = Arrays.asList(stringOfWords.split(WHITE_SPACES));
        return wordList.stream().map(word -> new WordInfo(word,1)).collect(Collectors.toList());
    }

    private String buildWordInfo(List<WordInfo> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : inputList) {
            String wordLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
            joiner.add(wordLine);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfos = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordInfos.containsKey(wordInfo.getValue())) {
                List<WordInfo> wordDetails = new ArrayList<>();
                wordDetails.add(wordInfo);
                wordInfos.put(wordInfo.getValue(), wordDetails);
            } else {
                wordInfos.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordInfos;
    }
}
