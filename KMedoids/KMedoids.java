import java.util.*;

class KMedoids {
    public ArrayList<Cluster> clusters;
    public int k;
    public ArrayList<String> points;

    public KMedoids(ArrayList<String> points, int k)
    {
        this.k = k;
        this.points = points;

        // make list of clusters with empty clusters
        clusters = new ArrayList<Cluster>();
        for(int i = 0; i < k; i++)
        {
            clusters.add(new Cluster());
        }
    }

    public ArrayList<Cluster> run()
    {
        // assign points with modulus
        for(int i = 0; i < points.size(); i++)
        {
            clusters.get(i%k).seqs.add(points.get(i));
        }

        // update cluster objects
        for(int i = 0; i < k; i++)
        {
            clusters.get(i).updateCount();
            clusters.get(i).updateMedoid();
            clusters.get(i).updateVariance();
        }

        // define conditions to end loop
        boolean changed = true;
        boolean success = true;
        int maxIter = 100 * points.size();
        int count = 0;

        while(count < maxIter)// && changed && success)
        {
            count++;

            success = updateAllMedoids();

            changed = updateClusters();

            /*System.out.println("ITERATION " + (count) + "!\n" + success + " " + changed);
            for(int i = 1; i <= clusters.size(); i++)
            {
            System.out.println("Cluster " + i + ": ");
            System.out.println("Center:" + clusters.get(i-1).medoid);
            for(int j = 0; j < clusters.get(i-1).seqs.size(); j++)
            {
                System.out.print(clusters.get(i-1).seqs.get(j) + (j==clusters.get(i-1).seqs.size()-1 ? "" : ",\n"));
            } 
            System.out.println("\n");
            }*/
        }

        System.out.println("Iterated " + count + " times.");
        return clusters;
    }

    public boolean updateAllMedoids()
    {
        boolean success = true;
        for(Cluster c : clusters)
        {
            if(c.seqs.size() == 0)
                success = false;
            c.updateMedoid();
        }
        return success;
    }

    public boolean updateClusters()
    {
        ArrayList<String> centers = new ArrayList<String>();
        for(Cluster c : clusters)
            centers.add(c.medoid);
        
        ArrayList<Cluster> newClusters = new ArrayList<Cluster>();
        for(int i = 0; i < k; i++)
        {
            newClusters.add(new Cluster());
        }



        // loop through all points
        for(String p : points)
        {
            double maxscore = -1*Double.MAX_VALUE;
            int minidx = -1;
            // compare point to each cluster mean to see which is closest
            for(int i = 0; i < centers.size(); i++)
            {
                String m = centers.get(i);
                double currentscore = Cluster.getScore(m, p);
                if(currentscore > maxscore)
                {
                    maxscore = currentscore;
                    minidx = i;
                }
                //System.out.println(points.indexOf(p) + ": " + p + " " + m + " " + currentscore + " " + minidx + " " + maxscore);
            }
            // add sequence to closest cluster
            newClusters.get(minidx).seqs.add(p);
        }

        for(int i = 0; i < newClusters.size(); i++)
        {
            newClusters.get(i).updateCount();
            newClusters.get(i).updateMedoid();
            newClusters.get(i).updateVariance();
        }

        // need to stop iterating if the clusters have stabilized
        // compare if old clusters equal new ones
        boolean changed = false;
        for(int i = 0; i < newClusters.size(); i++)
        {
            //System.out.println(newClusters.get(i));
            //System.out.println(clusters.get(i));
            //System.out.println(newClusters.get(i).equals(clusters.get(i)) + "\n");
            if(!(clusters.get(i).seqs.size() == newClusters.get(i).seqs.size() && clusters.get(i).seqs.containsAll(newClusters.get(i).seqs)))
            {
                changed = true;
            }
        }
        // may need to reimplement, not done right
        
        clusters = newClusters;

        return changed;
    }
}
