import java.math.BigInteger;
import java.util.Queue;

public class MonkeyTwo implements Comparable<MonkeyTwo> {
    
    int id;
    Queue<BigInteger> items;
    char operation;
    String operationAmount;
    String divisibleBy;
    int positiveTestMonkey;
    int negativeTestMonkey;
    int itemsInspected;

    public MonkeyTwo(int id, Queue<BigInteger> items, char operation, String operationAmount, String divisibleBy, int positiveTestMonkey, int negativeTestMonkey) {
        this.id = id;
        this.items = items;
        this.operation = operation;
        this.operationAmount = operationAmount;
        this.divisibleBy = divisibleBy;
        this.positiveTestMonkey = positiveTestMonkey;
        this.negativeTestMonkey = negativeTestMonkey;
        this.itemsInspected = 0;
    }

    public BigInteger performOperation(BigInteger item) {
        if(operation == '*') {
            if(operationAmount.equals("old")) {
                return item.multiply(item);
            } else {
                return item.multiply(new BigInteger(operationAmount));
            }
        } else {
            if(operationAmount.equals("old")) {
                return item.multiply(item);
            } else {
                return item.add(new BigInteger(operationAmount));
            }
        }
    }

    public int performTest(BigInteger item) {
        return (item.mod(new BigInteger(divisibleBy))).equals(new BigInteger("0")) ? positiveTestMonkey : negativeTestMonkey;
    }

    @Override
    public int compareTo(MonkeyTwo m) {
        return this.itemsInspected - m.itemsInspected;
    }

}
