/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc205assignment2;

public class DecimalToBinary32Format {

    public static String x;
    public static int LD;
    public static double RD;
    public static String BINARYLEFT;
    public static String BINARYRIGHT;
    public static String mantessa = "";
    public static String binaryExponent = "";

    public DecimalToBinary32Format(String y) {
        x = y;

    }

    public static int getLEFTSIDE() {
        int LEFTSIDE;
        int DecimalPoint = x.indexOf(".");
        LEFTSIDE = Integer.parseInt(x.substring(0, DecimalPoint));
        LD = LEFTSIDE;
        return LEFTSIDE;
    }

    public double getRIGHTSIDE() {
        double RIGHTSIDE;
        int DecimalPoint = x.indexOf(".");
        RIGHTSIDE = Double.parseDouble(x.substring(DecimalPoint, x.length()));
        RD = RIGHTSIDE;
        return RIGHTSIDE;
    }

    public static String magicBinaryLeftSide(int LD) {
        String BinaryLD = "";
        int answer = (int) LD;
        while (answer != 0) {
            int remainder = answer % 2;
            answer = answer / 2;
            BinaryLD = remainder + BinaryLD;
        }

        BINARYLEFT = BinaryLD;

        return BinaryLD;

    }

    public static String magicBinaryRightSide(double RD) {
//        System.out.println("HERE:" + RD);
        String BinaryRD = "";
        double answer = RD;
        while (answer != 1) {

            answer = answer * 2;
            if (answer >= 1) {
                BinaryRD += "1";
                answer = answer - 1;
                break;

            } else {
                BinaryRD += "0";
            }

        }
        BINARYRIGHT = BinaryRD;
        return BinaryRD;

    }

    public static String DecimalTo32Bit(DecimalToBinary32Format GP) {

        String sign = "";
        double YO = Double.parseDouble(x);
        if (YO < 0) {
            sign += 1;
        } else {
            sign += 0;
        }
        String Left = GP.magicBinaryLeftSide(LD) + ".";
        String Right = GP.magicBinaryRightSide(RD);
        String FullNumber = "" + Left + Right;
        double WholeNumber = Double.parseDouble(FullNumber);
        int exponent = (Left.length() - 2) + 127;
        int BinaryOfExponent = Integer.parseInt(magicBinaryLeftSide(exponent));
        String BinaryExponent = Integer.toString(BinaryOfExponent);
        binaryExponent = BinaryExponent;
        WholeNumber = WholeNumber * Math.pow(10, Left.length() - 1);
        String New = "" + WholeNumber;
        int placeofDecimal = New.indexOf(".") + 1;
        int placeofLetter = New.indexOf("E");
        String New2 = New.substring(placeofDecimal, placeofLetter);

        while (New2.length() < 23) {
            New2 = New2 + "0";
        }
        mantessa += "1";
        mantessa += New2;
        String Result = sign + " " + BinaryExponent + " " + New2;
        return Result;

    }

    public static String getMantessa() {
        return mantessa;

    }

    public static String DecimalTo64Bit(DecimalToBinary32Format GP) {

       String sign = "";
        double YO = Double.parseDouble(x);
        if (YO < 0) {
            sign += 1;
        } else {
            sign += 0;
        }
        String Left = GP.magicBinaryLeftSide(LD) + ".";
        String Right = GP.magicBinaryRightSide(RD);
        String FullNumber = "" + Left + Right;
        double WholeNumber = Double.parseDouble(FullNumber);
        int exponent = (Left.length() - 2) + 1023;
//        System.out.println("exponent:"+ exponent);
//        System.out.println("SUCKA "+ magicBinaryLeftSide(exponent));
        long BinaryOfExponent = Long.parseLong(magicBinaryLeftSide(exponent).replaceAll("\\s+",""));
//        System.out.println("HERE3 " + BinaryOfExponent);
        String BinaryExponent = Long.toString(BinaryOfExponent);
        binaryExponent = BinaryExponent;
        WholeNumber = WholeNumber * Math.pow(10, Left.length() - 1);
        String New = "" + WholeNumber;
        int placeofDecimal = New.indexOf(".") + 1;
        int placeofLetter = New.indexOf("E");
        String New2 = New.substring(placeofDecimal, placeofLetter);

        while (New2.length() < 52) {
            New2 = New2 + "0";
        }
        mantessa += "1";
        mantessa += New2;
        String Result = sign + " " + BinaryExponent + " " + New2;
        return Result;

    }

    public static double Method2(String LOL2) {
        double F = 0;
        for (int i = 0; i < LOL2.length(); i++) {
            int G = Character.getNumericValue(LOL2.charAt(i));
            F = F + (G * Math.pow(2, -(i + 1)));

        }

        return F;

    }

    public static int Method1(String LOL1) {
        int F = 0;
        int exponent = LOL1.length() - 1;
        for (int i = 0; i < LOL1.length(); i++) {
            int G = Character.getNumericValue(LOL1.charAt(i));
            F = F + (int) (G * Math.pow(2, (exponent)));
            exponent--;

        }
        F = (F - 127);
        System.out.println(getMantessa());
        double v = Double.parseDouble(getMantessa()) * Math.pow(10, -(23 - F));
        String NEWV = Double.toString(v);
        int indexofdecimal = (NEWV.indexOf("."));
        String newString1 = NEWV.substring(0, indexofdecimal);
        String newString2 = NEWV.substring(indexofdecimal, NEWV.length());
        int something = 0;
        for (int i = 0; i < newString1.length(); i++) {
            int G = Character.getNumericValue(newString1.charAt(i));
            something = something + (int) (G * Math.pow(2, (F)));
            F--;
           

        }
        double oo = 0.0;
        for (int i = 0; i < newString2.length(); i++) {
            int G = Character.getNumericValue(newString2.charAt(i));
            oo = oo + (G * Math.pow(2,-(i + 1)));
            oo = oo*-1;

        }
        double result22 =  something +oo;
        

        System.out.println("here is v:" + result22);
        return F;

    }

    public static String getbinaryExponent() {

        return binaryExponent;
    }

    public static void return32toDecimal() {

//        Method1(getbinaryExponent());
//        Method2(getMantessa());
        System.out.println("HERE" + Method1(getbinaryExponent()));
        System.out.println("THIS" + Method2(getMantessa()));

    }

    public static void main(String[] args) {
        String x = "12.370";
        DecimalToBinary32Format G = new DecimalToBinary32Format(x);
        int f = G.getLEFTSIDE();
        double d = G.getRIGHTSIDE();
//        System.out.println("BINARY: " + G.magicBinaryLeftSide(f));
//        System.out.println("BINARY2: " + G.magicBinaryRightSide(d));
        System.out.println("Here is 32 bit: "+DecimalTo32Bit(G));
//        return32toDecimal();
      System.out.println("Here is 64 bit: "+DecimalTo64Bit(G));

    }

}
