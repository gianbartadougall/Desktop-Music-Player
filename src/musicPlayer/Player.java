package musicPlayer;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Player {

    private MediaPlayer player;
    private Media media = null;
    private String musicLocation;
    private MediaView view;
    private boolean playing = false;

    public Player(String musicLocation) {
        this.musicLocation = musicLocation;
    }

    public boolean playNewSong(Song song) {
        try {
            removeLastSong();
            media = new Media(musicLocation + song.getFileLocation());
            player = new MediaPlayer(media);
            view = new MediaView(player);
            player.play();
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void removeLastSong() {
        if (player != null) {
            player.stop();
        }
    }

    public boolean play() {
        System.out.println("media: " + media);
        if (media != null) {
            System.out.println("status: " + player.getStatus());
            if (player.getStatus() == MediaPlayer.Status.PAUSED) {
                player.play();
                System.out.println("play");
                playing = true;
                return true;
            }
        } return false;
    }

    public boolean pause() {
        if (media != null) {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.pause();
                System.out.println("pausing");
                playing = false;
                return true;
            }
        } return false;
    }

    public MediaView getView() {
        return view;
    }

    public double getCurrentTime() {
        return player.getCurrentTime().toSeconds();
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public ReadOnlyObjectProperty<Duration> getCurrentTimeProperty() {
        return player.currentTimeProperty();
    }
}
