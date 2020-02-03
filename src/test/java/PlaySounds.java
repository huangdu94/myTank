import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//播放声音的类
public class PlaySounds implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        PlaySounds playSounds = new PlaySounds("/shoot.wav");
        new Thread(playSounds).start();
        Thread.sleep(1000);
        new Thread(playSounds).start();
    }

    private String filename;

    public PlaySounds(String wavfile) {
        System.out.println(getClass().getResource(wavfile).getPath());
        filename = getClass().getResource(wavfile).getPath();
    }

    public void run() {

        File soundFile = new File(filename);

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        auline.start();
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[512];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
}