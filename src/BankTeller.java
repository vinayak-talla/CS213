import java.util.Scanner;

/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class BankTeller {
    public void openAccounts(String[] tokens, AccountDatabase accountDatabase){
        if(tokens[1].equals("C")){
            accountDatabase.open(new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5])));
        }
        else if(tokens[1].equals("CC")){
            accountDatabase.open(new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6])));
        }
        else if(tokens[1].equals("S")){
            accountDatabase.open(new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6])));
        }
        else if(tokens[1].equals("MM")){
            accountDatabase.open(new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Double.parseDouble(tokens[5])));
        }
    }

    public void closeAccounts(String[] tokens, AccountDatabase accountDatabase){
        if(tokens[1].equals("C")){
            accountDatabase.close(new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0));
        }
        else if(tokens[1].equals("CC")){
            accountDatabase.close(new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0, 0));
        }
        else if(tokens[1].equals("S")){
            accountDatabase.close(new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0, 0));
        }
        else if(tokens[1].equals("MM")){
            accountDatabase.close(new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), 0));
        }
    }

    public void depositAccounts(String[] tokens, AccountDatabase accountDatabase){
        if(tokens[1].equals("C")){
            accountDatabase.deposit(new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5])));
        }
        else if(tokens[1].equals("CC")){
            accountDatabase.deposit(new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5]), 0));
        }
        else if(tokens[1].equals("S")){
            accountDatabase.deposit(new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5]), 0));
        }
        else if(tokens[1].equals("MM")){
            accountDatabase.deposit(new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5])));
        }
    }

    public void withdrawAccounts(String[] tokens, AccountDatabase accountDatabase){
        if(tokens[1].equals("C")){
            accountDatabase.withdraw(new Checking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5])));
        }
        else if(tokens[1].equals("CC")){
            accountDatabase.withdraw(new CollegeChecking(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5]), 0));
        }
        else if(tokens[1].equals("S")){
            accountDatabase.withdraw(new Savings(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5]), 0));
        }
        else if(tokens[1].equals("MM")){
            accountDatabase.withdraw(new MoneyMarket(new Profile(tokens[2], tokens[3], new Date(tokens[4])), Integer.parseInt(tokens[5])));
        }
    }



    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bank Teller is running.");
        AccountDatabase accountDatabase = new AccountDatabase();
        while(scanner.hasNextLine()){
            String[] tokens = scanner.nextLine().split("\\s");
            Account[] tempAccts = accountDatabase.getAccounts();
            int numAccts = accountDatabase.getNumAcct();
            if(tokens[0].equals("O")){
                openAccounts(tokens, accountDatabase);
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
            else if(tokens[0].equals("P")){
                accountDatabase.print();
            }
            else if(tokens[0].equals("PT")){
                accountDatabase.printByAccountType();
            }
            else if(tokens[0].equals("PI")){
                accountDatabase.printFeeAndInterest();
            }
            else if(tokens[0].equals("UB")){
                accountDatabase.updateFeesAndInterest();
            }
            else if(tokens[0].equals("Q")) {
                break;
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
