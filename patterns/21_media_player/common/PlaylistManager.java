import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {
    private static final String PLAYLIST_FILE_EXTENSION = ".playlist";
    private final String playlistDirectory;
    
    public PlaylistManager(String playlistDirectory) {
        this.playlistDirectory = playlistDirectory;
    }
    
    public void savePlaylist(String name, List<String> tracks) throws IOException {
        File playlistFile = new File(playlistDirectory, name + PLAYLIST_FILE_EXTENSION);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playlistFile))) {
            for (String track : tracks) {
                writer.write(track);
                writer.newLine();
            }
        }
    }
    
    public List<String> loadPlaylist(String name) throws IOException {
        List<String> tracks = new ArrayList<>();
        File playlistFile = new File(playlistDirectory, name + PLAYLIST_FILE_EXTENSION);
        
        if (!playlistFile.exists()) {
            return tracks;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(playlistFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (WavUtils.isValidWavFile(line)) {
                    tracks.add(line);
                }
            }
        }
        
        return tracks;
    }
    
    public List<String> getAvailablePlaylists() {
        List<String> playlists = new ArrayList<>();
        File directory = new File(playlistDirectory);
        
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> 
                name.toLowerCase().endsWith(PLAYLIST_FILE_EXTENSION));
                
            if (files != null) {
                for (File file : files) {
                    String name = file.getName();
                    playlists.add(name.substring(0, name.length() - PLAYLIST_FILE_EXTENSION.length()));
                }
            }
        }
        
        return playlists;
    }
    
    public void deletePlaylist(String name) {
        File playlistFile = new File(playlistDirectory, name + PLAYLIST_FILE_EXTENSION);
        if (playlistFile.exists()) {
            playlistFile.delete();
        }
    }
    
    public void addTrackToPlaylist(String playlistName, String trackPath) throws IOException {
        List<String> tracks = loadPlaylist(playlistName);
        if (!tracks.contains(trackPath) && WavUtils.isValidWavFile(trackPath)) {
            tracks.add(trackPath);
            savePlaylist(playlistName, tracks);
        }
    }
    
    public void removeTrackFromPlaylist(String playlistName, String trackPath) throws IOException {
        List<String> tracks = loadPlaylist(playlistName);
        if (tracks.remove(trackPath)) {
            savePlaylist(playlistName, tracks);
        }
    }
    
    public void reorderTrack(String playlistName, int fromIndex, int toIndex) throws IOException {
        List<String> tracks = loadPlaylist(playlistName);
        if (fromIndex >= 0 && fromIndex < tracks.size() && 
            toIndex >= 0 && toIndex < tracks.size() && 
            fromIndex != toIndex) {
            
            String track = tracks.remove(fromIndex);
            tracks.add(toIndex, track);
            savePlaylist(playlistName, tracks);
        }
    }
}