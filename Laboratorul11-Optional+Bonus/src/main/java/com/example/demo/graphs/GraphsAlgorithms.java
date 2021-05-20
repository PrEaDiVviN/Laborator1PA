package com.example.demo.graphs;

public class GraphsAlgorithms {
    public static void executeDfs(int start, int[][] matrix, int size, int[] connected, int comp) {
        connected[start] = comp;
        for (int i = 0; i < size; i++)
            if (connected[i] == 0 && matrix[start][i] == 1)
                executeDfs(i, matrix, size, connected, comp);
    }
}
