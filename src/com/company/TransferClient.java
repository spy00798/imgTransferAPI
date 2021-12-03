package com.company;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class TransferClient {

    public void getAPI(OutputStream os, String url) {
        HttpURLConnection conn;
        try {
            URL apiUrl = new URL(url);
            conn = (HttpURLConnection) apiUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ReadImageFile(os, conn.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private  void ReadImageFile(OutputStream os, InputStream is) {
        int len = 0;
            try {

                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args) {
	    String serverIp = "192.168.0.91";
        Socket socket = null;
        try {
            socket = new Socket(serverIp, 4432);
            TransferClient transferClient = new TransferClient();
            transferClient.getAPI(socket.getOutputStream(), "http://192.168.0.240/cgi-bin/snapshot.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
