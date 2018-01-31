
//Name: Surbhi Goel
//USC loginid: surbhigo@usc.edu
//CS 455 PA2
//Fall 2016


import java.util.ArrayList;

/**
A polynomial. Polynomials can be added together, evaluated, and
converted to a string form for printing.
 */
public class Polynomial 
{

	/**
    Creates the 0 polynomial
	 */
	public Polynomial() 
	{
		polynomial.add(new Term(0,0));
		valPoly = 0.0;
		flag = true;
		format = "0.0";
		assert isValidPolynomial();

	}


	/**
    Creates polynomial with single term given
	 */
	public Polynomial(Term term) 
	{
		polynomial.add(term);
		assert isValidPolynomial();
	}


	/**
    Returns the Polynomial that is the sum of this polynomial and b
    (neither poly is modified)
	 */
	public Polynomial add(Polynomial b) 
	{
		flag = true;
		
		assert b.isValidPolynomial();
		
		Polynomial resultPoly = new Polynomial();

		for(int i = 0; i < polynomial.size() ;)
		{
			for(int j = 0; j < b.toGetPoly().size() ;)
			{
		      	if(polynomial.get(i).getExpon() > b.toGetPoly().get(j).getExpon()) //Checks exponent with greater value and result that term to result polynomial
				{
					resultPoly.toGetPoly().add(polynomial.get(i));
					i++;
				}
			  else if(polynomial.get(i).getExpon() < b.toGetPoly().get(j).getExpon()) //Checks exponent with greater value and result that term to result polynomial
				{
					resultPoly.toGetPoly().add(b.toGetPoly().get(j));
					j++;
				}

				else if(polynomial.get(i).getExpon() == b.toGetPoly().get(j).getExpon()) // if both polynomial has same exponent then add their coefficients
				{
					Term addCoeff = (new Term(((polynomial.get(i).getCoeff() + b.toGetPoly().get(j).getCoeff())),(polynomial.get(i).getExpon())));
					resultPoly.toGetPoly().add(addCoeff);
					i++;
					j++;
					
				}
				if(i == polynomial.size()) // if first polynomial is added to result polynomial then merge the result polynomial with polynomial b
				{
					while(flag)
					{
						for(int p = j; p < b.toGetPoly().size(); p++)
						{
							resultPoly.toGetPoly().add(b.toGetPoly().get(p));
						}
						flag = false;
					}
					j++;
				}
				if(j == b.toGetPoly().size()) //if b polynomial is added to result polynomial then merge the result polynomial with first polynomial 
				{
					while(flag)
					{
						for(int q = i; q < polynomial.size(); q++)
						{
							resultPoly.toGetPoly().add(polynomial.get(q));
						}
						flag = false;
					}
					i++;
				}
			}
		}

		for(int i = 0; i < resultPoly.toGetPoly().size(); i++)
		{
			if(resultPoly.toGetPoly().get(i).getCoeff() == 0)
			{
				resultPoly.toGetPoly().remove(i);
			}
		}		
		assert resultPoly.isValidPolynomial();
		
		return resultPoly;  // dummy code.  just to get stub to compile
	}


	/**
    Returns the value of the poly at a given value of x.
	 */
	public double eval(double x) 
	{
		valPoly = 0;
		
		for(int i = 0; i < polynomial.size(); i++)
		{

			valPoly = valPoly + (Math.pow(x, polynomial.get(i).getExpon()))*polynomial.get(i).getCoeff();
		}

		return valPoly;         // dummy code.  just to get stub to compile
	}


	/**
    Return a String version of the polynomial with the 
    following format, shown by example:
    zero poly:   "0.0"
    1-term poly: "3.2x^2"
    4-term poly: "3.0x^5 + -x^2 + x + -7.9"

    Polynomial is in a simplified form (only one term for any exponent),
    with no zero-coefficient terms, and terms are shown in
    decreasing order by exponent.
	 */
	public String toFormattedString() 
	{
		StringBuffer st = new StringBuffer();
		if(polynomial.size() == 0 || ((polynomial.size() == 1) && (polynomial.get(0).getCoeff() == 0)))
		{
			return format;
		}

		if(polynomial.size() == 1)
		{
			format = String.valueOf(polynomial.get(0).getCoeff()) +  "x^" + String.valueOf(polynomial.get(0).getExpon() + " ") ;
			if(polynomial.get(0).getCoeff() == 1.0)
			{
				format = format.replace(String.valueOf(polynomial.get(0).getCoeff()), "");
			}
			format = format.replace("x^0 ", " ");
			format = format.replace("x^1 ", "x ");
			
			return format;
		}

		if(polynomial.size() > 1)
		{
			
			for(int i = 0 ; i < polynomial.size(); i++)
			{
				
				if(polynomial.get(i).getCoeff()!=0 )
				{
					st.append(String.valueOf(polynomial.get(i).getCoeff()) +  "x^" + String.valueOf(polynomial.get(i).getExpon()) + " + " );
					
				}
											
			}
			st.delete(st.length()-2, st.length()-1);
			for(int i =0;i<st.length();i+=1)
			{
				if((i+3)<st.length() && st.charAt(i+3)=='x' && st.charAt(i+5)!='0' && st.charAt(i)=='1' && st.charAt(i+1)=='.' && st.charAt(i+2)=='0' )
				{
					st.replace(i, i+3, "");
					i=i-3;
					
				}
			}
			format = st.toString();	
			format = format.replace("x^0 ", " ");
			format = format.replace("x^1 ", "x ");
			
	
		}
		return format;

		// dummy code.  just to get stub to compile
	}


	// **************************************************************
	//  PRIVATE METHOD(S)

	/**
    Returns true iff the poly data is in a valid state.
	 */
	private boolean isValidPolynomial() 
	{
		if( polynomial.size()==1)
		{
			return true;
		}

		for(int i = 0; i < polynomial.size()-1; i++)
		{
			if(polynomial.get(i).getExpon() < polynomial.get(i+1).getExpon())
			{
				return false;
			}

		}
		return true;     // dummy code.  just to get stub to compile
	}

	private ArrayList<Term> toGetPoly()
	{
		return polynomial;
	}


	// **************************************************************
	//  PRIVATE INSTANCE VARIABLE(S)
	private ArrayList<Term> polynomial = new ArrayList<Term>();
	private double valPoly;
	private boolean flag;
	private String format;
	
	/* List of Representation Invariants for the object: 
	* List must be ordered, ordered from highest power term to lowest.
	* The polynomial will be in a simplified form: besides no zero terms, we won't ever have two terms with the same exponent.
	* There are no restrictions on the term argument used in creating one Polynomial Class 
	(although the Term class itself has a restriction of no negative exponents); 
	this implies that the Polynomial code itself is responsible for 
	making sure the internal representation is a simplified polynomial.*/

}
