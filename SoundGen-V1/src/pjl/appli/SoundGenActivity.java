package pjl.appli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class SoundGenActivity extends Activity {
    /** Called when the activity is first created. */
    
    public RadioGroup radioGroup ;
 	public RadioButton fmButton;
 	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	radioGroup = (RadioGroup)findViewById(R.id.radioSyntheseSonore);
    }
    
   /* OnClickListener granularButtonConnexion = new OnClickListener()
    {
     @Override
     public void onClick(View actuelView){
     // On met en place le passage entre les deux activités sur ce Listener
     Intent intent = new Intent(MainViewActivity.this,DisplayLoginActivity.class);
     startActivity(intent);
     }
    };
     
    //On récupere le bouton souhaité et on lui affecte le Listener
    RadioButton granularButton = (RadioButton) findViewById(R.layout.main);
    granularButton.setOnClickListener(granularButtonConnexion);*/
    
    
    
    
    
    public void onFMButtonClicked(View v) {
        // Ce qui se produit lorsqu'on clique sur la synthèse FM
    	TextView coucou = null;
		coucou = new TextView(this);
		coucou.setText("Bonjour, vous etes en mode FM");
		setContentView(coucou);
    }
    
    public void onGranulaireButtonClicked(View v) {
        // Ce qui se produit lorsqu'on clique sur la synthèse Granulaire
        //Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SoundGenActivity.this, ModeGranulaireActivity.class);
        startActivity(intent);
    } 
    
   

}