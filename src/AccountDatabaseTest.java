import org.junit.Test;
import project2.*;

import static org.junit.Assert.*;

public class AccountDatabaseTest {

    @Test
    // Test 1: Open a project2.Savings account normally
    // Test 2: Open a project2.Checking account normally
    // Test 3: Open a project2.CollegeChecking account normally
    // Test 4: Open a Money Market project2.Savings account normally
    // Test 5: Reopen a closed account
    // Test 6: Open an account when there is already the same type in database
    // Test 7: Open a checking account when there is already a college checking account in the database
    // Test 8: Open a college checking account when there is already a checking account in the database
    public void openChecking() {
        AccountDatabase accountDB = new AccountDatabase();
        assertTrue(accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766)));
    }

    @Test
    public void openSavings(){
        AccountDatabase accountDB = new AccountDatabase();
        assertTrue(accountDB.open(new Savings(new Profile("Leonard", "Fuller", new Date("1/15/1987")), 450, 1)));
    }

    @Test
    public void openCollegeChecking(){
        AccountDatabase accountDatabase = new AccountDatabase();
        CollegeChecking collegeChecking = new CollegeChecking(new Profile("April", "March", new Date("1/15/1987")), 950,1);
        assertTrue(accountDatabase.open(collegeChecking));
    }

    @Test
    public void openMoneyMarket(){
        AccountDatabase accountDB = new AccountDatabase();
        assertTrue(accountDB.open(new MoneyMarket(new Profile("Jane", "Martin", new Date("1/15/1987")), 450)));
    }

    @Test
    public void openClosedAccount(){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        accountDB.close(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        assertFalse(accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 2000)));
    }

    @Test
    public void openSameTypeAccount(){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        assertFalse(accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 2000)));
    }

    @Test
    public void openCheckingAccountCollegeChecking(){
        AccountDatabase accountDB = new AccountDatabase();
        CollegeChecking collegeChecking = new CollegeChecking(new Profile("April", "March", new Date("1/15/1987")), 950,1);
        accountDB.open(collegeChecking);
        assertFalse(accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 1766)));
    }

    @Test
    public void openCollegeCheckingAccountChecking(){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        CollegeChecking collegeChecking = new CollegeChecking(new Profile("John", "Doe", new Date("2/19/1990")), 2000,1);
        assertFalse(accountDB.open(collegeChecking));
    }

    @Test
    public void closeChecking() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Checking checking = new Checking(new Profile("April", "March", new Date("1/15/1987")), 950);
        accountDatabase.open(checking);
        assertTrue(accountDatabase.close(checking));
    }

    @Test
    public void closeCollegeChecking() {
        AccountDatabase accountDatabase = new AccountDatabase();
        CollegeChecking collegeChecking = new CollegeChecking(new Profile("April", "March", new Date("1/15/1987")), 950,1);
        accountDatabase.open(collegeChecking);
        assertTrue(accountDatabase.close(collegeChecking));
    }

    @Test
    public void closeSavings() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Savings savings = new Savings(new Profile("April", "March", new Date("1/15/1987")), 950,0);
        accountDatabase.open(savings);
        assertTrue(accountDatabase.close(savings));
    }

    @Test
    public void closeMoneyMarket() {
        AccountDatabase accountDatabase = new AccountDatabase();
        MoneyMarket moneyMarket = new MoneyMarket(new Profile("April", "March", new Date("1/15/1987")), 950);
        accountDatabase.open(moneyMarket);
        assertTrue(accountDatabase.close(moneyMarket));
    }

    @Test
    public void close_already_closed_account() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Checking checking = new Checking(new Profile("April", "March", new Date("1/15/1987")), 950);
        accountDatabase.open(checking);
        accountDatabase.close(checking);
        assertFalse(accountDatabase.close(checking));
    }

    @Test
    public void close_nonexistent_account() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Checking checking = new Checking(new Profile("April", "March", new Date("1/15/1987")), 950);
        accountDatabase.open(checking);
        CollegeChecking collegeChecking = new CollegeChecking(new Profile("April", "March", new Date("1/15/1987")), 950,2);
        assertFalse(accountDatabase.close(collegeChecking));
    }
}