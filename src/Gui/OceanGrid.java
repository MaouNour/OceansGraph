package Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class OceanGrid extends JFrame {
    JPanel grid;
    JPanel riverPanel;
    private final int windowWidth = 720;
    private final int windowHeight = 600;

    OceanGrid() {
        setTitle("Ocean Grid");
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(windowWidth, windowHeight);
        grid = new JPanel(new GridLayout());
        riverPanel = new JPanel(new BorderLayout());
        riverPanel.add(grid, SwingConstants.CENTER);
        this.add(riverPanel, SwingConstants.CENTER);
        Label northOcean = new Label("Pacfic Ocean");
        northOcean.setBackground(Color.BLUE);
        Label westOcean = new Label("Pacfic Ocean");
        westOcean.setBackground(Color.BLUE);
        Label eastOcean = new Label("Atlantic Ocean");
        eastOcean.setBackground(Color.CYAN);
        Label southOcean = new Label("Atlantic Ocean");
        southOcean.setBackground(Color.CYAN);
        this.add(northOcean, BorderLayout.NORTH);
        this.add(westOcean, BorderLayout.WEST);
        this.add(eastOcean, BorderLayout.EAST);
        this.add(southOcean, BorderLayout.SOUTH);

    }

    public OceanGrid(int[][] arr, int[] booleanValues, boolean[][] desert, boolean[][] rivers, int n, int m) {
        this();
        ((GridLayout) (grid.getLayout())).setColumns(m);
        ((GridLayout) (grid.getLayout())).setRows(n);
        ((GridLayout) (grid.getLayout())).setHgap(1);
        ((GridLayout) (grid.getLayout())).setVgap(1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                JLabel label = new JLabel("" + arr[i][j], SwingUtilities.CENTER);
                grid.add(label);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(new LineBorder(Color.DARK_GRAY));
                if (booleanValues[i * m + j] == 3) {
                    label.setForeground(Color.BLUE);
                }
                if (desert[i][j]) {
                    label.setBackground(Color.ORANGE);
                }
                checkRivers(i, j, n, m, label, rivers);
            }
        }
        setVisible(true);
    }

    private void checkRivers(int i, int j, int n, int m, JLabel label, boolean[][] rivers) {
        if (rivers == null) {
            riverPanel.setBackground(Color.BLUE);
            grid.setOpaque(false);
            return;
        }
        int from = i * m + j;
        int[] to = { (i - 1) * m + j, (i + 1) * m + j, i * m + j - 1, i * m + j + 1 };
        for (int k = 0; k < to.length; k++) {
            if (to[k] >= 0 && to[k] < n * m)
                if (rivers[from][to[k]]) {
                    label.setBorder(new MultiColorBorder(
                            k == 0 ? 2 : 1,
                            k == 2 ? 2 : 1,
                            k == 1 ? 2 : 1,
                            k == 3 ? 2 : 1,
                            k == 0 ? Color.CYAN : Color.GRAY,
                            k == 2 ? Color.CYAN : Color.GRAY,
                            k == 1 ? Color.CYAN : Color.GRAY,
                            k == 3 ? Color.CYAN : Color.GRAY));
                }
        }

    }
}
