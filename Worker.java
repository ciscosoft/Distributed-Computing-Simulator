package Main;
import java.io.*;
import java.net.*;
/* 
Enhanced and optimized for Moodfinder application by Amin Amini Maghsoud Bigy: 2012
*/
public class Worker {
	 	
    public static void main(String[] args) throws IOException {
        // Set up the socket, in and out variables
        Socket Socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        boolean first_run = true;    
        try {
            Socket = new Socket("localhost", 4444);
            out = new PrintWriter(Socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: 4444.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        System.out.println("Initialised client and IO connections"); 
        /* Print out what the server says 
         * Take the client's response and send it to the server */ 
    	
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("END")) {
            	System.out.println("FINISHED");
            	  out.close();
                  in.close();
                  stdIn.close();
                  Socket.close();
            	break;
            }
            if (fromServer.contains("*"))
            {
                String [] number_list = fromServer.split("\\*");
              //Convert the receiving numbers from Manager back into Integers
                int num1  = Integer.parseInt(number_list[0]);
                int num2 = Integer.parseInt(number_list[1]);
                //Multiply the numbers and put them into Client_total variable
                int Client_total = num1*num2;
         	   System.out.println("Client answer =: " + Client_total); 
         	   out.println(Integer.toString(Client_total));  
            } else {
          if (first_run == true)
           {
            	fromUser = stdIn.readLine();
                if (fromUser != null) 
                {
                	first_run = false;
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser); 
                }
            } 
            else {
            fromUser = "yes";
            if (fromUser != null) 
            {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);                          
            }        
            }    
        }}
       // Tidy up
        out.close();
        in.close();
        stdIn.close();
        Socket.close();
    }
}
