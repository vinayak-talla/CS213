/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class MoneyMarket extends Savings {
    private int customerLoyalty;
    private double yearlyInterestRate;
    private int numOfWithdrawals = 0;
    private String loyal = "";
    private String closedString = "";

    public MoneyMarket(Profile profile, double balance){
        this.holder = profile;
        this.balance = balance;
        this.customerLoyalty = 1;
        yearlyInterestRate = 0.0095;
        this.closed = false;
    }

    public void withdraw(double amount){
        this.balance = this.balance - amount;
        if(this.balance < 2500.0){
            this.customerLoyalty = 0;
            yearlyInterestRate = 0.008;
        }
        numOfWithdrawals ++;
    }

    public double monthlyInterest() {
        return (yearlyInterestRate/12) * balance;
    }

    public double fee() {
        if(balance >= 2500 && numOfWithdrawals <= 3){
            return 0;
        }
        else {
            return 10;
        }
    }

    public String getType() {
        return "Money Market";
    }

    public String toString(){
        if(customerLoyalty == 1){
            loyal = "::Loyal";
        }
        if(closed){
            closedString = "::CLOSED";
        }
        return getType() + "::" + holder + "::Balance $" + balance + loyal + closedString + "::withdrawl: " + numOfWithdrawals;
    }

    public static void main(String[] args){
        MoneyMarket moneyMarket1 = new MoneyMarket(new Profile("Jane", "Doe", new Date("6/07/2002")), 2500.0);
        MoneyMarket moneyMarket2 = new MoneyMarket(new Profile("Alvin", "Alex", new Date("7/06/2002")), 2500.0);
        System.out.println(moneyMarket1);
        System.out.println(moneyMarket2);
        moneyMarket2.withdraw(200);
        System.out.println(moneyMarket2);
    }
}
