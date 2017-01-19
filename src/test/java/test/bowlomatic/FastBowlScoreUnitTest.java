package test.bowlomatic;

import app.bowlomatic.FastBowlScore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Warren Crossing
 */
public class FastBowlScoreUnitTest {

    public FastBowlScoreUnitTest() {
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
        Assert.assertEquals(Integer.valueOf(10), FastBowlScore.calcScore("1 2 3 4"));
    }

    @Test
    public void testBowlomatic2() {
        System.out.println("Test 2");
        Assert.assertEquals(Integer.valueOf(29), FastBowlScore.calcScore("9 1 9 1"));
    }

    @Test
    public void testBowlomatic3() {
        System.out.println("Test 3");
        Assert.assertEquals(Integer.valueOf(18), FastBowlScore.calcScore("1 1 1 1 10 1 1"));
    }

    @Test
    public void testBowlomatic4() {
        System.out.println("Test 4");
        Assert.assertEquals(Integer.valueOf(300), FastBowlScore.calcScore("10 10 10 10 10 10 10 10 10 10 10 10"));
    }

}
