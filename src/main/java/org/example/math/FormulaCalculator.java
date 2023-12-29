package org.example.math;

import javax.validation.constraints.NotNull;

public class FormulaCalculator {
    public static final String mainText = "Answer is %s";
    public static String calcFormula(@NotNull Formula formula, double[] numbers) {
        switch (formula){
            case Discriminant:return calcDiscriminant(numbers[0],numbers[1],numbers[2]);
            case Gerona:return calcGerona(numbers[0],numbers[1],numbers[2]);
            case DifficultPer:return calcDifficultPer(numbers[0],numbers[1],numbers[2]);
            case Pifagor:return calcPifagor(numbers[0],numbers[1]);
            case Cosinus_Teorem:return calcCosinus_Teorem(numbers[0],numbers[1]);
            case Sinus_Teorem:return calcSinus_Teorem(numbers[0],numbers[1]);
        }
       return "-1";
    }

    public static String calcDiscriminant(double a, double b, double c) {
        double D = b*b - 4*a*c;
        double x1 = (-b+D)/(2*a);
        double x2 = (-b-D)/(2*a);

        String answer = "x1="+ x1 + ", x2=" + x2;
        return String.format(mainText, answer);
    }

    public static String calcGerona(double a, double b, double c) {
        double p = (a+b+c)/2;
        double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        String answer = "Area is = " + s;
        return String.format(mainText, answer);
    }

    public static String calcDifficultPer(double a, double b, double c) {
        double r = a*Math.pow((1 +(b/100)),c);
        String answer = "Answer is " + r;
        return String.format(mainText, answer);
    }

    public static String calcPifagor(double a, double b) {
        double res = Math.sqrt(((a*a) + (b*b)));
        String answer = "Hipothenys is = " + res;
        return String.format(mainText, answer);
    }

    public static String calcCosinus_Teorem(double a, double b) {
        double res = a/b;
        String answer = "Cosinus is =" + res;
        return String.format(mainText, answer);
    }

    public static String calcSinus_Teorem(double a, double b) {
        double res = a/b;
        String answer = "Sinus is" + res;
        return String.format(mainText, answer);
    }
}
