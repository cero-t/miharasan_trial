import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Sample {
    static int H = 0;
    static int J = 1;
    static int E = 2;
    static int I = 3;
    static int A = 4;
    static int F = 5;
    static int G = 6;
    static int C = 7;
    static int D = 8;
    static int B = 9;

    public static void main(String[] args) {
        new Sample().run();
    }

    void confirm(int[] n) {
        long x = toLong(n[F], n[D], n[C], n[A], n[J], n[H]);
        System.out.println("FDCAJH=" + x);
        long y = toLong(n[I], n[B], n[C], n[F], n[E], n[H]);
        System.out.println("IBCFEH=" + y);
        long z = toLong(n[F], n[B], n[A], n[E], n[C], n[I], n[I], n[J], n[E], n[G], n[I], n[H]);
        System.out.println("FBAECIIJEGIH=" + z);
        System.out.println(x * y);
    }

    void run() {
        IntStream.rangeClosed(0, 9)
                .mapToObj(h -> new int[]{h})
                .filter(this::condition1)
                .flatMap(h -> newArray(3, h, this::condition2).stream())
                .flatMap(h -> newArray(3, h, this::condition3).stream())
                .flatMap(h -> newArray(1, h, this::condition4).stream())
                .flatMap(h -> newArray(2, h, this::condition5).stream())
                .filter(this::condition6)
                .filter(this::condition7)
                .filter(this::condition8)
                .filter(this::condition9)
                .filter(this::condition10)
                .filter(this::condition11)
                .filter(this::condition12)
                .forEach(this::confirm);
    }

    int f1(int[] n) {
        return n[H] * n[H];
    }

    int f2(int[] n) {
        return f1(n) / 10 + n[J] * n[H] + n[H] * n[E];
    }

    int f3(int[] n) {
        return f2(n) / 10 + n[A] * n[H] + n[J] * n[E] + n[H] * n[F];
    }

    int f4(int[] n) {
        return f3(n) / 10 + n[C] * n[H] + n[A] * n[E] + n[J] * n[F] + n[H] * n[C];
    }

    int f5(int[] n) {
        return f4(n) / 10 + n[D] * n[H] + n[C] * n[E] + n[A] * n[F] + n[J] * n[C] + n[H] * n[B];
    }

    int f6(int[] n) {
        return f5(n) / 10 + n[F] * n[H] + n[D] * n[E] + n[C] * n[F] + n[A] * n[C] + n[J] * n[B] + n[H] * n[I];
    }

    int f7(int[] n) {
        return f6(n) / 10 + n[F] * n[E] + n[D] * n[F] + n[C] * n[C] + n[A] * n[B] + n[J] * n[I];
    }

    int f8(int[] n) {
        return f7(n) / 10 + n[F] * n[F] + n[D] * n[C] + n[C] * n[B] + n[A] * n[I];
    }

    int f9(int[] n) {
        return f8(n) / 10 + n[F] * n[C] + n[D] * n[B] + n[C] * n[I];
    }

    int f10(int[] n) {
        return f9(n) / 10 + n[F] * n[B] + n[D] * n[I];
    }

    int f11(int[] n) {
        return f10(n) / 10 + n[F] * n[I];
    }

    boolean condition1(int[] n) { // h
        return f1(n) % 10 == n[H];
    }

    boolean condition2(int[] n) { // e, j, i
        return f2(n) % 10 == n[I];
    }

    boolean condition3(int[] n) { // a, f, g
        return f3(n) % 10 == n[G];
    }

    boolean condition4(int[] n) { // c
        return f4(n) % 10 == n[E];
    }

    boolean condition5(int[] n) { // d, b
        return f5(n) % 10 == n[J];
    }

    boolean condition6(int[] n) {
        return f6(n) % 10 == n[I];
    }

    boolean condition7(int[] n) {
        return f7(n) % 10 == n[I];
    }

    boolean condition8(int[] n) {
        return f8(n) % 10 == n[C];
    }

    boolean condition9(int[] n) {
        return f9(n) % 10 == n[E];
    }

    boolean condition10(int[] n) {
        return f10(n) % 10 == n[A];
    }

    boolean condition11(int[] n) {
        return f11(n) % 10 == n[B];
    }

    boolean condition12(int[] n) {
        return f11(n) / 10 == n[F];
    }

    long toLong(int... digit) {
        long num = 0;
        for (int i = 0; i < digit.length; i++) {
            num += digit[i] * (long) Math.pow(10, digit.length - i - 1);
        }
        return num;
    }

    List<int[]> newArray(int n, int[] head, Function<int[], Boolean> judge) {
        List<int[]> arrays = new ArrayList<>();

        for (int x = 0; x < Math.pow(10, n); x++) {
            int[] array = Arrays.copyOf(head, n + head.length);
            for (int i = 0; i < n; i++) {
                array[i + head.length] = (x % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i);
            }

            if (hasDuplicate(array)) continue;

            if (judge.apply(array)) {
                arrays.add(array);
            }
        }

        return arrays;
    }

    boolean hasDuplicate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i] == array[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
