package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int i = 0;
        String filename = "newImg";


        try {
            serverSocket = new ServerSocket(4432);
            byte[] buffer = new byte[1024];
            while(true) {
                socket = serverSocket.accept();
                System.out.println("연결 성공.");
                i++;
                FileOutputStream fos = new FileOutputStream("D:/new/" + filename + i + ".jpg");
                InputStream is = socket.getInputStream();

                int len;

                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

                is.close();
                fos.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
