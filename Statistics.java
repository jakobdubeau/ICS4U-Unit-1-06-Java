/*
* The Microwave program lets the user enter the food they want to heat up and how
* many of the items they want, then gives them the time they need to heat the items up.
*
* @author Jakob
* @version 1.0
* @since 2020-11-26
*
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation.
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers) {
        final int size = arrayOfIntegers.length;
        int total = 0;
        for (int counter = 0; counter < size; counter++) {
            total = total + arrayOfIntegers[counter];
        }
        final double meanFinal = (double) total / (double) size;
        return meanFinal;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers) {
        double returnValue = 0;
        Arrays.sort(arrayOfIntegers);
        final int numbersOfNumbers = arrayOfIntegers.length;
        if (numbersOfNumbers % 2 == 0) {
            final int numberOfMedian1 = (numbersOfNumbers / 2) - 1;
            final int median1 = arrayOfIntegers[numberOfMedian1];
            final int numberOfMedian2 = numbersOfNumbers / 2;
            final int median2 = arrayOfIntegers[numberOfMedian2];
            returnValue = (median1 + median2) / 2;
        }
        else {
            final int numberOfMedian = (int) (
                ((double) numbersOfNumbers / 2.0) + 0.5);
            returnValue = arrayOfIntegers[numberOfMedian];
        }
        return returnValue;
    }

    /**
    * The mode() function.
    *
    * @param numbers the collection of integers
    * @return the mode of the integers
    */
    public static List<Integer> mode(final Integer[] numbers) {
        final List<Integer> modes = new ArrayList<Integer>();
        final var arrayOfIntegers = numbers;
        int maxCount = 0;
        Arrays.sort(arrayOfIntegers);
        for (int comparing1 : arrayOfIntegers) {
            int count = 0;
            for (var comparing2 : arrayOfIntegers) {
                if (comparing1 == comparing2) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(0, comparing1);
            }
            else if (count == maxCount) {
                if (comparing1 != modes.get(0)) {
                    modes.add(0, comparing1);
                }
            }
        }
        return modes;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("../", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        }
        catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers);
        final double median = median(arrayOfNumbers);
        final List<Integer> mode = mode(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                            + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}
