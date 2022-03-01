package project2;

import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 * Class that represents a Checking Account, a type of Account, with a profile, and balance
 */
public class Checking extends Account {
    protected double yearlyInterestRate;

    /**
     * Default constructor for Checking class
     */
    public Checking(){

    }

    /**
     * Constructor for Checking class
     * Instantiates Savings object with Profile, and balance from parameters
     * @param profile the name and date of birth of the account holder
     * @param balance the initial deposit into the account
     */
    public Checking(Profile profile, double balance){
        this.holder = profile;
        this.balance = balance;
        this.closed = false;
        this.yearlyInterestRate = 0.001;
    }

    /**
     * Method that determines if two Checking objects are equal
     * @param obj the Checking object that is being compared
     * @return boolean value of whether two Checking objects are equal
     */
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof Checking || obj instanceof CollegeChecking))
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
        if(balance >= 1000){
            return 0;
        }
        else {
            return 25;
        }
    }

    /**
     * Method to determine Account type
     * @return type of Account as String
     */
    public String getType() {
        return "Checking";
    }

}
