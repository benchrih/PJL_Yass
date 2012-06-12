package pjl.appli;

import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.Toast;

public class SynthGranulaire {

	ArrayList<Grain> liste_Grains;
	LecteurAudioStreaming lecteur; 
	double Freq_Min;
	double Freq_Max;
	double Duree_Min;
	double Duree_Max;
	double Pixel_Freq;
	double Pixel_Dur;

	public SynthGranulaire(double FreqEch, double FreqMin, double FreqMax,
			double DureeMin, double DureeMax) {
		liste_Grains = new ArrayList<Grain>();
		Freq_Min = FreqMin;
		Freq_Max = FreqMax;
		Duree_Min = DureeMin;
		Duree_Max = DureeMax;
		Pixel_Dur = 700;
		Pixel_Freq = 1280;
		lecteur = new LecteurAudioStreaming(); 
	}

	public void AjouterGrain(int X, int Y, double beta) {

		double F_grain = Freq_Min + X * ((Freq_Max - Freq_Min) / Pixel_Freq);
		double duree_grain = Math.max(Duree_Max - Y* ((Duree_Max - Duree_Min) / Pixel_Dur), 1 / ModeGranulaireActivity.freq_ech);
		double instant_emmission_grain = (double) ((System.nanoTime()-ModeGranulaireActivity.depart)/1000000000);
		
		Grain grain_courant=new Grain(F_grain, duree_grain,
				instant_emmission_grain, beta);
		grain_courant.TabEch=Grain.CalculerGrainKaiser(grain_courant);
		this.liste_Grains.add(grain_courant);

	}

	public double[] SommeEchantillons(ArrayList<Grain> liste_Grains, int n) {
		double[] tabSommeEchantillons = new double[n];

		for (int i = 0; i < n; i++) {
			for (Grain grain : liste_Grains) {
				int nbre_ech_grain = (int) ((grain.duree) * ModeGranulaireActivity.freq_ech);
				if (grain.index_traitement == nbre_ech_grain-1) {
					liste_Grains.remove(grain);
				} else {
					for (int j = grain.index_traitement; j < nbre_ech_grain; j++) {
							tabSommeEchantillons[i] = tabSommeEchantillons[i]+ grain.TabEch[j];
							grain.index_traitement++;
					}
				}

			}
		}
		return tabSommeEchantillons;

	}
	
	
	
public void Jouer_Son(double[] son){
		

		
		byte[] son_genere=new byte[son.length];
		
			for(int j=0;j<son.length;++j){
				final short val = (short) ((son[j] *32767));
				son_genere[j] = (byte) (val & 0x00ff);
				son_genere[j] = (byte) ((val & 0xff00) >>> 8);
			}
			
		AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,(int) ModeGranulaireActivity.freq_ech, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT,100000,AudioTrack.MODE_STATIC);
		audioTrack.write(son_genere, 0, son_genere.length-1);
		audioTrack.play();
		
		//audioTrack.flush();
		}

}
