package compulsory.analyzation;

import compulsory.connection.ConnectionPool;
import compulsory.connection.ConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnalyzeThreadPoolWithSingletonConnection {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            Connection conn = null;
            conn = ConnectionSingleton.getConnection();
            Runnable worker = new ThreadUsingCustomConnection(conn,false);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        long endTime = System.currentTimeMillis();
        System.out.println("Time until end singleton: " + (endTime - startTime) + " miliseconds");
    }
}
