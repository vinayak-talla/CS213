import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Savings extends Account {
    private static double yearlyInterestRate;
    private boolean isLoyal;

    public double monthlyInterest() {
        DecimalFormat d = new DecimalFormat("#.##");
        return Double.parseDouble(d.format((yearlyInterestRate/12) * balance));
    }

    public Savings(){

    }

    public Savings(Profile profile, double balance, int customerLoyalty){
        this.holder = profile;
        this.balance = balance;
        this.closed = false;
        if(customerLoyalty == 0) {
            isLoyal = false;
        }
        else {
            isLoyal = true;
        }
        if(!isLoyal){
            yearlyInterestRate = .003;
        }
        else {
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

    public void setClosed(boolean closed) {
        this.closed = closed;

        if(closed) {
            isLoyal = false;
        }
    }


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

    public static void main(String[] args){
        Savings savings1 = new Savings(new Profile("Jane", "Doe", new Date("6/07/2002")), 0.0, 1);
        Savings savings2 = new Savings(new Profile("Alvin", "Alex", new Date("7/06/2002")), 100.0, 0);
        System.out.println(savings1);
        System.out.println(savings2);

    }
}
