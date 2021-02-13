package com.itestra.gc4connect;

import com.itestra.gc4connect.handler.*;
import com.itestra.gc4connect.message.GC4Message;
import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GC4Client {

    private static final Logger LOG = LoggerFactory.getLogger(GC4Client.class);

    private static final int GC4_PORT = 5001;
    private static final String GC4_IP = "192.168.100.76";

    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            LOG.debug("Connected to GC4 ...");

            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(byte[] dataBytes) {
        try {
            out.write(dataBytes);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // connect to GC4
        GC4Client gc4Client = new GC4Client();
        gc4Client.startConnection(GC4_IP, GC4_PORT);

        // receive data from GC4
        final Thread inThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int chunkSize = 1024;
                        byte[] receivedBytes = new byte[chunkSize];
                        boolean end = false;
                        StringBuilder hexMessageStringBuilder = new StringBuilder();
                        int totalBytesRead = 0;
                        while (!end) {
                            int currentBytesRead = gc4Client.in.read(receivedBytes, 0, chunkSize);
                            System.out.println("number of bytes read: " + currentBytesRead);

                            // add bytes to return string
                            totalBytesRead = currentBytesRead + totalBytesRead;
                            hexMessageStringBuilder.append(GC4RequestResponse.bytesToHexString(receivedBytes, currentBytesRead));
                            if (currentBytesRead < chunkSize) {
                                end = true;
                            }
                        }
                        String hexMessageString = hexMessageStringBuilder.toString();
                        Validate.isTrue(hexMessageString.length() >= 2, "hexMessageString too short");
                        System.out.println("hexMessageString received, length: " + hexMessageString.length());
                        System.out.println("hexMessageString:");
                        System.out.println(hexMessageString);

                        String hexMessageType = GC4MessageHandler.hexSubString(0, 1, hexMessageString);
                        String directionAsHexString = GC4MessageHandler.hexSubString(1, 1, hexMessageString);
                        switch (hexMessageType) {
                            case "01":
                                new Event01BallDetectionHandler().handleHexMessageString(hexMessageString);
                                break;

                            case "02":
                                new Event02Shot1Handler().handleHexMessageString(hexMessageString);
                                break;

                            case "03":
                                if (GC4MessageHandler.DIRECTION_001_GC4_EVENT.equals(directionAsHexString)) {
                                    new Event03Shot2Handler().handleHexMessageString(hexMessageString);
                                } else if (GC4MessageHandler.DIRECTION_001_GC4_TO_HOST.equals(directionAsHexString)) {
                                    new Response03Handler().handleHexMessageString(hexMessageString);

                                    // send 0602 to GC4
                                    Thread.sleep(1000);
                                    System.out.println("send 0602 message to GC4");
                                    gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_0602_STRING_60));
                                } else {
                                    throw new RuntimeException("direction unknown: " + directionAsHexString);
                                }
                                break;

                            case "05":
                                new Event05Shot3Handler().handleHexMessageString(hexMessageString);
                                break;

                            case "06":
                                new Response06Handler().handleHexMessageString(hexMessageString);
                                break;

                            case "11":
                                new Response11Handler().handleHexMessageString(hexMessageString);

                                // send 0302 to GC4
                                Thread.sleep(1000);
                                System.out.println("send 0302 message to GC4");
                                gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_0302_STRING_76));
                                break;

                            case "12":
                                new Response12Handler().handleHexMessageString(hexMessageString);

                                // send 1102 to GC4
                                Thread.sleep(1000);
                                System.out.println("send 1102 message to GC4");
                                gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_1102_STRING_76));
                                break;

                            default:
                                System.err.println("unknown message type: " + hexMessageType);
                        }
                    }
                } catch (
                        Throwable t) {
                    t.printStackTrace();
                } finally {
                    // disconnect
                    gc4Client.stopConnection();
                }
            }
        };
        inThread.start();

        // send 1202 to GC4
        System.out.println("send 1202 message to GC4");
        gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_1202_STRING_76));

        inThread.join();
    }
}