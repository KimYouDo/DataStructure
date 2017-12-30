import javax.swing.JOptionPane;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class MaintainQueue{
	private Queue<String> customers;
	public MaintainQueue() {
	customers = new LinkedList<String>();
	}
	public void processCustomers() {
		int choiceNum= 0;
		String[] choices = {"add", "peek", "remove", "size", "position", "quit"};
		while (choiceNum< choices.length-1) {
			choiceNum= JOptionPane.showOptionDialog(null,
					"Select an operation on customer queue","Queuemenu",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			try {
				String name;
				switch (choiceNum) {
				case 0:
					name = JOptionPane.showInputDialog("Enter new customer name");
					customers.offer(name);
					JOptionPane.showMessageDialog(null,"Customer " + name
							+ " added to the queue");
					break;
				case 1:
					JOptionPane.showMessageDialog(null,"Customer " + customers.peek()
							+ " is next in the queue");
					break;
				case 2:
					JOptionPane.showMessageDialog(null,"Customer " + customers.poll()
							+ " removed from the queue");
					break;
				case 3:
					JOptionPane.showMessageDialog(null,"Sizeof queue is " + customers.size()+"\n"
							+"Queue print \n"+toString());
					break;
				case 4:
					name = JOptionPane.showInputDialog("Enter customer name");
					int countAhead= 0;
					for (String nextName: customers) { // iteration() method
						if (!nextName.equals(name)) {
							countAhead++;
						} else {
							JOptionPane.showMessageDialog(null,
									"The number of customers ahead of "
											+ name + " is " + countAhead);
							break; // Customer found, exit loop.
						}
					}
					if (countAhead== customers.size()) {
						JOptionPane.showMessageDialog(null,name+ " is not in queue");
					}
					break;
				case 5:
					JOptionPane.showMessageDialog(null,"Leavingcustomer queue. "
							+ "\nNumberof customers in queue is "
							+ customers.size());
					break;
				default:
					JOptionPane.showMessageDialog(null,"Invalidselection");
					break;
				}
			} catch (NoSuchElementException e) {
				JOptionPane.showMessageDialog(null,"TheQueue is empty", "",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public String toString() {
		StringBuilder stb= new StringBuilder();
		for (String next : customers) {
			stb.append(next);
			stb.append("\n");
		}
		return stb.toString();
	}
	/*</exercise>*/
}