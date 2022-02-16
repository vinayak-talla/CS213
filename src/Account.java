
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

    }

    @Override
    public String toString(){

    }

    public void withdraw(double amount){

    }

    public void deposit(double amount){

    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();





}

