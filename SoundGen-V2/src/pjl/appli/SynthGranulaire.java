package pjl.appli;

import java.util.ArrayList;

public class SynthGranulaire {
	ArrayList<Grain> liste_Grains;
	double Freq_Ech;
	double Freq_Min;
	double Freq_Max;
	double Duree_Min;
	double Duree_Max;
	double Pixel_H;
	double Pixel_L;

	
	
	public SynthGranulaire(float freqech){
		liste_Grains = new ArrayList<Grain>();
		Freq_Ech = freqech;
		Freq_Min=300;
		Freq_Max=20000;
		Duree_Min=(float) 0.001;
		Duree_Max=2;
	}
	
	public double getFreq_Ech() {
		return Freq_Ech;
	}
	
	public void AjouterGrain(int X, int Y){
		
		double F_grain= Freq_Min + (X/Pixel_H)*(Freq_Max-Freq_Min);
		double duree_grain=Duree_Min + (X/Pixel_L)*(Duree_Max-Duree_Min);
		double instant_emmission_grain = (long)System.nanoTime();
		
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
