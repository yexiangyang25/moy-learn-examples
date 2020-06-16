package org.moy.netty.bio.timeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {


    public static void main(String[] args) throws IOException {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", TimeBIOServer.PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
            printWriter.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            System.out.println(bufferedReader.readLine());
        } finally {
            if (null != client) {
                if (!client.isClosed()) {
                    client.close();
                }
            }
        }
    }
}
