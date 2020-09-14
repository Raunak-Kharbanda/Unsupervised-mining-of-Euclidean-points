import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class twotest {
	int m=0;
	//I was trying to make an array of size 500x3 which would have columns i, it's closest cluster and
	//distance between them. This function would've been used to print that 2D array. But I couldn't move 
	//ahead with the idea of updating it with new clusters everytime a new minimum distance was found.
	/*static void print_minDist(float[][] arr) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i)
        {
        	for (int j=0; j<3; j++)
        	{
        		System.out.print(arr[i][j]+"\t\t\t");
        	}
        	System.out.println();
        }  
    } */
	
	
	//This function is used to generate and print a data-set of 500 points in 3 dimensional Euclidean space.
	//It also generates a 2D array/matrix which contains all distances 
	//between all values of i and j (rows and columns)
	//where i and j are 2 points in our data-set.
	private static void generate_group(int x, int y, int z, int NUMBER)
	{
		System.out.println();
		int a=0, m, n, i, j, k;
		int[] xArray = new int[10];
		int[] yArray = new int[10];
		int[] zArray = new int[10];
		float distance;
		float[][] Distances = new float[10][10];
		
		for (i = x; i<(x+50); i=i+10)
			{
			for (j = y; j<(y+1); j=j+1)
				{
				for (k = z; k<(z+30); k=k+15)
					{
					System.out.println("P"+a+"\t\tX = "+i+"\tY = "+j+"\tZ = "+k);
					xArray[a] = i;
					yArray[a] = j;
					zArray[a] = k;
					a++;
					}}}
		
		for(m=0; m<10; m++)
		{
			for(n=0; n<10; n++)
			{
				distance = (float) Math.sqrt(Math.pow((xArray[m] - xArray[n]), 2) + Math.pow((yArray[m]-yArray[n]), 2) + Math.pow((zArray[m]-zArray[n]), 2));
				Distances[m][n] = distance;
			}
		}
		outliers(Distances, 25, 0.44, NUMBER);
		}

	//This function finds out the outliers points in our data-set using it's description of DB(p, D)
	//It then removes the outliers from the original data-set and 
	//generates a new arraylist which has the new data-set with the outliers removed.
	//Also, the whole rows and columns of outliers in the Distances matrix are updated with a very high value (999)
	//so that they won't matter when we need to find minimum distance values ahead in the program.
	private static void outliers(float[][] Distances, double d, double p, int NUMBER)
	{
		int m,n, farPoints;
		ArrayList<Integer> S = new ArrayList<>();
		ArrayList<Integer> S_dash = new ArrayList<>();
		ArrayList<Integer> outlierPositions = new ArrayList<>();
		for (m=0; m<10; m++)
		{
			S.add(m);
		}
		System.out.println();
		System.out.println("S is: "+S);
		for(m=0; m<10; m++)
		{
			farPoints = 0;
			for(n=0; n<10; n++)
			{
				if (Distances[m][n] > d)
				{
					farPoints++;
				}
			}
			if ((farPoints/9.0) > p)
			{
				outlierPositions.add(m);
				S.removeAll(outlierPositions);
			}
		}
		S_dash = S;
		System.out.println("Outliers' positions are: "+outlierPositions);
		System.out.println("S' is: "+S_dash+"\n");
		for(int i=0; i<outlierPositions.size(); i++)
		{
			int a = outlierPositions.get(i);
			for(int j=0; j<10; j++)
			{
					Distances[a][j] = 999;
					Distances[j][a] = 999;	
			}
		}
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if (i==j)
				{
					Distances[i][j] = 999;
				}
			}
		}
		clustering(S_dash, Distances, NUMBER);
	}

	//This function is supposed to make clusters of the closest points. I had tried my luck on various methods
	//and loops inside this function to accomplish that but I was always getting stuck on how to proceed
	//after forming one cluster. In the next iteration, if one of the closest two points found is already in a cluster
	//I couldn't determine that in which cluster it already is. I wouldn't be able to traverse a linked list in an array
	//or an array-list, and I wouldn't know which linked list of the many existing ones needs to be accessed to add
	//the new object/data-point. 
	private static void clustering(ArrayList<Integer> A, float[][] B, int NUMBER)
	{
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		int i=0, j=0, m=0, n=0;
		int[] C = new int[10];
		ArrayList<Integer> A1 = new ArrayList<>();
		ArrayList<LinkedList> A2 = new ArrayList<LinkedList>();
		ArrayList<Integer> A3 = new ArrayList<>();
		A1 = A;
		float[][] Distances = new float[10][10];
		Distances = B;
		float[][] minDist = new float[10][3];
		LinkedList list1 = new LinkedList();
		int[][] array1 = new int[10][10];
		//System.out.println("I am called");
	
		System.out.print("\nDistances array:\n");
		for(m=0; m<10; m++)
		{
			for(n=0; n<10; n++)
			{
		System.out.print(numberFormat.format(Distances[m][n])+" \t\t");
			}
		System.out.println();
		}
		System.out.println();
		
		//------------------------------------------------------------------------------------------------
		
		/*for(i = 0; i < 10; i++)
		{
			float min = getMinValue(Distances);
			for(j = 0; j < 10; j++)
			{
			if (Distances[i][j] == min) 
				{ 
				m = i; n = j;
                Distances[i][j] = 999;
                Distances[j][i] = 999;
                list1.add(m);
    			list1.add(n);
				}
			else if(Distances[i][j] == 999)
			{
				//m++;
			}
			}
		}
		
		A2.add(list1);
		System.out.println(A2);*/
		
		for(i=0; i<10; i++)
		{
			float min = getMinValue(Distances);
			for(j=0; j<10; j++)
			{
				if(Distances[i][j]==min)
				{
					array1[0][i] = i;
					array1[1][i] = j;		
					Distances[i][j] = 999;
	                Distances[j][i] = 999;
				}
			}
			for(m=0;m<10;m++)
			{
				if(array1[1][i] == array1[1][m])
				{
					if(i<m)
					{
						array1[2][i] = m;
						array1[0][m] = 0;
						array1[1][m] = 0;
					}
					else if(i>m)
					{
						array1[2][m] = i;
						array1[0][i] = 0;
						array1[1][i] = 0;
					}
				}
			}
			
		}
		
		System.out.println("Partial filling of different clusters:");
		for(i=0; i<10; i++)
		{
			for(j=0;j<10;j++)
			{
				System.out.print(array1[i][j]+"\t\t");
			}
			System.out.println();
		}
		System.out.println();
		
		//I wanted to try repetitive loops to find out the minimum distance from one point at a time and a respective 
		//other point from which this distance would've been.
		/*for(i=0; i<A1.size(); i++)
		{
			float min2 = Distances[i][0];
			for(j=0; j<A1.size(); j++)
			{
				if (Distances[i][j] == min2) 
				{  
					m=i; n=j;
	             }
			}
			list1.add(m);
			list1.add(n);
			A2.add(list1);
			
			//list1.clear();
			m = i;
            n = j;
			i = i+1;
		}
		//System.out.println(list1);
		System.out.println(A2);*/
		
	}
	
	public static float getMinValue(float[][] numbers)
	{
        float minValue = (float) numbers[0][0];
        for (int j = 0; j < numbers.length; j++)
        {
            for (int i = 0; i < numbers.length; i++)
            {
                if (numbers[j][i] < minValue )
                {
                    minValue = (float) numbers[j][i];
                }
            }
        }
        return minValue;
    }

	//In the next function, I was trying to generate values to fill up a 500x3 array (2 dimensional) of point
	//numbers, the closest point to them, and the distances between the two; all for our 500 points in our dataset.
	/*public static ArrayList<Float> min_Distance(float[][] numbers, int i)
	{
		float sample = 0;
        float min = (float) numbers[i][0];
        ArrayList<Float> l1 = new ArrayList<>();
        for(int j=1; j<500; j++)
        {
        	if(numbers[i][j]<min)
            {
            	min = numbers[i][j];
            	sample = j;
            }
        }
        l1.add(min);
        l1.add(sample);
        //arraylist array.add 1 min, 2-j array return
        return l1;
    }*/
	
	public static void main(String[] args)
	{
		int k=0;
		Scanner ob = new Scanner(System.in);
		System.out.println("Enter the value for k!");
		k = ob.nextInt();
		System.out.println();
		Random rd = new Random(); // creating Random object
	    int[] set = new int[3];
	    for (int i = 0; i < set.length; i++) 
	    {
	         set[i] = rd.nextInt(20);
	    }
	    System.out.println("Dataset 1:");
	    generate_group(set[0], set[1], set[2], k);
	    System.out.println("______________________________________________________________________________\n");
	    System.out.println("Dataset 2:");
		generate_group(2, 3, 1, k);
	    System.out.println("______________________________________________________________________________\n");
		System.out.println("Dataset 3:");
		generate_group(15, 2, 8, k);
		
	}

}
