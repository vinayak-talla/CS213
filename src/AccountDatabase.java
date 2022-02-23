import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class AccountDatabase {
    private Account[] accounts;
    private int numAcct;
    public static final int NOT_FOUND= -1;


    public AccountDatabase(){
        this.accounts = new Account[4];
        this.numAcct = 0;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public int getNumAcct(){
        return numAcct;
    }

    private int find(Account account){
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].equals(account)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow(){
        Account[] temp = new Account[accounts.length + 4];
        for(int i = 0; i < accounts.length; i++){
            temp[i] = accounts[i];
        }
        accounts = temp;
    }

    public boolean open(Account account){
        if(numAcct == accounts.length-1){
            grow();
        }
        if(find(account) == NOT_FOUND){
            accounts[numAcct] = account;
            numAcct++;
        }
        else{
            accounts[find(account)].setClosed(false);
            accounts[find(account)].setBalance(account.balance);
        }
        return true;

    }

    public boolean close(Account account){
        int indexOfAccount = find(account);
        accounts[indexOfAccount].setClosed(true);
        accounts[indexOfAccount].setBalance(0);

        return true;
    }

    public void deposit(Account account){
        accounts[find(account)].deposit(account.balance);
    }

    public boolean withdraw(Account account){
        accounts[find(account)].withdraw(account.balance);
        return true;
    }

    public void print(){
        System.out.println("*list of accounts in the database*");
        for(int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list*");

    }

    public void printByAccountType(){
        Account temp = null;
        int minIndex = 0;
        String minAccount = "";
        for(int i = 0; i < numAcct; i++) {
            minAccount = accounts[i].getType();
            for( int j = i + 1; j < numAcct; j++) {

                if(accounts[j].getType().compareTo(minAccount) == -1) {
                    minIndex = j;
                    minAccount = accounts[j].getType();
                }
            }
            if(minIndex != -1) {
                temp = accounts[i];
                accounts[i] = accounts[minIndex];
                accounts[minIndex] = temp;
            }
            minIndex = -1;
        }
        System.out.println();
        System.out.println("*list of accounts by account type.");
        for(int i = 0; i < numAcct; i++){
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list");
        System.out.println();
    }

    public void printFeeAndInterest(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        System.out.println("*list of accounts with fee and monthly interest");
        for(int i = 0; i < numAcct; i++){
            System.out.println(accounts[i] + "::fee " + d.format(accounts[i].fee()) + "::monthly interest " + d.format(accounts[i].monthlyInterest()));
        }
        System.out.println("*end of list.");
        System.out.println();
    }

    public void updateFeesAndInterest(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        System.out.println("*list of accounts with updated balance");
        for(int i = 0; i < numAcct; i++){
            accounts[i].deposit(accounts[i].monthlyInterest());
            accounts[i].withdraw(accounts[i].fee());
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");
        System.out.println();
    }

    public static void main(String[] args){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 950));
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        accountDB.open(new CollegeChecking(new Profile("John", "Doe", new Date("2/19/1989")), 500.0, 0));
        accountDB.open(new CollegeChecking(new Profile("Chris", "Young", new Date("9/20/2001")), 500, 0));
        accountDB.printFeeAndInterest();
        accountDB.updateFeesAndInterest();
        accountDB.close(new Checking(new Profile("April", "March", new Date("1/15/1987")), 0));
        accountDB.print();
        accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 200));
        accountDB.print();

    }

}
