import java.text.DecimalFormat;
import java.text.NumberFormat;

// person is an abstract class, because we don't want and people who are not staff or students to be created
public abstract class Person {

	// all people have 4 variables
	private String name;
	private String address;
	protected int years;
	protected double budget;
	
	// formatting for numbers
	public static NumberFormat cashNumber = new DecimalFormat("#0.00");
	
	// the non-parameterized constructor assigns generic values to most variables
	public Person() {
		this.name = "Unknown";
		this.address = "Unknown";
		this.years = 0;
	}
	
	// the parameterized constructor assigns the variables directly
	// it is not used
//	public Person(String inName, String inAddress, int inYears) {
//		this.name = inName;
//		this.address = inAddress;
//		this.years = inYears;
//	}
	
	// getters and setters
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getYears() {
		return this.years;
	}
	
	public double getBudget() {
		return this.budget;
	};
	
	public void setName(String inName) {
		this.name = inName;
	}
	
	public void setAddress(String inAddress) {
		this.address = inAddress;
	}
	
	public void setYears(int inYears) {
		this.years = inYears;
	}
	
	// some abstract methods are declared
	public abstract void setBudget();
	
	// the toString method exists as part of the base object class
	@Override
	public abstract String toString();
	
	// the unique methods used for validation are last
	// the initial code for number validation checks that it is a number
	// but whether it's a valid number is assigned to the subclasses
	public int validNumber(String inNum) {
		int number;
		try {
			number = Integer.parseInt(inNum);
		} catch (Exception ex) {
			number = 0;
		}
		return number;
	};
	
	// string validation is put in person to keep it out of main
	public static boolean validString(String inString) {
		return ((inString != null) && !(inString.isEmpty()));
	}
	
}
