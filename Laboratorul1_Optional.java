import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
public class Optional {
    public static void dfs(int start, int[][] matrix, int size, int[] connected, int comp) {
        connected[start] = comp;
        for(int i = 0; i < size ; i ++ )
            if(connected[i] == 0 && matrix[start][i] == 1)
                dfs(i,matrix,size,connected,comp);
    }
    public static void dfs_tree(int start, int[][] matrix, int size, int[] connected, int comp) {
        connected[start] = comp;
        for(int i = 0; i < size ; i ++ )
            if(connected[i] == 0 && (matrix[start][i] == 1 || matrix[start][i] == 2)) {
                matrix[start][i] = 2; matrix [i][start] = 2;
                dfs_tree(i, matrix, size, connected, comp);
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        String number = reader.readLine();
        long startTime = System.nanoTime();
        if (number.startsWith("-")) {
            System.out.println("We do not allow negative integers!\n");
            System.exit(-1);
        }
        if (number.startsWith("0")) {
            System.out.println("An integer cannot start with 0!\n");
            System.exit(-1);
        }
        if (number.isEmpty() || !number.matches("^[0-9]*$")) {
            System.out.println("The value introduced is not an integer!\n");
            System.exit(-1);
        }
        int n = Integer.parseInt(number);
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                matrix[i][j] = 0;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int result = rand.nextInt(2);
                matrix[i][j] = result;
                matrix[j][i] = result;
            }
        }
        /*
        String s = Character.toString(64294);
        String s1 = Character.toString(64290);
        */
        if(n <= 15) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 1) {
                        System.out.print(1);
                        System.out.print(" ");
                    } else {
                        System.out.print(0);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            int[] connected = new int[n + 1];
            for (int i = 0; i < n; i++)
                connected[i] = 0;
            int comp = 1;
            for (int i = 0; i < n; i++)
                if (connected[i] == 0) {
                    dfs(i, matrix, n, connected, comp);
                    comp = comp + 1;
                }
            if (comp == 2) {
                System.out.println("Graph is connected!");
                for (int i = 0; i < n; i++)
                    connected[i] = 0;
                dfs_tree(0, matrix, n, connected, 1);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (matrix[i][j] == 2) {
                            System.out.print(1);
                            System.out.print(" ");
                        } else {
                            System.out.print(0);
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            } else {
                for (int i = 1; i < comp; i++) {
                    System.out.print("Componenta " + Integer.toString(i) + " : ");
                    for (int j = 0; j < n; j++)
                        if (connected[j] == i)
                            System.out.print(Integer.toString(j) + " ");
                }
            }
        } else {
            long stopTime = System.nanoTime();
            System.out.print("Execution time :");
            System.out.println(stopTime - startTime);
        }
        /*
        * javac Optional.java --- pentru compilare
        * java Optional --- pentru executare
        * */
    }
}
