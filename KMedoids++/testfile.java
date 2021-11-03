import java.util.*;

public class testfile {
    public static final int[][] BLOSUM62_MATRIX = 
    {
        //        A     C     D     E     F     G     H     I     K     L     M     N     P     Q     R     S     T     V     W     Y
        /*A*/ {   4,    0,   -2,   -1,   -2,    0,   -2,   -1,   -1,   -1,   -1,   -2,   -1,   -1,   -1,    1,    0,    0,   -3,   -2 },
        /*C*/ {   0,    9,   -3,   -4,   -2,   -3,   -3,   -1,   -3,   -1,   -1,   -3,   -3,   -3,   -3,   -1,   -1,   -1,   -2,   -2 },
        /*D*/ {  -2,   -3,    6,    2,   -3,   -1,   -1,   -3,   -1,   -4,   -3,    1,   -1,    0,   -2,    0,   -1,   -3,   -4,   -3 },
        /*E*/ {  -1,   -4,    2,    5,   -3,   -2,    0,   -3,    1,   -3,   -2,    0,   -1,    2,    0,    0,   -1,   -2,   -3,   -2 },
        /*F*/ {  -2,   -2,   -3,   -3,    6,   -3,   -1,    0,   -3,    0,    0,   -3,   -4,   -3,   -3,   -2,   -2,   -1,    1,    3 },
        /*G*/ {   0,   -3,   -1,   -2,   -3,    6,   -2,   -4,   -2,   -4,   -3,    0,   -2,   -2,   -2,    0,   -2,   -3,   -2,   -3 },
        /*H*/ {  -2,   -3,   -1,    0,   -1,   -2,    8,   -3,   -1,   -3,   -2,    1,   -2,    0,    0,   -1,   -2,   -3,   -2,    2 },
        /*I*/ {  -1,   -1,   -3,   -3,    0,   -4,   -3,    4,   -3,    2,    1,   -3,   -3,   -3,   -3,   -2,   -1,    3,   -3,   -1 },
        /*K*/ {  -1,   -3,   -1,    1,   -3,   -2,   -1,   -3,    5,   -2,   -1,    0,   -1,    1,    2,    0,   -1,   -2,   -3,   -2 },
        /*L*/ {  -1,   -1,   -4,   -3,    0,   -4,   -3,    2,   -2,    4,    2,   -3,   -3,   -2,   -2,   -2,   -1,    1,   -2,   -1 },
        /*M*/ {  -1,   -1,   -3,   -2,    0,   -3,   -2,    1,   -1,    2,    5,   -2,   -2,    0,   -1,   -1,   -1,    1,   -1,   -1 },
        /*N*/ {  -2,   -3,    1,    0,   -3,    0,    1,   -3,    0,   -3,   -2,    6,   -2,    0,    0,    1,    0,   -3,   -4,   -2 },
        /*P*/ {  -1,   -3,   -1,   -1,   -4,   -2,   -2,   -3,   -1,   -3,   -2,   -2,    7,   -1,   -2,   -1,   -1,   -2,   -4,   -3 },
        /*Q*/ {  -1,   -3,    0,    2,   -3,   -2,    0,   -3,    1,   -2,    0,    0,   -1,    5,    1,    0,   -1,   -2,   -2,   -1 },
        /*R*/ {  -1,   -3,   -2,    0,   -3,   -2,    0,   -3,    2,   -2,   -1,    0,   -2,    1,    5,   -1,   -1,   -3,   -3,   -2 },
        /*S*/ {   1,   -1,    0,    0,   -2,    0,   -1,   -2,    0,   -2,   -1,    1,   -1,    0,   -1,    4,    1,   -2,   -3,   -2 },
        /*T*/ {   0,   -1,   -1,   -1,   -2,   -2,   -2,   -1,   -1,   -1,   -1,    0,   -1,   -1,   -1,    1,    5,    0,   -2,   -2 },
        /*V*/ {   0,   -1,   -3,   -2,   -1,   -3,   -3,    3,   -2,    1,    1,   -3,   -2,   -2,   -3,   -2,    0,    4,   -3,   -1 },
        /*W*/ {  -3,   -2,   -4,   -3,    1,   -2,   -2,   -3,   -3,   -2,   -1,   -4,   -4,   -2,   -3,   -3,   -2,   -3,   11,    2 },
        /*Y*/ {  -2,   -2,   -3,   -2,    3,   -3,    2,   -1,   -2,   -1,   -1,   -2,   -3,   -1,   -2,   -2,   -2,   -1,    2,    7 }
    };

    public static final ArrayList<Character> residues = new ArrayList<Character>(Arrays.asList('A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'Y'));
    public static void main(String[] args)
    {
        for(int[] a : BLOSUM62_MATRIX)
        {
            for(int i : a)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println(residues);
        System.out.println(residues.indexOf('Y'));

        System.out.println(Double.MIN_VALUE);
    }
}