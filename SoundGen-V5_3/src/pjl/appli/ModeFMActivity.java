package pjl.appli;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
//import android.view.Gravity;
import android.view.MotionEvent;
//import android.widget.Toast;

public class ModeFMActivity extends Activity {

    //double depart=System.nanoTime();
    int Fe=8000;
    int n=1000; //nb de valeurs gardées pour oscFM
    double coeff=1;
    double Phi1[]=new double[n];  // contient la somme cumulée des phases
    double Phi2[]=new double[n];
    double tableau_instFM[]=new double[n];
    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,Fe, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT,1000000,AudioTrack.MODE_STREAM);
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fm_main);
   
     
     }
   


     public boolean onTouchEvent( MotionEvent event) {
 if(event.getActionMasked()==0){
             
     double freq=(Math.max(event.getX(),0)/1270)*(8000);
     int I= Math.max((int)(16000-(event.getY()/600)*(16000-80)), 0);
     
     byte[] valeur = Son_genere(freq,I);
     
        audioTrack.write(valeur, 0, valeur.length-1);
        audioTrack.play();
        audioTrack.flush();
     
         }
           
         
         return false;
       
        }
            
           
     
     double osc_fm(double freq, double Phi[], double  m){
                        
         ajouter(Phi, Phi[n-1]+2*Math.PI/Fe*freq);
         return m*Math.cos(Phi[n-1]);
     }
     
     double inst_fm(double fp, double fm, double d,double m){
         
         double ft;
         
         ft=fp+osc_fm(fm, Phi1, d);
         double out= osc_fm(ft, Phi2, d);
         return out;
     }

     void ajouter (double Phi[], double var) {
         
         for (int i = 0; i < Phi.length-1; ++i) {
             Phi[i]=Phi[i+1];
             }
         Phi[Phi.length-1]=var;
     }
     
     byte[] Son_genere(double freq, int I) {
         
            double Son[]=new double[n];
            byte[] Son_genere=new byte[n];
         
         double out = inst_fm(freq,freq*2, coeff*I, 2*coeff*I );
         
         ajouter(tableau_instFM, out);
         
           
            for (int i = 0; i < n; ++i) {
                Son[i] = (double)((float)(tableau_instFM[i]));
            }       
                   
           
            for(int j=0;j<n;++j){
                final short val = (short) ((Son[j] * 32767));
                Son_genere[j] = (byte) (val & 0x00ff);
                Son_genere[j] = (byte) ((val & 0xff00) >>> 8);
            }
           
            return Son_genere;
     }
     
}