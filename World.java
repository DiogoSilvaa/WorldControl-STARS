import java.util.ArrayList;
/**
 * This class implements Cwk 2
 *
 * @diogosilva
 * @26/04/2020
 */
public class World
{
    int world_number;
    String world_name;
    int world_rating;
    int world_capacity;
    ArrayList<Card> world_population;
    /**
     * Constructor for objects of class World
     */
    public World(int number,String name,int rating,int capacity)
    {
        this.world_population = new ArrayList<Card>();
        this.world_number = number;
        this.world_name = name;
        this.world_rating = rating;
        this.world_capacity = capacity;
    }
    /*Returns world rating*/
    public int getrating()
    {
        return world_rating;
    }
    /*Returns world number*/
    public int getnumber()
    {
        return world_number;
    }
    /*Returns world name*/
    public String getname()
    {
        return world_name;
    }
    /*Returns world id*/
    public int getid()
    {
        return world_number;
    }
    /*Adds  a card to world population*/
    public void enter(Card card)
    {
       world_population.add(card);
    }
    /*Removes a card from world population*/
    public void leave(Card card)
    {
       world_population.remove(card);
    }
    /*Checks if world still has capacity for additional cards*/
    public String checkcapacity(){
       if(world_population.size()<world_capacity){
           return "still has capacity.";
       } 
       if(world_population.size()>=world_capacity){
           return "reached capacity.";
        } else{
            return "An error has occurred.";
        }  
    }
    /*Returns all cards in world in a string*/
    public String checkpopulation(){
           return world_population.toString();
    }
    /*Returns a boolean value, showing if card is in world (true) or not (false)*/
    public boolean checkcard(Card card){
       return world_population.contains(card);
    }
    /*Returns all information about the world in a string*/
    public String toString(){  
        return "World name:"+world_name+"; ID:"+world_number+"; Luxury rating:"+world_rating+"; Capacity:"+world_capacity+" | ";  
    } 
}
