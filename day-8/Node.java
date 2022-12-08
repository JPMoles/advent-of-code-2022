public class Node {
    
    int val;
    int highestLeft, highestRight;
    int highestTop, highestBottom; 
    boolean isVisible;

    public Node(int val) {
        this.val = val;
        this.isVisible = false;
        this.highestLeft = -1;
        this.highestRight = -1;
        this.highestTop = -1;
        this.highestBottom = -1;
    }

}
