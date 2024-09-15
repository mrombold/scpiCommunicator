package scpiCommunicator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InstrumentCommunicator {
    public static void main(String[] args) {
    	
        String scope =     "10.11.13.220"; // Instrument IP addresses
        String dmm =       "10.11.13.221";
        String spectrum =  "10.11.13.222";
        String signal =    "10.11.13.223";
        String power =     "10.11.13.224";
        
        
        int port = 5025;                   // Port number for SCPI commands

        try (Socket dmmSocket = new Socket(dmm, port);
             PrintWriter out = new PrintWriter(dmmSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(dmmSocket.getInputStream()))) {
            
            // Send SCPI command to the instrument
            out.println("*IDN?");
            
            // Read response from the instrument
            String response = in.readLine();
            System.out.println("Response from instrument: " + response);
            
            out.println("CONF:TEMP THER,KITS90");    //Configure to measure K type thermocouple
            out.println("UNIT:TEMP F");    //Configure to measure K type thermocouple
            out.println("READ?");    //Configure to measure K type thermocouple
            
            // Read response from the instrument
            response = in.readLine();
            System.out.println("Temperature: " + response + " F");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
