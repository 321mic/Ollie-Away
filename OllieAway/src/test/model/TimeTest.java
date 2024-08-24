package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    private Time time1;
    private Time time2;

    @BeforeEach
    void runBefore() {
        time1 = new Time(60000);
        time2 = new Time(30000);
    }

    @Test
    void testConstructor() {
        assertEquals(60, time1.getTime());
        assertEquals(30, time2.getTime());
    }
}
