package musicPlayer;

import java.util.LinkedList;

public class Queue {

    private LinkedList<Song> queue;
    private int index;

    public Queue() {
        queue = new LinkedList<>();
        index = 0;
    }

    public Song getCurrentSong() {
        return queue.get(index);
    }

    public Song getNextSong() {
        if (index == queue.size()-1) {
            index = 0;
        } else index++;
        return queue.get(index);
    }

    public Song getPreviousSong() {
        if (index == 0) {
            index = queue.size()-1;
        } else index--;
        return queue.get(index);
    }

    public void addAlbum(Album album) {
        queue.addAll(album.getSongs());
    }

    public void clear() {
        queue.clear();
    }

    public void addSong(Song song) {
        queue.add(song);
    }

    public void removeSong() {

    }

    public void printQueue() {
        for (Song song : queue) {
            System.out.println(song.getTitle());
        }
        System.out.println("\n\n");
    }

    public LinkedList<Song> getSongs() {
//        return queue.toArray(new Song[queue.size()]);
        return queue;
    }



}
