package pjl.appli;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class LecteurAudioStreaming {
	AudioTrack track;
	byte[] buffer = new byte[ModeGranulaireActivity.taille_fenetre_sommation];
	int MinBuffer = AudioTrack.getMinBufferSize(
			ModeGranulaireActivity.freq_ech,
			AudioFormat.CHANNEL_CONFIGURATION_MONO,
			AudioFormat.ENCODING_PCM_16BIT);

	public LecteurAudioStreaming() {
		track = new AudioTrack(AudioManager.STREAM_MUSIC, 
				ModeGranulaireActivity.freq_ech,
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
		if (buffer.length < echantillons.length){
			buffer = new byte[echantillons.length];}
		
		for(int j=0;j<echantillons.length;++j){
			final short val = (short) ((echantillons[j] *32767));
			buffer[j] = (byte) (val & 0x00ff);
			buffer[j] = (byte) ((val & 0xff00) >>> 8);
		}
	}
}
