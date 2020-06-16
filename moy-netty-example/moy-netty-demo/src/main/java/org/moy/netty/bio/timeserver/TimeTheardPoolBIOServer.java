package org.moy.netty.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeTheardPoolBIOServer {

    private static ExecutorService executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            50,
            120L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1000));


    public final static Integer PORT = 8000;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(TimeTheardPoolBIOServer.PORT);
            do {
                Socket accept = server.accept();
                executor.execute(new TimeServerHandler(accept));
            } while (true);
        } finally {
            if (null != server) {
                if (!server.isClosed()) {
                    server.close();
                }
            }
        }
    }
}
