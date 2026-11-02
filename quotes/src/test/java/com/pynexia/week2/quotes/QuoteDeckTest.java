package com.pynexia.week2.quotes;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
public class QuoteDeckTest {
    @Test public void neverImmediatelyRepeatsWhenMultipleQuotesExist() {
        Random random = new Random(42); int current = 0;
        for (int i = 0; i < 1000; i++) { int next = QuoteDeck.next(current, 12, random); assertTrue(next >= 0 && next < 12); assertNotEquals(current, next); current = next; }
    }
    @Test public void handlesSingleItemAndInvalidCurrent() {
        assertEquals(0, QuoteDeck.next(0, 1, new Random(1)));
        int next = QuoteDeck.next(-1, 12, new Random(2)); assertTrue(next >= 0 && next < 12);
    }
    @Test(expected = IllegalArgumentException.class) public void rejectsEmptyCollection() { QuoteDeck.next(0, 0, new Random()); }
}