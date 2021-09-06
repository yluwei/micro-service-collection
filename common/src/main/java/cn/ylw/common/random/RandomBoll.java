package cn.ylw.common.random;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 随机
 *
 * @author yanluwei
 * @date 2021/9/6
 */
public class RandomBoll {
    private static String doubleColorBoll(int range, int total) {
        long current = LocalDate.now(ZoneOffset.of("+8")).atStartOfDay().toEpochSecond(ZoneOffset.of("+8")) * 1000;
        long factor = current % (long) Integer.MAX_VALUE;
        Map<Integer, Integer> front = new HashMap<>();
        int i = 0;
        Random random = new Random();
        do {
            int base = random.nextInt(10000000);
            int boll = ((base + (int) factor) % range) + 1;
            Integer first = front.putIfAbsent(boll, 1);
            if (first != null) {
                front.computeIfPresent(boll, (a, b) -> b + 1);
            }
            i++;
        } while (i < 10000);

        Set<Integer> set = new HashSet<>();

        List<Integer> list = front.values().stream().sorted().collect(Collectors.toList());
        for (int k = 0; k < list.size(); k++) {
            if (k >= total) {
                break;
            }
            Integer value = list.get(k);
            for (Map.Entry<Integer, Integer> entry : front.entrySet()) {
                if (value.equals(entry.getValue())) {
                    if (!set.contains(entry.getKey())) {
                        set.add(entry.getKey());
                        break;
                    }
                }
            }

        }

        return set.stream().map(Object::toString).collect(Collectors.joining(","));

    }

    private static void get(int num) {
        int i = 0;
        do {
            String front = doubleColorBoll(33, 6);
            String back = doubleColorBoll(16, 1);
            System.out.println(front + "+" + back);
            i++;
        } while (i < num);
    }

    public static void main(String[] args) {
        get(4);
    }
}
