

import server.SimplerServer;

public class Main {
    public static void main(String[] args) throws Exception {
        SimplerServer.start();
        Thread.currentThread().join(); // Keep app running
    }
}