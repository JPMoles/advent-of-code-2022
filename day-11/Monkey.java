import java.util.Queue;

public class Monkey implements Comparable<Monkey> {
    
    int id;
    Queue<Integer> items;
    char operation;
    String operationAmount;
    int divisibleBy;
    int positiveTestMonkey;
    int negativeTestMonkey;
    int itemsInspected;

    public Monkey(int id, Queue<Integer> items, char operation, String operationAmount, int divisibleBy, int positiveTestMonkey, int negativeTestMonkey) {
        this.id = id;
        this.items = items;
        this.operation = operation;
        this.operationAmount = operationAmount;
        this.divisibleBy = divisibleBy;
        this.positiveTestMonkey = positiveTestMonkey;
        this.negativeTestMonkey = negativeTestMonkey;
        this.itemsInspected = 0;
    }

    public int performOperation(int item) {
        if(operation == '*') {
            if(operationAmount.equals("old")) {
                return item * item;
            } else {
                return item * Integer.parseInt(operationAmount);
            }
        } else {
            if(operationAmount.equals("old")) {
                return item + item;
            } else {
                return item + Integer.parseInt(operationAmount);
            }
        }
    }

    public int performTest(int item) {
        return item % divisibleBy == 0 ? positiveTestMonkey : negativeTestMonkey;
    }

    @Override
    public int compareTo(Monkey m) {
        return this.itemsInspected - m.itemsInspected;
    }

}
