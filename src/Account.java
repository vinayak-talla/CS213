
/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Account))
            return false;

        Account acc = (Account) obj;

        if(this.holder.equals(acc.holder)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return holder + "::Balance $" + balance;
    }

    public void withdraw(double amount){
        this.balance = this.balance - amount;

    }

    public void deposit(double amount){
        this.balance = this.balance + amount;
    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();





}

