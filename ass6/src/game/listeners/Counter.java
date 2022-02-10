package game.listeners;

/**
 * Class Counter to listen after number of blocks.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the variable to add
     */
    public void increase(int number) {
        this.counter += number;

    }

    /**
     * subtract number from current count.
     *
     * @param number the var to substract from current count.
     */
    public void decrease(int number) {
        counter -= number;

    }

    /**
     * get current count.
     *
     * @return int-- the counter
     */
    public int getValue() {
        return counter;
    }

    @Override
    public String toString() {

        return "Score: " + this.getValue() + "";
    }
}
