package platform.basis.esb.app.support;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AliveKeeping {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliveKeeping.class);

    private static Timer timer;

    private static AtomicBoolean start = new AtomicBoolean(false);

    public static void start() {
        if (start.compareAndSet(false, true)) {
            timer = new Timer("AliveKeepingService");
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("I'm alive");
                    }
                }
            }, 1000 * 60 * 10, 1000 * 60 * 10);
        }
    }

    public static void stop() {
        if (start.compareAndSet(true, false)) {
            if (timer != null) {
                timer.cancel();
            }
        }
    }
}
