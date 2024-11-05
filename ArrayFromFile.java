import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayFromFile
{
    public static void main(String[] args)
    {
        String filePath = "numbers.txt";
        List<int[]> arrays = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] numbers = line.trim().split("\\s+");
                int[] array = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++)
                {
                    array[i] = Integer.parseInt(numbers[i]);
                }
                arrays.add(array);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading: " + e.getMessage());
        }
        for (int i = 0; i < arrays.size(); i++)
        {
            System.out.print("Massive " + (i + 1) + ": ");
            for (int num : arrays.get(i))
            {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
