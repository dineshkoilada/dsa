package patterns.media_player.android;

import android.content.Context;
import android.media.MediaPlayer;
import patterns.media_player.common.IMediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AndroidMediaPlayer implements IMediaPlayer {
    private MediaPlayer mediaPlayer;
    private final Context context;
    private List<String> playlist;
    private int currentTrackIndex;
    private RepeatMode repeatMode;
    private boolean shuffleEnabled;
    private boolean initialized;

    public AndroidMediaPlayer(Context context) {
        this.context = context;
        this.playlist = new ArrayList<>();
        this.currentTrackIndex = 0;
        this.repeatMode = RepeatMode.NONE;
        this.shuffleEnabled = false;
    }

    @Override
    public void initialize() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(mp -> handleTrackCompletion());
        initialized = true;
    }

    @Override
    public void loadPlaylist(String directory) {
        File dir = new File(directory);
        playlist.clear();
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".wav"));
            if (files != null) {
                for (File file : files) {
                    playlist.add(file.getAbsolutePath());
                }
            }
        }
        if (shuffleEnabled) {
            Collections.shuffle(playlist);
        }
    }

    @Override
    public void play() {
        if (!initialized || playlist.isEmpty()) return;
        try {
            if (!mediaPlayer.isPlaying()) {
                if (mediaPlayer.getCurrentPosition() == 0) {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(getCurrentTrack());
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    public void next() {
        if (playlist.isEmpty()) return;
        stop();
        currentTrackIndex = (currentTrackIndex + 1) % playlist.size();
        play();
    }

    @Override
    public void previous() {
        if (playlist.isEmpty()) return;
        stop();
        currentTrackIndex = (currentTrackIndex - 1 + playlist.size()) % playlist.size();
        play();
    }

    @Override
    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    @Override
    public void setVolume(float volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume, volume);
        }
    }

    @Override
    public void setRepeatMode(RepeatMode mode) {
        this.repeatMode = mode;
    }

    @Override
    public void setShuffleMode(boolean enabled) {
        this.shuffleEnabled = enabled;
        if (enabled && !playlist.isEmpty()) {
            Collections.shuffle(playlist);
        }
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
    }

    @Override
    public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
    }

    @Override
    public String getCurrentTrack() {
        return playlist.isEmpty() ? null : playlist.get(currentTrackIndex);
    }

    private void handleTrackCompletion() {
        switch (repeatMode) {
            case NONE:
                if (currentTrackIndex < playlist.size() - 1) {
                    next();
                }
                break;
            case SINGLE:
                play();
                break;
            case ALL:
                next();
                break;
        }
    }
}