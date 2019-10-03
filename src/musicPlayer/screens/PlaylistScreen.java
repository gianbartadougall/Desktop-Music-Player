package musicPlayer.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import kradle.Nodes.LabelNode;
import kradle.Nodes.Styles.LabelStyle;
import kradle.screens.Screen;

import static kradle.Layouts.HboxLayout.createHBox;
import static kradle.Layouts.LayoutBorderPane.createBorderPane;

public class PlaylistScreen {


    private Screen screen;
    private int displayWidth, displayHeight;
    private BorderPane borderPane;
    private HBox topSection;
    private Label main, queueList, playlists, search;

    public PlaylistScreen(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        createPlaylistScreen();
    }

    private void createPlaylistScreen() {
        borderPane = createBorderPane("black", "black");
        borderPane.setMinSize(displayWidth, displayHeight);
        borderPane.setMaxSize(displayWidth, displayHeight);

        topSection = createControlPanel();
        borderPane.setTop(topSection);



        screen = new Screen(borderPane);
    }

    private HBox createControlPanel() {
        HBox hBox = createHBox(10, Pos.CENTER_LEFT);
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
