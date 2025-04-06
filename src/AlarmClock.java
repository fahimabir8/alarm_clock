import java.time.LocalTime;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;

    AlarmClock(LocalTime alarmTime){
        this.alarmTime = alarmTime;
    }

    @Override
    public void run(){
        LocalTime now = LocalTime.now();

        while (now.isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("THread is disturbed.");
            }

        }
    }

}
