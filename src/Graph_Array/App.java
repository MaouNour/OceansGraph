package Graph_Array;

import java.util.Scanner;

public class App {
    static int n;
    static int m;
    static int[][] arr;

    public static void main(String[] args) {
        dataEntry();
    }

    public static void dataEntry() {
        System.out.print("Enter n (number of rows): ");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        System.out.print("Enter m (number of columns): ");
        m = scan.nextInt();
        arr = new int[n][m];
        System.out.println("Enter data row by row: ");
        for (int i = 0; i < n; i++) {
            System.out.print("row " + i + " : ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = scan.nextInt();
            }
            System.out.println();
        }

        Graph g = new Graph(n, m);
        g.dataArrEntery(arr, n, m);
        System.out.println("Enter y if you want to enter desert: (anything else for no deserts)");
        String desertAccept = scan.next();
        if (desertAccept.equals("y")) {
            System.out.println("Enter Desert Array : 0 for no and 1 for desert");
            for (int i = 0; i < n; i++) {
                System.out.print("row " + i + " : ");
                for (int j = 0; j < m; j++) {
                    g.desert[i][j] = scan.nextInt() == 1;
                }
                System.out.println();
            }
        }
        while (true) {
            System.out.println("want to add River? (write x to quit)");
            String c = scan.next();
            if (c.equals("x"))
                break;
            System.out.println(
                    "Enter i from and j from then i to and j to (Only rivers on the same land height and adjastant lands will be considered)");
            int ifrom = scan.nextInt();
            int jfrom = scan.nextInt();
            int ito = scan.nextInt();
            int jto = scan.nextInt();
            g.addRiver(ifrom, jfrom, ito, jto);
        }
        scan.close();
        g.solve();

    }

    public static boolean isOnPacificOcean(int i) {
        return (getRow(i) == 0 || getColumn(i) == 0);
    }

    public static boolean isOnAtlanticOcean(int i) {
        return (getRow(i) == n - 1 || getColumn(i) == m - 1);
    }

    public static int getRow(int i) {
        return i / m;
    }

    public static int getColumn(int i) {
        return i % m;
    }
}
