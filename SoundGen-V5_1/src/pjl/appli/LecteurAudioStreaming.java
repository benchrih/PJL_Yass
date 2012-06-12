package pjl.appli;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class LecteurAudioStreaming {
	AudioTrack track;
	short[] buffer = new short[1024];
	int MinBuffer = AudioTrack.getMinBufferSize(
			44100,
			AudioFormat.CHANNEL_CONFIGURATION_MONO,
			AudioFormat.ENCODING_PCM_16BIT);

	public LecteurAudioStreaming() {
		track = new AudioTrack(AudioManager.STREAM_MUSIC, 
				44100,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, 
				MinBuffer, 
				AudioTrack.MODE_STREAM);
		
		track.play();
	}

	public void ecrireEchantillons(double[] echantillons) {
		remplirBuffer(echantillons);
		track.write(buffer, 0, echantillons.length);
	}

	private void remplirBuffer(double[] echantillons) {
		/*if (buffer.length < echantillons.length){
			buffer = new short[echantillons.length];}*/

		for (int i = 0; i < echantillons.length; i++)
			buffer[i] = (short) (echantillons[i] * Short.MAX_VALUE);
		;
	}
}
