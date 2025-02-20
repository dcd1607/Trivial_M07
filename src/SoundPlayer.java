import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class SoundPlayer {
    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean loop;

    public void play(String filePath) {
        loop = true;
        playMusic(filePath);
    }

    private void playMusic(String filePath) {
        playerThread = new Thread(() -> {
            while (loop) {
                try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                    player = new AdvancedPlayer(fileInputStream);
                    player.play();
                } catch (IOException | JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        playerThread.start();
    }

    public void playSoundEffect(String filePath) {
        new Thread(() -> {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                AdvancedPlayer soundEffectPlayer = new AdvancedPlayer(fileInputStream);
                soundEffectPlayer.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        loop = false;
        stopMusic();
    }

    private void stopMusic() {
        try {
            if (player != null) {
                player.close(); // Atura la música correctament
            }
            if (playerThread != null && playerThread.isAlive()) {
                playerThread.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}