package musicPlayer.youtube;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VideoSearch {

    private String batchFilePath;
    private ArrayList<Video> videos;
    private ArrayList<ImageView> images = new ArrayList<>();
    private TextField search;

    public VideoSearch(String batchFilePath, TextField search) {
        this.batchFilePath = batchFilePath;
        this.search = search;
    }

    public void searchYoutube() {
        videos = new ArrayList<>();
        String searchTerm = this.search.getText();
//        String path = "C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/Youtube/YoutubeSearch/res/Scripts/search.bat \"" + searchTerm + "\"";
        String path = batchFilePath + " \"" + searchTerm + "\"";
        try {
            Process runtime = Runtime.getRuntime().exec(path);
            BufferedReader input = new BufferedReader(new InputStreamReader(runtime.getInputStream()));

            String s;
            while ((s = input.readLine()) != null) {

                if (s.length() > 0 && s.contains("https")) {
                    String refine1 = s.substring(1, s.length()-1);
                    String refine2 = refine1.replaceAll("'", "");
                    String refine3 = refine2.trim();
                    String[] refine4 = refine3.split("\\?sqp=-");
                    String photoURL = refine4[0].trim();
                    String[] data = refine4[1].split(",");

                    System.out.println("Photo URL " + photoURL);
                    System.out.println("Video URL " + data[1].trim());
                    System.out.println("title " + data[2].trim());

                    videos.add(new Video(photoURL, data[1].trim(), data[2].trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

}
