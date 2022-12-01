import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-1/input.txt");
            BufferedReader bs = new BufferedReader(fs);

            ArrayList<Integer> elvesTotalCalories = new ArrayList<Integer>();

            int sum = 0;
            String line;
            while((line = bs.readLine()) != null) {
                if(line.equals("")) {
                    elvesTotalCalories.add(sum);
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }
            elvesTotalCalories.add(sum);

            elvesTotalCalories.sort(Comparator.naturalOrder());
            
            int topThree = 0;
            for(int i = elvesTotalCalories.size() - 1; i > elvesTotalCalories.size() - 4; i--)
                topThree += elvesTotalCalories.get(i);

            System.out.println(topThree);

            bs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}