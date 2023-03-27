import java.util.ArrayList;
import javax.swing.*;

public class Main {
	
	// method called to prompt the user for years
	private static String getYearInput(String entryType) {
		
		// creates a variable
		String inYears;
		
		// depending on the entry type (staff or student) the text of the prompt is different
		if (entryType == "student") {
			inYears = JOptionPane.showInputDialog("Enter student year (1-4):");
		} else {
			inYears = JOptionPane.showInputDialog("Enter staff years of service:");
		}
		
		// return the input
		return inYears;
	}

	public static void main(String[] args) {
		
		// create an array list to hold the people entered
		ArrayList<Person> peopleAtSchool = new ArrayList<Person>(); 
		
		// create and display a welcome message
		JOptionPane.showMessageDialog(null,
			"Welcome to the College Accounting App",
			"Accounting App",
			JOptionPane.INFORMATION_MESSAGE
		);
		
		// toggle for whether there are more people to add, defaults to true
		boolean moreEntries = true;
		
		// all the code to add people is contained in a do while block
		do {
			
			// Create a list of button options: "Invited", "Not invited"
			Object[] personList = { "Student", "Staff", "Finish" };
						
			// ask the user whether they're inputting staff or student, or if they're done
			int entryChoice = JOptionPane.showOptionDialog(null,
				"Select Student or Staff.",
				"Accounting App",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				personList,
				personList[0]
			);
			
			// entry is a blank Person object which will be added to the personList after validation
			Person entry;
			
			// entry type is whether the person is staff or student
			// this is used mainly for message dialog
			String entryType;
			
			// if the user is finished, break the loop and skip to the next section
			if (entryChoice == 2) {
				moreEntries = false;
				break;
				
			// if the user is not finished, execute the rest of the loop
			} else {
				
				// set the entry and entryType according to their choice
				if (entryChoice == 0) {
					entry = new Student();
					entryType = "student";
				} else {
					entry = new Staff();
					entryType = "staff";
				}
				
				// prompt the user for a name
				String inName = JOptionPane.showInputDialog(String.format("Enter %s name:", entryType));
				
				// validate that the name has been filled in using the validString method from Person
				while (Person.validString(inName) == false) {
					
					// if the name is blank, prompt again, as many times as needed
					inName = JOptionPane.showInputDialog(String.format("You must enter a valid string.\n"
							+ "Enter %s name:", entryType));
				};
				
				// user the setter for name in the Person class
				entry.setName(inName);
				
				// address is set and validated using the same code as name
				String inAddress = JOptionPane.showInputDialog(String.format("Enter %s address:", entryType));
				while (Person.validString(inAddress) == false) {
					inAddress = JOptionPane.showInputDialog(String.format("You must enter a valid string.\n"
							+ "Enter %s address:", entryType));
				};
				entry.setAddress(inAddress);
				
				// since the years dialog prompt is more than one word different between staff and students
				// we set it as a method and call on it here
				String inYears = getYearInput(entryType);
				
				// the validNumber method is in all 3 classes, with slight variation
				// since entry knows it is both a person and something else, and the Person method is overridden
				// the appropriate class method is called
				while (entry.validNumber(inYears) == 0) {
					
					// if the number is not valid, a message box is displayed
					JOptionPane.showMessageDialog(null,
						"Please enter a valid number.",
						"Accounting App",
						JOptionPane.INFORMATION_MESSAGE
					);
					
					// and then the years input method is called again
					inYears = getYearInput(entryType);
				}
				
				// the valid input is assigned to the entry
				// validNumber both validates and converts from string to int, so it needs to be called here as well
				entry.setYears(entry.validNumber(inYears));
				
				// now that entry has been validated, we can add it to the array list
				peopleAtSchool.add(entry);
				
				// we can also calculate the budget using the years and base numbers
				entry.setBudget();
				
				// during testing, we print the entry to console to make sure its working correctly
				// System.out.println(entry.toString());
			}
			
		// end of entry do while loop
		} while (moreEntries == true);
		
		// we set the main report text to a blank string
		String bigReport = "";
		
		// we set a counter to 1, to use in the list of people in that report
		int counter = 1;
		
		// we're calculating students first, so the amount they're paying in is set to 0 to start
		double incoming = 0;
		
		// since the report has headers, we want to generate the list items in their own string
		String studentReports = "";
		
		// loop through the array list with a for in loop
		for (Person thisGuy : peopleAtSchool) {
			
			// check if the current entry is a student
			if (thisGuy instanceof Student) {
				
				// if they are, add a numbered list item with their information
				studentReports += String.format("%d. %s\n", counter, thisGuy.toString());
				
				// calculate how much they're paying right now
				incoming += (thisGuy.getBudget() / 2);
				
				// increment the counter
				counter += 1;
			}
		}
		
		// set the header and data for the students to the big report text
		bigReport += String.format("Students: [Total: %d]\n%s", counter, studentReports);
		
		// reset the counter to 1
		counter = 1;
		
		// set outgoing funds to 0 to start
		double outgoing = 0;
		
		// create an empty string for the staff reports
		String staffReports = "";

		// loop through people again
		for (Person thisGuy : peopleAtSchool) {
			
			// this time, check if they're staff
			if (thisGuy instanceof Staff) {
				staffReports += String.format("%d. %s\n", counter, thisGuy.toString());
				
				// the budget is calculated differently from students
				outgoing += (thisGuy.getBudget() / 26);
				counter += 1;
			}
		}
		
		// add the staff header and data to the big report
		bigReport += String.format("Staff: [Total: %d]\n%s", counter, staffReports);
		
		// do the math to figure out the current balance
		double balance = incoming - outgoing;
		
		// add that to the big report as well
		bigReport += String.format("\nResults:\nOutgoing: $%s\nIncoming: $%s\nTotal: $%s",
				Person.cashNumber.format(outgoing),
				Person.cashNumber.format(incoming),
				Person.cashNumber.format(balance));
		
		// once all the text for the big report has been generated, output it to the user
		JOptionPane.showMessageDialog(null,
			bigReport,
			"Accounting App",
			JOptionPane.INFORMATION_MESSAGE
		);
	}

}
