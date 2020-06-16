package org.moy.netty.bio.timeserver;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket;
    private String hostAddress;
    private Integer port;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
        this.hostAddress = socket.getInetAddress().getHostAddress();
        this.port = socket.getPort();
    }

    @Override
    public void run() {
        try {
            handler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handler() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String inputLine = inputReader.readLine();
            if (null == inputLine) {
                break;
            }
            System.out.printf("server[%s] - client[%s] : %s", Thread.currentThread().getName(),
                    hostAddress + ":" + port, inputLine);
            System.out.println();
            outWriter.println(simpleDateFormat.format(new Date()));
        }
    }
}
