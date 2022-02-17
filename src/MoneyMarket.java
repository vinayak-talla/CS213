/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class MoneyMarket extends Savings {
    private int customerLoyalty = 1;
    private static final double YEARLYINTERESTRATE = 0.8;
    public double monthlyInterest() {
        return (YEARLYINTERESTRATE/12) * balance;
    }
    public double fee() {
        if(balance >= 2500){
            return 0;
        }
        else {
            return 10;
        }
    }
    public String getType() {
        return "Money Market";
    }
}
