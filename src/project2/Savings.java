package project2;

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
    private static final double NONLOYALINTERESTRATE = .003;
    private static final double LOYALINTERESTRATE = .0045;
    private static final double MINIMUMBALANCE = 300;
    private static final double SAVINGSFEE = 6;


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
            yearlyInterestRate = NONLOYALINTERESTRATE;
        }
        else {
            isLoyal = true;
            yearlyInterestRate = LOYALINTERESTRATE;
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
        if(balance >= MINIMUMBALANCE) {
            return 0;
        }
        else{
            return SAVINGSFEE;
        }
    }

    /**
     * Method to determine Account type
     * @return type of Account as String
     */
    public String getType() {
        return "Savings";
    }

    /**
     * Setter method for closed
     * @param closed, boolean value of whether an account is closed or not
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
        this.isLoyal = false;
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

}

