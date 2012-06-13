package pjl.appli;

public class Grain {
	   
    double frequence;
    double instant_emission;
    double duree;
    double beta;
    int index_traitement;
    double[] TabEch;
    
    //Constructeur du grain
    public Grain(double F,double dureeT,double instantT,double Beta){
        frequence = F;
        duree=dureeT;
        instant_emission=instantT;
        beta= Beta;
        index_traitement=0;
        //TabEch = new double[3][(int)(dureeT*ModeGranulaireActivity.freq_ech)];
        //TabEch=CalculerGrainKaiser(this);
        }
    
    
   
    //FOnction pour le calcul des fenetres de KAISER
    public static double iIndiceO (double x) {
        double t =1.e-08;
        double y=0.5*x;
        double e=1.0;
        double de=1.0;
        int i;
        double xi;
        double sde;
        for (i=1;i<26;i++){
        	xi=(double)i;
            de*=y/xi;
            sde=de*de;
            e+=sde;
            if((e*t-sde)>0) break;
        }
        return (double)e;
    }
       
       
       
   
    public static double[] CalculerGrainKaiser(Grain grain ){
       
        int Nbre_Ech =(int) ((grain.duree)*ModeGranulaireActivity.freq_ech);
        double t = (double)(grain.instant_emission);
        double beta= grain.beta;
        //creation d'un tableau ˆ n colonnes et 3 lignes, chaque colonne repertorie : 
        //[1] = instant t de l'echantillon
        //[2] = amplitude ˆ cet instant
        //[3] = marqueur de position (en vue de la sommation)
        double echantillons[]= new double[Nbre_Ech];
        double xi;
        double xind = ((double)Nbre_Ech-1)*((double)Nbre_Ech-1);
        double[] w= new double[Nbre_Ech];
       
        for (int i = 0;i<Nbre_Ech;i++){ 
            
            xi=(2.0*(double)i-(double)(Nbre_Ech-1))*(2.0*(double)i-(double)(Nbre_Ech-1));
            w[i]=(iIndiceO((beta*(double)Math.sqrt(1-xi/xind))) /iIndiceO(beta));
            //
            
            echantillons[i]=(double)(w[i]*((Math.cos(2 * Math.PI * (double)grain.frequence * ((double)i/(double)ModeGranulaireActivity.freq_ech)))));      
            }
       
        return echantillons;   
    }
   

}
