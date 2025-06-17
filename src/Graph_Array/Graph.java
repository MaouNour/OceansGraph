package Graph_Array;
import java.util.Arrays;

public class Graph {
    int numberOfNodes;
    int n;
    int m;
    int[][] arr;
    int[] booleanValues;
    boolean[][] desert;
    boolean[][] rivers;
    Graph(int n,int m){
        this.numberOfNodes = n*m;
        this.n=n;
        this.m=m;
        this.arr = new int[numberOfNodes][numberOfNodes];
        this.rivers = new boolean[numberOfNodes][numberOfNodes];
        this.desert= new boolean[n][m];
    }
    public void add(int i,int j,int value1,int value2)
    {
        arr[i][j]=value2;
        arr[j][i]=value1;
    }
    public void addWeighted(int i,int j,int value1,int value2)
    {
        if(value1>value2)
        arr[i][j]=value2;
        else if(value1==value2) 
        {
            if(rivers[i][j])
            {
                arr[i][j]=value2;
                arr[j][i]=value1;
            }
        }
        else
        {
        arr[j][i]=value1;
        }
    }
    public boolean isRiver(int from,int to)
    {
        return rivers[from][to];
    }
    public void dataArrEntery(int[][] dataEntry,int n,int m)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //the tile with its left
                if(j!=m-1)
                addWeighted(i*m+j,i*m+j+1,dataEntry[i][j],dataEntry[i][j+1]);
                //the tile with its down
                if(i!=n-1)
                addWeighted(i*m+j, (i+1)*m+j, dataEntry[i][j], dataEntry[i+1][j]);
            }
        }
    }
    public void addRiver(int ifrom,int jfrom,int ito,int jto)
    {
        rivers[ifrom*m+jfrom][ito*m+jto]=true;
    }
    public void solve()
    {
        booleanValues = new int[numberOfNodes];
        Arrays.fill(booleanValues, 0);
        int temp =0;
        for (int i=0;i<numberOfNodes;i++) {
            temp = calculateBooleanValue(i);
            booleanValues[i]=temp;
        }
        for (int i = 0; i < numberOfNodes; i++) {
            if(booleanValues[i]==3)
            System.out.println("("+i/m+", "+i%m+")");
        }
    }
    public int calculateBooleanValue(int i)
    {
        if(desert[App.getRow(i)][App.getColumn(i)])
        return 0;
        if(booleanValues[i]!=0) return booleanValues[i];
        int temp =0;
        if(App.isOnAtlanticOcean(i)&&App.isOnPacificOcean(i)) {
           booleanValues[i]=temp=3;
           return temp;
        }
        else if(App.isOnAtlanticOcean(i)) temp=1;
        else if(App.isOnPacificOcean(i)) temp=2;
        for(int j=0;j<numberOfNodes;j++)
        {
            if(arr[i][j]!=0)
            {
                temp=addBooleanValues(calculateBooleanValue(j), temp);
            }
        }
        booleanValues[i]=temp;
        return temp;
        
    }
    public int addBooleanValues(int a,int b)
    {
        if(a==b) return a;
        if(a+b>3) return 3;
        return a+b;
    }
}
