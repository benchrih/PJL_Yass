package pjl.appli;

public class Grain {
	   
    double frequence;
    double instant_emission;
    double duree;
    double[][] TabEch;
    
    //Constructeur du grain
    public Grain(double F,double dureeT,double instantT,double beta){
        frequence = F;
        duree=dureeT;
        instant_emission=instantT;
        TabEch=CalculerGrainKaiser(beta, this);
        
        }
   
    //FOnction pour le calcul des fenetres de KAISER
    public static double iIndiceO (double x) {
        double t = 1.e-08;
        double y=0.5*x;
        double e=1.0;
        double de=1.0;
        int i;
        double xi;
        double sde;
        for (i=1;i<26;i++){
            xi=i;
            de*=y/xi;
            sde=de*de;
            e+=sde;
            if((e*t-sde)>0) break;
        }
        return e;
    }
       
       
       
   
    public static double[][] CalculerGrainKaiser(double beta, Grain grain ){
       
        int Nbre_Ech =(int) ((grain.duree)*ModeGranulaireActivity.freq_ech);
        double t = (grain.instant_emission);
        //creation d'un tableau ˆ n colonnes et 3 lignes, chaque colonne repertorie : 
        //[1] = instant t de l'echantillon
        //[2] = amplitude ˆ cet instant
        //[3] = marqueur de position (en vue de la sommation)
        double echantillons[][]= new double[3][Nbre_Ech];
        double xi;
        double xind = (Nbre_Ech-1)*(Nbre_Ech-1);
        double[] w= new double[Nbre_Ech];
       
        for (int i = 0;i<Nbre_Ech;i++){
            echantillons[0][i]=t+(double)i/ModeGranulaireActivity.freq_ech;  
            
            xi=(2*i-(Nbre_Ech))*(2*i-(Nbre_Ech-1));
            w[i]=iIndiceO(beta*Math.sqrt(1-xi/xind)) /iIndiceO(beta);
            
            
            echantillons[1][i]=w[i]*((float)(Math.cos(2 * Math.PI * grain.frequence * ((double)i/ModeGranulaireActivity.freq_ech))));
            echantillons[2][i]=0;
                   
            }
        //echantillons[3][0]=1;
       
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
