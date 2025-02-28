import java.io.*;
import java.util.*;

class Node {
    char data;
    Node next;
    
    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;
    
    public Stack() {
        this.top = null;
    }
    
    public void push(char data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }
    
    public char pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        char data = top.data;
        top = top.next;
        return data;
    }
    
    public char peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}

public class InfixToPostfix {
    
    public static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }
    
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    
    public static boolean isValidInfix(String exp) {
        Stack stack = new Stack();
        boolean lastWasOperator = true;
        boolean lastWasOpenParen = false;
        boolean lastWasOperand = false;
        
        for (char ch : exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                lastWasOperator = false;
                lastWasOpenParen = false;
                lastWasOperand = true;
            } else if (ch == '(') {
                stack.push(ch);
                lastWasOperator = false;
                lastWasOpenParen = true;
                lastWasOperand = false;
            } else if (ch == ')') {
                if (stack.isEmpty() || lastWasOperator || lastWasOpenParen) {
                    return false;
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                if (stack.isEmpty()) return false;
                stack.pop();
                lastWasOperator = false;
                lastWasOpenParen = false;
                lastWasOperand = true;
            } else if (isOperator(ch)) {
                if (lastWasOperator || !lastWasOperand) return false;
                lastWasOperator = true;
                lastWasOpenParen = false;
                lastWasOperand = false;
            } else {
                return false;
            }
        }
        return !lastWasOperator && stack.isEmpty();
    }
    
    public static String infixToPostfix(String exp) {
        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();
        
        for (char ch : exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java InfixToPostfix <filename>");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            int expressionNumber = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Expression " + expressionNumber + ":");
                System.out.println("Infix exp: " + line);
                if (isValidInfix(line)) {
                    System.out.println("Valid");
                    System.out.println("Postfix exp: " + infixToPostfix(line));
                } else {
                    System.out.println("Not-Valid");
                }
                expressionNumber++;
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
