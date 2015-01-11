package Main;
import java.net.*;
import java.io.*;

/* 
          Enhanced and optimized for Moodfinder application by Amin Amini Maghsoud Bigy: 2012
          */
public class Instance extends Thread {
  // Variable declarations
  private Socket socket = null;
  //Constructor method
  //The syntax used here can be confusing
  public Instance(Socket socket) {
    super("Instance");
    this.socket = socket;
  }
  public void run() {
    try {
      System.out.println("Initialising thread IO connections and state object");
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String inputLine, outputLine;
      Moodfinder mood = new Moodfinder();
      outputLine = mood.processInput(null);
      out.println(outputLine);
      while ((inputLine = in.readLine()) != null) {
        outputLine = mood.processInput(inputLine);
        out.println(outputLine);
        if (outputLine.equals("document printed successfully"))
          break;
      }
       out.close();
       in.close();
       socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
