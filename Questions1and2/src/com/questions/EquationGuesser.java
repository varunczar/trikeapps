package com.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * This class in its constructor takes as one argument an sequence of integers 
 * and as another argument another integer. It provides a method guess that takes 
 * an integer (guesses). When guess is called, it outputs string representations of equations, 
 * their result, and the difference between that result and the desired result guesses times. 
 * Finally, it outputs the number of guesses and what the best guess was.
 * @author varun
 *
 */
public class EquationGuesser {

	//Input array of integers
	private int[] numbers;
	//Expected Result
	private int expectedResult;
	//String constants
	private static final String SPACE = " ";
	private static final String EQUALS = "= ";
	private static final String PARENTHESIS_OPEN = " (";
	private static final String PARENTHESIS_CLOSE = ")";
	
	/**
	 * Constructor to initialise array of input integers and an expected result
	 * @param numbers
	 * @param result
	 */
	public EquationGuesser(int[] numbers, int expectedResult) {
		//Halt execution if input data is invalid
		if (numbers == null || numbers.length < 1) {
			System.out.println("Invalid size of array of integers");
			return;
		}
		
		this.numbers = numbers;
		this.expectedResult = expectedResult;
	}
	
	/**
	 * This method invokes the expression calculator that outputs string representations 
	 * of equations, their result, and the difference between that result and the desired 
	 * result guesses times
	 * @param guesses - number of guesses allowed
	 */
	public void guesses(int guesses)
	{
		//Calculate number of input integers
		int lengthOfArrayOfIntegers = numbers.length;
		char[] perm = new char[lengthOfArrayOfIntegers-1];
		String operators = "+*-/";
		
		//Compute results for arrays with more than 2 integers
		if(lengthOfArrayOfIntegers>2)
		{
			List<String> operatorPermutations = new ArrayList<>();
			//Call the method permutation, to calculate permutations  
			permutation(perm, 0, operators,operatorPermutations);

			compute(operators, operatorPermutations, guesses);
		}
		//Compute results for arrays with 2 integers
		else if(lengthOfArrayOfIntegers==2)
		{
			compute(guesses, operators);
		}
	}

	/**
	 * This method takes in the number of guesses and operators and invokes
	 * the evaluate method to evaluate the expression
	 * @param guesses
	 * @param operators
	 */
	private void compute(int guesses, String operators) {
		int guessCounter=0;
		//Iterate through the list of operators
		for(int index=0;index<operators.length();index++)
		{
			int increment = 0;
			//Create expression
			StringBuilder builder = new StringBuilder();
			builder.append(numbers[increment]);
			builder.append(SPACE);
			builder.append(operators.charAt(index));
			builder.append(SPACE);
			increment++;
			builder.append(numbers[increment]);
			builder.append(SPACE);
			//Increment the guess Counter
			guessCounter++;
			String expression=builder.toString();
			//Obtain the difference between the result obtained and the expected result
			int difference = evaluate(guesses, guessCounter, expression);
			if(difference==0 || guessCounter==guesses)
			{
				//If the result has been obtained or the total number of guesses
				//have been made, exit from the programme
				return;
			}
		}
	}

	/**
	 * This method takes in the number of guesses, operatorPermutations and operators and invokes
	 * the evaluate method to evaluate the expression
	 * @param operators
	 * @param operatorPermutations
	 * @param guesses
	 */
	private void compute(String operators, List<String> operatorPermutations, int guesses) {
		//Calculate the number of operators
		int totalSize = operatorPermutations.size();
		//Calculate the chunksize to process operators in batches 
		int chunkSize = totalSize/(operators.length()-1);
		int guessCounter=0;
		
		int init = 0;
		//Iterate through the list of permutations
		while(init<operatorPermutations.size()-1)
		{
			int increment = init;
			
			for(int index=0;index<operators.length();index++)
			{
				//Combine the operators with the operands and evaluate the expression so obtained
				if(increment<operatorPermutations.size())
				{
					String operatorString = operatorPermutations.get(increment);
					int integerIndex=0;
					
					StringBuilder builder = new StringBuilder();
					builder.append(numbers[integerIndex]);
					builder.append(SPACE);
					while(integerIndex<operatorString.length())
					{
						builder.append(operatorString.charAt(integerIndex));
						builder.append(SPACE);
						integerIndex++;
						builder.append(numbers[integerIndex]);
						builder.append(SPACE);
						
					}
					//Increment the guess Counter
					guessCounter++;
					String expression=builder.toString();
					//Obtain the difference between the result obtained and the expected result
					int difference = evaluate(guesses, guessCounter, expression);
					if(difference==0 || guessCounter==guesses)
					{
						//If the result has been obtained or the total number of guesses
						//have been made, exit from the programme
						return;
					}
					  
					
				}
				increment+=chunkSize;
			
			}
			init++;
		}
	}

	/**
	 * This method evaluates the expression and prints the output 
	 * It also prints the Guesses made to arrive at the correct result
	 * @param guesses
	 * @param guessCounter
	 * @param expression
	 * @return difference between the result obtained and the expected result
	 */
	private int evaluate(int guesses, int guessCounter, String expression) {
		int result;
		result=evaluate(expression);
		//Calculate difference
		int difference = result-expectedResult;
		System.out.println(expression +EQUALS+result +PARENTHESIS_OPEN
				+difference+PARENTHESIS_CLOSE);
		if(difference==0)
		{
			//Print the number of guesses if the correct result has been obtained
			System.out.println("Guesses: "+guessCounter+" Best Guess: "+expression +EQUALS+result +PARENTHESIS_OPEN
				+difference+PARENTHESIS_CLOSE);
		}
		return difference;
		
	}
	
	/**
	 * This method populates an array of permutations of operators
	 * @param permutationPrefix - Prefix of character arrays to facilitate permutation creation
	 * @param position - index to compute permutation prefix placement
	 * @param value - suffix value of the permutation string
	 * @param operatorPermutations - list of operator permutations
	 */
	private void permutation(char[] permutationPrefix, int position, 
			String value,List<String> operatorPermutations) {
	    if (position == permutationPrefix.length) {
	    	operatorPermutations.add(new String(permutationPrefix));
	    } else {
	        for (int index = 0 ; index < value.length() ; index++) {
	        	permutationPrefix[position] = value.charAt(index);
	            permutation(permutationPrefix, position+1, value,operatorPermutations);
	        }
	    }
	}	
	
	/**
	 * This method evaluates in the input expression using stacks	
	 * @param expression to evaluate
	 * @return result of evaluation
	 */
	public int evaluate(String expression)
    {
        char[] expressionFields = expression.toCharArray();
        //Operators
        Stack<Character> operators = new Stack<Character>();
        //Integer values
        Stack<Integer> integers = new Stack<Integer>();
        //Length of expression tokens
        int lengthOfFields = expressionFields.length;
        
        for (int expressionIndex = 0; expressionIndex < lengthOfFields; expressionIndex++)
        {
            //Skipping white spaces
            if (expressionFields[expressionIndex] == ' ')
                continue;
 
            if (expressionFields[expressionIndex] >= '0' && expressionFields[expressionIndex] <= '9')
            {
                StringBuilder builder = new StringBuilder();
                // There may be more than one digits in number
                while (expressionIndex < expressionFields.length && 
                		expressionFields[expressionIndex] >= '0' && 
                		expressionFields[expressionIndex] <= '9')
                {
                    builder.append(expressionFields[expressionIndex++]);
                }
                //Push number onto the stack
                integers.push(Integer.parseInt(builder.toString()));
            }

            // Evaluate operators
            else if (expressionFields[expressionIndex] == '+' 
            		|| expressionFields[expressionIndex] == '-' 
            		|| expressionFields[expressionIndex] == '*' 
            		|| expressionFields[expressionIndex] == '/')
            {
              	// Apply the operator to the top two elements based on precedence
                while (!operators.empty() && hasPrecedence(expressionFields[expressionIndex], operators.peek()))
                  integers.push(applyOp(operators.pop(), integers.pop(), integers.pop()));
 
                // Push operators onto the stack
                operators.push(expressionFields[expressionIndex]);
            }
        }
 
        // Apply all remaining operators
        while (!operators.empty())
            integers.push(applyOp(operators.pop(), integers.pop(), integers.pop()));
 
        // Return the result
        return integers.pop();
    }
 
    /**
     * This method checks for precedence of operators and returns true
     */
    public boolean hasPrecedence(char operator1, char operator2)
    {

        if ((operator1 == '*' || operator1 == '/') 
        		&& (operator2 == '+' || operator2 == '-'))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
 
    /**
     * This method applies the operator to the operands and returns a result
     * from the operations
     * @param operator
     * @param number2
     * @param number1
     * @return result from operations
     */
    public static int applyOp(char operator, int number2, int number1)
    {
        switch (operator)
        {
        case '+':
            return number1 + number2;
        case '-':
            return number1 - number2;
        case '*':
            return number1 * number2;
        case '/':
            if (number2 == 0)
                throw new
                UnsupportedOperationException("Divide by zero error");
            return number1 / number2;
        }
        return 0;
    }

    public static void main(String[] args)
	{
		int numbers[] = {1,2,3,4};
		int expectedResult = 24;
		
		EquationGuesser equationGuesser = new EquationGuesser(numbers, expectedResult);
		equationGuesser.guesses(3);
		
	}
}
