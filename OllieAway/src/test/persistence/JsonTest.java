package persistence;

import model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAccount(String name, String password, Account account) {
        assertEquals(name, account.getName());
        assertEquals(password, account.getPassword());
    }
}
