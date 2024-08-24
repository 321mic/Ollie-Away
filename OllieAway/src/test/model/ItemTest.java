package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void runBefore() {
        item1 = new Item("Speed Demon", "Speed Boost");
        item2 = new Item("Jump!", "Jump Boost");
    }

    @Test
    void testConstructor() {
        assertEquals("Speed Demon", item1.getName());
        assertEquals("Speed Boost", item1.getAbility());
        assertEquals("Jump!", item2.getName());
        assertEquals("Jump Boost", item2.getAbility());
    }
}
