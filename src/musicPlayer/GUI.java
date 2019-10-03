package musicPlayer;

import javafx.beans.binding.Bindings;
import kradle.Display;
import kradle.screens.ScreenManager;
import kradle.transitions.Transitions;
import musicPlayer.screens.MainScreen;
import musicPlayer.screens.PlaylistScreen;
import musicPlayer.screens.QueueScreen;
import musicPlayer.screens.SearchScreen;

import static musicPlayer.Controller.queue;

public class GUI {

    private ScreenManager screenManager;
    private MainScreen mainScreen;
    private QueueScreen queueScreen;
    private SearchScreen searchScreen;
    private PlaylistScreen playlistScreen;

    // NODES FOR MAIN VIEW SCREEN
    private Display display;
    private Player player;


    public void createDisplay(Player player) {
        this.player = player;
        screenManager = new ScreenManager();

        display = new Display(500, 600, "Music player");
        display.setStartPos(400, 400);

        mainScreen = new MainScreen(display.getWidth(), display.getHeight());
        queueScreen = new QueueScreen(display.getWidth(), display.getHeight());
        searchScreen = new SearchScreen(display.getWidth(), display.getHeight());
        playlistScreen = new PlaylistScreen(display.getWidth(), display.getHeight());

        screenManager.setScreen(mainScreen.getScreen());

        setUpMainViewNodes();
        setUpQueueViewNodes();
        setUpPlaylistsViewNodes();
        setUpSearchViewNodes();

        Transitions trans = new Transitions(mainScreen.getSongTitle(), 5000, -300);
        trans.play();
        display.createDisplay(screenManager, false);

        playSong(queue.getCurrentSong());
    }

    private void setUpSearchViewNodes() {
        searchScreen.getMain().setOnMouseClicked(e -> display.setScreen(mainScreen.getScreen().getScene()));
        searchScreen.getQueueList().setOnMouseClicked(e -> display.setScreen(queueScreen.getScreen().getScene()));
        searchScreen.getPlaylists().setOnMouseClicked(e -> display.setScreen(playlistScreen.getScreen().getScene()));
        searchScreen.getSearch().setOnMouseClicked(e -> display.setScreen(searchScreen.getScreen().getScene()));
    }

    private void setUpPlaylistsViewNodes() {
        playlistScreen.getMain().setOnMouseClicked(e -> display.setScreen(mainScreen.getScreen().getScene()));
        playlistScreen.getQueueList().setOnMouseClicked(e -> display.setScreen(queueScreen.getScreen().getScene()));
        playlistScreen.getPlaylists().setOnMouseClicked(e -> display.setScreen(playlistScreen.getScreen().getScene()));
        playlistScreen.getSearch().setOnMouseClicked(e -> display.setScreen(searchScreen.getScreen().getScene()));
    }

    private void setUpQueueViewNodes() {
        queueScreen.getMain().setOnMouseClicked(e -> display.setScreen(mainScreen.getScreen().getScene()));
        queueScreen.getQueueList().setOnMouseClicked(e -> display.setScreen(queueScreen.getScreen().getScene()));
        queueScreen.getPlaylists().setOnMouseClicked(e -> display.setScreen(playlistScreen.getScreen().getScene()));
        queueScreen.getSearch().setOnMouseClicked(e -> display.setScreen(searchScreen.getScreen().getScene()));
    }

    private void setUpMainViewNodes() {
        mainScreen.getMain().setOnMouseClicked(e -> display.setScreen(mainScreen.getScreen().getScene()));
        mainScreen.getQueueList().setOnMouseClicked(e -> display.setScreen(queueScreen.getScreen().getScene()));
        mainScreen.getPlaylists().setOnMouseClicked(e -> display.setScreen(playlistScreen.getScreen().getScene()));
        mainScreen.getSearch().setOnMouseClicked(e -> display.setScreen(searchScreen.getScreen().getScene()));

        mainScreen.getNextHover().setOnMouseClicked(e -> playSong(queue.getNextSong()));
        mainScreen.getNextHover().setOnMouseExited(e -> mainScreen.setToNormal(player.isPlaying()));
        mainScreen.getNext().setOnMouseEntered(e -> mainScreen.setNextHover(player.isPlaying()));

        mainScreen.getPlayHover().setOnMouseClicked(e -> player.play());
        mainScreen.getPlay().setOnMouseEntered(e -> mainScreen.setPlayHover());
        mainScreen.getPlayHover().setOnMouseExited(e -> mainScreen.setToNormal(player.isPlaying()));

        mainScreen.getBackHover().setOnMouseClicked(e -> {
            if (player.getCurrentTime() < 3) {
                playSong(queue.getPreviousSong());
            } else playSong(queue.getCurrentSong());
        });


        mainScreen.getBackHover().setOnMouseExited(e -> mainScreen.setToNormal(player.isPlaying()));
        mainScreen.getBack().setOnMouseEntered(e -> mainScreen.setPreviousHover(player.isPlaying()));

        mainScreen.getPauseHover().setOnMouseClicked(e -> player.pause());
        mainScreen.getPauseHover().setOnMouseExited(e -> mainScreen.setToNormal(player.isPlaying()));
        mainScreen.getPause().setOnMouseEntered(e -> mainScreen.setPauseHover());

        mainScreen.getQueueList().setOnMouseClicked(e -> {
            queueScreen.addSongsToList(queue.getSongs());
            display.setScreen(queueScreen.getScreen().getScene());
        });
    }

    private void bindLabelToCurrentSong() {
        mainScreen.getTime().textProperty().bind(
                Bindings.createStringBinding(() -> {
                    double time = player.getCurrentTime();
                    mainScreen.getProgressBar().setProgress(time/100);
                    return formatTime((int) time);
                }, player.getCurrentTimeProperty()));
    }

    private String formatTime(int currentTime) {
        if (currentTime > 59) {
            int minutes = Math.floorDiv(currentTime, 60);
            int seconds = currentTime % 60;
            return minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
        } else return "0:" + (currentTime < 10 ? "0" + currentTime : currentTime);
    }

    private void playSong(Song song) {
        if (player.playNewSong(song)) {
            mainScreen.setSongText(song.getTitle());
            bindLabelToCurrentSong();
        }
    }


}
