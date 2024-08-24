package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account3 = new Account("abc", "abcd");
    private Account account4 = new Account("123", "4567");
    private Account account1;
    private Account account2;

    //TODO: ItemList: addItem(), itemIsEmpty(), getSpecificItem(), getNumItems(), printList()
    //TODO: AllTimes: addTime(), timesIsEmpty(), getNumTime(), bestTime(), getSpecificTime()

    //TODO: test equals() and hashCode()?, test equalsMatch

    @Test
    void testId() {
        assertEquals(21, account3.getId());
        assertEquals(22, account4.getId());
        // id = 11 and 12 because AccountListTest runs first
        // which creates more Accounts and therefore adds on to the ids
    }

    @BeforeEach
    void runBefore() {
        account1 = new Account("Mickey", "Minie");
        account2 = new Account("Blablabla", "123456");
    }

    @Test
    void testConstructor() {
        // can't test id since it keeps increasing and never resetting in every new AddAccount instance
        assertEquals("Mickey", account1.getName());
        assertEquals("Minie", account1.getPassword());

        assertEquals("Blablabla", account2.getName());
        assertEquals("123456", account2.getPassword());
    }
}
