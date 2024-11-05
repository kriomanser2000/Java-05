import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileComparison
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to first file: ");
        String filePath1 = scanner.nextLine();
        System.out.print("Enter path to second file: ");
        String filePath2 = scanner.nextLine();
        try
        {
            List<String> file1Lines = readFileLines(filePath1);
            List<String> file2Lines = readFileLines(filePath2);
            compareAndPrintDifferences(file1Lines, file2Lines);
        }
        catch (IOException e)
        {
            System.out.println("Error reading: " + e.getMessage());
        }
    }
    private static List<String> readFileLines(String filePath) throws IOException
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        return lines;
    }
    private static void compareAndPrintDifferences(List<String> file1Lines, List<String> file2Lines)
    {
        int maxLines = Math.max(file1Lines.size(), file2Lines.size());
        System.out.println("Rows that do not match: ");
        for (int i = 0; i < maxLines; i++) {
            String line1 = i < file1Lines.size() ? file1Lines.get(i) : null;
            String line2 = i < file2Lines.size() ? file2Lines.get(i) : null;
            if (line1 == null || line2 == null || !line1.equals(line2))
            {
                if (line1 != null)
                {
                    System.out.println("File 1, line " + (i + 1) + ": " + line1);
                }
                if (line2 != null)
                {
                    System.out.println("File 2, line " + (i + 1) + ": " + line2);
                }
            }
        }
    }
}
