package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * Created by longxingyu on 2019/4/21.
 * 数学计算（带有括号表示其优先级）
 * value : push onto the value stack
 * operator: push onto the ops stack
 * Left parenthesis: ignore
 * right parenthesis: pop ops and two values;
 * push the result of applying that ops to those values
 * onto the value stack
 */
public class Evaluate {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> val = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) val.push(val.pop() + val.pop());
                else if (op.equals("-")) val.push(val.pop() - val.pop());
                else if (op.equals("*")) val.push(val.pop() * val.pop());
                else if (op.equals("/")) val.push(val.pop() / val.pop());
            }
            else val.push(Double.parseDouble(s));
        }
        StdOut.println(val.pop());
    }
}
