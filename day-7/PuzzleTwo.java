import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PuzzleTwo {
    
    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);
            
            System.out.println("Day 7");

            // Create a tree of the directory structure
            // Need to be able to move back? so it should be doubly linked?
            // Directory '/' is the root

            Node root = new Node(null, "/");

            // Read input, know that it starts at / with 'cd /'
            Node currentDirectory = null;

            boolean alreadyReadLine = false;
            String line = null;
            while(alreadyReadLine || (line = br.readLine()) != null) {
                if(alreadyReadLine) alreadyReadLine = false;
                if(line.charAt(0) == '$') { // command: cd /, cd .., cd x, ls
                    String[] tokens = line.split(" ");
                    if(tokens[1].equals("cd")) {
                        if(tokens[2].equals("/")) {
                            // at root node
                            currentDirectory = root;
                        } else if(tokens[2].equals("..")) {
                            // set current directory to previous directory
                            if(currentDirectory != root) { // if at root, stay there
                                currentDirectory = currentDirectory.parent;
                            }
                        } else { // change to a named directory
                            // assuming all directories tried are valid, they have 0 size anyway
                            if(currentDirectory.hasDir(tokens[2])) {
                                // directory already exists, just change to it
                                currentDirectory = currentDirectory.getDir(tokens[2]);
                            } else {
                                // make directory and change to it
                                Node cdDir = new Node(currentDirectory, tokens[2]); // create new dir
                                currentDirectory.dirs.add(cdDir); // add dir to parent
                                currentDirectory = cdDir; // set current dir to parent
                            }
                        }
                    } else if(tokens[1].equals("ls")) {
                        // list current directory contents
                        // add files and dirs to directory + size
                        while(!alreadyReadLine && (line = br.readLine()) != null) {
                            tokens = line.split(" ");
                            if(tokens[0].equals("$")) {
                                currentDirectory.alreadyLS = true;
                                alreadyReadLine = true;
                                break;
                            }

                            // Two options:
                            // dir dirName
                            // number fileName

                            if(tokens[0].equals("dir")) { // line starts with $
                                // Add new dir if it doesn't exist, always check this, possible cd without ls
                                if(!currentDirectory.hasDir(tokens[1])) {
                                    Node dir = new Node(currentDirectory, tokens[1]);
                                    currentDirectory.dirs.add(dir);
                                }
                            } else {
                                // Check alreadyLS, if true -> do nothing, if false -> add all files
                                if(!currentDirectory.alreadyLS) {
                                    // tokens contains:
                                    // fileSize fileName
                                    Node file = new Node(currentDirectory, tokens[1], Integer.parseInt(tokens[0]));
                                    currentDirectory.files.add(file);
                                }
                            }
                        }
                    }
                }
            }

            // After creating the directory structure with files and sizes
            // Traverse the tree depth first search to determine the size of each directory
            // Each directories size is based on the contents, directories (size) + files (size)

            // directories can have the same name, so should map by node
            Map<Node, Integer> dirSizes = new HashMap<Node, Integer>();
            // Node -> size

            calcDirSizes(root, dirSizes);

            int spaceStillNeeded = 0;
            final int SPACE_NEEDED_FOR_UPDATE = 30_000_000;
            final int TOTAL_SPACE = 70_000_000;

            spaceStillNeeded = SPACE_NEEDED_FOR_UPDATE - (TOTAL_SPACE - dirSizes.get(root));
            System.out.println("Space still needed: " + spaceStillNeeded);

            PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
                (Node node1, Node node2) -> dirSizes.get(node1) - dirSizes.get(node2)
            );

            for(Node dir : dirSizes.keySet()) {
                if(dirSizes.get(dir) >= spaceStillNeeded)
                    minHeap.add(dir);
            }

            Node smallest = minHeap.poll();
            System.out.format("The node %s would work with size %s", smallest.val, dirSizes.get(smallest));

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int calcDirSizes(Node root, Map<Node, Integer> dirSizes) {

        int size = 0;
        // Sum file sizes
        for(Node file : root.files)
            size += file.size;
        
        // Sum directory sizes, doesn't call if dirs is empty
        for(Node dir : root.dirs) {
            size += dirSizes.containsKey(root) ? dirSizes.get(root) : calcDirSizes(dir, dirSizes);
        }

        dirSizes.put(root, size); // add dir to list
        return size;
    }
}
