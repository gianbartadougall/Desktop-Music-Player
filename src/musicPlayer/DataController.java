package musicPlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataController {

    private static Scanner scanner;

    public static void loadFile(String resFolderPath) {
        File[] files = new File(resFolderPath).listFiles();

        // Need to handle possible null pointer exception

        for (File f : files) {
            if (f.getName().equals("Albums.txt")) {
                return;
            }
        }

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(resFolderPath + "Albums.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mediaPath = resFolderPath + "Media";
        files = new File(mediaPath).listFiles();

        // Need to handle possible null pointer exception

        writer.print("album:");

        for (File f : files) {

            if (f.getName().contains(".mp4")) {
                writer.print(f.getName() + ",");
            }
        }
        writer.close();
    }

    public static ArrayList<String> loadAlbum(String resFolderPath, String albumTitle) {

        loadFile(resFolderPath);

        String albumsPath = resFolderPath + "Albums.txt";

        ArrayList<String> songs = new ArrayList<>();

        try {
            scanner = new Scanner(new File(albumsPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(albumTitle)) {
                    String[] data = line.split(":");
                    songs.addAll(Arrays.asList(data[1].split(",")));
                }
            }

        } catch (IOException e) {
            System.out.println("could not find file");
        } finally {
            scanner.close();
        }
        return songs;
    }

    public static void addNewSongToAlbumFile(String nameOfSong) {
        try {
            FileWriter writer = new FileWriter("res/Music", true);
            writer.append(nameOfSong).append(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
