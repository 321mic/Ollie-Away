package persistence;

import model.Account;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AccountList accountList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccountList() {
        try {
            AccountList accountList = new AccountList();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyAccountList.json");
            accountList = reader.read();
            assertEquals(0, accountList.getNumAccounts());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccountList() {
        Account acccount1 = new Account("Selena", "12345");
        Account acccount2 = new Account("Seashell", "09876");

        try {
            AccountList accountList = new AccountList();
            accountList.addAccount(new Account("Selena", "12345"));
            accountList.addAccount(new Account("Seashell", "09876"));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralAccountList.json");
            accountList = reader.read();
            assertEquals(2, accountList.getNumAccounts());
            checkAccount("Selena", "12345", acccount1);
            checkAccount("Seashell", "09876", acccount2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
