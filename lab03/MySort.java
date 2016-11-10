package lab03;

import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by DuyAnhPham on 10/11/2016.
 */

public class MySort {

    public void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 1; i < N; i++) {
            Comparable t = a[i];
            int j;
            for (j = i - 1; j >= 0 && t.compareTo(a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }

    public void qsort(Comparable[] a) {
        Arrays.sort(a);
    }

    public void bsort(Comparable[] a) {
        int N = a.length;
        while (N != 0) {
            int newN = 0;
            for (int i = 1; i < N - 1; i++) {
                if (a[i - 1].compareTo(a[i]) > 0) {
                    Comparable tmp = a[i - 1];
                    a[i - 1] = a[i];
                    a[i] = tmp;
                    newN = i;
                }
            }
            N = newN;
        }
    }

    final static int strLen = 32;

    public static class InsertionSortTest extends MySort implements Stopwatch.Test {
        Comparable[] array;
        int N;

        public InsertionSortTest(int n) {
            this.N = n;
        }

        @Override
        public void setup() {
            array = new Comparable[N];

            for (int j = 0; j < N; j++) {
                array[j] = randomString(strLen);
            }
        }

        @Override
        public void test() {
            super.sort(array);
        }
    }

    public static class QuickSortTest extends MySort implements Stopwatch.Test {
        Comparable[] array;
        int N;

        public QuickSortTest(int n) {
            this.N = n;
        }

        @Override
        public void setup() {
            array = new Comparable[N];

            for (int j = 0; j < N; j++) {
                array[j] = randomString(strLen);
            }
        }

        @Override
        public void test() {
            super.qsort(array);
        }
    }

    public static class BubbleSortTest extends MySort implements Stopwatch.Test {
        Comparable[] array;
        int N;

        public BubbleSortTest(int n) {
            this.N = n;
        }

        @Override
        public void setup() {
            array = new Comparable[N];

            for (int j = 0; j < N; j++) {
                array[j] = randomString(strLen);
            }
        }

        @Override
        public void test() {
            super.bsort(array);
        }
    }

    private static final int N = 10;        // number of test points
    private static final int STARTN = 1000;
    private static final int INCN = 1000;
    private static final String OUTPUTFILE = "./iSortAndbSort.csv";

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    static private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /*
    public static void main(String[] args) {
        //Stopwatch.main(args);
        int arrayN;

        try {
            PrintWriter writer = new PrintWriter(OUTPUTFILE, "UTF-8");

            System.out.println("Number of CPU cores " + Runtime.getRuntime().availableProcessors());
            writer.println("N;InsertionSort;QuickSort");
            Stopwatch sw = new Stopwatch();
            arrayN = STARTN;
            for (int i = 0; i < N; i++) {
                System.out.println("N: " + arrayN);

                InsertionSortTest iSort = new InsertionSortTest(arrayN);
                QuickSortTest qSort = new QuickSortTest(arrayN);

                StringBuilder result = new StringBuilder();
                result.append(arrayN);

                sw.measure(iSort);
                result.append(";");
                sw.toValue(result);

                sw.measure(qSort);
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
    */

    public static void main(String[] args) {
        //Stopwatch.main(args);
        int arrayN;

        try {
            PrintWriter writer = new PrintWriter(OUTPUTFILE, "UTF-8");

            System.out.println("Number of CPU cores " + Runtime.getRuntime().availableProcessors());
            writer.println("N;InsertionSort;BubbleSort");
            Stopwatch sw = new Stopwatch();
            arrayN = STARTN;
            for (int i = 0; i < N; i++) {
                System.out.println("N: " + arrayN);

                InsertionSortTest iSort = new InsertionSortTest(arrayN);
                BubbleSortTest bSort = new BubbleSortTest(arrayN);

                StringBuilder result = new StringBuilder();
                result.append(arrayN);

                sw.measure(iSort);
                result.append(";");
                sw.toValue(result);

                sw.measure(bSort);
                result.append(";");
                sw.toValue(result);

                writer.println(result.toString().replace(".", ","));

                arrayN += INCN;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
