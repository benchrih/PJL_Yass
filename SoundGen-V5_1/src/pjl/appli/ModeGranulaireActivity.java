package pjl.appli;


import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class ModeGranulaireActivity extends Activity {



	public static double depart=System.nanoTime();
	public static int freq_ech=16000;
	SynthGranulaire synthGran = new SynthGranulaire(freq_ech, 261.63, 523.25, 0.00025, 2);

	
	public static int taille_fenetre_lecture = 8000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.granulatormain);
		//setContentView(new SampleView(this));
		Backoffice nouvelle_session = new Backoffice();
	    nouvelle_session.execute(synthGran);








	}

	@Override
	public boolean onTouchEvent( MotionEvent event) {

		if(event.getActionMasked()==0){

			synthGran.AjouterGrain((int)event.getX(), (int)event.getY(), 3.0*Math.PI);


		}
		return true;
	}
}
