
//Name: Surbhi Goel
//USC loginid: surbhigo@usc.edu
//CS 455 PA2
//Fall 2016

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PolynomialCalculator

{
	/**
    Return a Polynomial version 
	 * As user enter Create command the prompt will ask for coeff-power pairs
	 * If user missed the last exponent (i.e., odd number of values). 
	For that ignores the last value entered (i.e., the term that had a coefficient given, but no corresponding exponent).
	 * If a negative exponent is entered. For this uses the absolute value.
    For the last two items listed above, where you are 
    taking corrective action, you should label your message with the string "WARNING:" instead.
	 */
	private static Polynomial UserInput(Scanner in)
	{
		ArrayList<Double> userList = new ArrayList<Double>();
		Polynomial poly = new Polynomial();
		double coeff = 0.0;
		int exponent = 0;

		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>" );

		String input = in.nextLine();
		Scanner lineScanner = new Scanner(input);	

		while(lineScanner.hasNextDouble())
		{

			userList.add(lineScanner.nextDouble());

		}
		if(userList.size()%2!= 0)
		{
			System.out.println("WARNING: missing the last exponent (i.e., odd number of values)");
		}

		for(int i = 0; i < userList.size()-1; i+=2)
		{
			if(userList.get(i+1) < 0)
			{
				System.out.println("WARNING: A negative exponent is found");
			}
			coeff = userList.get(i);
			exponent = Math.abs(userList.get(i+1).intValue());
			poly = poly.add(new Polynomial(new Term(coeff,exponent)));
		}
		return poly;
	}

	/**
    	Return a Polynomial version 
	 * Assignment of polynomial to Polynomial Array
	 * Checks for illegal polynomial index (for the array of polynomials)
	 */

	private static void createInput(int value, Polynomial polyArr[], Scanner in)
	{

		if(value > polyArr.length-1)
		{
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
		}
		else
		{
			Polynomial p2 = UserInput(in);
			polyArr[value]=p2;
		}							
	}

	/**
	 As user enter Print command then: 
    Prints a String version of the polynomial.
    Polynomial is in a simplified form (only one term for any exponent),
    with no zero-coefficient terms, and terms are shown in
    decreasing order by exponent.
	 */

	private static void printPoly(int value , Polynomial polyArr[])
	{

		if(value > polyArr.length-1)
		{
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
		}
		else
		{
			System.out.println(polyArr[value].toFormattedString());
		}
	}

	/**
	 As user enter eval command then the prompt will ask for floating point value for x and 
    evaluates value of the polynomial at value for x.
	 */
	private static void evalPoly(int value, Polynomial polyArr[], Scanner in)
	{

		if(value >  polyArr.length-1)
		{
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
		}
		else
		{
			System.out.print("Enter a floating point value for x: ");
			Double x = in.nextDouble();
			System.out.println(polyArr[value].eval(x));
		}

	}

	/**
	 * As user enter add command then add two polynomials
    (neither poly is modified)
	 */

	private static void addPoly(Polynomial polyArr[], StringTokenizer st)
	{			
			String check = st.nextToken();
			String token = st.nextToken();
			String nextToken = st.nextToken();
			try 
			{
				value = Integer.parseInt(check); 
				int nextValue = Integer.parseInt(token);
				int lastValue = Integer.parseInt(nextToken);
				if((value >  polyArr.length-1) || (nextValue >  polyArr.length-1) || (lastValue >  polyArr.length-1))
				{
					System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
				}
				else
				{	
					Polynomial poly1= polyArr[Integer.parseInt(token)];
					Polynomial poly2= polyArr[Integer.parseInt(nextToken)];
					Polynomial poly3= new Polynomial();
					poly3 = poly2.add(poly1);
					polyArr[value] = poly3;
				}
			}
			catch(NumberFormatException e) 
			{
				System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
			} 
		

	}
	/*
	 * As user enter help command then it will provide a command summary.
	 */
	private static void helpPoly()
	{


		System.out.println("create: To build a Polynomial");
		System.out.println("print: To print a Polynomial");
		System.out.println("eval: To evaluate value of Polynomial");
		System.out.println("quit: To quit from running inputs");

	}

	private static final int size = 10; //size of Polynomial array
	private static int value = 0;

	public static void main(String[] args)
	{
		boolean flag = true;
		Polynomial polyArr [] = new Polynomial[size]; //Initialize an array of 10 polynomials with zero value
		for(int i = 0; i < polyArr.length; i++)
		{
			polyArr[i] = new Polynomial();
		}

		System.out.println("Press help to see all command options: ");
		Scanner in = new Scanner(System.in);

		while(flag)
		{	
			String scan = in.nextLine();
			String command = "";
			StringTokenizer st = new StringTokenizer(scan, " ");

			while(st.hasMoreTokens())
			{															
				command = st.nextToken().toLowerCase();

		  if(command.equals("create") || command.equals("print") || command.equals("eval") || command.equals("add") || command.equals("quit") || command.equals("help"))
				{

					if(command.equals("create"))
					{

						String check = st.nextToken();
						try 
						{
							value = Integer.parseInt(check); 
							createInput(value,polyArr,in);
						}
						catch(NumberFormatException e) 
						{
							System.out.println("ERROR: Illegal command.  Type 'help' for command options.");

						} 

					}

					if(command.equals("print"))
					{
						String check = st.nextToken();
						try 
						{
							value = Integer.parseInt(check); 
							printPoly(value, polyArr);
						}
						catch(NumberFormatException e) 
						{
							System.out.println("ERROR: Illegal command.  Type 'help' for command options.");

						} 


					}

					if(command.equals("eval"))
					{
						String check = st.nextToken();
						try 
						{
							value = Integer.parseInt(check); 
							evalPoly(value,polyArr,in);
						}
						catch(NumberFormatException e) 
						{
							System.out.println("ERROR: Illegal command.  Type 'help' for command options.");

						} 


					}
					if(command.equals("add"))
					{	

						addPoly(polyArr,st);

					}

					if(command.equals("help"))
					{
						if(st.hasMoreTokens())
						{
							System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
							break;
						}
						else
						{
							helpPoly();
							break;
						}

					}

					if(command.equals("quit"))
					{
						if(st.hasMoreTokens())
						{
							System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
							break;
						}
						else
						{
							flag = false;
							break;
						}
						
					}
				}
				else
				{
					System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
					break;
				}
			}
		}

	}
}



