import java.util.*;

class Cluster {
    public ArrayList<String> seqs;
    public int count;
    public String medoid;
    public double variance;

    // no B, J, O, U, X, Z
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


    public Cluster()
    {
        this.seqs = new ArrayList<String>();
    }

    public Cluster(ArrayList<String> seqs)
    {
        this.seqs = seqs;
        updateCount();
        updateMedoid();
        updateVariance();
    }

    public int updateCount()
    {
        this.count = this.seqs.size();
        return this.count;
    }

    public String updateMedoid()
    {
        String bestMedoid = "";
        double maxScore = Double.MIN_VALUE;
        double currentVar = 0;
        for(int i = 0; i < seqs.size(); i++)
        {
            currentVar = getVariance(seqs, seqs.get(i));
            if(currentVar > maxScore)
            {
                bestMedoid = seqs.get(i);
                maxScore = currentVar;
            }
        }
        medoid = bestMedoid;
        variance = maxScore;
        return bestMedoid;
    }

    //USE NWAlign Class
    public static int getScore(String a, String b)
    {
        int score = 0;
        for(int i = 0; i < a.length(); i++)
        {
            if(a.charAt(i)=='-' && b.charAt(i)=='-')
            {
                continue; //update with gap penalty
            }
            if(a.charAt(i)=='-' || b.charAt(i)=='-')
            {
                score -= 2; //gap penalty
                continue;
            }
            score += BLOSUM62_MATRIX[residues.indexOf(a.charAt(i))][residues.indexOf(b.charAt(i))];
        }
        return score;
    }

    public double updateVariance()
    {
        double var = 0;
        for(String s : seqs)
        {
            var += getScore(s, medoid);
        }
        var /= seqs.size();
        variance = var;
        return var;
    }

    public double getVariance(ArrayList<String> c, String center)
    {
        double var = 0;
        for(String s : c)
        {
            var += getScore(s, center);
        }
        var /= seqs.size();
        variance = var;
        return var;
    }
}
