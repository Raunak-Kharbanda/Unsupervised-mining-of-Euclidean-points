import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
//import java.util.list.subtract;

public class two {
	int m;
	static void printArray_minDist(float[][] arr) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i)
        {
        	for (int j=0; j<3; j++)
        	{
        		System.out.print(arr[i][j]+"\t\t");
        	}
        	System.out.println();
        }  
    } 
	
	private static void generate_group(int x, int y, int z)
	{
		int a=0, m, n, i, j, k;
		int[] xArray = new int[500];
		int[] yArray = new int[500];
		int[] zArray = new int[500];
		float distance;
		float[][] Distances = new float[500][500];
		
		for (i = x; i<(x+50); i=i+10)
			{
			for (j = y; j<(y+20); j=j+1)
				{
				for (k = z; k<(z+30); k=k+6)
					{
					System.out.println("P"+a+"\t\tX = "+i+"\tY = "+j+"\tZ = "+k);
					xArray[a] = i;
					yArray[a] = j;
					zArray[a] = k;
					a++;
					}}}
		
		for(m=0; m<500; m++)
		{
			for(n=0; n<500; n++)
			{
				distance = (float) Math.sqrt(Math.pow((xArray[m] - xArray[n]), 2) + Math.pow((yArray[m]-yArray[n]), 2) + Math.pow((zArray[m]-zArray[n]), 2));
				Distances[m][n] = distance;
			}
		}
		
		
		
		outliers(Distances, 30, 0.46);
		}

	private static void outliers(float[][] Distances, double d, double p)
	{
		int m,n, farPoints;
		ArrayList<Integer> S = new ArrayList<>();
		ArrayList<Integer> S_dash = new ArrayList<>();
		ArrayList<Integer> outlierPositions = new ArrayList<>();
		for (m=0; m<500; m++)
		{
			S.add(m);
		}
		
		for(m=0; m<500; m++)
		{
			farPoints = 0;
			for(n=0; n<500; n++)
			{
				if (Distances[m][n] > d)
				{
					farPoints++;
				}
			}
			if ((farPoints/499.0) > p)
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
			for(int j=0; j<500; j++)
			{
					Distances[a][j] = 999;
					Distances[j][a] = 999;	
			}
		}
		clustering(S_dash, Distances);
	}

	
	private static void clustering(ArrayList<Integer> A, float[][] B)
	{
		int i=0, j=0, m=0, n=0;
		int[] C = new int[500];
		ArrayList<Integer> A1 = new ArrayList<>();
		ArrayList<LinkedList> A2 = new ArrayList<LinkedList>();
		ArrayList<Integer> A3 = new ArrayList<>();
		A1 = A;
		float[][] Distances = new float[500][500];
		Distances = B;
		float[][] minDist = new float[500][3];
		LinkedList list1 = new LinkedList();
		//System.out.println("I am called");
		
		while(i < 500)
		{
			while(j < 500)
			{
				if (i==j)
				{
					Distances[i][j] = 999;
				}
				j++;
			}
			i++;
		}
		
		/*for(m=0; m<30; m++)
		{
			for(n=0; n<30; n++)
			{
		System.out.print(Distances[m][n]+" ");
			}
		System.out.println();
		}*/
		
		//------------------------------------------------------------------------------------------------

		
		/*float min = getMinValue(Distances);
		while(i < 500)
		{
			while(j < 500)
			{
			if (Distances[i][j] == min) { 
                
                Distances[i][j] = 999;
                Distances[j][i] = 999;
             } 
            else 
            { 
                j = j + 1; 
            }
			}
			
		}*/
		
		
		
		//A2.addAll(A1);
		for(i=0; i<A1.size(); i++)
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
		System.out.println(A2);
		
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

	public static ArrayList<Float> min_Distance(float[][] numbers, int i)
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
    }
	
/*	public static int closest(float[][] numbers, int i)
	{
		int sample = 0;
        float min = (float) numbers[i][0];
        
        for(int j=0; j<500; j++)
        {
        	if(numbers[i][j]<min)
            {
            	min = numbers[i][j];
            	sample = j;
            }
        }
        return sample;
    }*/
	
	public static void main(String[] args)
	{
		int k=0;
		Scanner ob = new Scanner(System.in);
		System.out.println("Enter the value for k!");
		k = ob.nextInt();
		Random rd = new Random(); // creating Random object
	    int[] set = new int[3];
	    for (int i = 0; i < set.length; i++) 
	    {
	         set[i] = rd.nextInt(20);
	    }
	    //generate_group(set[0], set[1], set[2]);
		generate_group(2, 3, 1);
		//generate_group(15,2,8);
		
	}

}
