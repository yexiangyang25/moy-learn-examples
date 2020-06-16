package org.moy.netty.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeBIOServer {


    public final static Integer PORT = 8000;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(TimeBIOServer.PORT);
            do {
                Socket accept = server.accept();
                new Thread(new TimeServerHandler(accept)).start();
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
