import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Superclass that represents the shared attributes of all types of accounts
 */
public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;

    /**
     * Setter method for closed
     * @param closed, boolean value of whether an account is closed or not
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Getter method for closed
     * @return closed
     */
    public boolean getClosed() {
        return this.closed;
    }

    /**
     * Setter method for balance
     * @param balance, int value of the balance of the account
     */
    public void setBalance(double balance){
        this.balance= balance;
    }

    /**
     * Method that determines if two Account objects are equal
     * @param obj the Account object that is being compared
     * @return boolean value of whether two Accounts objects are equal
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Account))
            return false;

        Account acc = (Account) obj;

        if(this.holder.equals(acc.holder) && this.getType().equals(acc.getType())) {
            return true;
        }
        else if(this.holder.equals(acc.holder) && this.getType().equals("College Checking") && acc.getType().equals("Checking")){
            return true;
        }
        else if(this.holder.equals(acc.holder) && this.getType().equals("Checking") && acc.getType().equals("College Checking")){
            return true;
        }

        return false;
    }

    /**
     * Method to print out Account object
     * @return Account object formatted as String
     */
    @Override
    public String toString(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        String closedString = "";
        if(this.closed){
            closedString = "::CLOSED";
        }
        return getType() + "::" + holder + "::Balance " + d.format(balance) + closedString;
    }

    /**
     * Method to withdraw money from an account
     * @param amount, the amount of money to withdraw
     */
    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    /**
     * Method to deposit money into an account
     * @param amount, the amount of money to deposit
     */
    public void deposit(double amount){
        this.balance = this.balance + amount;
    }

    /**
     * Abstract method for subclasses to determine monthly interest based on type of Account
     * @return value of monthly interest for an account
     */
    public abstract double monthlyInterest();

    /**
     * Abstract method for subclasses to determine monthly fees based on type of Account
     * @return value of fees for an account
     */
    public abstract double fee();

    /**
     * Abstract method for subclasses to determine type of Account
     * @return type of Account as String
     */
    public abstract String getType();


}


