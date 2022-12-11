import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 11");

            ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
            
            String line;
            while((line = br.readLine()) != null) {
                // read in monkeys and starting state
                String[] tokens = line.split(" ");
                int id = Integer.parseInt(tokens[1].substring(0, 1));

                // Next line is starting items
                line = br.readLine();
                tokens = line.split(" ");
                Queue<Integer> items = new LinkedList<Integer>();
                for(int i = 4; i < tokens.length; i++) // all numbers in starting items
                    items.add(Integer.parseInt(tokens[i].replace(",", "")));

                line = br.readLine();
                tokens = line.split(" ");
                char operation = tokens[6].charAt(0);
                String operationAmount = tokens[7];

                line = br.readLine();
                tokens = line.split(" ");
                int divisibleBy = Integer.parseInt(tokens[5]);

                line = br.readLine();
                tokens = line.split(" ");
                int positiveTestMonkey = Integer.parseInt(tokens[9]);

                line = br.readLine();
                tokens = line.split(" ");
                int negativeTestMonkey = Integer.parseInt(tokens[9]);

                line = br.readLine(); // go past empty line

                monkeys.add(new Monkey(id, items, operation, operationAmount, divisibleBy, positiveTestMonkey, negativeTestMonkey));
            }

            // Perform 20 rounds
            for(int j = 0; j < 20; j++) {
                // 1 Round
                for(int i = 0; i < monkeys.size(); i++) {
                    Monkey m = monkeys.get(i);
    
                    // Monkey throw all items
                    while(m.items.size() > 0) {
                        int item = m.items.remove();
                        item = m.performOperation(item); // operation
                        item /= 3;// divide by 3 from monkey being bored with item
                        int monkeyToThrowTo = m.performTest(item); // perform test
                        monkeys.get(monkeyToThrowTo).items.add(item); // throw to monkey
                        m.itemsInspected++;
                    }
                }
                System.out.format("After round %d\n", j+1);
                printMonkeys(monkeys);
            }
            
            Collections.sort(monkeys);
            for(Monkey m : monkeys) {
                System.out.format("Monkey %d has %d items inspected\n", m.id, m.itemsInspected);
            }

            int highest = monkeys.get(monkeys.size() - 1).itemsInspected;
            int secondHighest = monkeys.get(monkeys.size() - 2).itemsInspected;

            System.out.format("Monkey business: %d", highest * secondHighest);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printMonkeys(ArrayList<Monkey> monkeys) {
        for(Monkey m : monkeys) {
            System.out.format("Monkey %d: ", m.id);
            ((LinkedList<Integer>) m.items).forEach((num) -> { System.out.print("" + num + ","); });
            System.out.println();
        }
    }

}