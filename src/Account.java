import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public void setBalance(double balance){
        this.balance= balance;
    }

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

    @Override
    public String toString(){
        DecimalFormat d = new DecimalFormat("'$'###,###,##0.00");
        String closedString = "";
        if(this.closed){
            closedString = "::CLOSED";
        }
        return getType() + "::" + holder + "::Balance " + d.format(balance) + closedString;
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    public void deposit(double amount){
        this.balance = this.balance + amount;
    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();





}

