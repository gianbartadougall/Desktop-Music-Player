package musicPlayer.youtube;

import java.io.File;
import java.io.IOException;

public class DownloadVideo implements Runnable {

    private String saveLocation;
    private String youtubeExeLocation;
    private String link;
    private Video video;

    public DownloadVideo(String link) {
        this.link = link;
    }

    public DownloadVideo(Video video, String link, String saveLocation, String youtubeExeLocation) {
        this.video = video;
        this.link = link;
        this.saveLocation = saveLocation;
        this.youtubeExeLocation = youtubeExeLocation;
        run();
    }

    @Override
    public void run() {

        Runtime runtime = Runtime.getRuntime();

        try {
            System.out.println("running command " + youtubeExeLocation + " " + link);
            runtime.exec(youtubeExeLocation + " " + link);
            System.out.println("command has been run");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean fileExists = false;
        while (!fileExists) {
            File dir = new File("C:/Users/Gian/Desktop/4221/Java/Java Programs/Test Projects/MusicPlayer/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    if (child.getName().contains(".mp4")) {
                        if (child.renameTo(new File(saveLocation + video.getTitle()))) {
                            fileExists = true;
                            System.out.println("file successfully moved");
                        }
                    } else sleep(1000);
                }
            } else sleep(1000);
        }

        System.out.println("Video succesfully transfered");
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    public void setYoutubeExeLocation(String youtubeExeLocation) {
        this.youtubeExeLocation = youtubeExeLocation;
    }

}

