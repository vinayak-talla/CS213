/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class CollegeChecking extends Checking {
    private static final double YEARLYINTERESTRATE = 0.25;
    public double monthlyInterest() {
        return (YEARLYINTERESTRATE/12) * balance;
    }

    public double fee() {
        return 0;
    }
    public String getType() {
        return "College Checking";
    }
}
