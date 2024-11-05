import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LongestLineFinder
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String filePath = scanner.nextLine();
        String longestLine = "";
        int maxLength = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.length() > maxLength)
                {
                    maxLength = line.length();
                    longestLine = line;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading: " + e.getMessage());
            return;
        }
        System.out.println("Length of the longest string: " + maxLength);
        System.out.println("The longest line: " + longestLine);
    }
}
