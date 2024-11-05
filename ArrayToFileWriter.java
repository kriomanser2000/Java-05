import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayToFileWriter
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter massive element with spacebar: ");
        String[] input = scanner.nextLine().split(" ");
        int[] array = new int[input.length];
        for(int i = 0; i < input.length; i++)
        {
            array[i] = Integer.parseInt(input[i]);
        }
        List<String> lines = new ArrayList<String>();
        lines.add(IntStream.of(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
        lines.add(IntStream.of(array)
                .filter(num -> num % 2 == 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
        lines.add(IntStream.of(array)
                .filter(num -> num % 2 != 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
        lines.add(IntStream.rangeClosed(1, array.length)
                .mapToObj(i -> String.valueOf(array[array.length - i]))
                .collect(Collectors.joining(" ")));
        try
        {
            Files.write(Path.of(filePath), lines);
            System.out.println("Massive added");
        }
        catch (IOException e)
        {
            System.err.println("Error writing" + e.getMessage());
        }
    }
}
