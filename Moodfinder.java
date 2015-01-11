package Main;
import java.util.Random;
/* 
Enhanced and optimized for Moodfinder application by Amin Amini Maghsoud Bigy: 2012
*/
public class Moodfinder {
    //These correspond to the states of Manager.
    private static final int START = 0;
    private static final int CALCULATE = 1;
    private static final int ANSWER = 2;
    public int firstNum=0;
	  public int SecondNum=0;
    /* Variable declarations */
    private int state = START;
    public int Client_answer=0;
    /* The processInput method */
    	public String processInput(String theInput) {
    		String theOutput = null;
    		Random diceRoller = new Random();  	
    		switch (state){
    		case START:
    			//Prompting user to start the Moodfinder process.
    			
    			theOutput = "Do you want to start the process? YES/NO";
    			
    			state = CALCULATE;
    			break;
    		case CALCULATE:
    			//Check to see if the TOTAL reaches the goal.
    			if (Manager.total >= 2000000) {
    				theOutput = "END";	
    			} else {
    			if (theInput.equalsIgnoreCase("yes")) {
    				//Generate two random numbers and send them to the Worker.
    		    		  firstNum = diceRoller.nextInt(20) + 1;
    		    		  SecondNum = diceRoller.nextInt(40) + 1;
    		    		  theOutput = firstNum+"*"+SecondNum;
    		    		  state = ANSWER;
    			} 
    			else if(theInput.equalsIgnoreCase("No")){
    				theOutput = "END";
    			}
    			}		
    			break;
    		case ANSWER:
    			//Convert the results coming from Worker back into Integer
    			Client_answer = Integer.parseInt(theInput);
    		    //Add the results coming from Worker to the Total
    			Manager.total += Client_answer;	
				theOutput = "     Target:" +Manager.total;
				state = CALCULATE;
					break;		
    		}
    		//Once we have the output message, pass it back to Server.
    		return theOutput;
    	}
    	
    	}
    	
