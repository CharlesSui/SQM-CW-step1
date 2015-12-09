package g53sqm.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class StubClient implements Runnable {

  private String serverAddress;
  private int serverPort;
  private String[] commands;
  private String clientName;

  StubClient(int serverPort, String serverAddress, String[] commands, String clientName) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.commands = commands;
    this.clientName = clientName;
  }

  public void run() {

    BufferedReader in = null;
    PrintWriter out = null;
    Socket clientSocket = null;
    Random rand = new Random();

    try {
      clientSocket = new Socket(this.serverAddress, this.serverPort); in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      out = new PrintWriter(clientSocket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      System.out.println(this.clientName + ": " + in.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (String command: commands) {

      out.println(command);
      try {
        System.out.println(this.clientName + ": " + in.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        Thread.sleep(rand.nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}

