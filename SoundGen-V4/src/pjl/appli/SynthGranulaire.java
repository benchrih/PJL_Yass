package pjl.appli;

import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class SynthGranulaire {

	ArrayList<Grain> liste_Grains;
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
	}

	public void AjouterGrain(int X, int Y, double beta) {

		double F_grain = Freq_Min + X * ((Freq_Max - Freq_Min) / Pixel_Freq);
		double duree_grain = Math.max(Duree_Max - Y
				* ((Duree_Max - Duree_Min) / Pixel_Dur), 1 / ModeGranulaireActivity.freq_ech);
		double instant_emmission_grain = (long) System.nanoTime();

		this.liste_Grains.add(new Grain(F_grain, duree_grain,
				instant_emmission_grain, beta));

	}

	public double[] SommeEchantillons(ArrayList<Grain> liste_Grains, int n) {
		double[] tabSommeEchantillons = new double[n];

		for (int i = 0; i < n; i++) {
			for (Grain grain : liste_Grains) {
				int nbre_ech_grain = (int) ((grain.duree) * ModeGranulaireActivity.freq_ech);
				if (grain.TabEch[2][nbre_ech_grain-1] == 1) {
					liste_Grains.remove(grain);
				} else {
					for (int j = 0; j < nbre_ech_grain; j++) {
						if (grain.TabEch[2][j] == 0) {
							tabSommeEchantillons[i] = tabSommeEchantillons[i]+ grain.TabEch[1][j];
							grain.TabEch[2][j] = 1;

						}
					}
				}

			}
		}
		return tabSommeEchantillons;

	}
	
	
	
void Jouer_Son(double[] son){
		

		
		byte[] son_genere=new byte[son.length];
		
			for(int j=0;j<son.length;++j){
				final short val = (short) ((son[j] *32767));
				son_genere[j] = (byte) (val & 0x00ff);
				son_genere[j] = (byte) ((val & 0xff00) >>> 8);
			}
			
		AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,(int) ModeGranulaireActivity.freq_ech, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT,1000000,AudioTrack.MODE_STATIC);
		audioTrack.write(son_genere, 0, son_genere.length-1);
		audioTrack.play();
		/*Toast popup = new Toast(getBaseContext());
		popup = Toast.makeText(this, "Min buffer "+audioTrack.getMinBufferSize(freq_ech,audioTrack.getChannelConfiguration() , AudioFormat.CHANNEL_CONFIGURATION_MONO), Toast.LENGTH_SHORT);		 
		popup.show();*/		
		audioTrack.flush();
		}

}
