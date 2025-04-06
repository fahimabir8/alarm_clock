import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scan;

    AlarmClock(LocalTime alarmTime, String filePath, Scanner scan){
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scan = scan;
    }

    @Override
    public void run(){

        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);

                LocalTime now  = LocalTime.now();

                System.out.printf("\r%02d:%02d:%02d",
                        now.getHour(),
                        now.getMinute(),
                        now.getSecond());
            }
            catch (InterruptedException e) {
                System.out.println("THread is disturbed.");
            }

        }
        System.out.println("\n**ALARM NOISES**");
        Toolkit.getDefaultToolkit().beep();
        playTungTang(filePath);
    }
    private void playTungTang(String filePath){
        File audioFile  = new File(filePath);


        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        ){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.print("Press enter to stop the alarm:");
            scan.nextLine();
            clip.stop();
            scan.close();
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Audio file format is not supported.");
        }
        catch (LineUnavailableException f){
            System.out.println("Audio is unavailable.");
        }
        catch (IOException e){
            System.out.println("Error reading audio file.");
        }
    }
}
