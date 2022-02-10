package game.listeners;

/**
 * Hit notifier interface.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl listener object
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl listener object
     */
    void removeHitListener(HitListener hl);
}
