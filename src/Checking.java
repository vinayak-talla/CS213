/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Checking extends Account {
    private static final double YEARLYINTERESTRATE = 0.1;
    public double monthlyInterest() {
        return (YEARLYINTERESTRATE/12) * balance;
    }

    public double fee() {
        if(balance >= 1000){
            return 0;
        }
        else {
            return 25;
        }
    }

    public String getType() {
        return "Checking";
    }

}
