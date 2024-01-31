package seventh.part1;

import workwithfile.FileReader;
import workwithfile.FileReaderImpl;

import java.util.*;

public class CamelCards {
    private static final FileReader FILE_READER;
    private static final Map<Character, Character> SUITS_CODE;
    protected List<String> data;
    private String fileName;
    private List<String> sortedCodes;
    protected Map<String, Long> cardBid;
    //---------------------------------------
    protected List<String> fiveOfaKind;
    protected List<String> fourOfaKind;
    protected List<String> fullHouse;
    protected List<String> threeOfaKind;
    protected List<String> twoPair;
    protected List<String> onePair;
    protected List<String> highCard;

    //--------------------------
    static {
        FILE_READER = new FileReaderImpl();
        SUITS_CODE = new HashMap<>() {
            {
                put('J', '2');
                put('2', '3');
                put('3', '4');
                put('4', '5');
                put('5', '6');
                put('6', '7');
                put('7', '8');
                put('8', '9');
                put('9', ':');
                put('T', ';');
                put('Q', '<');
                put('K', '=');
                put('A', '>');
            }
        };
    }

    {

        cardBid = new HashMap<>();
        sortedCodes = new ArrayList<>();
        fiveOfaKind = new ArrayList<>();
        fourOfaKind = new ArrayList<>();
        fullHouse = new ArrayList<>();
        threeOfaKind = new ArrayList<>();
        twoPair = new ArrayList<>();
        onePair = new ArrayList<>();
        highCard = new ArrayList<>();
    }

    public CamelCards(String fileName) {
        this.fileName = fileName;
        setData();
    }

    private void setData() {
        data = FILE_READER.read(fileName);
    }

    public void distributeCombinations() {
        for (String s : data) {
            String card = s.split(" ")[0];
            StringBuilder stringBuilder = new StringBuilder();
            Map<Character, Integer> tmp = new HashMap<>();
            for (char c : card.toCharArray()) {
                stringBuilder.append(SUITS_CODE.get(c));
                tmp.merge(SUITS_CODE.get(c), 1, Integer::sum);
            }
            Map<Integer, List<Character>> tm1 = new HashMap<>();
            for (Map.Entry<Character, Integer> entry : tmp.entrySet()) {
                if (tm1.get(entry.getValue()) == null) {
                    List<Character> characters = new ArrayList<>();
                    characters.add(entry.getKey());
                    tm1.put(entry.getValue(), characters);
                } else {
                    tm1.get(entry.getValue()).add(entry.getKey());
                }

            }
            cardBid.put(stringBuilder.toString(),
                    Long.parseLong(s.split(" ")[1]));
            if (!stringBuilder.toString().contains("2")) {
                if (tm1.get(5) != null) {
                    fiveOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(4) != null) {
                    fourOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(3) != null && tm1.get(2) != null) {
                    fullHouse.add(stringBuilder.toString());
                } else if (tm1.get(3) != null) {
                    threeOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(2) != null && tm1.get(2).size() == 2) {
                    twoPair.add(stringBuilder.toString());
                } else if (tm1.get(2) != null) {
                    onePair.add(stringBuilder.toString());
                } else {
                    highCard.add(stringBuilder.toString());
                }
            }
        }
    }

    public void sortCards() {
        Collections.sort(highCard);
        Collections.sort(onePair);
        Collections.sort(twoPair);
        Collections.sort(threeOfaKind);
        Collections.sort(fullHouse);
        Collections.sort(fourOfaKind);
        Collections.sort(fiveOfaKind);

        sortedCodes.addAll(highCard);
        sortedCodes.addAll(onePair);
        sortedCodes.addAll(twoPair);
        sortedCodes.addAll(threeOfaKind);
        sortedCodes.addAll(fullHouse);
        sortedCodes.addAll(fourOfaKind);
        sortedCodes.addAll(fiveOfaKind);
    }


    public long getSumOfAllWins() {
        distributeCombinations();
        sortCards();
        long sum = 0;
        for (int i = 0; i < sortedCodes.size(); i++) {
            sum += (i + 1) * cardBid.get(sortedCodes.get(i));
        }
        return sum;
    }
}
