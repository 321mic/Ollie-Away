package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountListTest {
    private AccountList accountList;
    private Account account1;
    private Account account2;

    //TODO: getAccounts(), notEqualToAny(), matchAccount(), getAccount(name, pass), getAccount(account) !!!

    @BeforeEach
    void runBefore() {
        accountList = new AccountList();
        account1 = new Account("Mickey", "Minie");
        account2 = new Account("BlaBlaBla", "eee");
    }

    @Test
    void testAddAccountOneAccount() {
        accountList.addAccount(account1);
        assertEquals(account1, accountList.getSpecificAccount(0));
        assertNotEquals(account2, accountList.getSpecificAccount(0));
    }

    @Test
    void testAddAccountMultiAccounts() {
        accountList.addAccount(account1);
        accountList.addAccount(account2);
        assertEquals(account1, accountList.getSpecificAccount(0));
        assertEquals(account2, accountList.getSpecificAccount(1));
    }

    @Test
    void testIsEmpty() {
        assertTrue(accountList.isEmpty());
        accountList.addAccount(account1);
        assertFalse(accountList.isEmpty());
        accountList.addAccount(account2);
        assertFalse(accountList.isEmpty());
    }

    @Test
    void testGetNumAccounts() {
        assertEquals(0, accountList.getNumAccounts());
        accountList.addAccount(account1);
        assertEquals(1, accountList.getNumAccounts());
        accountList.addAccount(account2);
        assertEquals(2, accountList.getNumAccounts());
    }

    @Test
    void testPrintList() {
        assertEquals("", accountList.printList());

        accountList.addAccount(account1);
        String acc1 = "\nAccount: 1, Username: Mickey, Password: Minie";
        assertEquals(acc1, accountList.printList());

        accountList.addAccount(account2);
        String acc2 = "\nAccount: 2, Username: BlaBlaBla, Password: eee";
        assertEquals(acc1 + acc2, accountList.printList());
    }
}
