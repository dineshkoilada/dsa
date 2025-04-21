public class MediaPlayerConfig {
    private static final MediaPlayerConfig instance = new MediaPlayerConfig();
    
    private float defaultVolume = 1.0f;
    private int fadeInDurationMs = 500;
    private int fadeOutDurationMs = 500;
    private boolean enableVolumeNormalization = true;
    private String playlistDirectory;
    
    private MediaPlayerConfig() {}
    
    public static MediaPlayerConfig getInstance() {
        return instance;
    }
    
    public float getDefaultVolume() {
        return defaultVolume;
    }
    
    public void setDefaultVolume(float volume) {
        this.defaultVolume = Math.max(0.0f, Math.min(1.0f, volume));
    }
    
    public int getFadeInDuration() {
        return fadeInDurationMs;
    }
    
    public void setFadeInDuration(int durationMs) {
        this.fadeInDurationMs = Math.max(0, durationMs);
    }
    
    public int getFadeOutDuration() {
        return fadeOutDurationMs;
    }
    
    public void setFadeOutDuration(int durationMs) {
        this.fadeOutDurationMs = Math.max(0, durationMs);
    }
    
    public boolean isVolumeNormalizationEnabled() {
        return enableVolumeNormalization;
    }
    
    public void setVolumeNormalizationEnabled(boolean enabled) {
        this.enableVolumeNormalization = enabled;
    }
    
    public String getPlaylistDirectory() {
        return playlistDirectory;
    }
    
    public void setPlaylistDirectory(String directory) {
        this.playlistDirectory = directory;
    }
}