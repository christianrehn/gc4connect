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
                        // read 1st byte: operation
                        byte operation = (byte) gc4Client.in.read();
                        byte[] operationBytes = new byte[]{operation};
                        String operationHexString = GC4Message.bytesToHexString(operationBytes, 1);

                        // get message length for operation
                        int messageLengthBytes = GC4MessageHandler.getMessageLengthBytes(operationHexString);
                        int restMessageLengthBytes = messageLengthBytes - 1; // 1 byte already read

                        // read the rest of this message
                        byte[] receivedBytes = new byte[restMessageLengthBytes];
                        int currentBytesRead = gc4Client.in.read(receivedBytes, 0, restMessageLengthBytes);
                        System.out.println("number of bytes read: " + currentBytesRead);
                        Validate.isTrue(restMessageLengthBytes == currentBytesRead);

                        // bild hex string from message
                        String messageHexString = operationHexString + GC4RequestResponse.bytesToHexString(receivedBytes, currentBytesRead);
                        Validate.isTrue(messageHexString.length() >= 2, "hexMessageString too short");
                        System.out.println("messageHexString received, length: " + messageHexString.length());
                        System.out.println("messageHexString:");
                        System.out.println(messageHexString);

                        // get message direction/type
                        String directionHexString = GC4MessageHandler.hexSubString(1, 1, messageHexString);

                        // handle message
                        switch (operationHexString) {
                            case "01":
                                new Event01BallDetectionHandler().handleHexMessageString(messageHexString);
                                break;

                            case "02":
                                new Event02ShotBallDataHandler().handleHexMessageString(messageHexString);
                                break;

                            case "03":
                                if (GC4MessageHandler.DIRECTION_001_GC4_EVENT.equals(directionHexString)) {
                                    new Event03ShotSpinDataHandler().handleHexMessageString(messageHexString);
                                } else if (GC4MessageHandler.DIRECTION_001_GC4_TO_HOST.equals(directionHexString)) {
                                    new Response03Handler().handleHexMessageString(messageHexString);

                                    // send 0602 to GC4
                                    Thread.sleep(1000);
                                    System.out.println("send 0602 message to GC4");
                                    gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_0602_STRING_60));
                                } else {
                                    throw new RuntimeException("direction unknown: " + directionHexString);
                                }
                                break;

                            case "05":
                                new Event05ShotClubDataHandler().handleHexMessageString(messageHexString);
                                break;

                            case "06":
                                new Response06Handler().handleHexMessageString(messageHexString);
                                break;

                            case "11":
                                new Response11Handler().handleHexMessageString(messageHexString);

                                // send 0302 to GC4
                                Thread.sleep(1000);
                                System.out.println("send 0302 message to GC4");
                                gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_0302_STRING_76));
                                break;

                            case "12":
                                new Response12Handler().handleHexMessageString(messageHexString);

                                // send 1102 to GC4
                                Thread.sleep(1000);
                                System.out.println("send 1102 message to GC4");
                                gc4Client.sendMessage(GC4Message.hexStringToByteArray(GC4RequestResponse.REQUEST_1102_STRING_76));
                                break;

                            default:
                                System.err.println("unknown message type: " + operationHexString);
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