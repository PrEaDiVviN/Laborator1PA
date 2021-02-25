import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class Optional {
    /**
     * Execute Dfs algorithm on the matrix and modifies connected variable
     * to indicate to which component it belongs to and the comp variable to tell how many components are.
     *
     * @param  start node which is a starting point for the dfs algorithm
     * @param  matrix adjacency matrix of the graph
     * @param  size size of the matrix
     * @param  connected marks to which component is a index node
     * @param  comp tells the number of components
     * @return
     */
    public static void executeDfs(int start, int[][] matrix, int size, int[] connected, int comp) {
        connected[start] = comp;
        for (int i = 0; i < size; i++)
            if (connected[i] == 0 && matrix[start][i] == 1)
                executeDfs(i, matrix, size, connected, comp);
    }

    /**
     * Execute Dfs algorithm on the matrix and modifies connected variable
     * to indicate that, that index node was selected. Marks with 2 the Dfs
     * tree in the matrix.
     * @param  start node which is a starting point for the dfs algorithm
     * @param  matrix adjacency matrix of the graph
     * @param  size size of the matrix
     * @param  connected marks to which component is a index node
     * @param  comp tells the number of components
     * @return
     */
    public static void constructDfsTree(int start, int[][] matrix, int size, int[] connected, int comp) {
        connected[start] = comp;
        for (int i = 0; i < size; i++)
            if (connected[i] == 0 && (matrix[start][i] == 1 || matrix[start][i] == 2)) {
                matrix[start][i] = 2;
                matrix[i][start] = 2;
                constructDfsTree(i, matrix, size, connected, comp);
            }
    }
    /**
     * Constructs the Bfs tree on the matrix and modifies connected variable
     * to indicate that, that index node was selected. Marks with 2 the Bfs
     * tree in the matrix.
     *
     * @param  start node which is a starting point for the dfs algorithm
     * @param  matrix adjacency matrix of the graph
     * @param  size size of the matrix
     * @param  connected marks to which component is a index node
     * @return
     */
    public static void constructBfsTree(int start, int[][] matrix, int size, int[] connected) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        connected[start] = 1;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int i = 0; i < size; i++)
                if (connected[i] == 0 && matrix[current][i] != 0) {
                    matrix[current][i] = 2;
                    matrix[i][current] = 2;
                    queue.add(i);
                    connected[i] = 1;
                }
        }
    }

    public static void main(String[] args) throws IOException {
        /* Reading from the keyboard */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        String number = reader.readLine();
        /* Starting the System countdown */
        long startTime = System.nanoTime();
        /* Verifying that the number introduces is a valid number */
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
        /* Constructing the random matrix */
        int size = Integer.parseInt(number);
        int[][] matrix = new int[size + 1][size + 1];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int result = rand.nextInt(2);
                matrix[i][j] = result;
                matrix[j][i] = result;
            }
        }
        /* Printing on the screen only if the size of the matrix is lower equal with 15 */
        if (size <= 15) {
            /* Printing the random matrix on the screen */
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (matrix[i][j] == 1) {
                        System.out.print(1);

                    } else {
                        System.out.print(0);
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            /* Verifying if the matrix is connected */
            int[] connected = new int[size + 1];
            int comp = 1;
            for (int i = 0; i < size; i++)
                if (connected[i] == 0) {
                    executeDfs(i, matrix, size, connected, comp);
                    comp = comp + 1;
                }
            /* comp == 2 means matrix has only 1 component => is connected */
            if (comp == 2) {
                System.out.println("Graph is connected!");
                for (int i = 0; i < size; i++)
                    connected[i] = 0;
                /* Constructing the dfs tree and printing it */
                constructDfsTree(0, matrix, size, connected, comp);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (matrix[i][j] == 2) {
                            System.out.print(1);

                        } else {
                            System.out.print(0);
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
            } else {
                /* printing the connected components */
                for (int i = 1; i < comp; i++) {
                    System.out.print("Componenta " + i + " : ");
                    for (int j = 0; j < size; j++)
                        if (connected[j] == i)
                            System.out.print(j + " ");
                }
            }
        } else {
            /* Printing the time taken to resolve the problem */
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
