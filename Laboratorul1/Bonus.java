import java.util.Stack;
import java.util.Random;

public class Bonus {
    public static void dfs(int start, int[][] matrix, int size, int[] connected, int depth) {
        connected[start] = 1;
        int aux = depth;
        while( aux != 0 ) {
            System.out.print("  ");
            aux = aux - 1;
        }
        System.out.println("+node" + Integer.toString(start));
        for(int i = 0; i < size ; i ++ )
            if(connected[i] == 0 && matrix[start][i] == 1)
                dfs(i,matrix,size,connected, depth + 1);
    }


    public static void main( String[] args) {
        int size_tree = 10;
        int[][] matrix = new int [size_tree + 1][size_tree + 1];
        boolean []added = new boolean[size_tree + 1];
        Random rand = new Random();
        int number_of_children = size_tree;
        int root = rand.nextInt(size_tree+1);
        Stack<Integer> queue = new Stack<>();
        queue.add(root);
        while(number_of_children != 0) {

            //choosing the number of children
            int child_num = rand.nextInt(number_of_children + 1);
            if (number_of_children - child_num >= 0 && child_num > 0) {
                int current = queue.pop();
                number_of_children = number_of_children - child_num;
                while (child_num > 0) { // adding the new node as a child to the current node
                    int child = rand.nextInt(size_tree);
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
        for (int i = 0; i < size_tree; i++) {
            for (int j = 0; j < size_tree; j++) {
                if(matrix[i][j] == 1) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        int[] connected = new int[size_tree + 1];
        dfs(root,matrix,size_tree,connected,0);

    }
}
