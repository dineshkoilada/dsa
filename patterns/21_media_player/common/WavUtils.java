import java.io.*;
import java.util.Arrays;

public class WavUtils {
    private static final int HEADER_SIZE = 44;
    private static final int SAMPLE_RATE = 44100;
    
    public static class WavHeader {
        public int sampleRate;
        public short channels;
        public short bitsPerSample;
        public int dataSize;
    }
    
    public static WavHeader readWavHeader(String filePath) throws IOException {
        WavHeader header = new WavHeader();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] headerData = new byte[HEADER_SIZE];
            fis.read(headerData);
            
            // Verify RIFF header
            if (!new String(Arrays.copyOfRange(headerData, 0, 4)).equals("RIFF")) {
                throw new IOException("Invalid WAV file format");
            }
            
            header.channels = (short)(headerData[22] & 0xFF);
            header.sampleRate = (headerData[24] & 0xFF) | 
                              ((headerData[25] & 0xFF) << 8) |
                              ((headerData[26] & 0xFF) << 16) |
                              ((headerData[27] & 0xFF) << 24);
            header.bitsPerSample = (short)(headerData[34] & 0xFF);
            header.dataSize = (headerData[40] & 0xFF) |
                            ((headerData[41] & 0xFF) << 8) |
                            ((headerData[42] & 0xFF) << 16) |
                            ((headerData[43] & 0xFF) << 24);
        }
        return header;
    }
    
    public static float[] normalizeVolume(float[] audioData) {
        float maxAmplitude = 0.0f;
        for (float sample : audioData) {
            maxAmplitude = Math.max(maxAmplitude, Math.abs(sample));
        }
        
        if (maxAmplitude > 0) {
            float normalizedFactor = 1.0f / maxAmplitude;
            float[] normalizedData = new float[audioData.length];
            for (int i = 0; i < audioData.length; i++) {
                normalizedData[i] = audioData[i] * normalizedFactor;
            }
            return normalizedData;
        }
        
        return audioData;
    }
    
    public static float[] applyFadeEffect(float[] audioData, int fadeInSamples, int fadeOutSamples) {
        float[] processedData = Arrays.copyOf(audioData, audioData.length);
        
        // Apply fade in
        for (int i = 0; i < fadeInSamples && i < processedData.length; i++) {
            float factor = (float) i / fadeInSamples;
            processedData[i] *= factor;
        }
        
        // Apply fade out
        int startFadeOut = processedData.length - fadeOutSamples;
        for (int i = 0; i < fadeOutSamples && (startFadeOut + i) < processedData.length; i++) {
            float factor = 1.0f - ((float) i / fadeOutSamples);
            processedData[startFadeOut + i] *= factor;
        }
        
        return processedData;
    }
    
    public static boolean isValidWavFile(String filePath) {
        try {
            readWavHeader(filePath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static int msToSamples(int milliseconds) {
        return (milliseconds * SAMPLE_RATE) / 1000;
    }
}