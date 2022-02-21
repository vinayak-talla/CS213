import java.text.DecimalFormat;
/**
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Checking extends Account {
    private static final double YEARLYINTERESTRATE = 0.001;

    public Checking(){

    }

    public Checking(Profile profile, double balance){
        this.holder = profile;
        this.balance = balance;
        this.closed = false;
    }

    public double monthlyInterest() {
        DecimalFormat d = new DecimalFormat("#.##");
        return Double.parseDouble(d.format((YEARLYINTERESTRATE/12) * balance));
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

    public static void main(String[] args){
        Checking checking1 = new Checking(new Profile("Jane", "Doe", new Date("6/07/2002")), 0.0);
        Checking checking2 = new Checking(new Profile("Alvin", "Alex", new Date("7/06/2002")), 100.0);
        checking1.getType();
        checking1.deposit(1000);
        System.out.println(checking1);
        checking1.withdraw(100);
        System.out.println(checking1);
        System.out.println(checking2);

        System.out.println(checking1.monthlyInterest());
    }

}
