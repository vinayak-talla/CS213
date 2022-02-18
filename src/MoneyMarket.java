/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class MoneyMarket extends Savings {
    private double yearlyInterestRate;
    private int numOfWithdrawals = 0;
    private boolean isLoyal;

    public MoneyMarket(Profile profile, double balance){
        this.holder = profile;
        this.balance = balance;
        this.isLoyal = true;
        yearlyInterestRate = 0.0095;
        this.closed = false;
    }

    public void setLoyal(boolean loyal){
        this.isLoyal = loyal;
    }

    public void withdraw(double amount){
        this.balance = this.balance - amount;
        if(this.balance < 2500.0){
            this.isLoyal = false;
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

    public void setClosed(boolean closed) {
        this.closed = closed;

        if(closed) {
            isLoyal = false;
        }
    }

    public String getType() {
        return "Money Market";
    }

    public String toString(){
        String loyal = "";
        String closedString = "";
        if(this.isLoyal){
            System.out.println("running");
            loyal = "::Loyal";
        }
        if(this.closed){
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
