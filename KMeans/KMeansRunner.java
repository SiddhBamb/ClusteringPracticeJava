import java.util.*;
import java.io.*;

class KMeansRunner {
    public static void main(String[] args) throws FileNotFoundException
    {
        KMeansRunner kmr = new KMeansRunner();
        kmr.start();
    }

    public void start() throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("KMeansInput.txt"));

        ArrayList<Point> arr = new ArrayList<Point>();

        while(scan.hasNextLine())
        {
            arr.add(new Point(scan.nextDouble(), scan.nextDouble()));
        }
        //System.out.println(arr);
        KMeans km = new KMeans(arr, 3);
        ArrayList<ArrayList<Point>> clusters = km.run();

        for(int i = 1; i <= clusters.size(); i++)
        {
            System.out.println("Cluster " + i + ": ");
            System.out.println("Center:" + km.means.get(i-1));
            for(int j = 0; j < clusters.get(i-1).size(); j++)
            {
                System.out.print(clusters.get(i-1).get(j) + (j==clusters.get(i-1).size()-1 ? "" : ","));
            } 
            System.out.println("\n");
        }
    }
}
