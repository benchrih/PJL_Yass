package pjl.appli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class SoundGenActivity extends Activity {
    /** Called when the activity is first created. */
	
    public RadioGroup radioGroup ;
    

	
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //radioGroup = (RadioGroup)findViewById(R.id.radioSyntheseSonore);

    }
    
 
    public void onFMButtonClicked(View v) {
    	Intent intent = new Intent(SoundGenActivity.this, ModeFMActivity.class);
        startActivity(intent);
    }
    
    public void onGranulaireButtonClicked(View v) {
        Intent intent = new Intent(SoundGenActivity.this, ModeGranulaireActivity.class);
        startActivity(intent);
    }
}