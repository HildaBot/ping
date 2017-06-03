package ch.jamiete.hilda.ping;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import ch.jamiete.hilda.Hilda;

public class PingMonitor implements Runnable {
    private final Hilda hilda;
    private final CircularFifoQueue<Long> q = new CircularFifoQueue<Long>(30); // 15 min at 2 per min

    public PingMonitor(final Hilda hilda) {
        this.hilda = hilda;
    }

    @Override
    public void run() {
        synchronized (this.q) {
            this.q.add(this.hilda.getBot().getPing());
        }
    }

    /**
     * Gets the number of minutes captured by the monitor
     * @return Number of minutes captured
     */
    public int getMinutes() {
        synchronized (this.q) {
            return this.q.size() < 2 ? 0 : this.q.size() / 2;
        }
    }

    /**
     * Gets the average ping response over a maximum of 15 minutes.
     * @return Average ping response
     */
    public long getAverage() {
        synchronized (this.q) {
            long total = 0;

            for (long p : this.q) {
                total += p;
            }

            return total == 0 ? total : total / this.q.size();
        }
    }

}
