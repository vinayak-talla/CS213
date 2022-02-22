import java.util.Scanner;

/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class BankTeller {
    public void openAccounts(String[] tokens, AccountDatabase accountDatabase){
        if(tokens[1].equals("C")){
            accountDatabase.open(new Checking(new Profile(tokens[2], tokens[3]), new Date(tokens[4])), Double.parseDouble(tokens[5]))
        }
        else if(tokens[1].equals("CC")){

        }
        else if(tokens[1].equals("S")){

        }
        else if(tokens[1].equals("MM")){

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
                accountDatabase.open(schedule, tempAppts, numAppts, new Appointment(new Patient(tokens[2], tokens[3], new Date(tokens[1])), new Timeslot(new Date(tokens[4]), new Time(tokens[5])), location));
            }
            else if(tokens[0].equals("C")){
                removeAppointment(schedule, new Appointment(new Patient(tokens[2], tokens[3], new Date(tokens[1])), new Timeslot(new Date(tokens[4]), new Time(tokens[5])), location));
            }
            else if(tokens[0].equals("D")){
                tempAppts = schedule.getAppointments();
                cancelPatientAppts(schedule,tempAppts, numAppts, new Patient(tokens[2], tokens[3], new Date(tokens[1])));
            }
            else if(tokens[0].equals("W")){

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
