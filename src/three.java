import java.util.Random;

public class three{
public static int TARGET_CLUSTERS = 4; // number of clusters we are targeting for the various "schemes"
public static int NUMBER_OF_POINTS = 500; // number of points we want
public static float SPACE_BETWEEN = 50; // space between clusters for scheme 1

public static void PrintUsage() {
System.out.println("USAGE: java Generate3DPoints SCHEME#\n");
System.out.println("Scheme #1: Separating points into TARGET_CLUSTERS with SPACE_BETWEEN between them");
System.out.println("Scheme #2: Generating each cluster into different overlapping bands, the first one going 0 to 1/4 the second 0 to 1/2 for 4 clusters, etc.... ");
System.out.println("Scheme #3: No restriction, every point is generated anywhere within the x/y/z max");
return;
}

public static void main(String[] args){
//Change these numbers to move the cluster around
int xMax = 100;
int yMax = 100;
int zMax = 100;
int scheme = 0;

if (args.length < 1) {
System.out.println("Please specify a scheme #");
PrintUsage();
return;
}
try {
scheme = Integer.parseInt(args[0]);
} catch (Exception e) {
System.out.println("Unable to convert scheme# " + args[0] + " to integer.");
PrintUsage();
return;
}
if (scheme < 1 || scheme > 3) {
System.out.println("Scheme must be a number between 1 and 3, please see the schemes below:");
PrintUsage();
return;
}

//generateNormalPoints(xMax, 0, yMax, 0, zMax, 0, 100);
generateRandomPoints(xMax, yMax, zMax, NUMBER_OF_POINTS, scheme);
}


/* Created by me to use the normal random generator, modified with ideas from Jianjun Huang however I added the concept of space between clusters */
public static void generateRandomPoints(int xMax, int yMax, int zMax, int numberOfPoints, int scheme) {
Random r = new Random();
int clustergroup;
int intervalX = (int)(xMax/ TARGET_CLUSTERS);
int intervalY = (int)(yMax/ TARGET_CLUSTERS);
int intervalZ = (int)(zMax/ TARGET_CLUSTERS);
int x, y, z;
for (int i = 0;i < numberOfPoints; i++) {
clustergroup = i / (numberOfPoints / TARGET_CLUSTERS);
if (scheme == 1) { // Separated clusters
x = Math.round(intervalX*clustergroup + SPACE_BETWEEN * clustergroup) + r.nextInt(intervalX);
y = Math.round(intervalY*clustergroup + SPACE_BETWEEN * clustergroup) + r.nextInt(intervalY);
z = Math.round(intervalZ*clustergroup + SPACE_BETWEEN * clustergroup) + r.nextInt(intervalZ);
} else if (scheme == 2) { // Overlapping bands
clustergroup++;
x = r.nextInt(intervalX*clustergroup);
y = r.nextInt(intervalY*clustergroup);
z = r.nextInt(intervalZ*clustergroup);
} else { // no restriction on any points
x = r.nextInt(xMax);
y = r.nextInt(yMax);
z = r.nextInt(zMax);
}

System.out.printf("%3d %3d %3d%n", x, y, z);
}

}


/**
This method will output a random, normal vector in 3D space around a 0, 0, 0 center
The Max and Min specifed are only a loose limitation bacuse the values can exceed them
*/
public static void generateNormalPoints(int xMax, int xMin, int yMax, int yMin, int zMax, int zMin, int numberOfPoints){
Random r = new Random();

for (int i = 0; i < numberOfPoints; i++){
int x = (int)(r.nextGaussian() * (xMax - xMin)) + xMin;
int y = (int)(r.nextGaussian() * (yMax - yMin)) + yMin;
int z = (int)(r.nextGaussian() * (zMax - zMin)) + zMin;

System.out.printf("%3d %3d %3d%n", x, y, z);
//System.out.printf("%3.5f, %3.5f, %3.5f%n", r.nextGaussian(), r.nextGaussian(), r.nextGaussian());
}
}
}