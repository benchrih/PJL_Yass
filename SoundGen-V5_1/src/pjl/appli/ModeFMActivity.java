package pjl.appli;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

public class ModeFMActivity extends Activity {

	double depart=System.nanoTime();
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.fm_main);

	 
	 }

	 public boolean onTouchEvent( MotionEvent event) {
 if(event.getActionMasked()==0){
			 
			 Toast popup = new Toast(getBaseContext());
			 popup = Toast.makeText(this, "cela fait " + ((System.nanoTime())-depart)/1000000000 +" secondes que la SynthFM tourne", Toast.LENGTH_SHORT);
			 popup.setGravity(Gravity.TOP|Gravity.LEFT, (int)event.getX(), (int)event.getY());
			 popup.show();
		 }
	    	
		 
		 return false;
		
		}

}
