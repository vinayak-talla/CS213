
public class AccountDatabaseTest {

    public void isAccountClosedAlready() {
        Checking checking = new Checking(new Profile("Jane","Doe", new Date("1/2/2002")), 5000);
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.open(checking);
        //assertFalse(accountDatabase.close(checking));
    }


}
