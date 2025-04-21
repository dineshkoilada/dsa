import AVFoundation

class IOSMediaPlayer: IMediaPlayer {
    private var audioPlayer: AVAudioPlayer?
    private var playlist: [String] = []
    private var currentTrackIndex: Int = 0
    private var repeatMode: RepeatMode = .none
    private var shuffleEnabled: Bool = false
    private var initialized: Bool = false
    
    func initialize() {
        do {
            try AVAudioSession.sharedInstance().setCategory(.playback)
            try AVAudioSession.sharedInstance().setActive(true)
            initialized = true
        } catch {
            print("Failed to initialize audio session: \(error)")
        }
    }
    
    func loadPlaylist(directory: String) {
        playlist.removeAll()
        let fileManager = FileManager.default
        guard let files = try? fileManager.contentsOfDirectory(atPath: directory) else { return }
        
        playlist = files
            .filter { $0.lowercased().hasSuffix(".wav") }
            .map { directory + "/" + $0 }
        
        if shuffleEnabled {
            playlist.shuffle()
        }
    }
    
    func play() {
        guard initialized, !playlist.isEmpty else { return }
        
        if audioPlayer == nil {
            prepareCurrentTrack()
        }
        
        audioPlayer?.play()
    }
    
    func pause() {
        audioPlayer?.pause()
    }
    
    func stop() {
        audioPlayer?.stop()
        audioPlayer = nil
    }
    
    func next() {
        guard !playlist.isEmpty else { return }
        stop()
        currentTrackIndex = (currentTrackIndex + 1) % playlist.count
        play()
    }
    
    func previous() {
        guard !playlist.isEmpty else { return }
        stop()
        currentTrackIndex = (currentTrackIndex - 1 + playlist.count) % playlist.count
        play()
    }
    
    func seekTo(position: Int) {
        audioPlayer?.currentTime = TimeInterval(position) / 1000.0
    }
    
    func setVolume(_ volume: Float) {
        audioPlayer?.volume = volume
    }
    
    func setRepeatMode(_ mode: RepeatMode) {
        self.repeatMode = mode
    }
    
    func setShuffleMode(enabled: Bool) {
        shuffleEnabled = enabled
        if enabled {
            playlist.shuffle()
            currentTrackIndex = 0
        }
    }
    
    func isPlaying() -> Bool {
        return audioPlayer?.isPlaying ?? false
    }
    
    func getCurrentPosition() -> Int {
        return Int((audioPlayer?.currentTime ?? 0) * 1000)
    }
    
    func getDuration() -> Int {
        return Int((audioPlayer?.duration ?? 0) * 1000)
    }
    
    func getCurrentTrack() -> String? {
        guard !playlist.isEmpty else { return nil }
        return playlist[currentTrackIndex]
    }
    
    private func prepareCurrentTrack() {
        guard let trackPath = getCurrentTrack() else { return }
        let url = URL(fileURLWithPath: trackPath)
        
        do {
            audioPlayer = try AVAudioPlayer(contentsOf: url)
            audioPlayer?.delegate = self
            audioPlayer?.prepareToPlay()
        } catch {
            print("Failed to load audio file: \(error)")
        }
    }
}

extension IOSMediaPlayer: AVAudioPlayerDelegate {
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        switch repeatMode {
        case .none:
            if currentTrackIndex < playlist.count - 1 {
                next()
            }
        case .single:
            play()
        case .all:
            next()
        }
    }
}