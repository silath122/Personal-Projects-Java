import java.util.ArrayList;

/**
 * MailCoach class for Project5
 *
 * @author Siah
 * @version 1.0 11/04/22
 */
public class MailCoach
{
    // instance variables 
    private String destination;
    private double weight;
    private int volume;
    private ArrayList<Shipment> cargo;
    
    /**
     * The standard constructor
     */
    public MailCoach() {
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.cargo = new ArrayList<Shipment>();
    }
    
    /**
     * The second constructor
     * 
     * @param destination the destination of the MailCoach object
     */
    public MailCoach(String destination) {
        this();
        this.destination = destination;
    }
    
    /**
     * The destination getter method
     * 
     * @return String
     */
    public String getDestination() {
        return this.destination;
    }
    
    /**
     * The volume getter method
     * 
     * @return int
     */
    public int getVolume() {
        return this.volume;
    }
    
    /**
     * The weight getter method
     * 
     * @return double
     */
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * The cargo getter method
     * 
     * @return ArrayList< Shipment >
     */
    public ArrayList< Shipment > getCargo() {
       return this.cargo; 
    }
    
    // appending the shipment object to the cargo list  
    /**
     * The add shipment method that appends the shipment object to the cargo list
     * 
     * @param s Shipment object to be added to cargo list
     */
    public void addShipment(Shipment s) {
        // if the destination given is equivalent to the shipments destination
        if (s.getDestination().equals(this.destination)) {
           this.cargo.add(s);
           this.volume += s.getVolume(); // add the volume of the shipment object to the cargos volume 
           this.weight += s.getWeight(); // add the weight of the shipment object to the cargos weight 
        }
    }
    
}
