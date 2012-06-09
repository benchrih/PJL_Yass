package pjl.appli;


import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

public class ModeGranulaireActivity extends Activity {

	double depart=System.nanoTime();
	int freq_ech=8000;
	
	

	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.granulatormain);
	 }
	
	
	
	
	
	public double iIndiceO (double x) {
		double t = 1.e-08;
		double y=0.5*x;
		double e=1.0;
		double de=1.0;
		int i;
		double xi;
		double sde;
		for (i=1;i<26;i++){
			xi=i;
			de*=y/xi;
			sde=de*de;
			e+=sde;
			if((e*t-sde)>0) break;
		}
		return e;
	}
	

	
	void Jouer_Son(double freq,int nbre_ech, double beta){
		
		double Son[]=new double[nbre_ech];
		byte[] Son_genere=new byte[nbre_ech];
		double[] w= new double[nbre_ech];
		double bes = 1.0/iIndiceO(beta);
		//int odd = nbre_ech%2;
		double xi;
		double xind = (nbre_ech-1)*(nbre_ech-1);
			
		
		for (int i = 0; i < nbre_ech; ++i) {
			//if (odd==1) xi = i + 0.5;
			//else xi = i;
			//xi = 4*xi*xi;
			xi=(2*i-(nbre_ech-1))*(2*i-(nbre_ech-1));
			w[i]  = iIndiceO(beta*Math.sqrt(1-xi/xind)) * bes;
			//w[i] = (float) Math.exp(-Math.pow((0.5*(((i-nbre_ech/2)/(nbre_ech/2)))), 2));
			Son[i] = (double)((float)(Math.cos(2 * Math.PI * i / (freq_ech/freq))));
		}		
		
		
		
		
		for(int j=0;j<nbre_ech;++j){
			final short val = (short) ((Son[j] * 32767));
			Son_genere[j] = (byte) (val & 0x00ff);
			Son_genere[j] = (byte) ((val & 0xff00) >>> 8);
		}
			
		AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,freq_ech, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT,1000000,AudioTrack.MODE_STATIC);
		audioTrack.write(Son_genere, 0, Son_genere.length-1);
		audioTrack.play();
		audioTrack.flush();
		}


	 public boolean onTouchEvent( MotionEvent event) {
		 if(event.getActionMasked()==0){
			 double freq=(Math.max(event.getX(),0)/1270)*(8000);
			 int nbre_ech= Math.max((int)(16000-(event.getY()/600)*(16000-80)), 0);
			 Toast popup = new Toast(getBaseContext());
			 popup = Toast.makeText(this, "X = "+event.getX()+"Y = "+event.getY()+"frequence : "+freq+" ; "+"Duree : "+(double)(nbre_ech)/(double)(freq_ech), Toast.LENGTH_SHORT);
			 popup.setGravity(Gravity.TOP|Gravity.LEFT, (int)event.getX(), (int)event.getY());
			 popup.show();

			 Jouer_Son(freq,nbre_ech,1.8*Math.PI);
		 }
	    	
		
		    return false;
		
		}

}
