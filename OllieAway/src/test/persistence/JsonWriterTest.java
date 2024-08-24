package persistence;

import model.Account;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    //TODO: test for write(account) !!!

    @Test
    void testWriterInvalidFile() {
        try {
            AccountList accountList = new AccountList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccountList() {
        try {
            AccountList accountList = new AccountList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccountList.json");
            accountList = reader.read();
            assertEquals(0, accountList.getNumAccounts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccountList() {
        try {
            AccountList accountList = new AccountList();
            accountList.addAccount(new Account("Blablabla", "hehehehe"));
            accountList.addAccount(new Account("fly", "ploop"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccountList.json");
            accountList = reader.read();
            assertEquals(2, accountList.getNumAccounts());
            checkAccount("Blablabla", "hehehehe", accountList.getSpecificAccount(0));
            checkAccount("fly", "ploop", accountList.getSpecificAccount(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
