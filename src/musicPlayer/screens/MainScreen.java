package musicPlayer.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import kradle.Layouts.LayoutStackPane;
import kradle.Nodes.LabelNode;
import kradle.Nodes.Styles.LabelStyle;
import kradle.screens.Screen;

import java.util.ArrayList;
import java.util.Arrays;

import static kradle.Layouts.HboxLayout.createHBox;
import static kradle.Layouts.LayoutBorderPane.createBorderPane;

public class MainScreen {

    private int displayWidth, displayHeight;
    private Screen screen;
    private BorderPane borderPane;
    private Label songTitle, time, main, queueList, playlists, search;
    private VBox centerSection;
    private StackPane songInfo;
    private HBox bottomSection, topSection;
    private ProgressBar progressBar;
    private ImageView back, play, next, backHover, playHover, nextHover, pause, pauseHover;

    public MainScreen(int displayWidth, int displayHeight) {
        createMainView(displayWidth, displayHeight);
    }

    private void createMainView(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        borderPane = createBorderPane("black", "white");
        borderPane.setMinSize(displayWidth, displayHeight);
        borderPane.setMaxSize(displayWidth, displayHeight);


        bottomSection = createControlBar();
        topSection = createControlPanel();

        songInfo = new StackPane();

        centerSection = new VBox();
        centerSection.setSpacing(10);
        centerSection.getChildren().add(songInfo);

        createLabels();
        createProgressBar();

        borderPane.setBottom(bottomSection);
        borderPane.setCenter(centerSection);
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

    private void createProgressBar() {
        HBox hBox = new HBox();

        time = new Label();
        time.setMinSize(10, 10);
        time.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        progressBar = new ProgressBar();
        progressBar.setMaxSize(200, 10);
        progressBar.setMinSize(200, 10);
        progressBar.setStyle("-fx-accent: green;");
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(time, progressBar);
        centerSection.getChildren().add(hBox);
    }

    private void createLabels() {
        songTitle = LabelNode.createLabel("Unknown", 50, 100, 16, LabelStyle.LABEL_WHITE_NO_BORDER);
//        songTitle.getStylesheets().add("label-blue");
        songTitle.setAlignment(Pos.CENTER);
        StackPane.setAlignment(songTitle, Pos.CENTER);

        Label leftLabel = LabelNode.createLabel("", 100, 60, 16, new LabelStyle("gray", "gray", "blue"));
        leftLabel.setAlignment(Pos.CENTER);
        StackPane.setAlignment(leftLabel, Pos.CENTER_LEFT);

        Label rightLabel = LabelNode.createLabel("", 100, 60, 16, new LabelStyle("gray", "gray", "blue"));
        rightLabel.setAlignment(Pos.CENTER);
        StackPane.setAlignment(rightLabel, Pos.CENTER_RIGHT);

        songInfo.getChildren().addAll(songTitle, leftLabel, rightLabel);
    }

    public HBox createControlBar() {
        String fileLocation = "file:C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/MusicPlayer/res/Images/";

        back = new ImageView(new Image(fileLocation + "previous.png"));
        play = new ImageView(new Image(fileLocation + "play.png"));
        next = new ImageView(new Image(fileLocation + "next.png"));
        pause = new ImageView(new Image(fileLocation + "pause.png"));

        backHover = new ImageView(new Image(fileLocation + "previousHover.png"));
        playHover = new ImageView(new Image(fileLocation + "playHover.png"));
        nextHover = new ImageView(new Image(fileLocation + "nextHover.png"));
        pauseHover = new ImageView(new Image(fileLocation + "pauseHover.png"));

        ArrayList<ImageView> images = new ArrayList<>(Arrays.asList(back, play, next, pause, backHover, playHover, nextHover, pauseHover));

        for (ImageView image : images) {
            image.setPreserveRatio(true);
            image.setFitHeight(40);
            StackPane stackPane = LayoutStackPane.createStackPaneWithImageBackground(image, null, null, null);
            stackPane.setMinSize(40, 40);
            stackPane.setMaxSize(40, 40);
        }

        bottomSection = createHBox(10, Pos.CENTER);
        bottomSection.getChildren().addAll(images.get(0), images.get(3), images.get(2));
        BorderPane.setMargin(bottomSection, new Insets(0, 0, 20, 0));
        return bottomSection;
    }

    public void setSongText(String text) {
        songTitle.setText(text);
    }

    public void setNextHover(boolean playing) {
        bottomSection.getChildren().clear();
        bottomSection.getChildren().addAll(back, playing ? pause : play, nextHover);
    }

    public void setPlayHover() {
        bottomSection.getChildren().clear();
        bottomSection.getChildren().addAll(back, playHover, next);
    }

    public void setPreviousHover(boolean playing) {
        bottomSection.getChildren().clear();
        bottomSection.getChildren().addAll(backHover, playing ? pause : play, next);
    }

    public void setToNormal(boolean playing) {
        bottomSection.getChildren().clear();
        bottomSection.getChildren().addAll(back, playing ? pause : play, next);
    }

    public void setPauseHover() {
        bottomSection.getChildren().clear();
        bottomSection.getChildren().addAll(back, pauseHover, next);
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Label getSongTitle() {
        return songTitle;
    }

    public Label getTime() {
        return time;
    }

    public Label getQueueList() {
        return queueList;
    }

    public VBox getCenterSection() {
        return centerSection;
    }

    public StackPane getSongInfo() {
        return songInfo;
    }

    public HBox getBottomSection() {
        return bottomSection;
    }

    public HBox getTopSection() {
        return topSection;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ImageView getBack() {
        return back;
    }

    public ImageView getPlay() {
        return play;
    }

    public ImageView getNext() {
        return next;
    }

    public ImageView getBackHover() {
        return backHover;
    }

    public ImageView getPlayHover() {
        return playHover;
    }

    public ImageView getNextHover() {
        return nextHover;
    }

    public ImageView getPause() {
        return pause;
    }

    public ImageView getPauseHover() {
        return pauseHover;
    }

    public Screen getScreen() {
        return screen;
    }

    public Label getPlaylists() {
        return playlists;
    }

    public Label getMain() {
        return main;
    }

    public Label getSearch() {
        return search;
    }
}
