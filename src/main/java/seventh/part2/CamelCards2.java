package seventh.part2;

import seventh.part1.CamelCards;

import java.util.*;

public class CamelCards2 extends CamelCards {
    private static final Map<Character, Character> SUITS_CODE;

    static {
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

    }

    public CamelCards2(String input) {
        super(input);
    }

    @Override
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
            } else {
                if (tm1.get(5) != null) {
                    fiveOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(4) != null) {
                    fiveOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(3) != null && tm1.get(2) != null) {
                    fiveOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(3) != null) {
                    fourOfaKind.add(stringBuilder.toString());
                } else if (tm1.get(2) != null && tm1.get(2).size() == 2) {
                    if (tm1.get(2).contains('2')) {
                        fourOfaKind.add(stringBuilder.toString());
                    } else {
                        fullHouse.add(stringBuilder.toString());
                    }
                } else if (tm1.get(2) != null) {
                    threeOfaKind.add(stringBuilder.toString());
                } else {
                    onePair.add(stringBuilder.toString());
                }
            }
        }
    }
}
