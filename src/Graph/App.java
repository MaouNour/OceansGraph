package Graph;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import Gui.OceanGrid;

public class App {
    static int n;
    static int m;
    static int[][] arr;
    static Graph g;

    public static void main(String[] args) {
        dataEntry();
        SwingUtilities.invokeLater(() -> new OceanGrid(arr, g.booleanValues, g.desert, g.rivers, n, m));
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

        g = new Graph(n, m);
        System.out.println("Enter y if you want to enter desert: (anything else for no deserts)");
        String desertAccept = scan.next();
        if (desertAccept.equals("y")) {
            System.out.println("Enter Desert Array : 0 for no and 1 for desert ");
            for (int i = 0; i < n; i++) {
                System.out.print("row " + i + " : ");
                for (int j = 0; j < m; j++) {
                    g.desert[i][j] = scan.nextInt() == 1;
                }
                System.out.println();
            }
        }
        while (true) {
            System.out.println(
                    "want to add River? (x to not , q to quit or \"all\" to make all adjanstant cells have rivers)");
            String c = scan.next();
            if (c.equals("x")) {
                g.useRivers = false;
            }
            if (c.equals("q") || c.equals("x"))
                break;
            if (c.equals("all")) {
                g.rivers = null;
                break;
            }
            System.out.println(
                    "Enter i from and j from then i to and j to (should be adjastant and from the same height to be considerd)");
            int ifrom = scan.nextInt();
            int jfrom = scan.nextInt();
            int ito = scan.nextInt();
            int jto = scan.nextInt();
            if (!adjastant(ifrom, jfrom, ito, jto)) {
                System.out.println("Islands are not adjastant");
                continue;
            }
            g.addRiver(ifrom, jfrom, ito, jto);
        }
        scan.close();
        g.arrayEntry(arr);
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

    public static boolean adjastant(int i, int j, int i2, int j2) {
        if (i == i2) {
            if (j == j2 + 1 || j == j2 - 1)
                return true;
        }
        if (j == j2) {
            if (i == i2 + 1 || i == i2 - 1)
                return true;
        }
        return false;
    }

}
