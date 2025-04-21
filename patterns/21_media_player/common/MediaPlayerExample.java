public class MediaPlayerExample {
    public static void demonstrateUsage(IMediaPlayer mediaPlayer) {
        // Initialize the media player
        mediaPlayer.initialize();
        
        // Configure settings
        MediaPlayerConfig config = MediaPlayerConfig.getInstance();
        config.setDefaultVolume(0.8f);
        config.setFadeInDuration(500);
        config.setFadeOutDuration(500);
        config.setVolumeNormalizationEnabled(true);
        
        // Set up playlist directory
        String playlistDir = "path/to/your/playlists";
        config.setPlaylistDirectory(playlistDir);
        
        // Create playlist manager
        PlaylistManager playlistManager = new PlaylistManager(playlistDir);
        
        try {
            // Load WAV files from a directory
            mediaPlayer.loadPlaylist("path/to/wav/files");
            
            // Basic playback controls
            mediaPlayer.play();
            Thread.sleep(2000); // Play for 2 seconds
            
            mediaPlayer.pause();
            Thread.sleep(1000); // Pause for 1 second
            
            mediaPlayer.play();
            Thread.sleep(2000);
            
            // Volume control
            mediaPlayer.setVolume(0.5f);
            Thread.sleep(1000);
            
            // Navigation
            mediaPlayer.next();
            Thread.sleep(2000);
            
            mediaPlayer.previous();
            Thread.sleep(2000);
            
            // Playback modes
            mediaPlayer.setRepeatMode(IMediaPlayer.RepeatMode.SINGLE);
            mediaPlayer.setShuffleMode(true);
            
            // Clean up
            mediaPlayer.stop();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Example of how to use the media player in different platforms
        
        // For Android:
        // AndroidMediaPlayer androidPlayer = new AndroidMediaPlayer(context);
        // demonstrateUsage(androidPlayer);
        
        // For iOS:
        // IOSMediaPlayer iosPlayer = new IOSMediaPlayer();
        // demonstrateUsage(iosPlayer);
    }
}