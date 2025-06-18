package Graph;

import java.util.*;

public class Graph {
    int numberOfNodes;
    int n;
    int m;
    ArrayList<LinkedList<Integer>> arrRows;
    int[] booleanValues;
    boolean[][] desert;
    boolean[][] rivers;

    Graph(int n, int m) {
        this.m = m;
        this.n = n;
        this.numberOfNodes = n * m;
        arrRows = new ArrayList<LinkedList<Integer>>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            arrRows.add(new LinkedList<>());
        }
        desert = new boolean[n][m];
        rivers = new boolean[numberOfNodes][numberOfNodes];
        booleanValues = new int[numberOfNodes];
    }

    Graph(int n, int m, int[][] dataArray) {
        this(n, m);
        arrayEntry(dataArray);
    }

    Graph(int n, int m, int[][] dataArray, boolean rivers[][]) {
        this(n, m);
        this.rivers = rivers;
        arrayEntry(dataArray);
    }

    public void arrayEntry(int[][] dataArray) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int valueOfTheNode = dataArray[i][j];
                // Node with its upper
                LinkedList<Integer> rows = arrRows.get(i * m + j);
                if (i != 0)
                    add(rows, i * m + j, (i - 1) * m + j, valueOfTheNode, dataArray[i - 1][j]);
                // Node with its down
                if (i != n - 1)
                    // --------
                    add(rows, i * m + j, (i + 1) * m + j, valueOfTheNode, dataArray[i + 1][j]);
                // Node with its left
                if (j != 0) {
                    add(rows, i * m + j, i * m + j - 1, valueOfTheNode, dataArray[i][j - 1]);
                }
                // Node with its right
                if (j != m - 1)
                    add(rows, i * m + j, i * m + j + 1, valueOfTheNode, dataArray[i][j + 1]);
            }
        }
    }

    public void add(LinkedList<Integer> row, int i, int nodeNumber, int value1, int value2) {
        if (value1 > value2)
            row.add(nodeNumber);
        else if (value1 == value2) {
            if (rivers == null || rivers[i][nodeNumber]) {
                row.add(nodeNumber);
            }
        }

    }

    public void addRiver(int ifrom, int jfrom, int ito, int jto) {
        rivers[ifrom * m + jfrom][ito * m + jto] = true;
        // rivers[ito*m+jto][ifrom*m+jfrom]=true;
    }

    public void solve() {
        Arrays.fill(booleanValues, 0);
        // int temp =0;
        for (int i = 0; i < numberOfNodes; i++) {
            calculateBooleanValue(i);
            // booleanValues[i]=temp;
        }
        for (int i = 0; i < numberOfNodes; i++) {
            if (booleanValues[i] == 3)
                System.out.println("(" + i / m + ", " + i % m + ")");
        }
    }

    public int calculateBooleanValue(int i) {
        if (desert[App.getRow(i)][App.getColumn(i)])
            return 0;
        if (booleanValues[i] != 0)
            return booleanValues[i];
        int temp = 0;
        if (App.isOnAtlanticOcean(i) && App.isOnPacificOcean(i)) {
            booleanValues[i] = temp = 3;
            return temp;
        } else if (App.isOnAtlanticOcean(i))
            temp = 1;
        else if (App.isOnPacificOcean(i))
            temp = 2;
        for (Integer j : arrRows.get(i)) {
            temp = addBooleanValues(calculateBooleanValue(j), temp);
        }
        booleanValues[i] = temp;
        return temp;

    }

    public int addBooleanValues(int a, int b) {
        if (a == b)
            return a;
        if (a + b > 3)
            return 3;
        return a + b;
    }
}
