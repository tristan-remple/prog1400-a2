// student also extends staff
public class Student extends Person {
	
	// the static base fee is set up front
	private static double baseFee = 3000;
	
	// parameterized constructor, unused
//	public Student(String inName, String inAddress, int inYears) {
//		super(inName, inAddress, inYears);
//		double additionalFees = inYears * 100;
//		this.budget = baseFee + additionalFees;
//	}
	
	// non-parameterized constructor passes up to person
	public Student() {
		super();
	}
	
	// set budget calculates the annual fees for the student
	@Override
	public void setBudget() {
		double additionalFees = this.years * 100;
		this.budget = (baseFee + additionalFees);
	}
	
	// number validation
	@Override
	public int validNumber(String inNum) {
		
		// calls on the super class to make sure it is a number
		int number = super.validNumber(inNum);
		
		// then checks whether that number is valid
		if (number < 1 || number > 4) {
			number = 0;
		}
		return number;
	}
	
	// the to string method generates a report specific to students
	@Override
	public String toString() {
		String outReport = String.format("name = %s, address = %s, year = %d, fee = $%s",
				this.getName(), this.getAddress(), this.getYears(), Person.cashNumber.format(this.getBudget()));
		return outReport;
	}

}
