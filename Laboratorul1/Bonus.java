import java.util.Stack;
import java.util.Random;

public class Bonus {
    /* Function that prints on the screen the random rooted tree */
    public static void dfs(int start, int[][] matrix, int size, int[] connected, int depth) {
        connected[start] = 1;
        int aux = depth; /* Add the depth for the printing */
        while( aux != 0 ) {
            System.out.print("  ");
            aux = aux - 1;
        }
        System.out.println("+node" + start);
        for(int i = 0; i < size ; i ++ )
            if(connected[i] == 0 && matrix[start][i] == 1)
                dfs(i,matrix,size,connected, depth + 1);
    }


    public static void main( String[] args) {
        int sizeTree = 10; /* Variable telling the size of the random matrix */
        int[][] matrix = new int [sizeTree + 1][sizeTree + 1];
        boolean []added = new boolean[sizeTree + 1]; /* Tells which nodes have been added to the graph */
        Random rand = new Random();
        int numberChildren = sizeTree; /* Numarul de copii ai nodului root */
        int root = rand.nextInt(sizeTree+1);
        Stack<Integer> queue = new Stack<>();
        queue.add(root);
        while(numberChildren != 0) { /* Constructia arborelui random */

            //choosing the number of children
            int child_num = rand.nextInt(numberChildren + 1);
            if (numberChildren - child_num >= 0 && child_num > 0) {
                int current = queue.pop();
                numberChildren = numberChildren - child_num;
                while (child_num > 0) { // adding the new node as a child to the current node
                    int child = rand.nextInt(sizeTree);
                    if (!added[child] && current != child) {
                        matrix[current][child] = 1;
                        matrix[child][current] = 1;
                        child_num = child_num - 1;
                        added[child] = true;
                        queue.add(child);
                    }
                }
            }
        }
        /* Prints on the screen the adjacency matrix*/
        for (int i = 0; i < sizeTree; i++) {
            for (int j = 0; j < sizeTree; j++) {
                if(matrix[i][j] == 1) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        int[] connected = new int[sizeTree + 1];
        dfs(root,matrix,sizeTree,connected,0);

    }
}
