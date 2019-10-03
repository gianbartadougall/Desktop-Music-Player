package musicPlayer;

import java.util.ArrayList;

public class Album {

    private ArrayList<Song> songs = new ArrayList<>();

    public Album(ArrayList<String> songs) {
        convertToSongs(songs);
    }

    private void convertToSongs(ArrayList<String> songs) {
        for (String title : songs) {
            this.songs.add(new Song(title, title, Song.NOT_PLAYING));
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

}
