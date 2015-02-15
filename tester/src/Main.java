import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

	public static void main (String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader(args[0]));
		String line;
		while ((line = f.readLine()) != null)
		{
			int index = Character.getNumericValue(line.charAt(line.length() - 1));
			if(index <= (line.length() / 2))
				System.out.println(line.charAt(line.length() - index * 2 - 1));
		}
		
		f.close();
	}
}
