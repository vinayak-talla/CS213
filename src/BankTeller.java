import java.util.Scanner;

/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents a bank teller with user input commands
 */
public class BankTeller {

    /**
     * Private helper method to open an account
     * @param accountDatabase the database for all accounts
     * @param account the account the user inputs
     */
    private void sameAccount(AccountDatabase accountDatabase, Account account) {
        boolean oldChecking = false;
        if(accountDatabase.getNumAcct() > 0 && findAccount(accountDatabase.getAccounts(), account, accountDatabase.getNumAcct()) != -1) {
            oldChecking = accountDatabase.getAccounts()[findAccount(accountDatabase.getAccounts(), account, accountDatabase.getNumAcct())].closed;
        }
        System.out.println(oldChecking);
        if(accountDatabase.open(account)){
            System.out.println("Account opened.");
        }
        else if((account.getType().equals("Checking") || account.getType().equals("College Checking")) && findAccount(accountDatabase.getAccounts(), account ,accountDatabase.getNumAcct()) == -1) {
            System.out.println(account.holder + " same account(type) is in the database.");
        }

        else if(account.getType().equals(accountDatabase.getAccounts()[findAccount(accountDatabase.getAccounts(), account ,accountDatabase.getNumAcct())].getType()) && !oldChecking){
            System.out.println(account.holder + " same account(type) is in the database.");
        }
        else{
            System.out.println("Account reopened.");
        }
    }

    /**
     * Private helper method to find type of account
     * @param tokens the user input as a String array
     * @param accountDatabase the database for all accounts
     */
    private void findAccountType(String[] tokens, AccountDatabase accountDatabase){
        try {
            if(!validDOB(new Date(tokens[4]))) {
                return;
            }
            if(tokens[1].equals("C") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Checking checking1 = new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                sameAccount(accountDatabase,checking1);
            }
            else if(tokens[1].equals("CC") && validAmount(tokens[0], Double.parseDouble(tokens[5])) && validCode(Integer.parseInt(tokens[6]))){
                CollegeChecking checking2 = new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]));
                sameAccount(accountDatabase,checking2);
            }
            else if(tokens[1].equals("S") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Savings savings1 = new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]));
                sameAccount(accountDatabase,savings1);
            }
            else if(tokens[1].equals("MM") && validAmount(tokens[0], Double.parseDouble(tokens[5])) && validMM(Double.parseDouble(tokens[5]))) {
                MoneyMarket moneyMarket1 = new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                sameAccount(accountDatabase,moneyMarket1);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Private helper method to check whether a campus code is valid
     * @param code the campus code to be validated
     * @return  boolean value of whether the campus code is valid
     */
    private boolean validCode(int code) {
        if(code == 0 || code == 1 || code == 2) {
            return true;
        }
        System.out.println("Invalid campus code.");
        return false;
    }

    /**
     * Private helper method to check whether the initial deposit into a MoneyMarker account is valid
     * @param deposit the initial deposit in the MoneyMarket account
     * @return  boolean value of whether the initial deposit is valid
     */
    private boolean validMM(Double deposit) {
        if( deposit < 2500) {
            System.out.println("Minimum of $2500 to open a MoneyMarket account.");
            return false;
        }
        return true;
    }

    /**
     * Private helper method to check whether a date of birth is valid
     * @param date the date of birth to be validated
     * @return  boolean value of whether the date of birth is valid
     */
    private boolean validDOB(Date date) {
        if(date.isValid() && date.isValidDOB()) {
            return true;
        }
        System.out.println("Date of birth invalid.");
        return false;
    }

    /**
     * Private helper method to check whether a deposit or withdrawal amount is valid
     * @param command user input command of D for deposit or W for withdrawal
     * @param amount the amount to be potentially deposited or withdrawn
     * @return  boolean value of whether the amount is valid
     */
    private boolean validAmount(String command, Double amount) {
        if(amount <= 0){
            if(command.equals("O")) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return false;
            }
            if(command.equals("D")) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return false;
            }
            if(command.equals("W")) {
                System.out.println("Withdraw - amount cannot be 0 or negative.");
                return false;
            }
        }
        return true;
    }


    /**
     * Private helper method to close an account
     * @param tokens the user input as a String array
     * @param accountDatabase the database for all accounts
     */
    private void closeAccounts(String[] tokens, AccountDatabase accountDatabase) {
        try {
            if (tokens[1].equals("C")) {
                if (accountDatabase.close(new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0))) {
                    System.out.println("Account closed.");
                } else {
                    System.out.println("Account is closed already.");
                }
            } else if (tokens[1].equals("CC")) {
                if (accountDatabase.close(new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0, 0))) {
                    System.out.println("Account closed.");
                } else {
                    System.out.println("Account is closed already.");
                }
            } else if (tokens[1].equals("S")) {
                if (accountDatabase.close(new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0, 0))) {
                    System.out.println("Account closed.");
                } else {
                    System.out.println("Account is closed already.");
                }
            } else if (tokens[1].equals("MM")) {
                if (accountDatabase.close(new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0)))
                    System.out.println("Account closed.");
                else {
                    System.out.println("Account is closed already.");
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Missing data for closing an account.");
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Method that finds the index of an account in accounts
     * @param accounts the array of accounts
     * @param account which is the Account to find
     * @param numAccts the number of accounts in the array
     * @return the index of the found account or -1 if account isn't in accounts
     */
    private int findAccount(Account[] accounts, Account account, int numAccts) {
        for(int i = 0; i < numAccts; i++) {
            if(accounts[i].equals(account)) {
                if(!((accounts[i].getType().equals("College Checking") && account.getType().equals("Checking")) || (accounts[i].getType().equals("Checking") && account.getType().equals("College Checking")))) {
                    return i;

                }
            }
        }
        return -1;
    }


    private void depositMoney(AccountDatabase accountDatabase, Account account) {
        if(findAccount(accountDatabase.getAccounts(), account,accountDatabase.getNumAcct()) != -1) {
            accountDatabase.deposit(account);
            System.out.println("Deposit - balance updated.");
        } else {
            System.out.println(account.holder + " " + account.getType() + " is not in the database.");
        }
    }
    /**
     * Private helper method to deposit money into an account
     * @param tokens the user input as a String array
     * @param accountDatabase the database for all accounts
     */
    private void depositAccounts(String[] tokens, AccountDatabase accountDatabase){
        try {
            if(tokens[1].equals("C") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Checking checking = new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                depositMoney(accountDatabase,checking);

            }
            else if(tokens[1].equals("CC") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                CollegeChecking collegeChecking = new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), 0);
                depositMoney();
            }
            else if(tokens[1].equals("S") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Savings savings = new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), 0);
                if(findAccount(accountDatabase.getAccounts(), savings,accountDatabase.getNumAcct()) != -1) {
                    accountDatabase.deposit(savings);
                    System.out.println("Deposit - balance updated.");
                } else {
                    System.out.println(savings.holder + " " + savings.getType() + " is not in the database.");
                }
            }
            else if(tokens[1].equals("MM") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                MoneyMarket moneyMarket = new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                if(findAccount(accountDatabase.getAccounts(), moneyMarket,accountDatabase.getNumAcct()) != -1) {
                    accountDatabase.deposit(moneyMarket);
                    System.out.println("Deposit - balance updated.");
                } else {
                    System.out.println(moneyMarket.holder + " " + moneyMarket.getType() + " is not in the database.");
                }
            }
        }

        catch (IndexOutOfBoundsException ex) {
            System.out.println("Missing data for depositing into account.");
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Private helper method to check if balance is enough for withdrawal and withdraws if enough
     * @param accountDatabase the database for all accounts
     * @param account the account with the balance of the withdrawal
     */
    private void isSufficient(AccountDatabase accountDatabase, Account account) {
        if(accountDatabase.withdraw(account)) {
            System.out.println("Withdraw - balance updated.");
            return;
        }
        System.out.println("Withdraw - insufficient fund.");
    }

    /**
     * Private helper method to withdraw money into an account
     * @param tokens the user input as a String array
     * @param accountDatabase the database for all accounts
     */
    public void withdrawAccounts(String[] tokens, AccountDatabase accountDatabase){
        try {
            if(tokens[1].equals("C") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Checking checking = new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                if(findAccount(accountDatabase.getAccounts(), checking,accountDatabase.getNumAcct()) != -1) {
                    isSufficient(accountDatabase,checking);
                } else {
                    System.out.println(checking.holder + " " + checking.getType() + " is not in the database.");
                }
            }
            else if(tokens[1].equals("CC") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                CollegeChecking collegeChecking = new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), 0);
                if(findAccount(accountDatabase.getAccounts(), collegeChecking,accountDatabase.getNumAcct()) != -1) {
                    isSufficient(accountDatabase,collegeChecking);
                } else {
                    System.out.println(collegeChecking.holder + " " + collegeChecking.getType() + " is not in the database.");
                }
            }
            else if(tokens[1].equals("S") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                Savings savings = new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), 0);
                if(findAccount(accountDatabase.getAccounts(), savings,accountDatabase.getNumAcct()) != -1) {
                    isSufficient(accountDatabase,savings);
                } else {
                    System.out.println(savings.holder + " " + savings.getType() + " is not in the database.");
                }
            }
            else if(tokens[1].equals("MM") && validAmount(tokens[0], Double.parseDouble(tokens[5]))){
                MoneyMarket moneyMarket = new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]));
                if(findAccount(accountDatabase.getAccounts(), moneyMarket,accountDatabase.getNumAcct()) != -1) {
                    isSufficient(accountDatabase,moneyMarket);
                } else {
                    System.out.println(moneyMarket.holder + " " + moneyMarket.getType() + " is not in the database.");
                }
            }

        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Missing data for withdrawing from account.");
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Private helper method to withdraw money into an account
     * @param accountDatabase the database for all accounts
     * @param command user input command of P, PT, PI, or UB
     * @param numAcct the number of accounts in the array
     */
    private void printAccounts(AccountDatabase accountDatabase, String command, int  numAcct) {
        if(numAcct == 0) {
            System.out.println("Account Database is empty!");
            return;
        }
        if(command.equals("P")) {
            accountDatabase.print();
        }
        else if(command.equals("PT")) {
            accountDatabase.printByAccountType();
        }
        else if(command.equals("PI")) {
            accountDatabase.printFeeAndInterest();
        }
        else if(command.equals("UB")){
            accountDatabase.updateFeesAndInterest();
        }
    }



    /**
     * Method that starts and runs the bank teller and user inputs
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bank Teller is running.");
        AccountDatabase accountDatabase = new AccountDatabase();
        while(scanner.hasNextLine()){
            String[] tokens = scanner.nextLine().split("\\s");
            Account[] tempAccts = accountDatabase.getAccounts();
            int numAccts = accountDatabase.getNumAcct();
            if(tokens[0].equals("O")){
                findAccountType(tokens, accountDatabase);
            }
            else if(tokens[0].equals("C")){
                //removeAppointment(schedule, new Appointment(new Patient(tokens[2], tokens[3], new Date(tokens[1])), new Timeslot(new Date(tokens[4]), new Time(tokens[5])), location));
                closeAccounts(tokens, accountDatabase);
            }
            else if(tokens[0].equals("D")){
                depositAccounts(tokens,accountDatabase);
            }
            else if(tokens[0].equals("W")){
                withdrawAccounts(tokens,accountDatabase);
            }
            else if(tokens[0].equals("P") || tokens[0].equals("PT") || tokens[0].equals("PI") || tokens[0].equals("UB")){
                printAccounts(accountDatabase,tokens[0],numAccts);
            }
            else if(tokens[0].equals("Q")) {
                break;
            }
            else if(tokens[0].equals("")){

            }
            else {
                System.out.println("Invalid command!");
            }
        }
        scanner.close();
        System.out.println("Bank Teller is terminated.");
    }

    public static void main(String[] args){
        new BankTeller().run();
    }
}

