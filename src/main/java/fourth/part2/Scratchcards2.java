package fourth.part2;

import fourth.part1.Scratchcards;

import java.util.*;

public class Scratchcards2 extends Scratchcards {
    private final Map<Integer, Integer> numberOfCopies;

    {
        numberOfCopies = new HashMap<>();
    }

    public Scratchcards2(String fileName) {
        super(fileName);
    }


    public int getSumOfAllCopies() {
        findMatchedCards();
        int sum = 0;
        for (int value : numberOfCopies.values()) {
            sum += value;
        }
        return sum;
    }

    public void findMatchedCards() {
        for (int i = 0; i < data.size(); i++) {
            String[] part1 = data.get(i).split("\\|")[0].split(":")[1].split(" ");
            String[] part2 = data.get(i).split("\\|")[1].split(" ");
            int amountOfMatches = getAmountOfMatches(part1, part2);
            for (int q = i + 1; q < i + 2 + amountOfMatches; q++) {
                fillNumberOfCopies(q);
            }
            int limit = numberOfCopies.get(i + 1);
            for (int c = 0; c < limit - 1; c++) {
                for (int q = i + 2; q < i + 2 + amountOfMatches; q++) {
                    fillNumberOfCopies(q);
                }
            }
        }
    }

    @Override
    protected int getAmountOfMatches(String[] part1, String[] part2) {
        int amount = 0;
        for (String p2 : part2) {
            for (String p1 : part1) {
                if (!p1.isEmpty() && p1.equals(p2)) {
                    amount++;
                }
            }
        }
        return amount;
    }

    private void fillNumberOfCopies(int cardNumber) {
        if (numberOfCopies.containsKey(cardNumber)) {
            numberOfCopies.put(cardNumber, numberOfCopies.get(cardNumber) + 1);
        } else {
            numberOfCopies.put(cardNumber, 1);
        }
    }
}
