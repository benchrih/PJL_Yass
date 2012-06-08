package pjl.appli;

import java.util.ArrayList;


public class SynthGranulaire {
	ArrayList<Grain> liste_Grains;
	float Freq_Ech;
	float Freq_Min;
	float Freq_Max;
	float Duree_Min;
	float Duree_Max;
	float Pixel_H;
	float Pixel_L;

	
	
	public SynthGranulaire(float f){
		liste_Grains = new ArrayList<Grain>();
		Freq_Ech = f;
		Freq_Min=300;
		Freq_Max=20000;
		Duree_Min=(float) 0.001;
		Duree_Max=2;
	}
	
	public float getFreq_Ech() {
		return Freq_Ech;
	}
	
	public void AjouterGrain(int X, int Y){
		
		float F_grain= Freq_Min + (X/Pixel_H)*(Freq_Max-Freq_Min);
		float duree_grain=Duree_Min + (X/Pixel_L)*(Duree_Max-Duree_Min);
		float instant_emmission_grain = 1;
		
		this.liste_Grains.add(new Grain(F_grain,duree_grain,instant_emmission_grain));
		
	}
	
	public void SupprimerGrain(float t){
		
		for(Grain current : liste_Grains) 
		{
		  if(current.isOver(t)){
			  this.liste_Grains.remove(current);
			  }
		}
		
	}
	
	

	}

	