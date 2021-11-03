import java.util.*;

class KMeans
{
    public int k;
    public ArrayList<Point> points;
    public ArrayList<Point> means;
    public ArrayList<ArrayList<Point>> clusters;

    // constructor: inputs of points and number of clusters
    public KMeans(ArrayList<Point> arr, int k)
    {
        this.points = arr;
        this.k = k;
        means = new ArrayList<Point>();
        clusters = new ArrayList<ArrayList<Point>>();
    }

    // running the k means algorithm
    public ArrayList<ArrayList<Point>> run()
    {
        means = new ArrayList<Point>();
        clusters = new ArrayList<ArrayList<Point>>();
        // initialize k arraylists for k clusters
        for(int i = 0; i < k; i++)
        {
            clusters.add(new ArrayList<Point>());
        }

        // assign points with modulus
        for(int i = 0; i < points.size(); i++)
        {
            clusters.get(i%k).add(points.get(i));
        }

        // define conditions to end loop
        boolean changed = true;
        boolean success = true;
        int maxIter = 10 * points.size();
        int count = 0;

        // end if clusters haven't changed in an iteration, if a cluster is empty, 
        // or if max iteration count reached
        while(count < maxIter && changed && success)
        {
            count++;
            // update means
            success = getMeans();
            // update clusters based on new means
            changed = updateClusters();
            // System.out.println(success + " " + changed);
        }
        System.out.println("Iterated " + count + " times.");
        return clusters;
    }

    // Finds and returns means of clusters of a 2d array by having an array of points
    // with each point corresponding to the centroid of the corresponding cluster (by index) 
    public boolean getMeans()
    {
        means.clear();
        double xmean = 0;
        double ymean = 0;
        boolean success = true;
        for(ArrayList<Point> c : clusters)
        {
            xmean = 0;
            ymean = 0;
            if(c.size() == 0)
            {
                success = false;
            }
            for(Point p : c)
            {
                xmean += p.x;
                ymean += p.y;
            }

            // find average x and y values of a cluster
            xmean /= c.size();
            ymean /= c.size();

            // add to new means arraylist
            means.add(new Point(xmean, ymean));
        }
        return success;
    }

    public boolean updateClusters()
    {
        ArrayList<ArrayList<Point>> newClusters = new ArrayList<ArrayList<Point>>();
        for(int i = 0; i < k; i++)
        {
            newClusters.add(new ArrayList<Point>());
        }

        // loop through all points
        for(Point p : points)
        {
            double mindistance = Double.MAX_VALUE;
            int minidx = -1;
            // compare point to each cluster mean to see which is closest
            for(int i = 0; i < means.size(); i++)
            {
                Point m = means.get(i);
                if(p.dist(m) < mindistance)
                {
                    mindistance = p.dist(m);
                    minidx = i;
                }
                //System.out.println(p + " " + m + " " + p.dist(m) + " " + minidx + " " + mindistance);
            }
            // add point to closest cluster
            newClusters.get(minidx).add(p);
        }

        // need to stop iterating if the clusters have stabilized
        // compare if old clusters equal new ones
        boolean changed = false;
        for(int i = 0; i < newClusters.size(); i++)
        {
            //System.out.println(newClusters.get(i));
            //System.out.println(clusters.get(i));
            //System.out.println(newClusters.get(i).equals(clusters.get(i)) + "\n");
            if(!(newClusters.get(i).equals(clusters.get(i))))
            {
                changed = true;
            }
        }
        // may need to reimplement, not done right
        
        clusters = newClusters;

        return changed;
    }
}
