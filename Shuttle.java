/**
 * This class implements Cwk 2
 *
 * @diogosilva
 * @26/04/2020
 */  
public class Shuttle
{
    String journey_code;
    World journey_source;
    World journey_destination; 
    /**
     * Constructor for objects of class Shuttle
     */
    public Shuttle(World source, World destination, String code)
    {
        this.journey_source = source;
        this.journey_destination = destination;
        this.journey_code = code;
    }
    /*Checks if card has all the requirements to travel and returns a boolean value to represent if card can (true) or not (false)*/
    public boolean checkcard(Card card)
    {
        if (card.getrating()>=journey_destination.getrating()){
            if(card.checkjourney() == true){
                if(journey_destination.checkcapacity() == "still has capacity."){
                    if(journey_source.checkcard(card)==true){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
         return false;
        }
    }
    /*If card can travel, changes the world array it belongs and charges the travel fee*/
    public String travel(Card card){
        if(checkcard(card)){
        card.pay();
        journey_source.leave(card);
        journey_destination.enter(card);
        return "Card "+card+" travelled succefully from "+journey_source+" to "+journey_destination+".";
        }
        else{
            return "Card "+card+" wasn't able to travel from "+journey_source+" to "+journey_destination+".";
        }    
    }
    /*Returns all information about the shuttle*/
    public String toString(){
        return "The shuttle "+journey_code+" travels from "+journey_source+" to "+journey_destination+"."; 
    }
    /* Returns shuttle code*/
    public String getcode(){
        return journey_code;
    }
    /* Returns shuttle destination*/
    public World getdestination(){
        return journey_destination;
    }
    /* Returns a boolean value "true" if string input'ed is the same as shuttle code, or "false" if it isn't*/
    public boolean checkshuttlecode(String code){
        if(code==journey_code){
            return true;
        }else{
          return false;
        }
    }
    /*Returns shuttle source*/
    public World getsource(){
        return journey_source;
    }
}
