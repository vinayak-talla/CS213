package project2;

import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents a MoneyMarket Account, a type of Account, with a profile, balance
 */
public class MoneyMarket extends Savings {
    private int numOfWithdrawals;

    /**
     * Constructor for MoneyMarket class
     * Instantiates MoneyMarket object with Profile, and balance from parameters
     * @param profile the name and date of birth of the account holder
     * @param balance the initial deposit into the account
     */
    public MoneyMarket(Profile profile, double balance){
        this.holder = profile;
        this.balance = balance;
        this.isLoyal = true;
        yearlyInterestRate = 0.0095;
        numOfWithdrawals = 0;
        this.closed = false;
    }

    /**
     * Method that determines if two MoneyMarket objects are equal
     * @param obj the MoneyMarket object that is being compared
     * @return boolean value of whether two MoneyMarket objects are equal
     */
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof MoneyMarket))
            return false;

        return super.equals(obj);
    }

    /**
     * Method to withdraw money from MoneyMarket account
     * @param amount, the amount of money to withdraw
     */
    public void withdraw(double amount){
        this.balance = this.balance - amount;
        if(this.balance < 2500.0){
            this.isLoyal = false;
            yearlyInterestRate = 0.008;
        }
        numOfWithdrawals ++;
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
        if(balance >= 2500 && numOfWithdrawals <= 3){
            return 0;
        }
        else {
            return 10;
        }
    }

    /**
     * Setter method for closed
     * @param closed, boolean value of whether an account is closed or not
     */
    public void setClosed(boolean closed) {
        this.closed = closed;

        if(closed) {
            isLoyal = false;
            numOfWithdrawals = 0;
        }
    }

    /**
     * Method to determine Account type
     * @return type of Account as String
     */
    public String getType() {
        return "Money Market Savings";
    }

    /**
     * Method to print out MoneyMarket object
     * @return MoneyMarket object formatted as String
     */
    public String toString(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        String loyal = "";
        String closedString = "";
        if(this.balance >= 2500){
            this.isLoyal = true;
        }
        if(this.isLoyal){
            loyal = "::Loyal";
        }
        if(this.closed){
            closedString = "::CLOSED";
        }
        return getType() + "::" + holder + "::Balance " + d.format(balance) + loyal + closedString + "::withdrawl: " + (numOfWithdrawals);
    }

}
