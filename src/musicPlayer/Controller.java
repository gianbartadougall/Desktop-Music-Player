package musicPlayer;

public class Controller {

    private String albumLocation = "C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/MusicPlayer/res/";
    private GUI gui;
    private Player player;
    public static Queue queue;

    public void startApp(String musicLocation) {

        queue = new Queue();
        Album album = new Album(DataController.loadAlbum(albumLocation, "album"));
        Album album1 = new Album(DataController.loadAlbum(albumLocation, "album1"));
        queue.addAlbum(album);
        queue.addAlbum(album1);

        player = new Player(musicLocation);

        gui = new GUI();
        gui.createDisplay(player);

    }

}
