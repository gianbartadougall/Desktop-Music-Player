package musicPlayer.youtube;

public class Video {

    private String photoURL;
    private String VideoURL;
    private String title;

    public Video(String photoURL, String videoURL, String title) {
        this.photoURL = photoURL;
        VideoURL = videoURL;
        this.title = title;
    }

    public String getVideoURL() {
        return VideoURL;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getTitle() {
        return title;
    }
}
