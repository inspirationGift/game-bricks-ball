package main.com.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Scheduler test")
public class SchedulerTest {

    @BeforeEach
    void setUp() {
    }


    @Test
    public void checkScheduler_NoDelay() {
        assertFalse(Scheduler.isDelayed(), "delay doesnt set up = 0. Delay doesnt proceed");
        assertEquals(0, Scheduler.getDelay());
    }


    @Test
    public void checkScheduler_HasDelay_5000() {
        Scheduler.setDelay(5000);
        assertTrue(Scheduler.isDelayed());
        assertEquals(5000, Scheduler.getDelay());
        Scheduler.isDelayed();
        assertNotEquals(Scheduler.getPreviousTime(), Scheduler.getCurrentTime());

        Scheduler.isDelayed();
        assertNotEquals(Scheduler.getPreviousTime(), Scheduler.getCurrentTime());

        Scheduler.setDelay(5000);
        assertEquals(5000, Scheduler.getDelay());
    }

    @Test
    public void checkScheduler_Wait_2_sec_Still_isDelay_Working() throws InterruptedException {
        Scheduler.setDelay(2000);
        assertTrue(Scheduler.isDelayed());

        TimeUnit.MILLISECONDS.sleep(1000);
        assertTrue(Scheduler.isDelayed());
        assertEquals(2000, Scheduler.getDelay());
    }

    @Test
    public void checkScheduler_Wait_After_isDelay_Past() throws InterruptedException {
        Scheduler.setDelay(1000);
        assertTrue(Scheduler.isDelayed());
        TimeUnit.MILLISECONDS.sleep(1001);
        assertFalse(Scheduler.isDelayed());
        assertEquals(0, Scheduler.getDelay());


    }

}
