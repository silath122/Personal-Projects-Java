import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/***
 * About: The Project5 program implements an application that simply uses the
 * Shipment and MailCoach entity classes to print a report containting a list 
 * of all outgoing shipments from the 'week1.txt' file. Each mail coach is created using a 
 * specific destination. The max limits for all mail coaches are
 * 100 units of volume and a weight of 500 lbs. Each shipment is put into the first mail coach
 * that matches it's destination. Once the shipment is put into the mail coach,
 * then it is put into the first coach that can hold both the shipments 
 * weight and volume. If no such coach exists, then one is created and the shipment is
 * loaded onto the coach. Once all the cargo is loaded for each mail coach, then
 * the reciept is printed for the shipment. If the coach contains 50+ units of volume
 * and/or 250+ lbs then the cargo is dispatched, otherwise it is held.
 * 
 * @author Siah Thomas
 * @version 1.0 
 * @since 12/27/22
 */
public class Project5 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER: 10/31/22 3:46 PM       <--------------------
    
    //DO NOT ALTER THE MAIN METHOD
    /**
     * This is the main method whih makes use of the testShipment, testMailCoach,
     * readData, sortData, and printReport methods. 
     */
    public static void main( String[] args ) {
        //test your entity classes, comment out when you passed all tests
        testShipment();
        testMailCoach();
        
        //readData reads the input file into an array list
        //it fills the array list with Shipment objects
        ArrayList< Shipment > allMail = readData( "week1.txt" );
        
        //sortData goes through the raw data contained in all mail and populates
        //an array list for the destination with MailCoach objects
        ArrayList< MailCoach > stolat = sortData( "Stolat", allMail );
        ArrayList< MailCoach > quirm = sortData( "Quirm", allMail );
        ArrayList< MailCoach > pseudopolis = sortData( "Pseudopolis", allMail );
        ArrayList< MailCoach > borogravia = sortData( "Borogravia", allMail );
        ArrayList< MailCoach > ueberwald = sortData( "Ueberwald", allMail );
        ArrayList< MailCoach > krull = sortData( "Krull", allMail );
        
        //printReport prints the mail coach data dor each destination in turn
        printReport( stolat );
        printReport( quirm );
        printReport( pseudopolis );
        printReport( borogravia );
        printReport( ueberwald );
        printReport( krull );
    }//DO NOT ALTER THE MAIN METHOD
    public static void testShipment() { //DO NOT ALTER THIS METHOD
        Shipment s1 = new Shipment();
        assert s1.getDestination().equals( "" ) && s1.getVolume() == 0 && 
               s1.getId() == 0 && Math.abs( s1.getWeight() - 0 ) < 0.001 : "Shipment standard constructor not working";
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        assert s2.getDestination().equals( "Stolat" ) : "Shipment second costructor destination not set correctly";
        assert s2.getVolume() == 13 : "Shipment second costructor volume not set correctly";
        assert s2.getId() == 44 : "Shipment second costructor id not set correctly";
        assert Math.abs( s2.getWeight() - 10.5 ) < 0.001 : "Shipment second costructor weight not set correctly";
        System.out.println( "Shipment all tests passed" );
    } //DO NOT ALTER THIS METHOD
    public static void testMailCoach() { //DO NOT ALTER THIS METHOD
        MailCoach mc1 = new MailCoach();
        assert mc1.getDestination().equals( "" ) && mc1.getVolume() == 0 && 
               Math.abs( mc1.getWeight() - 0 ) < 0.001 && mc1.getCargo() != null : "MailCoach standard constructor not working";
        MailCoach mc2 = new MailCoach( "Stolat" );  
        assert mc2.getDestination().equals( "Stolat" ) && mc2.getVolume() == 0 && 
               Math.abs( mc2.getWeight() - 0 ) < 0.001 && mc2.getCargo() != null : "MailCoach second constructor not working";
        Shipment s1 = new Shipment( 12, "Stolat", 106.7, 45 );
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        mc2.addShipment( s1 );
        mc2.addShipment( s2 );
        assert mc2.getVolume() == 58 : "MailCoach addShipment not working";
        assert Math.abs( mc2.getWeight() - 117.2 ) < 0.001 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 0 ) == s1 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 1 ) == s2 : "MailCoach addShipment not working";
        System.out.println( "MailCoach all tests passed" );
    }//DO NOT ALTER THIS METHOD
    
    /**
     * This is the readData method would reads in the file and into an ArrayList.
     * 
     * @param fileName the file with all the dat
     * @return ArrayList< Shipment >
     * @exception FileNotFoundException on scanning in the file
     * @see FileNotFoundException
     */
    public static ArrayList< Shipment > readData( String fileName ) {
        
        Scanner in = null;
        String inFile = fileName;
        
        // catch FileNotFoundException
        try {
            File f = new File(inFile);
            in = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        // create the return ArrayList       
        ArrayList<Shipment> shipList = new ArrayList<Shipment>();

        // read in each line as a Shipment object to append to the shipList ArrayList 
        while(in.hasNext()) {
           
           // get rid of all spaces and characters that are irrelevant
           String tempLine = in.nextLine();
           String[] tempArray = tempLine.split(" ");
           
           // using temp variables for each argument of the Shipment object created
           int tempId = Integer.parseInt(tempArray[7].replace("#", ""));;
           String tempDestination = tempArray[4];
           double tempWeight = Double.parseDouble(tempArray[0]); 
           tempArray[2] = tempArray[2].replace("(","");
           tempArray[2] = tempArray[2].replace(")","");
           int tempVolume = Integer.parseInt(tempArray[2]);
           
           Shipment tempShip = new Shipment(tempId, tempDestination, tempWeight, tempVolume);
           shipList.add(tempShip); 
           
        }
            
        return shipList;

    }
    
    
    /** 
     * This is the sortData method that reads in an ArrayList and destination. It then
     * goes through the ArrayList and organizes it based on the destination and
     * limitations for each coach.
     * 
     * @param destination the destination of the sorted data
     * @param allMail an ArrayList created using Shipment objects
     * @return ArrayList< MailCoach >
    */
   
    public static ArrayList< MailCoach > sortData( String destination, ArrayList< Shipment > allMail ) {
        // an ArrayList that contains MailCoach objects where each mail coach stores multiple shipments
        ArrayList<MailCoach> mailList = new ArrayList<MailCoach>();
        
        // mail coach object created using the second constructor and destination provided by user
        MailCoach tempMailCoach = new MailCoach(destination);
        
        
        // add all the specific destinations to a temporary destination list
        // importance: organize by destination, add all shipments to the an ArrayList that only contain the specific destination the user is looking for
        ArrayList<Shipment> destinationMail = new ArrayList<Shipment>();
        for (int i = 0; i < allMail.size(); i++) {
            if(allMail.get(i).getDestination().equals(destination)) {
                destinationMail.add(allMail.get(i));
            }
        }
        
        // add a temporary mail coach object to the mailList, so the for loop can run through
        mailList.add(tempMailCoach);
        
        // while loop keeps running until the destinationMail ArrayList is empty
        while(!destinationMail.isEmpty()) {
            // run through each MailCoach object in the mailList
            for( int j = 0; j < mailList.size(); j++) {
                // if the weight of the first shipments index in destinationMail plus the overall weight of the specific mailList index is less than or equal
                // to 500.0 lbs, AND the volume of the first shipment index in destinationMail plus the overall weight of the specific mailList index is less
                // than or equal to 100 units of volume THEN use the addShipment method for the destinationMail and access the first index 
                if (mailList.get(j).getWeight() + destinationMail.get(0).getWeight() <= 500.0 && mailList.get(j).getVolume() + destinationMail.get(0).getVolume() <= 100) {
                    mailList.get(j).addShipment(destinationMail.get(0));
                    destinationMail.remove(0); // remove the first instance in order to look at the next shipment 
                    break;
                }
                // else if this is the last coach in the list then create another MailCoach object and appened it to the list
                // then add the shipment to the new coach created
                else if(mailList.get(j).equals(mailList.get(mailList.size() - 1))) {
                    mailList.add(new MailCoach(destination));
                    mailList.get(j + 1).addShipment(destinationMail.get(0));
                    destinationMail.remove(0); // remove the first instance in order to look at the next shipment
                    break;
                }
            }
        }
        
        return mailList;
    }
    
    
    /**
     * This is the printReport method which takes in an ArrayList argument, goes
     * through the arrayList, and then prints a report based on if the coach can be
     * dispatched, held, or if there are not shipments.
     * 
     * @param mc an ArrayList created using MailCoach objects
     */
    public static void printReport( ArrayList< MailCoach > mc ) {
        System.out.printf("Next week's mail coaches to %s:%n", mc.get(0).getDestination());
        for(int i = 0; i < mc.size(); i++) {
            if(mc.get(i).getWeight() >= 250 || mc.get(i).getVolume() >= 50) {
                System.out.printf("    DISPATCH: coach %d (shipments", i + 1);
                for(int j = 0; j < mc.get(i).getCargo().size(); j++) {
                    System.out.printf(" #%d", mc.get(i).getCargo().get(j).getId());
                }
                System.out.printf(")%n");
            }
            else if(mc.get(i).getWeight() > 0 || mc.get(i).getVolume() > 0){
                System.out.printf("    HOLD:    coach %d (shipments", i + 1);
                for(int j = 0; j < mc.get(i).getCargo().size(); j++) {
                    System.out.printf(" #%d", mc.get(i).getCargo().get(j).getId());
                }
                System.out.printf(")%n");  
            }
            else {
                System.out.println("    NO SHIPMENTS");
            }
        }
    }
}
