import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents a Savings Account, a type of Account, with a profile, balance, and loyalty
 */
public class Savings extends Account {
    protected double yearlyInterestRate;
    protected boolean isLoyal;

    /**
     * Default constructor for Savings class
     */
    public Savings() {

    }

    /**
     * Constructor for Savings class
     * Instantiates Savings object with Profile, balance, and loyalty from parameters
     * @param profile the name and date of birth of the account holder
     * @param balance the initial deposit into the account
     * @param customerLoyalty, to see if a customer is loyal or not
     */
    public Savings(Profile profile, double balance, int customerLoyalty){
        this.holder = profile;
        this.balance = balance;
        this.closed = false;
        if(customerLoyalty == 0) {
            isLoyal = false;
            yearlyInterestRate = .003;
        }
        else {
            isLoyal = true;
            yearlyInterestRate = 0.0045;
        }
    }

    /**
     * Method that determines if two Savings objects are equal
     * @param obj the Savings object that is being compared
     * @return boolean value of whether two Savings objects are equal
     */
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof Savings))
            return false;

        return super.equals(obj);
    }

    /**
     * Method to determine monthly interest
     * @return value of monthly interest
     */
    public double monthlyInterest() {
        DecimalFormat d = new DecimalFormat("#.##");
        return Double.parseDouble(d.format((yearlyInterestRate/12) * balance));
    }

    /**
     * Method to determine monthly fees
     * @return value of monthly fees
     */
    public double fee() {
        if(balance >= 300) {
            return 0;
        }
        else{
            return 6;
        }
    }

    /**
     * Method to determine Account type
     * @return type of Account as String
     */
    public String getType() {
        return "Savings";
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
        if(closed) {
            isLoyal = false;
        }
    }

    /**
     * Method to print out Savings object
     * @return Savings object formatted as String
     */
    public String toString(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        String closedString = "";
        if(this.closed){
            closedString = "::CLOSED";
        }

        if(isLoyal) {
            return getType() + "::" + holder + "::Balance " + d.format(balance) + "::Loyal";
        }
        else {
            return getType() + "::" + holder + "::Balance " + d.format(balance) + closedString;
        }
    }

    public static void main(String[] args){
        Savings savings1 = new Savings(new Profile("Jane", "Doe", new Date("6/07/2002")), 0.0, 1);
        Savings savings2 = new Savings(new Profile("Alvin", "Alex", new Date("7/06/2002")), 100.0, 0);
        System.out.println(savings1);
        System.out.println(savings2);

    }
}

