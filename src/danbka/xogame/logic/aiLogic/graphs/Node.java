package danbka.xogame.logic.aiLogic.graphs;

public class Node {
    private String label;
    private boolean visited;

    public Node(String label) {
        this.label = label;
        visited = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
