package pjl.appli;

public class Grain {
	float frequence;
	float instant_emission;
	float duree;

	public Grain(float F,float dureeT,float instantT){
		frequence = F;
		duree=dureeT;
		instant_emission=instantT;
		}
	
	public  float[][] getAmplitude(float t,int n){
		
		int Nbre_Ech = (int)((this.duree)*8000);
		
		float echantillons[][]= new float[2][n];
		
		for (int i = 0;i<n;i++){
			echantillons[1][i]=t+(float)i/8000;
			float w = (float) Math.exp(-0.5*(10*((i-Nbre_Ech/2)/(Nbre_Ech/2))));
			echantillons[2][i]=(float) (w*Math.cos(2*Math.PI*this.frequence*(echantillons[1][i]-this.instant_emission)));
			}
		
		return echantillons;	
	}
	
	public boolean isOver(float t){
		if(t>(this.instant_emission+this.duree)){
			return true;
		}
		else{
			return false;
		}
		}
		
	}
		

