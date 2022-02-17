/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Savings extends Account {
    private static final double YEARLYINTERESTRATE = 0.3;
    public double monthlyInterest() {
        return (YEARLYINTERESTRATE/12) * balance;
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
}
