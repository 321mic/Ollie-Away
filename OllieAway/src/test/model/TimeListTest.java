package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeListTest {
    private TimeList allTimes;
    private Time time1;
    private Time time2;

    @BeforeEach
    void runBefore() {
        allTimes = new TimeList();
        time1 = new Time(40000);
        time2 = new Time(60000);
    }

    @Test
    void testAddTimeOneTime() {
        allTimes.addTime(time1);
        assertEquals(time1, allTimes.getSpecificTime(0));
        assertNotEquals(time2, allTimes.getSpecificTime(0));
    }

    @Test
    void testAddItemMultiItems() {
        allTimes.addTime(time1);
        allTimes.addTime(time2);
        assertEquals(time1, allTimes.getSpecificTime(0));
        assertEquals(time2, allTimes.getSpecificTime(1));
    }

    @Test
    void testIsEmpty() {
        assertTrue(allTimes.isEmpty());
        allTimes.addTime(time1);
        assertFalse(allTimes.isEmpty());
        allTimes.addTime(time2);
        assertFalse(allTimes.isEmpty());
    }

    @Test
    void testGetNumItems() {
        assertEquals(0, allTimes.getNumTime());
        allTimes.addTime(time1);
        assertEquals(1, allTimes.getNumTime());
        allTimes.addTime(time2);
        assertEquals(2, allTimes.getNumTime());
    }

    @Test
    void testBestTimeOneTime() {
        allTimes.addTime(time1);
        assertEquals(40, allTimes.bestTime());
    }

    @Test
    void testBestTimeFirstBiggerThanSecond() {
        allTimes.addTime(time2);
        assertEquals(60, allTimes.bestTime());
        allTimes.addTime(time1);
        assertEquals(60, allTimes.bestTime());
    }

    @Test
    void testBestTimeFirstSmallerThanSecond() {
        allTimes.addTime(time1);
        assertEquals(40, allTimes.bestTime());
        allTimes.addTime(time2);
        assertEquals(60, allTimes.bestTime());
    }
}
