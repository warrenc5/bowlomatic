package app.bowlomatic;

/**
 *
 * @author Warren Crossing
 */
public class FastBowlScore {

    public static Integer calcScore(String score) {
        String[] balls = score.split(" ");

        int total = 0;
        int frameTotal = 0;
        int count = 0;

        for (int i = 0; i < Math.min(balls.length, 10); i++) {
            int ball = Integer.parseInt(balls[i]);

            if (ball == 10) {
                total += ball + ((balls.length > i + 2) ? Integer.parseInt(balls[i + 1]) + Integer.parseInt(balls[i + 2]) : 0);
            } else if (count == 0) {
                frameTotal += ball;
                count++;
            } else {
                frameTotal += ball;
                if (frameTotal == 10) {
                    frameTotal += ((balls.length > i + 1) ? Integer.parseInt(balls[i + 1]) : 0);
                    total += frameTotal;
                } else {
                    total += frameTotal;
                }
                frameTotal = 0;
                count = 0;
            }
            System.out.println(i + " " + ball + " " + count + " " + frameTotal + " " + total);
        }
        return total;
    }

}
