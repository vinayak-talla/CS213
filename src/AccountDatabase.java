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
        accounts[numAcct] = account;
        numAcct++;
        return true;

    }

    public boolean close(Account account){
        account.setClosed(true);
        account.setBalance(0);

        return true;
    }

    public void deposit(Account account){


    }

    public boolean withdraw(Account account){

    }

    public void print(){
        for(int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }

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
        for(int i = 0; i < numAcct; i++){
            System.out.println(accounts[i] + "::fee $" + accounts[i].fee() + "::monthly interest $" + accounts[i].monthlyInterest());
        }
    }

    public static void main(String[] args){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 950));
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        accountDB.open(new CollegeChecking(new Profile("John", "Doe", new Date("7/06/2002")), 300));
        accountDB.open(new Checking(new Profile("Alvin", "Alex", new Date("7/06/2002")), 300));
        accountDB.open(new Checking(new Profile("Alvin", "Alex", new Date("7/06/2002")), 300));
        accountDB.open(new Checking(new Profile("Alvin", "Alex", new Date("7/06/2002")), 300));
    }

}
