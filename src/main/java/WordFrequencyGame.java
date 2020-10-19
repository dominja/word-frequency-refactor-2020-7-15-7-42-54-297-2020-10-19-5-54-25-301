import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputStr) {


        if (inputStr.split(WHITE_SPACES).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(WHITE_SPACES);

                List<WordInfo> inputList = new ArrayList<>();
                for (String word : words) {
                    WordInfo input = new WordInfo(word, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map = getListMap(inputList);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : inputList) {
                    String wordLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
                    joiner.add(wordLine);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
