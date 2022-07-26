package projectmetallurgy.metallurgy.utils;

public class ProcessingCalc {
    public static double calcA(double alpha){
        return Math.pow(Math.abs(alpha)+1,Double.compare(alpha, 0.0));
    }

    //分离比
    public static double calcEta(double eta0,double phi, double alpha){
        return Math.pow(eta0*(5.0-Math.log10(phi))/4.0,calcA(alpha));
    }
}
