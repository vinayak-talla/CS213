import org.junit.Test;
import project2.Date;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test_days_Feb_nonLeap() {
        Date date = new Date("2/29/2011");
        assertFalse(date.isValid());
        Date date2 = new Date("2/28/2011");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_days_Feb_leap() {
        Date date = new Date("2/29/2008");
        assertTrue(date.isValid());
        Date date2 = new Date("2/28/2011");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_max_months(){
        Date date = new Date("13/29/2005");
        assertFalse(date.isValid());
        Date date2 = new Date("12/10/2005");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_min_months(){
        Date date = new Date("0/29/2005");
        assertFalse(date.isValid());
        Date date2 = new Date("1/10/2005");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_days_in_31_month(){
        Date date = new Date("3/32/2019");
        assertFalse(date.isValid());
        Date date2 = new Date("3/31/2019");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_negative_months(){
        Date date2 = new Date("-1/07/2021");
        assertFalse(date2.isValid());
    }

    @Test
    public void test_days_in_30_months(){
        Date date = new Date("4/31/2019");
        assertFalse(date.isValid());
        Date date2 = new Date("4/30/2019");
        assertTrue(date2.isValid());
    }

    @Test
    public void test_0_days_in_month(){
        Date date = new Date("4/0/2019");
        assertFalse(date.isValid());
    }

    @Test
    public void test_large_and_small_dates(){
        Date date = new Date("4/30/202332");
        assertTrue(date.isValid());
        Date date2 = new Date("4/30/0");
        assertTrue(date2.isValid());
    }

}