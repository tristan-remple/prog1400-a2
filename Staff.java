// staff is a subclass of person
public class Staff extends Person {
	
	// the base pay of a staff member never changes, so it's static and set up front
	private static double basePay = 50000;
	
	// parameterized constructor sets everything at once, including doing the budget calculation
	// this constructor is not used
//	public Staff(String inName, String inAddress, int inYears) {
//		
//		// some values are passed to the superclass
//		super(inName, inAddress, inYears);
//		double additionalPay = inYears * 500;
//		this.budget = basePay + additionalPay;
//	}
	
	// the non-parameterized constructor simply passes up to the person constructor
	public Staff() {
		super();
	}
	
	// set budget calculates the annual salary of a staff member
	@Override
	public void setBudget() {
		double additionalPay = this.years * 500;
		this.budget = (basePay + additionalPay);
	}
	
	// the number validation method
	@Override
	public int validNumber(String inNum) {
		
		// calls on the superclass to make sure the numnber is a number
		int number = super.validNumber(inNum);
		
		// checks if the number is valid; assigns number to 0 if it isn't valid
		if (number < 1 || number > 30) {
			number = 0;
		}
		return number;
	}
	
	// the to string method generates a report specific to staff
	@Override
	public String toString() {
		String outReport = String.format("name = %s, address = %s, years = %d, pay = $%s",
				this.getName(), this.getAddress(), this.getYears(), Person.cashNumber.format(this.getBudget()));
		return outReport;
	}

}
