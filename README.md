Nina Burger 672115028

#Compilation and Execution Instructions

1. Compilation

To compile the Java program, open a terminal or command prompt in the directory where InfixToPostfix.java is located and run:

javac InfixToPostfix.java

2. Execution

To execute the compiled program, provide the input file input1.txt as a command-line argument:

java InfixToPostfix input1.txt

Input File Format

The program reads expressions from input1.txt.

Each line in the file should contain a single infix expression.

Example Input (input1.txt)

(5 + 3) * (2 - (8 / 4))
((7 + 6) * (5 - 2)) / (4 + 1)
15 - ((3 * 8) / (2 + 2))

Expected Output

Expression 1:
Infix exp: (5 + 3) * (2 - (8 / 4))
Valid
Postfix exp: 5 3 + 2 8 4 / - *

Expression 2:
Infix exp: ((7 + 6) * (5 - 2)) / (4 + 1)
Valid
Postfix exp: 7 6 + 5 2 - * 4 1 + /

Expression 3:
Infix exp: 15 - ((3 * 8) / (2 + 2))
Valid
Postfix exp: 15 3 8 * 2 2 + / -

