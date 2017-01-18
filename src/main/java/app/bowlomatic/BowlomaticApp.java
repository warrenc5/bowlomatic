package app.bowlomatic;

import static app.bowlomatic.BowlomaticApp.StateMachine.State.FILL;
import static app.bowlomatic.BowlomaticApp.StateMachine.State.SPARE;
import static app.bowlomatic.BowlomaticApp.StateMachine.State.START;
import static app.bowlomatic.BowlomaticApp.StateMachine.State.STRIKE;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Warren Crossing
 */
public class BowlomaticApp {

    public static void main(String[] args) {
        new BowlomaticApp().calculateScore(args);
    }

    private void calculateScore(String[] balls) {
        new BowlomaticApp().calculateScore(balls);
    }

    public Integer calculateScore(String balls) {
        return this.calculateScore(BowlomaticApp.this.createQueue(balls));
    }

    /**
     * Take the input data and produce the queue of Integers.
     *
     * @param rolls
     * @return
     */
    private Deque createQueue(String rolls) {
        String[] split = rolls.split(" ");
        System.out.println(Arrays.asList(split).toString());
        return createQueue(split);
    }

    private Deque createQueue(String[] rolls) {
        List<Integer> intRolls = new ArrayList<Integer>(rolls.length);

        for (String pins : rolls) {
            intRolls.add(Integer.valueOf(pins));
        }

        Deque<Integer> deque = new ArrayDeque<Integer>(intRolls);
        System.out.println(deque.size());
        return deque;
    }

    private int calculateScore(Deque<Integer> deque) {
        StateMachine stateMachine = new StateMachine(deque);
        return stateMachine.readFrames();
    }

    static class StateMachine {

        private Deque<Integer> deque;
        private Frame currentFrame;
        private Deque<Frame> frames;
        private static final int FILL_BALL = 10;

        public StateMachine(Deque<Integer> deque) {
            this.deque = deque;
            frames = new ArrayDeque<Frame>();
        }

        /**
         * Read all the frames and keep a running total
         * @return 
         */
        private int readFrames() {
            int runningTotal = 0;
            resetFrame();

            while (!deque.isEmpty()) {
                readNextBall(START);
                if (currentFrame.isCompleted()) {
                    runningTotal += currentFrame.calculateFrameTotal();
                    System.out.println("frame: " + (frames.size() + 1) + " " + currentFrame + " => " + runningTotal);
                    resetFrame();
                }
            }

            System.out.println("==" + runningTotal);
            return runningTotal;
        }

        private void readNextBall(State state) {

            if (deque.peekFirst() == null) {
                return;
            }

            int pins = deque.pollFirst();
            if (fillBall()) {
                state = FILL;
            }

            switch (state) {
                case STRIKE:
                case SPARE:
                    currentFrame.addBonus(pins);
                    deque.addFirst(pins);
                    break;
                case FILL:
                    break;
                default:

                    currentFrame.addBall(pins);
                    if (currentFrame.isStrike()) {
                        readNextBall(STRIKE);
                        readNextBall(STRIKE);
                    } else if (currentFrame.isSpare()) {
                        readNextBall(SPARE);
                    }
            }
        }

        private void resetFrame() {
            if (currentFrame != null) {
                frames.addLast(currentFrame);
            }

            currentFrame = new Frame();
        }

        private boolean fillBall() {
            return frames.size() >= FILL_BALL;
        }

        static enum State {
            START,
            STRIKE,
            SPARE,
            FILL;
        }
    }

    static class Frame {

        private int bonus;
        private int ball1, ball2;
        private int bowls;

        private static final int TEN = 10;

        private Frame() {
        }

        private void addBall(int pins) {
            if (bowls == 0) {
                ball1 = pins;
            } else {
                ball2 = pins;
            }
            bowls++;
        }

        private int calculateFrameTotal() {
            return ball1 + ball2 + bonus;
        }

        private boolean isCompleted() {
            return bowls == 2 || (bowls == 1 && ball1 == TEN);
        }

        private void addBonus(int bonus) {
            this.bonus += bonus;
        }

        private boolean isStrike() {
            return ball1 == TEN;
        }

        private boolean isSpare() {
            return ball1 != TEN && (ball1 + ball2 == TEN);
        }

        @Override
        public String toString() {
            return "Frame{" + "bonus=" + bonus + ", ball1=" + ball1 + ", ball2=" + ball2 + ", bowls=" + bowls + '}';
        }
    }
}
