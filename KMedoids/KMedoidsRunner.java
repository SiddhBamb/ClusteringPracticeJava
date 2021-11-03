import java.util.*;
import java.io.*;

class KMedoidsRunner {
    public static void main(String[] args) throws FileNotFoundException
    {
        KMedoidsRunner kmr = new KMedoidsRunner();
        kmr.start();
    }

    public void start() throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("KMedoidsInput.txt"));

        ArrayList<String> arr = new ArrayList<String>();

        while(scan.hasNext())
        {
            arr.add(scan.next());
        }


        // remove later
        Collections.shuffle(arr);

        Scanner scan2 = new Scanner(System.in);
        System.out.println("Number of clusters?");
        int numClusters = scan2.nextInt();

        //System.out.println(arr);
        KMedoids km = new KMedoids(arr, numClusters);
        ArrayList<Cluster> clusters = km.run();

        for(int i = 1; i <= clusters.size(); i++)
        {
            System.out.println("Cluster " + i + " (" + clusters.get(i-1).seqs.size() + " items):");
            System.out.println("Center: " + clusters.get(i-1).medoid);
            System.out.println("Variance: " + clusters.get(i-1).variance);
            for(int j = 0; j < clusters.get(i-1).seqs.size(); j++)
            {
                System.out.print(clusters.get(i-1).seqs.get(j) + (j==clusters.get(i-1).seqs.size()-1 ? "" : ",\n"));
            } 
            System.out.println("\n");
        }

        /*System.out.println(Cluster.getScore("----PLKAAKDSYK-GSAPIASDKHGKPHKLSP-VW", "----PVKAGKDSYK-GSAPLVSDKHGKPRKLSP-IW"));

        System.out.println(Cluster.getScore("----PLKAAKDSYK-GSAPIASDKHGKPHKLSP-VW", "DIVFPVKAA-DSHKWASNPLNSDMHGKPHKLSPLIW"));

        System.out.println(Cluster.getScore("----PLKAAKDSYK-GSAPIASDKHGKPHKLSP-VW", "DIVFPLKAA-DSHKWVSNPLNSDMHGKPHKLSPLIW"));

        System.out.println(Cluster.getScore("----PVKAGKDSYK-GSAPLVSDKHGKPRKLSP-IW", "DIVFPVKAA-DSHKWASNPLNSDMHGKPHKLSPLIW"));

        System.out.println(Cluster.getScore("----PVKAGKDSYK-GSAPLVSDKHGKPRKLSP-IW", "DIVFPLKAA-DSHKWVSNPLNSDMHGKPHKLSPLIW"));

        System.out.println(Cluster.getScore("DIVFPLKAA-DSHKWVSNPLNSDMHGKPHKLSPLIW", "DIVFPVKAA-DSHKWASNPLNSDMHGKPHKLSPLIW"));

        System.out.println((new Cluster(new ArrayList<String>(Arrays.asList("DIVFPVKAA-DSHKWASNPLNSDMHGKPHKLSPLIW", "DIVFPLKAA-DSHKWVSNPLNSDMHGKPHKLSPLIW")))).variance);*/
    }
}
