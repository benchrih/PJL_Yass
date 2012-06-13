package pjl.appli;


import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.MotionEvent;

public class ModeGranulaireActivity extends Activity {
	
	

	public static double depart=System.nanoTime();
	public static int freq_ech=/*AudioTrack.getNativeOutputSampleRate(MODE_WORLD_READABLE)*/16000;
	
	public static SynthGranulaire synthGran = new SynthGranulaire(freq_ech, 261.63, 523.25, 0.001, 2);
	public static int taille_fenetre_sommation=AudioTrack.getMinBufferSize(freq_ech, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT);

	

	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.granulatormain);
	synthGran.execute();
	
		
	 }

@Override
	 public boolean onTouchEvent( MotionEvent event) {
		
		 if(event.getActionMasked()==0){
			 
			 synthGran.AjouterGrain((int)event.getX(), (int)event.getY(), 3.0*Math.PI);

				 for(Grain grain_courant:synthGran.liste_Grains){
				 
				 if(grain_courant.index_traitement>=(int)((grain_courant.duree) * ModeGranulaireActivity.freq_ech)-1){
					 synthGran.liste_Grains.remove(grain_courant);
				 }

			 }
			 
		 
		 
		 
		 
		 }
		 
	    	
		
		    return false;	 
	 }

	    	
	 	    	
}
