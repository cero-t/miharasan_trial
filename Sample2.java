import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Sample2 {
    public static void main(String[] args) {
        new Sample2().run();
    }

    void run() {
        IntStream.rangeClosed(0, 9)
                .mapToObj(h -> Map.of("H", h))
                .filter(this::condition1)
                .flatMap(n -> newArray(n, this::condition2, "E", "J", "I").stream())
                .flatMap(n -> newArray(n, this::condition3, "A", "F", "G").stream())
                .flatMap(n -> newArray(n, this::condition4, "C").stream())
                .flatMap(n -> newArray(n, this::condition5, "D", "B").stream())
                .filter(this::condition6)
                .filter(this::condition7)
                .filter(this::condition8)
                .filter(this::condition9)
                .filter(this::condition10)
                .filter(this::condition11)
                .filter(this::condition12)
                .forEach(this::confirm);
    }

    void confirm(Map<String, Integer> numbers) {
        long x = toLong(numbers, "F", "D", "C", "A", "J", "H");
        System.out.println("FDCAJH=" + x);

        long y = toLong(numbers, "I", "B", "C", "F", "E", "H");
        System.out.println("IBCFEH=" + y);

        long z = toLong(numbers, "F", "B", "A", "E", "C", "I", "I", "J", "E", "G", "I", "H");
        System.out.println("FBAECIIJEGIH=" + z);

        System.out.println(x * y);
    }

    long toLong(Map<String, Integer> numbers, String... digit) {
        long num = 0;
        for (int i = 0; i < digit.length; i++) {
            num += numbers.get(digit[i]) * (long) Math.pow(10, digit.length - i - 1);
        }
        return num;
    }

    int f1(Map<String, Integer> n) {
        return n.get("H") * n.get("H");
    }

    int f2(Map<String, Integer> n) {
        return f1(n) / 10 + n.get("J") * n.get("H") + n.get("H") * n.get("E");
    }

    int f3(Map<String, Integer> n) {
        return f2(n) / 10 + n.get("A") * n.get("H") + n.get("J") * n.get("E") + n.get("H") * n.get("F");
    }

    int f4(Map<String, Integer> n) {
        return f3(n) / 10 + n.get("C") * n.get("H") + n.get("A") * n.get("E") + n.get("J") * n.get("F") + n.get("H") * n.get("C");
    }

    int f5(Map<String, Integer> n) {
        return f4(n) / 10 + n.get("D") * n.get("H") + n.get("C") * n.get("E") + n.get("A") * n.get("F") + n.get("J") * n.get("C") + n.get("H") * n.get("B");
    }

    int f6(Map<String, Integer> n) {
        return f5(n) / 10 + n.get("F") * n.get("H") + n.get("D") * n.get("E") + n.get("C") * n.get("F") + n.get("A") * n.get("C") + n.get("J") * n.get("B") + n.get("H") * n.get("I");
    }

    int f7(Map<String, Integer> n) {
        return f6(n) / 10 + n.get("F") * n.get("E") + n.get("D") * n.get("F") + n.get("C") * n.get("C") + n.get("A") * n.get("B") + n.get("J") * n.get("I");
    }

    int f8(Map<String, Integer> n) {
        return f7(n) / 10 + n.get("F") * n.get("F") + n.get("D") * n.get("C") + n.get("C") * n.get("B") + n.get("A") * n.get("I");
    }

    int f9(Map<String, Integer> n) {
        return f8(n) / 10 + n.get("F") * n.get("C") + n.get("D") * n.get("B") + n.get("C") * n.get("I");
    }

    int f10(Map<String, Integer> n) {
        return f9(n) / 10 + n.get("F") * n.get("B") + n.get("D") * n.get("I");
    }

    int f11(Map<String, Integer> n) {
        return f10(n) / 10 + n.get("F") * n.get("I");
    }

    boolean condition1(Map<String, Integer> numbers) {
        return f1(numbers) % 10 == numbers.get("H");
    }

    boolean condition2(Map<String, Integer> n) {
        return f2(n) % 10 == n.get("I");
    }

    boolean condition3(Map<String, Integer> n) {
        return f3(n) % 10 == n.get("G");
    }

    boolean condition4(Map<String, Integer> n) {
        return f4(n) % 10 == n.get("E");
    }

    boolean condition5(Map<String, Integer> n) {
        return f5(n) % 10 == n.get("J");
    }

    boolean condition6(Map<String, Integer> n) {
        return f6(n) % 10 == n.get("I");
    }

    boolean condition7(Map<String, Integer> n) {
        return f7(n) % 10 == n.get("I");
    }

    boolean condition8(Map<String, Integer> n) {
        return f8(n) % 10 == n.get("C");
    }

    boolean condition9(Map<String, Integer> n) {
        return f9(n) % 10 == n.get("E");
    }

    boolean condition10(Map<String, Integer> n) {
        return f10(n) % 10 == n.get("A");
    }

    boolean condition11(Map<String, Integer> n) {
        return f11(n) % 10 == n.get("B");
    }

    boolean condition12(Map<String, Integer> n) {
        return f11(n) / 10 == n.get("F");
    }

    List<Map<String, Integer>> newArray(Map<String, Integer> numbers, Function<Map<String, Integer>, Boolean> judge, String... newLetters) {
        List<Map<String, Integer>> arrays = new ArrayList<>();

        for (int x = 0; x < Math.pow(10, newLetters.length); x++) {
            Map<String, Integer> newNumbers = new HashMap<>(numbers);
            for (int i = 0; i < newLetters.length; i++) {
                newNumbers.put(newLetters[i], (x % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i));
            }

            if (noDuplication(newNumbers.values()) && judge.apply(newNumbers)) {
                arrays.add(newNumbers);
            }
        }

        return arrays;
    }

    boolean noDuplication(Collection<Integer> values) {
        Set<Integer> set = new HashSet<>(values);
        return set.size() == values.size();
    }
}
