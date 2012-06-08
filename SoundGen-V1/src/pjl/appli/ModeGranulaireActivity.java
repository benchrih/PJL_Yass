package pjl.appli;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class ModeGranulaireActivity extends Activity {
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.granulatormain);
     //Intent thisIntent = getIntent();
	 }

	 public boolean onTouchEvent( MotionEvent event) {
	    	Toast.makeText(this, ""+event.getX()+","+event.getY(), Toast.LENGTH_SHORT).show();
			return false;
		}
	
}


