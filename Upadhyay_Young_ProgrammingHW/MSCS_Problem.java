import java.io.FileReader;
import java.lang.System;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Random;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;

/*
* @author CJ Young
* @author Shanti Upadhyay
* @version 15 NOV 2022
* Dr. Levent Yilmaz - COMP 3270
*/
public class MSCS_Problem {

   public static void main(String[] args) throws FileNotFoundException {

     /*
      * Resource Used:
      *    https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java
      */
      int[] inputArray = new int[10];
      String inputString = "";
      String input;
      try (BufferedReader buffReader = new BufferedReader(new FileReader("phw_input.txt"))) {
         while ((input = buffReader.readLine()) != null) {
            inputString = input;
         }
      } catch (Exception exception) {
         System.out.println("Error: The following operation could not be carried out\n");
         System.out.println(exception);
      }
   
      /* Takes the buffered information and outputs in an array */
      String[] inStr = inputString.split(",");
      for (int i = 0; i < inStr.length; i++) {
         inputArray[i] = Integer.parseInt(inStr[i]);
      }
   
      /* Computes array information in the MSCS Problem */
      MSCS_Problem algorMSCS = new MSCS_Problem();
      System.out.print("algorithm-1: " + algorMSCS.Algorithm1(inputArray) + "; " + "algorithm-2: "
            + algorMSCS.Algorithm2(inputArray) + "; " + "algorithm-3: "
            + algorMSCS.MaxSum(inputArray, 0, inputArray.length - 1) + "; " + "algorithm-4: "
            + algorMSCS.Algorithm4(inputArray) + "\n");
   
      /*
       * Resources Used:
       *    https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
       *    https://www.baeldung.com/java-generating-random-numbers-in-range
       */
      int[][] randomSequences = new int[19][];
      int sequenceNumber = 0;
      for (int i = 10; i <= 100; i += 5) {
         /* Marks the new sequences that are being put inside of the randomSequences */
         int[] interimArray = new int[i];
          /* For-loop to generate the randomized values placed inside of the interimArray(s) */
         for (int j = 0; j < i; j++) {
            /* Generates a number between -100 and 100 */
            int randomInt = new Random().nextInt(100 + 100) - 100;
            interimArray[j] = randomInt;
         }
         randomSequences[sequenceNumber] = interimArray;
         sequenceNumber++;
      }
   
      /* Resource used: https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#nanoTime() */
      long[][] sequenceData = new long[19][8];
   
      /* Algorithm 1 Simulation */
      for (int i = 0; i < 19; i++) {
         /* Amount of time to run all 19 arrays */
         long startTime = System.nanoTime();
         long delta = 0;
         for (int j = 0; j < 1000; j++) {
            /* Amount of time elapsed after randomSequences[i] goes through Algorithm1 */
            algorMSCS.Algorithm1(randomSequences[i]);
            long endTime = System.nanoTime();
            long timeElapse = endTime - startTime;
            delta += timeElapse;
         }
         long average = delta / 1000;
         sequenceData[i][0] = average;
      }
   
      /* Algorithm 2 Simulation */
      for (int i = 0; i < 19; i++) {
         /* Amount of time to run all 19 arrays */
         long startTime = System.nanoTime();
         long delta = 0;
         for (int j = 0; j < 1000; j++) {
            /* Amount of time elapsed after randomSequences[i] goes through Algorithm2 */
            algorMSCS.Algorithm2(randomSequences[i]);
            long endTime = System.nanoTime();
            long timeElapse = endTime - startTime;
            delta += timeElapse;
         }
         long average2 = delta / 1000;
         sequenceData[i][1] = average2;
      }
   
      /* Algorithm 3 Simulation */
      for (int i = 0; i < 19; i++) {
         /* Amount of time to run all 19 arrays */
         long startTime = System.nanoTime();
         long delta = 0;
         for (int j = 0; j < 1000; j++) {
            /* Amount of time elapsed after randomSequences[i] goes through MaxSum */
            algorMSCS.MaxSum(randomSequences[i], 0, randomSequences[i].length - 1);
            long endTime = System.nanoTime();
            long timeElapse = endTime - startTime;
            delta += timeElapse;
         }
         long average3 = delta / 1000;
         sequenceData[i][2] = average3;
      }
   
      /* Algorithm 4 Simulation */
      for (int i = 0; i < 19; i++) {
         /* Amount of time to run all 19 arrays */
         long startTime = System.nanoTime();
         long delta = 0;
         for (int j = 0; j < 1000; j++) {
            /* Amount of time elapsed after randomSequences[i] goes through Algorithm1 */
            algorMSCS.Algorithm4(randomSequences[i]);
            long endTime = System.nanoTime();
            long timeElapse = endTime - startTime;
            delta += timeElapse;
         }
         long average4 = delta / 1000;
         sequenceData[i][3] = average4;
      }
   
      int index = 0;
      for (int n = 10; n <= 100; n += 5) {
         // O(n^3)
         sequenceData[index][4] = (long) Math.ceil(Math.pow(n, 3) * 1000);
         // O(n^2)
         sequenceData[index][5] = (long) Math.ceil(Math.pow(n, 2) * 1000);
         // O(nlogn)
         sequenceData[index][6] = (long) Math.ceil(n * (Math.log(n) / Math.log(2)) * 1000);
         // O(n)
         sequenceData[index][7] = (long) Math.ceil(Math.pow(n, 1) * 1000);
         index++;
      }
   
      /* Stores the values into a physical string array in order to be printed */
      ArrayList<String> sequenceList = new ArrayList<String>();
      for (int i = 0; i < 19; i++) {
         String matrix = "";
         for (int k = 0; k <= 6; k++) {
            matrix += sequenceData[i][k] + ",";
         }
         matrix += sequenceData[i][7];
         sequenceList.add(matrix);
      }
   
      /* Resource Used: https://www.w3schools.com/java/java_files_create.asp */
      try {
         FileWriter writeFile = new FileWriter("UpadhyayYoung_phw_output.txt");
         writeFile.write("algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)\n");
         for (String value : sequenceList) {
            writeFile.write(value + "\n");
         }
         writeFile.close();
         System.out.println("Output file 'UpadhyayYoung_phw_output.txt' created");
      
      } catch (IOException error) {
         System.out.println("Error. This operation could not be carried out");
         error.printStackTrace();
      }
   }

   /* Algorithm 1 */
   public int Algorithm1(int[] X) {
      int maxSoFar = 0;
      int P = 0;
      int Q = X.length;
   
      for (int L = P; L <= Q; L++) {
         for (int U = L; U <= Q; U++) {
            int sum = 0;
            for (int I = L; I < U; I++) {
               sum = sum + X[I]; /* sum now contains the sum of X[L..U] */
            }
            maxSoFar = Math.max(maxSoFar, sum);
         }
      }
      return maxSoFar;
   }

   /* Algorithm 2 */
   public int Algorithm2(int[] X) {
      int maxSoFar = 0;
      int P = 0;
      int Q = X.length;
   
      for (int L = P; L <= Q; L++) {
         int sum = 0;
         for (int U = L; U < Q; U++) {
            sum = sum + X[U]; /* sum now contains the sum of X[L...U] */
            maxSoFar = Math.max(maxSoFar, sum);
         }
      }
      return maxSoFar;
   }

   /* Algorithm 3 */
   public int MaxSum(int[] X, int L, int U) {
      /* Base Case where it is a zero element vector or has invalid bounds */
      if (L > U) { return 0; }
   
      if (L == U) { /* Base Case where it is a one element vector */
         return Math.max(0, X[L]);
      }
      int M = (L + U) / 2; /* A is X[L..M], B is X[M+1..U] */
   
      /* Find max crossing to left. */
      int sum = 0;
      int maxToLeft = 0;
      for (int I = M; I >= 0; I--) {
         sum = sum + X[I];
         maxToLeft = Math.max(maxToLeft, sum);
      }
   
      /* Find max crossing to the right */
      sum = 0;
      int MaxToRight = 0;
      for (int I = M + 1; I <= U; I++) {
         sum = sum + X[I];
         MaxToRight = Math.max(MaxToRight, sum);
      }
      int maxCrossing = maxToLeft + MaxToRight;
      int maxInA = MaxSum(X, L, M);
      int maxInB = MaxSum(X, M + 1, U);
      int combinedMax = Math.max(maxInA, maxInB);
   
      return Math.max(maxCrossing, combinedMax);
   }

   /* Algorithm 4 */
   public int Algorithm4(int[] X) {
      int P = 0;
      int Q = X.length;
   
      int maxSoFar = 0;
      int maxEndingHere = 0;
      for (int I = P; I < Q; I++) {
         maxEndingHere = Math.max(0, (maxEndingHere + X[I]));
         maxSoFar = Math.max(maxSoFar, maxEndingHere);
      }
      return maxSoFar;
   }
}