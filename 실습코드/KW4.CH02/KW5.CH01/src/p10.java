import javax.swing.JOptionPane;

public class p10 {
	private static String str="";
	public static void main(String argc[])
	{
		String st = JOptionPane.showInputDialog("알파벳을 입력하시오");
		String[] let = st.split(" ");
		char[] letters = new char[let.length];
		for(int i=0; i<let.length; i++)
			letters[i] = let[i].charAt(0);
		int n = letters.length;
		
		for(int i=1; i<=n; i++)
		{
			printCombination(letters, n, i);
			str+="\n";
		}
		JOptionPane.showMessageDialog(null, str);
	}
		public static void printCombination(char[] arr, int n, int r)
		{
			// A temporary array to store all combination one by one
			char[] data = new char[r];
			// Print all combination using temprary array 'data[]'
			combinationUtil(arr, data, 0, n-1, 0, r);
		}
		public static void combinationUtil(char[] arr, char[] data, int start, int end, int index, int r)
		{
			// Current combination is ready to be printed, print it
			if (index == r)
			{
				for (int j=0; j<r; j++)
					//JOptionPane.showMessageDialog(null,data[j]);
					str+=(data[j]+"");
				str+=" ";
				return;
			}
			for (int i=start; i<=end && end-i+1 >= r-index; i++)
			{
				data[index] = arr[i];
				combinationUtil(arr, data, i+1, end, index+1, r);
			}
		}
	}
