package eighth.part2;

import eighth.part1.HauntedWasteland;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class HauntedWasteland2 extends HauntedWasteland {

    public HauntedWasteland2(String input) {
        super(input);
    }


    public void setData() {
        super.setData();
        for (String key : nodes.keySet()) {
            if (key.endsWith("A")) {
                startNodes.add(key);
            }
        }
    }

    @Override
    public long getAmountOfMoves() {
        setData();
        AtomicLong amount = new AtomicLong();
        while (true) {
            Map<Long, Set<String>> tmp1 = new HashMap<>();
            long finalAmount = amount.get();
            startNodes = startNodes.parallelStream().map(node -> {
                for (int i = 0; i < 10_000_000; i++) {
                    for (char c : route) {
                        long amountTmp = finalAmount;
                        if (node.endsWith("Z")) {
                            if (tmp1.get(amountTmp) == null) {
                                Set<String> tmp = new HashSet<>(List.of(node));
                                tmp1.put(amountTmp, tmp);
                            } else {
                                tmp1.get(amountTmp).add(node);
                            }
                        }
                        if (c == 'L') {
                            node = nodes.get(node).get(0);
                        } else {
                            node = nodes.get(node).get(1);
                        }
                        amountTmp++;
                        if (amountTmp > 100_000_000) {
                            amount.addAndGet(amountTmp);
                            throw new RuntimeException();
                        }
                    }
                }
                return node;
            }).collect(Collectors.toSet());
            amount.addAndGet(route.length * 10_000_000L);
            for (long key : tmp1.keySet()) {
                if (tmp1.get(key).size() == 6) {
                    return key;
                }
            }
        }
    }
}
