/**
 * This is the class constructs treasures in game
 * @author yifanzhang
 *
 */
public class Treasure {
	public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final int D = 4;
    public static final int E = 5;
    public static final int F = 6;
    public static final int G = 7;
    public static final int H = 8;
    public static final int I = 9;
    public static final int J = 10;
    public static final int K =11;
    public static final int L = 12;
    public static final int M = 13;
    public static final int N = 14;
    public static final int O = 15;
    public static final int P = 16;
    public static final int Q = 17;
    public static final int R = 18;
    public static final int S = 19;
    public static final int T = 20;
    public static final int U = 21;
    public static final int V = 22;
    public static final int W = 23;
    public static final int X = 24;
    public int number;
    
   
	public Treasure(int n){
    	number = n;
    }
    public int getNumber(){
    	return number;
    }
    public String toString(){
    	String nString;
        switch (number) {
            case 1:
                nString = " A";
                break;
            case 2:
                nString = " B";
                break;
            case 3:
                nString = " C";
                break;
            case 4:
                nString = " D";
                break;
            case 5:
                nString = " E";
                break;
            case 6:
                nString = " F";
                break;
            case 7:
                nString = " G";
                break;
            case 8:
                nString = " H";
                break;
            case 9:
                nString = " I";
                break;
            case 10:
                nString = " J";
                break;
            case 11:
                nString = " K";
                break;
            case 12:
                nString = " L";
                break;
            case 13:
                nString = " M";
                break;
            case 14:
                nString = " N";
                break;
            case 15:
                nString = " O";
                break;
            case 16:
                nString = " P";
                break;
            case 17:
                nString = " Q";
                break;
            case 18:
                nString = " R";
                break;
            case 19:
                nString = " S";
                break;
            case 20:
                nString = " T";
                break;
            case 21:
                nString = " U";
                break;
            case 22:
                nString = " V";
                break;
            case 23:
                nString = " W";
                break;
            case 24:
                nString = " X";
                break;
            default:
                nString = "Something is wrong";
        }
        return nString;
    }
}
