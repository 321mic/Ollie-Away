package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {
    private ItemList itemList;
    private Item item1;
    private Item item2;

    @BeforeEach
    void runBefore() {
        itemList = new ItemList();
        item1 = new Item("Speed Demon", "Speed Boost");
        item2 = new Item("Jump!", "Jump Boost");
    }

    @Test
    void testAddItemOneItem() {
        itemList.addItem(item1);
        assertEquals(item1, itemList.getSpecificItem(0));
        assertNotEquals(item2, itemList.getSpecificItem(0));
    }

    @Test
    void testAddItemMultiItems() {
        itemList.addItem(item1);
        itemList.addItem(item2);
        assertEquals(item1, itemList.getSpecificItem(0));
        assertEquals(item2, itemList.getSpecificItem(1));
    }

    @Test
    void testIsEmpty() {
        assertTrue(itemList.isEmpty());
        itemList.addItem(item1);
        assertFalse(itemList.isEmpty());
        itemList.addItem(item2);
        assertFalse(itemList.isEmpty());
    }

    @Test
    void testGetNumItems() {
        assertEquals(0, itemList.getNumItems());
        itemList.addItem(item1);
        assertEquals(1, itemList.getNumItems());
        itemList.addItem(item2);
        assertEquals(2, itemList.getNumItems());
    }

    @Test
    void testPrintList() {
        assertEquals("", itemList.printList());

        itemList.addItem(item1);
        String speedItem = "\n\tItem: Speed Demon, Ability: Speed Boost";
        assertEquals(speedItem, itemList.printList());

        itemList.addItem(item2);
        String jumpItem = "\n\tItem: Jump!, Ability: Jump Boost";
        assertEquals(speedItem + jumpItem, itemList.printList());
    }
}
