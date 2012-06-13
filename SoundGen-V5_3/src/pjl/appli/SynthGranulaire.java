package pjl.appli;

import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.widget.Toast;

public class SynthGranulaire extends AsyncTask<Void, Void, Void> {

	ArrayList<Grain> liste_Grains;
	double Freq_Min;
	double Freq_Max;
	double Duree_Min;
	double Duree_Max;
	double Pixel_Freq;
	double Pixel_Dur;

	public SynthGranulaire(double FreqEch, double FreqMin, double FreqMax,double DureeMin, double DureeMax) {
		liste_Grains = new ArrayList<Grain>();
		Freq_Min = FreqMin;
		Freq_Max = FreqMax;
		Duree_Min = DureeMin;
		Duree_Max = DureeMax;
		Pixel_Dur = 700;
		Pixel_Freq = 1280;
	}
	



		@Override
		protected Void doInBackground(Void... params) {

			LecteurAudioStreaming lecteur = new LecteurAudioStreaming( );
			double echantillons[] = new double[ModeGranulaireActivity.taille_fenetre_sommation];

			while( true )
			{
				if(ModeGranulaireActivity.synthGran.liste_Grains.size()==0){
					echantillons = new double[ModeGranulaireActivity.taille_fenetre_sommation];
					lecteur.ecrireEchantillons(echantillons );
					
				}else{
				

				for (int i = 0; i < ModeGranulaireActivity.taille_fenetre_sommation; i++) {
					
					int nbre_grains_ajoutes=0;
					for (Grain grain : ModeGranulaireActivity.synthGran.liste_Grains) {
						int nbre_ech_grain = (int) ((grain.duree) * ModeGranulaireActivity.freq_ech);
						
						/*if (grain.index_traitement == nbre_ech_grain-1) {
							ModeGranulaireActivity.synthGran.liste_Grains.remove(grain);
						} else {*/
						
						if (grain.index_traitement < nbre_ech_grain){
							nbre_grains_ajoutes++;
							echantillons[i]=(echantillons[i]+grain.TabEch[grain.index_traitement]);
									grain.index_traitement++;
									
						}
							
							/*}*/
						}
					
					echantillons[i]=echantillons[i]/(double)nbre_grains_ajoutes;
					}
				
				lecteur.ecrireEchantillons(echantillons );
				echantillons = new double[ModeGranulaireActivity.taille_fenetre_sommation];
				
				}
			}
				
				
				
					
			}
			


	public void AjouterGrain(int X, int Y, double beta) {

		double F_grain = Freq_Min + X * ((Freq_Max - Freq_Min) / Pixel_Freq);
		double duree_grain = Math.max(Duree_Max - Y* ((Duree_Max - Duree_Min) / Pixel_Dur),
				1 / ModeGranulaireActivity.freq_ech);
		double instant_emmission_grain = (double) ((System.nanoTime() - ModeGranulaireActivity.depart) / 1000000000);

		Grain grain_courant = new Grain(F_grain, duree_grain,
				instant_emmission_grain, beta);

		grain_courant.TabEch = Grain.CalculerGrainKaiser(grain_courant);

		this.liste_Grains.add(grain_courant);

	}
}
