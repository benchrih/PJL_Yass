package pjl.appli;

import java.util.ArrayList;
import java.util.Iterator;

import pjl.appli.LecteurAudioStreaming;
import android.os.AsyncTask;
import android.widget.Toast;

class Backoffice extends AsyncTask<SynthGranulaire , Void, Void>{


	@Override
	protected Void doInBackground(SynthGranulaire ...liste_synthGranulaire) {
		
		SynthGranulaire synthGran=liste_synthGranulaire[0];

		ArrayList<Grain> liste_Grains=liste_synthGranulaire[0].liste_Grains;


		double[] tabSommeEchantillons = new double[ModeGranulaireActivity.taille_fenetre_lecture];

		while(true){
			
			for (int i = 0; i < ModeGranulaireActivity.taille_fenetre_lecture; i++) {
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
			
			synthGran.lecteur.ecrireEchantillons(tabSommeEchantillons);
		}




		
	}





}
