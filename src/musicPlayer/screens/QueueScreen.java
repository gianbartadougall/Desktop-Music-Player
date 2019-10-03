package musicPlayer.screens;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import kradle.Nodes.LabelNode;
import kradle.Nodes.Styles.LabelStyle;
import kradle.screens.Screen;
import musicPlayer.Song;

import java.util.LinkedList;

import static kradle.Layouts.HboxLayout.createHBox;
import static kradle.Layouts.LayoutBorderPane.createBorderPane;

public class QueueScreen {

    private Screen screen;
    private BorderPane borderPane;
    private HBox topSection;
    private ListView<String> queue;
    private int displayWidth, displayHeight;
    private Label main, queueList, playlists, search;

    public QueueScreen(int displayWidth, int displayHeight) {
        createSettingsScreen(displayWidth, displayHeight);
    }

    private void createSettingsScreen(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        createSettingsView();

        screen = new Screen(borderPane);
    }

    private Parent createSettingsView() {
        borderPane = createBorderPane();
        borderPane.setMinSize(displayWidth, displayHeight);
        borderPane.setMaxSize(displayWidth, displayHeight);
        queue = new ListView<>();
        borderPane.setCenter(queue);

        topSection = createControlPanel();
        borderPane.setTop(topSection);

        return borderPane;
    }

    private HBox createControlPanel() {
        HBox hBox = createHBox(10, Pos.CENTER_LEFT);
        hBox.setStyle("-fx-background-color: black;");
        hBox.setMinSize(displayWidth, 40);
        hBox.setMaxSize(displayWidth, 40);

        main = LabelNode.createLabel("Main", 40, 100, 16, LabelStyle.LABEL_WHITE_NO_BORDER);
        queueList = LabelNode.createLabel("Queue", 40, 100, 16, LabelStyle.LABEL_WHITE_NO_BORDER);
        playlists = LabelNode.createLabel("Playlists", 40, 100, 16, LabelStyle.LABEL_WHITE_NO_BORDER);
        search = LabelNode.createLabel("Search", 40, 100, 16, LabelStyle.LABEL_WHITE_NO_BORDER);

        hBox.setOnMouseEntered(e -> showControlPanel());
        hBox.setOnMouseExited(e -> hideControlPanel());
        return hBox;
    }

    public void showControlPanel() {
        topSection.getChildren().addAll(main, queueList, playlists, search);
    }

    public void hideControlPanel() {
        topSection.getChildren().clear();
    }

    public void addSongToList(Song song) {
        queue.getItems().add(song.getTitle());
    }

    public ListView<String> getQueue() {
        return queue;
    }

    public void addSongsToList(LinkedList<Song> songs) {
        for (Song song : songs) {
            queue.getItems().add(song.getTitle());
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public Label getMain() {
        return main;
    }

    public Label getQueueList() {
        return queueList;
    }

    public Label getPlaylists() {
        return playlists;
    }

    public Label getSearch() {
        return search;
    }
}
