import java.util.ArrayList;
import java.util.List;

public class ListOrInt implements Comparable<ListOrInt> {

    // Integer or List
    boolean isInt;
    int val;
    List<ListOrInt> list;

    public ListOrInt(Integer val, String list) {
        if(val != null) {
            this.val = val;
            this.isInt = true;
        } else {
            this.isInt = false;
            this.list = new ArrayList<ListOrInt>();
            processList(list);
        }
    }

    private void processList(String list) {

        // Possible characters:
        // '[' ']'
        // ','
        // 0-10

        int interiorLists = 0;
        boolean listStarted = false;
        boolean skipInterior = false;
        boolean listElement = false;
        for(int i = 0; i < list.length(); i++) {
            char c = list.charAt(i);
            if(c == ']' && interiorLists == 0) {
                break; // This list is done
            } else if(c == ',') { // skip
                continue;
            } else if(c == '[' && !listStarted) { // Start list
                listStarted = true;
            } else if(c == '[' && listStarted && !skipInterior && !listElement) {
                // new interior list
                this.list.add(new ListOrInt(null, list.substring(i))); // adds the rest of the string, including parts that shouldn't be included
                interiorLists++;
                skipInterior = true; // skip everything in inner lists for this list
                listElement = true;
            } else if(c == '[' && listStarted && listElement) {
                // increase interior lists
                interiorLists++;
            } else if(c >= '0' && c <= '9' && !skipInterior) { // skip numbers in interior lists, doesn't handle multiple digit numbers
                // add number
                int numStart = i;
                while(list.charAt(i+1) >= '0' && list.charAt(i+1) <= '9')
                    i++;
                this.list.add(new ListOrInt(Integer.parseInt(list.substring(numStart, i+1)), null)); // could also be c - '0'
            } else if(c == ']' && interiorLists == 1) {
                // decrease interior list, start counting numbers/lists again
                interiorLists--;
                skipInterior = false;
                listElement = false;
            } else if(c == ']') {
                // decrease interior lists
                interiorLists--;
            }

        }

    }

    // return -1 for correct, 0 for exactly the same, 1 for incorrect
    public int checkOrder(ListOrInt packet2) {

        // Possible comparisons:
            // Two integers
            // Two lists
            // List and integer

        
        // Should check length of lists first

        int index = 0;
        while(index < this.list.size() && index < packet2.list.size()) {
            ListOrInt p1 = this.list.get(index);
            ListOrInt p2 = packet2.list.get(index);
            // should return true/false in here for being right/wrong order
            if(p1.isInt && p2.isInt) {
                if(p1.val != p2.val)
                    return p1.val < p2.val ? -1 : 1;
            } else if((p1.isInt && !p2.isInt) || (!p1.isInt && p2.isInt)) {
                if(p1.isInt) {
                    ListOrInt p1List = new ListOrInt(null, "[" + p1.val + "]"); // this is not right
                    int result = p1List.checkOrder(p2);
                    if(result != 0)
                        return result;
                } else { // p2.isInt
                    ListOrInt p2List = new ListOrInt(null, "[" + p2.val + "]"); // this is not right
                    int result = p1.checkOrder(p2List);
                    if(result != 0)
                        return result;
                }
            } else { // both are list
                int result = p1.checkOrder(p2);
                if(result != 0)
                    return result;
            }
            index++;
        }

        // Need to check which list ended first, then return true/false
        // return negative value if left packet is shorter than right
        return this.list.size() == packet2.list.size() ? 0 : this.list.size() - packet2.list.size();
    }

    @Override
    public int compareTo(ListOrInt o) {
        return this.checkOrder(o);
    }

    @Override
    public String toString() {
        return isInt ? String.valueOf(val) : list.toString();
    }

}
