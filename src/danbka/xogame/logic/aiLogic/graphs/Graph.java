package danbka.xogame.logic.aiLogic.graphs;

import com.sun.jmx.remote.internal.ArrayQueue;

public class Graph {
    private final int MAX_COUNT;
    private Node[] NodeList;
    private int NodeCount;
    private int[][] matrix;

    public Graph(int MAX_COUNT) {
        this.MAX_COUNT = MAX_COUNT;
        matrix = new int[MAX_COUNT][];
        for (int i = 0; i < MAX_COUNT; i++) {
            matrix[i] = new int[MAX_COUNT - i];
            for (int j = 0; j < MAX_COUNT - i; j++) {
                matrix[i][j] = 0;
            }
        }
        NodeCount = 0;
        NodeList = new Node[MAX_COUNT];
    }

    public void addNode(String label) {
        NodeList[NodeCount++] = new Node(label);
    }

    public void addEdge(int begin, int end) {
        matrix[begin][end] = 1;
    }

    void bfs(int v) {
        ArrayQueue
        NodeList[v].setVisited(true);
        queue.insert(v);
        int Node;

        //выведем вершину, с которой начинается обход, на экран
        System.out.println(NodeList[v].getLabel());
        while (!queue.isEmpty()) {
            int current = queue.pop();
            while ((Node = getSuccessor(current)) != -1) {
                NodeList[Node].setVisited(true);
                queue.insert(Node);
                System.out.println(NodeList[Node].getLabel());
            }
        }
        for (int j = 0; j < NodeCount; j++)
            NodeList[j].setVisited(false);
    }

    /**
     * Method return first found unvisited neighbor of <code>i</code> or <code>-1</code> if there none unvisited
     *
     * @param i
     * @return
     */
    int getSuccessor(int i) {
        for(int j = 0; j < nodeCount; j++) {
            if (matrix[i][j] == 1 && nodeList[j].isVisited() == false) {
                return j;
            }
        }
        return -1; //таких вершин нет
    }
}
