/**
 * Common interface for WAV file media player implementation
 */
public interface IMediaPlayer {
    void initialize();
    void loadPlaylist(String directory);
    void play();
    void pause();
    void stop();
    void next();
    void previous();
    void seekTo(int position);
    void setVolume(float volume);
    void setRepeatMode(RepeatMode mode);
    void setShuffleMode(boolean enabled);
    boolean isPlaying();
    int getCurrentPosition();
    int getDuration();
    String getCurrentTrack();
    
    enum RepeatMode {
        NONE,
        SINGLE,
        ALL
    }
}