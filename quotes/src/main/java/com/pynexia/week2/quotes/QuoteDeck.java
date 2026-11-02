package com.pynexia.week2.quotes;
import java.util.Random;
public final class QuoteDeck {
    private QuoteDeck() {}
    public static int next(int current, int size, Random random) {
        if (size < 1) throw new IllegalArgumentException("The collection must not be empty.");
        if (size == 1) return 0;
        if (current < 0 || current >= size) return random.nextInt(size);
        int picked = random.nextInt(size - 1);
        return picked >= current ? picked + 1 : picked;
    }
}
