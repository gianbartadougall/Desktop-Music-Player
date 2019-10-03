package musicPlayer;

public class Song {

    public static final int NOT_PLAYING = 0, PLAYING = 1, PAUSED = 2;

    private String fileLocation, title;
    private int status, duration;

    public Song(String title, String fileLocation, int status) {
        this.title = title.replaceAll("\\.mp4", "");
        this.fileLocation = fileLocation.replaceAll(" ", "%20");;
        this.status = status;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}
