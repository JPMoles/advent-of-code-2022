import java.util.ArrayList;

public class Node {

    Node parent;
    ArrayList<Node> dirs;
    ArrayList<Node> files;
    String val; // name of file or dir
    int size;
    boolean alreadyLS;

    public Node(Node parent, String val) {
        this.parent = parent;
        this.val = val;
        this.dirs = new ArrayList<Node>(); // no dirs to start
        this.files = new ArrayList<Node>(); // no files to start
        this.alreadyLS = false;
        this.size = 0;
    }

    public Node(Node parent, String val, int size) {
        this.parent = parent;
        this.val = val;
        this.dirs = new ArrayList<Node>(); // no dirs to start
        this.files = new ArrayList<Node>(); // no files to start
        this.alreadyLS = false;
        this.size = size;
    }

    public boolean hasDir(String dirName) {
        for(int i = 0; i < dirs.size(); i++) {
            if(dirs.get(i).val.equals(dirName))
                return true;
        }
        return false;
    }

    public Node getDir(String dirName) {
        for(int i = 0; i < dirs.size(); i++) {
            if(dirs.get(i).val.equals(dirName))
                return dirs.get(i);
        }
        return null;
    }

}
