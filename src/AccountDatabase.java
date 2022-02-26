import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents the database of Accounts
 */
public class AccountDatabase {
    private Account[] accounts;
    private int numAcct;
    public static final int NOT_FOUND= -1;

    /**
     * Constructor for AccountDatabase class
     * Instantiates AccountDatabase object instantiating accounts array with a default size of 4 and sets numAcct to 0
     */
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

    /**
     * Method that finds the index of an account in accounts
     * @param account which is the Account to find
     * @return the index of the found account or NOT_FOUND if account isn't in accounts
     */
    private int find(Account account){
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].equals(account)){
                if(accounts[i].getType().equals(account.getType())) {
                    return i;
                }
                if( (accounts[i].getType().equals("College Checking") && account.getType().equals("Checking")) || (accounts[i].getType().equals("Checking") && account.getType().equals("College Checking"))) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     * Method that increases the size of the accounts array by 4
     */
    private void grow(){
        Account[] temp = new Account[accounts.length + 4];
        for(int i = 0; i < accounts.length; i++){
            temp[i] = accounts[i];
        }
        accounts = temp;
    }

    /**
     * Method that opens a new account and adds it to the accounts array and grows it if it is full.
     * @param account that needs to be opened
     * @return boolean value representing if the account was opened successfully
     */
    public boolean open(Account account){
        if(numAcct == accounts.length-1){
            grow();
        }
        if(find(account) == NOT_FOUND){
            accounts[numAcct] = account;
            numAcct++;
            return true;
        }
        else{
            if((accounts[find(account)].getType().equals("College Checking") && account.getType().equals("Checking")) || (accounts[find(account)].getType().equals("Checking") && account.getType().equals("College Checking"))){
                return false;
            }
            if(accounts[find(account)].getType().equals(account.getType()) && !accounts[find(account)].closed){
                return false;
            }
            accounts[find(account)].setClosed(false);
            accounts[find(account)].setBalance(account.balance);
            if(accounts[find(account)].getType().equals("College Checking")){
                accounts[find(account)] = account;
            }

            return false;
        }
    }

    /**
     * Method that closes an account in the accounts array
     * @param account that needs to be closed
     * @return boolean value representing if the account was closed successfully
     */
    public boolean close(Account account){
        int indexOfAccount = find(account);

        if(find(account) == -1) {return false;}

        if(accounts[indexOfAccount].getClosed()) {
            return false;
        }
        accounts[indexOfAccount].setClosed(true);
        accounts[indexOfAccount].setBalance(0);

        return true;
    }

    /**
     * Method that deposits money into an account
     * @param account the account with a balance of the deposit for the account in the array
     */
    public void deposit(Account account){
        accounts[find(account)].deposit(account.balance);
    }

    /**
     * Method that withdraws money from an account
     * @param account the account with a balance of the withdrawal for the account in the array
     * @return boolean value of whether the withdrawal was successful
     */
    public boolean withdraw(Account account){
        if(accounts[find(account)].balance - account.balance <= 0){
            return false;
        }
        else {
            accounts[find(account)].withdraw(account.balance);
            return true;
        }
    }

    /**
     * Method that prints out the list of accounts based on their place in the array
     */
    public void print(){
        System.out.println();
        System.out.println("*list of accounts in the database*");
        for(int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list*");
        System.out.println();

    }

    /**
     * Method that prints out the list of accounts based on the account type
     */
    public void printByAccountType(){
        Account temp = null;
        int minIndex = 0;
        String minAccount = "";
        for(int i = 0; i < numAcct; i++) {
            minAccount = accounts[i].getType();
            for( int j = i + 1; j < numAcct; j++) {
                if(accounts[j].getType().compareTo(minAccount) < 0) {

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
        System.out.println("*end of list.");
        System.out.println();
    }

    /**
     * Method that prints out the list of accounts with fees and interest based on their place in the array
     */
    public void printFeeAndInterest(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        System.out.println();
        System.out.println("*list of accounts with fee and monthly interest");
        for(int i = 0; i < numAcct; i++){
            System.out.println(accounts[i] + "::fee " + d.format(accounts[i].fee()) + "::monthly interest " + d.format(accounts[i].monthlyInterest()));
        }
        System.out.println("*end of list.");
        System.out.println();
    }

    /**
     * Method that updates the fees and interests and prints out the list of accounts based on their place in the array
     */
    public void updateFeesAndInterest(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        System.out.println();
        System.out.println("*list of accounts with updated balance");
        for(int i = 0; i < numAcct; i++){
            accounts[i].deposit(accounts[i].monthlyInterest());
            accounts[i].setBalance(accounts[i].balance - accounts[i].fee());
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");
        System.out.println();
    }

    public static void main(String[] args){
        AccountDatabase accountDB = new AccountDatabase();
        accountDB.open(new Savings(new Profile("Leonard", "Fuller", new Date("1/15/1987")), 450, 1));
        accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 950));
        accountDB.open(new Checking(new Profile("John", "Doe", new Date("2/19/1990")), 1766));
        accountDB.open(new MoneyMarket(new Profile("Jane", "Martin", new Date("1/15/1987")), 450));
        accountDB.open(new CollegeChecking(new Profile("John", "Doe", new Date("2/19/1989")), 500.0, 0));
        accountDB.open(new CollegeChecking(new Profile("Chris", "Young", new Date("9/20/2001")), 500, 0));

        accountDB.printByAccountType();
//        accountDB.printFeeAndInterest();
//        accountDB.updateFeesAndInterest();
//        accountDB.close(new Checking(new Profile("April", "March", new Date("1/15/1987")), 0));
//        accountDB.print();
//        accountDB.open(new Checking(new Profile("April", "March", new Date("1/15/1987")), 300));
//        accountDB.open(new CollegeChecking(new Profile("April", "March", new Date("1/15/1987")), 490, 0));
        //accountDB.print();

    }

}
