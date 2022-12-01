import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-1/input.txt");
            BufferedReader bs = new BufferedReader(fs);

            int largestNum = 0, sum = 0;
            String line;
            while((line = bs.readLine()) != null) {
                if(line.equals("")) {
                    if(largestNum < sum)
                        largestNum = sum;
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }

            if(largestNum < sum)
                largestNum = sum;

            System.out.println(largestNum);

            bs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}