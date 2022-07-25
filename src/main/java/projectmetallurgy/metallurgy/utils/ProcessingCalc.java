package projectmetallurgy.metallurgy.utils;

public class ProcessingCalc {
    public static double CalcAlpha(double alpha){
        return Math.pow(Math.abs(alpha)+1,Double.compare(alpha, 0.0));
    }

    public static double CalcA(double eta,double phi, double alpha){
        return Math.pow(eta*(5.0-Math.log10(phi))/4.0,CalcAlpha(alpha));
    }
}
