package musicPlayer.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kradle.Nodes.LabelNode;
import kradle.Nodes.Styles.LabelStyle;
import kradle.Nodes.Styles.TextFieldStyle;
import kradle.screens.Screen;
import musicPlayer.DataController;
import musicPlayer.youtube.DownloadVideo;
import musicPlayer.youtube.Video;
import musicPlayer.youtube.VideoSearch;

import java.nio.file.Paths;
import java.util.ArrayList;

import static kradle.Layouts.HboxLayout.createHBox;
import static kradle.Layouts.LayoutBorderPane.createBorderPane;
import static kradle.Layouts.LayoutGridPane.setGridPaneConstraints;
import static kradle.Nodes.TextFieldNode.createTextField;

public class SearchScreen {

    public static final int VIDEO_DISPLAY_HEIGHT = 100, VIDEO_DISPLAY_GAP = 10;

    private Screen screen;
    private TextField textField;
    private BorderPane borderPane;
    private int displayWidth, displayHeight;
    private HBox topSection;
    private Label main, queueList, playlists, search;
    private ScrollPane videos;
    private GridPane gridPane;
    private VideoSearch searchYoutube;
    private String defaultImagePath = "res/Images/video.png",
                   youtubeExeLocation = "C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/MusicPlayer/res/Scripts/youtube-dl.exe",
                   saveVideoLocation = "res/Media/",
                   batchFilePath = "C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/Youtube/YoutubeSearch/res/Scripts/search.bat";

    public SearchScreen(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        createDisplay();
    }

    private void createDisplay() {
        borderPane = createBorderPane("black", "black");
        borderPane.setMinSize(displayWidth, displayHeight);
        borderPane.setMaxSize(displayWidth, displayHeight);

        VBox vBox = new VBox();
        topSection = createControlPanel();
        vBox.getChildren().add(topSection);

        textField = createTextField("New Animation Trailers", 40, 200,
                new TextFieldStyle("white", "black", "black"));
        vBox.getChildren().add(textField);
        vBox.setAlignment(Pos.CENTER);

        borderPane.setTop(vBox);

        videos = createVideoSection();
        borderPane.setCenter(videos);

        screen = new Screen(borderPane);
    }

    private ScrollPane createVideoSection() {
        searchYoutube = new VideoSearch(batchFilePath, textField);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);

        gridPane.setVgap(VIDEO_DISPLAY_GAP);

        textField.setOnAction(e -> {
            searchYoutube.searchYoutube();
            displayVideos(searchYoutube.getVideos());
        });

        scrollPane.setContent(gridPane);
        return scrollPane;
    }

    private void displayVideos(ArrayList<Video> videos) {
        gridPane.getChildren().clear();
        for (int i=0; i<videos.size(); i++) {
            // adding video image
            ImageView imageView = createImage(videos.get(i));
            setGridPaneConstraints(imageView, 1, i, 0, null);
            gridPane.getChildren().add(imageView);

            // adding video title
            Label label = new Label(videos.get(i).getTitle());
            label.setWrapText(true);
            setGridPaneConstraints(label, 1, i, 1, new Insets(0, 0, 0, 10));
            gridPane.getChildren().add(label);

            Button button = new Button("Download");
            button.setMinWidth(100);
            final Video v = videos.get(i);
            button.setOnAction(e -> downLoadVideo(v));
            setGridPaneConstraints(button, 1, i, 2, new Insets(0, 0, 0, 10));
            gridPane.getChildren().add(button);
        }
    }

    private void downLoadVideo(Video v) {
        new DownloadVideo(v, v.getVideoURL(), saveVideoLocation, youtubeExeLocation);
        DataController.addNewSongToAlbumFile(v.getTitle());
        System.out.println("downloading video " + v.getTitle());
    }

    private int getChosenGrid(double y) {
        int height = gridPane.getChildren().size() * 100 + ((gridPane.getChildren().size() - 1) * 10);
        int videoHeight = VIDEO_DISPLAY_HEIGHT + VIDEO_DISPLAY_GAP;
        System.out.println("height: " + height);
        System.out.println("VHeight: " + videos.getVvalue());
        double pixelsMoved = (height * videos.getVvalue());
        double amountToAdjust = height - pixelsMoved;
        double video = (videoHeight + (height * videos.getVvalue()));
        System.out.println("video chosen was " + video);



        return 0;
    }

    private ImageView createImage(Video video) {
        ImageView image = new ImageView(new Image(video.getPhotoURL()));
        image.setPreserveRatio(true);
        image.setFitHeight(VIDEO_DISPLAY_HEIGHT);
        return image;
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

    private ImageView getDefaultImage() {
        ImageView defaultImage = new ImageView(new Image(toURL(defaultImagePath)));
        defaultImage.setPreserveRatio(true);
        defaultImage.setFitHeight(120);
        return defaultImage;
    }

    private String toURL(String path) {
        return Paths.get(path).toAbsolutePath().toUri().toString();
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
