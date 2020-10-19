import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputStr) {


        if (inputStr.split(WHITE_SPACES).length == 1) {
            return inputStr + " 1";
        } else {

            try {
                String[] words = inputStr.split(WHITE_SPACES);

                List<WordInfo> inputList = new ArrayList<>();
                for (String word : words) {
                    WordInfo input = new WordInfo(word, 1);
                    inputList.add(input);
                }

                Map<String, List<WordInfo>> wordInfoMap = getListMap(inputList);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    wordInfos.add(input);
                }
                inputList = wordInfos;
                inputList.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());

                return buildWordInfo(inputList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
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
