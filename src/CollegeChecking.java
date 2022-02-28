import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents a CollegeChecking Account, a type of Account, with a profile, balance, and campus code
 */
public class CollegeChecking extends Checking {
    private String campusCode;

    /**
     * Constructor for Checking class
     * Instantiates Savings object with Profile, and balance from parameters
     * @param profile the name and date of birth of the account holder
     * @param balance the initial deposit into the account
     * @param campusCode the code for one of three campuses
     */
    public CollegeChecking(Profile profile, double balance, int campusCode){
        this.holder = profile;
        this.balance = balance;
        this.closed = false;
        this.yearlyInterestRate = 0.0025;
        if(campusCode == 0){
            this.campusCode = "NEW_BRUNSWICK";
        }
        else if(campusCode == 1){
            this.campusCode = "NEWARK";
        }
        else if(campusCode == 2){
            this.campusCode = "CAMDEN";
        }
    }

    /**
     * Method that determines if two CollegeChecking objects are equal
     * @param obj the CollegeChecking object that is being compared
     * @return boolean value of whether two CollegeChecking objects are equal
     */
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof CollegeChecking || obj instanceof Checking))
            return false;

        return super.equals(obj);
    }

    /**
     * Method to determine monthly interest
     * @return value of monthly interest
     */
    public double monthlyInterest() {
        DecimalFormat d = new DecimalFormat("#.##");
        return Double.parseDouble(d.format((this.yearlyInterestRate/12) * balance));
    }

    /**
     * Method to determine monthly fees
     * @return value of monthly fees
     */
    public double fee() {
        return 0;
    }

    /**
     * Method to print out CollegeChecking object
     * @return CollegeChecking object formatted as String
     */
    public String toString(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        String closedString = "";
        if(this.closed){
            closedString = "::CLOSED";
        }
        return getType() + "::" + holder + "::Balance " + d.format(balance) + closedString+ "::" + campusCode;
    }

    /**
     * Method to determine Account type
     * @return type of Account as String
     */
    public String getType() {
        return "College Checking";
    }

    public static void main(String[] args){
        CollegeChecking checking1 = new CollegeChecking(new Profile("Jane", "Doe", new Date("6/07/2002")), 0.0, 1);
        CollegeChecking checking2 = new CollegeChecking(new Profile("Alvin", "Alex", new Date("7/06/2002")), 100.0, 0);
        System.out.println(checking1);
        System.out.println(checking2);

    }
}
