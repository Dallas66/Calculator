import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {


    final String[] OPERATORS = {"+","-","/","*"};
    protected Stack<String> stackOperations = new Stack<>();
    protected Stack<String> stackPoNum = new Stack<>();
    protected Stack<String> stackCalc = new Stack<>();

    public Calculator() {
        stackOperations.clear();
        stackPoNum.clear();
        stackCalc.clear();
    }

    protected void convertToRPN(String str){
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if(isNumber(token)){
                stackPoNum.push(token);
            }else if (isOperator(token)){
                while (!stackOperations.empty() && isOperator(stackOperations.lastElement()) && priority(token) <= priority(stackOperations.lastElement())){
                    stackPoNum.push(stackOperations.pop());
                }
                stackOperations.push(token);
            }else if (leftBracket(token)){
                stackOperations.push(token);
            }else if (rightBracket(token)){
                while (!stackOperations.empty() && !leftBracket(stackOperations.lastElement())){
                    stackPoNum.push(stackOperations.pop());
                }
                stackOperations.pop();
            }else {
                System.out.println("Unexpected item!");
                stackOperations.clear();
                stackPoNum.clear();
                return ;
            }
        }
        while (!stackOperations.empty()){
            stackPoNum.push(stackOperations.pop());
        }
        System.out.println("We've got an expression:\n"+ stackPoNum);
    }

    public double calculate(String input){
        convertToRPN(input);

        Collections.reverse(stackPoNum);
        stackCalc.clear();

        while(!stackPoNum.empty()){
            String token = stackPoNum.pop();
            if(isNumber(token)){
                stackCalc.push(token);
            }else if (isOperator(token)){
                double op1 = Double.parseDouble(stackCalc.pop());
                double op2 = Double.parseDouble(stackCalc.pop());
                switch (token){
                    case "+":
                        stackCalc.push(String.valueOf(op2+op1));
                        break;
                    case "-":
                        stackCalc.push(String.valueOf(op2-op1));
                        break;
                    case "*":
                        stackCalc.push(String.valueOf(op2*op1));
                        break;
                    case "/":
                        stackCalc.push(String.valueOf(op2/op1));
                        break;
                }
            }
        }
        return Double.parseDouble(stackCalc.pop());
    }

    protected boolean isNumber(String token){
        try {
            Double.parseDouble(token);
        }catch (Exception exc){
            return false;
        }
        return true;
    }

    protected boolean isOperator(String token) {
        for (String op : OPERATORS)
            if (op.equals(token))
                return true;
        return false;
    }

    protected int priority(String operation) {
        if (operation.equals("+") || operation.equals("-"))
            return 1;
        else
            return 2;
    }

    protected boolean leftBracket(String token) {
        return token.equals("(");
    }

    protected boolean rightBracket(String token) {
        return token.equals(")");
    }
}

