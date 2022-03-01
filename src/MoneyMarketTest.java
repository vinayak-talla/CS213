import org.junit.Test;
import project2.Date;
import project2.MoneyMarket;
import project2.Profile;

import static org.junit.Assert.*;

public class MoneyMarketTest {

    @Test
    public void balanceAbove2500() {
        MoneyMarket moneyMarket = new MoneyMarket(new Profile("Jane", "Doe", new Date("5/12/1972")), 4000);
        assertEquals(3.17,moneyMarket.monthlyInterest(),0.001);
    }

    @Test
    public void balanceEquals2500() {
        MoneyMarket moneyMarket = new MoneyMarket(new Profile("Jane", "Doe", new Date("5/12/1972")), 2500);
        assertEquals(1.98,moneyMarket.monthlyInterest(),0.001);
    }

    @Test
    public void balanceUnder2500() {
        MoneyMarket moneyMarket = new MoneyMarket(new Profile("Jane", "Doe", new Date("5/12/1972")), 2500);
        moneyMarket.withdraw(1000);
        assertEquals(1.00,moneyMarket.monthlyInterest(),0.001);
    }
}