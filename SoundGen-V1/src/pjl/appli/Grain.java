package pjl.appli;

public class Grain {
	double frequence;
	double instant_emission;
	double duree;

	public Grain(double F,double dureeT,double instantT){
		frequence = F;
		duree=dureeT;
		instant_emission=instantT;
		}
	
	public  double[][] getAmplitude(double t,int n){
		
		double Nbre_Ech = (double)((this.duree)*8000);
		
		double echantillons[][]= new double[2][n];
		
		for (int i = 0;i<n;i++){
			echantillons[1][i]=t+(double)i/8000;
			float w = (float) Math.exp(-0.5*(10*((i-Nbre_Ech/2)/(Nbre_Ech/2))));
			echantillons[2][i]=(w*Math.cos(2*Math.PI*this.frequence*(echantillons[1][i]-this.instant_emission)));
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
		

