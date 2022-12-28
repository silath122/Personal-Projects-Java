
/**
 * Shipment class for Project5
 *
 * @author Siah
 * @version 1.0 11/04/22
 */
public class Shipment
{
    // instance variables 
    private String destination;
    private double weight;
    private int volume;
    private int id;
    
    /**
     * The standard constructor
     */
    public Shipment() {
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.id = 0;
    }
    
    /**
     * The second constructor
     * @param id the id number of the object
     * @param destination the destination of the object
     * @param weight the weight of the object
     * @param volume the volume of the object
     */
    public Shipment(int id, String destination, double weight, int volume) {
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.volume = volume;
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
     * The weight getter method
     * 
     * @return double
     */
    public double getWeight() {
        return this.weight;
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
     * The id getter method
     * 
     * @return int
     */
    public int getId() {
        return this.id;
    }
}
