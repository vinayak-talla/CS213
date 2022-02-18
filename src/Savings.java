/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Savings extends Account {
    private static double yearlyInterestRate;
    private int customerLoyalty;

    public double monthlyInterest() {
        return (yearlyInterestRate/12) * balance;
    }

    public Savings(){

    }

    public Savings(Profile profile, double balance, int customerLoyalty){
        this.holder = profile;
        this.balance = balance;
        this.customerLoyalty = customerLoyalty;
        this.closed = false;
        if(customerLoyalty == 0){
            yearlyInterestRate = .003;
        }
        else if(customerLoyalty == 1){
            yearlyInterestRate = 0.0045;
        }
    }

    public double fee() {
        if(balance >= 300) {
            return 0;
        }
        else{
            return 6;
        }
    }

    public String getType() {
        return "Savings";
    }

    public String toString(){
        if(customerLoyalty == 0){
            return getType() + "::" + holder + "::Balance $" + balance;
        }
        else if(customerLoyalty == 1){
            return getType() + "::" + holder + "::Balance $" + balance + "::Loyal";
        }
        return "NOT_FOUND";
    }

    public static void main(String[] args){
        Savings savings1 = new Savings(new Profile("Jane", "Doe", new Date("6/07/2002")), 0.0, 1);
        Savings savings2 = new Savings(new Profile("Alvin", "Alex", new Date("7/06/2002")), 100.0, 0);
        System.out.println(savings1);
        System.out.println(savings2);
    }
}
