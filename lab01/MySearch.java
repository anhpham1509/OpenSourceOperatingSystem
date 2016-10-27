package lab01;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by DuyAnhPham on 24/10/2016.
 */
public class MySearch {

    private Comparable[] array;

    public void setArray(Comparable[] array) {
        this.array = array;
    }

    public int LinearSearch(Comparable item) {
        for (int i = 0; i < array.length; i++) {
            if (item.compareTo(array[i]) == 0) {
                return i;
            }
        }

        return -1;
    }

    public int BinarySearch(Comparable item) {
        int first = 0;
        int last = array.length - 1;
        int mid;

        while (first <= last) {
            mid = (first + last) / 2;
            if (item.compareTo(array[mid]) == 0) {
                return mid;
            }

            if (item.compareTo(array[mid]) < 0) {
                // less than mid
                last = mid-1;
            } else {
                // bigger than mid
                first = mid+1;
            }
        }

        return -1;
    }

    String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateString() {
        int length = 32;

        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private Random randomGenerator = new Random();

    public int generateInteger(int max) {
        return randomGenerator.nextInt(max);
    }

    public static class LinearSearchTest extends MySearch implements Stopwatch.Test {
        private Comparable searchItem;
        private int max;

        public LinearSearchTest(int n, boolean isString) {
            Comparable[] array;
            this.max = n;

            if (isString) {
                array = new String[n];

                for (int i=0; i < n; i++)
                    array[i] = generateString();
            } else {
                array = new Integer[n];

                for (int i=0; i < n; i++)
                    array[i] = generateInteger(Integer.MAX_VALUE);
            }

            searchItem = array[generateInteger(max)];

            setArray(array);
        }

        @Override
        public void test() {
            for (int i = 0; i < 24; i++) {
                searchItem = super.array[generateInteger(max)];

                for (int j = 0; j < 500; j++) {
                    LinearSearch(searchItem);
                }
            }
        }
    }

    public static class BinarySearchTest extends MySearch implements Stopwatch.Test {
        private Comparable searchItem;
        private int max;

        public BinarySearchTest(int n, boolean isString) {
            Comparable[] array;
            this.max = n;

            if (isString) {
                array = new String[n];

                for (int i=0; i < n; i++)
                    array[i] = generateString();
            } else {
                array = new Integer[n];

                for (int i=0; i < n; i++)
                    array[i] = generateInteger(Integer.MAX_VALUE);
            }

            Arrays.sort(array);
            setArray(array);
        }

        @Override
        public void test() {
            for (int i = 0; i < 24; i++) {
                searchItem = super.array[generateInteger(max)];

                for (int j = 0; j < 500; j++) {
                    BinarySearch(searchItem);
                }
            }
        }
    }

    private static final int N = 10;        // number of test points
    private static final int STARTN = 10000;
    private static final int INCN = 5000;
    private static final String OUTPUTFILE = "./data.csv";

    public static void main(String[] args) {
        //Stopwatch.main(args);
        int arrayN;

        try {
            PrintWriter writer = new PrintWriter(OUTPUTFILE, "UTF-8");

            System.out.println("Number of CPU cores " + Runtime.getRuntime().availableProcessors());
            writer.println("N;LinearSearchInteger;BinarySearchInteger;LinearSearchString;BinarySearchString");
            Stopwatch sw = new Stopwatch();
            arrayN = STARTN;
            for (int i = 0; i < N; i++) {
                System.out.println("N: " + arrayN);

                LinearSearchTest linearSearchIntTest = new LinearSearchTest(arrayN, false);
                BinarySearchTest binarySearchIntTest = new BinarySearchTest(arrayN, false);

                LinearSearchTest linearSearchStrTest = new LinearSearchTest(arrayN, true);
                BinarySearchTest binarySearchStrTest = new BinarySearchTest(arrayN, true);

                StringBuilder result = new StringBuilder();
                result.append(arrayN);

                sw.measure(linearSearchIntTest);
                result.append(";");
                sw.toValue(result);

                sw.measure(binarySearchIntTest);
                result.append(";");
                sw.toValue(result);

                sw.measure(linearSearchStrTest);
                result.append(";");
                sw.toValue(result);

                sw.measure(binarySearchStrTest);
                result.append(";");
                sw.toValue(result);

                writer.println(result.toString().replace(".",","));

                arrayN += INCN;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
