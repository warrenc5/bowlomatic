package test.bowlomatic;

import app.bowlomatic.BowlomaticApp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Warren Crossing
 */
public class BowlomaticUnitTest {

    public BowlomaticUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBowlomatic1() {
        System.out.println("Test 1");
        Assert.assertEquals(Integer.valueOf(10), new BowlomaticApp().calculateScore("1 2 3 4"));
    }

    @Test
    public void testBowlomatic2() {
        System.out.println("Test 2");
        Assert.assertEquals(Integer.valueOf(29), new BowlomaticApp().calculateScore("9 1 9 1"));
    }

    @Test
    public void testBowlomatic3() {
        System.out.println("Test 3");
        Assert.assertEquals(Integer.valueOf(18), new BowlomaticApp().calculateScore("1 1 1 1 10 1 1"));
    }

    @Test
    public void testBowlomatic4() {
        System.out.println("Test 4");
        Assert.assertEquals(Integer.valueOf(300), new BowlomaticApp().calculateScore("10 10 10 10 10 10 10 10 10 10 10 10"));
    }

}
