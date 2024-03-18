package nilespider.app.ui.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GraphVisualization extends JPanel {
    private final int V; // Number of vertices
    private final LinkedList<Integer>[] adj; // Adjacency list
    private JButton[] buttons;
    private JButton backButton;

    private final ActionListener backActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(backButton);
            for (int i = 0; i < V; ++i) {
                if (buttons[i] != null) {
                    remove(buttons[i]);
                }
            }
            repaint();
        }
    };

    public GraphVisualization(int v) {
        V = v;
        adj = new LinkedList[v];
        buttons = new JButton[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // Add an edge to the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // Draw the graph
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int radius = 20; // Node radius

        // Draw nodes
        for (int i = 0; i < V; ++i) {
            int x = (int) (width * (0.5 + 0.4 * Math.cos(2 * Math.PI * i / V)));
            int y = (int) (height * (0.5 + 0.4 * Math.sin(2 * Math.PI * i / V)));
            g.setColor(Color.BLUE);
            g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i), x - 4, y + 4); // Node label
            if (buttons[i] != null) {
                buttons[i].setBounds(x - radius, y - radius, 2 * radius, 2 * radius);
            }
        }

        // Draw edges
        for (int i = 0; i < V; ++i) {
            int x1 = (int) (width * (0.5 + 0.4 * Math.cos(2 * Math.PI * i / V)));
            int y1 = (int) (height * (0.5 + 0.4 * Math.sin(2 * Math.PI * i / V)));
            for (int j : adj[i]) {
                int x2 = (int) (width * (0.5 + 0.4 * Math.cos(2 * Math.PI * j / V)));
                int y2 = (int) (height * (0.5 + 0.4 * Math.sin(2 * Math.PI * j / V)));
                g.setColor(Color.RED);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public void addButtonActionListener(int vertex, ActionListener listener) {
        if (buttons[vertex] == null) {
            buttons[vertex] = new JButton();
            buttons[vertex].setOpaque(false);
            buttons[vertex].setContentAreaFilled(false);
            buttons[vertex].setBorderPainted(false);
            buttons[vertex].setFocusPainted(false);
            buttons[vertex].addActionListener(listener);
            add(buttons[vertex]);
        }
    }

    public void addBackButton() {
        backButton = new JButton("Back");
        backButton.addActionListener(backActionListener);
        backButton.setBounds(10, 10, 80, 30);
        add(backButton);
    }

    public static void main(String[] args) {
        GraphVisualization graph = new GraphVisualization(10);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5,6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 0);
        JScrollPane scrollPane = new JScrollPane(graph);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            graph.addButtonActionListener(i, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button " + finalI + " clicked.");
                    graph.addBackButton();
                }
            });
        }
    }
}
