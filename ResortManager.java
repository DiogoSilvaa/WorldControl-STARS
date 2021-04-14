import java.util.*;

/**This class implements Cwk 2
 *
 * @author A.A.Marczyk 
 * @version 09/02/20
 **/
public class ResortManager 
{
   String location;
   ArrayList<Card> resort_population;
   ArrayList<World> resort_worlds;
   ArrayList<Shuttle> resort_shuttles;
    World world0;
    World world1;
    World world2;
    World world3;
    World world4;
   public  ResortManager(String location)
   {
        this.location = location;
        this.resort_population = new ArrayList<Card>();
        this.resort_worlds = new ArrayList<World>();
        this.resort_shuttles = new ArrayList<Shuttle>();
        loadCards();
        loadWorlds();
        setUpShuttles();
        for(int x=0; x<resort_population.size();x++){
            resort_worlds.get(0).enter(resort_population.get(x));
        }    
   }
   /**
     * Returns all of the data about all worlds including the cards
     * currently on each world, r "No cards"
     * @return all of the details of all worlds including  
     * and all cards currently on each world, or "No cards" 
     */
    public String toString()
   {
        return "Worlds: "+resort_worlds.toString()+" Cards: "+resort_population.toString();
   }

   /**Returns a String representation of all the cards on all worlds, listed 
     * by world 
     * @return a String representation of all cards on all worlds
     **/
    public String getAllCardsOnEachWorld(){
        return (resort_worlds.get(0)).getname()+" has the following cards: "+(resort_worlds.get(0)).checkpopulation()+", "+(resort_worlds.get(1)).getname()+" has the following cards: "+(resort_worlds.get(1)).checkpopulation()+", "+(resort_worlds.get(2)).getname()+" has the following cards: "+(resort_worlds.get(2)).checkpopulation()+", "+(resort_worlds.get(3)).getname()+" has the following cards: "+(resort_worlds.get(3)).checkpopulation()+", "+(resort_worlds.get(4)).getname()+" has the following cards: "+(resort_worlds.get(4)).checkpopulation();
   } 
   /**Returns the name of the world which contains the specified card or null
     * @param cr - the specified card
     * @return the name of the World which contains the card, or null
     **/
    public String findCard(int cr)
    {
        Card card=idfindcard(cr);
        for (int i=0; i<resort_worlds.size(); i++){
            World world = resort_worlds.get(i);      
            if (world.checkcard(card)==true){
                return world.getname();
            }
        }
        return null;
    }
    
    
    /** Given the name of a world, returns the world id number
     * or -1 if world does not exist
     * @param name of world
     * @return id number of world
     */
    public int getWorldNumber(String ww)
    {
        for (int i=0; i<resort_worlds.size(); i++){
            World worldwanted = findworld(ww);
            if(resort_worlds.contains(worldwanted)){
                return worldwanted.getnumber();
            } else{
                return -1;
            }
        }
        return -1;
    }
        /**Given the world name, returns world **/
    private World findworld(String worldname){   
        for (int i=0; i<resort_worlds.size(); i++){
            World world = resort_worlds.get(i);      
            if (world.getname() == worldname){
                return world;
            }
        }
        return null;
    }
                
    /**Returns a String representation of all the cards on specified world
     * @return a String representation of all cards on specified world
     **/
    public String getAllCardsOnWorld(String world)
    {   
        World actualworld = findworld(world);
        return "The cards in "+actualworld.getname()+" are: "+actualworld.checkpopulation().toString();
    }
    
    /**Given the shuttle code, returns shuttle **/
    private Shuttle findshuttle(String shtlCode){   
        for (int i=0; i<resort_shuttles.size(); i++){
            Shuttle shuttle = resort_shuttles.get(i);
            if (shuttle.getcode() == shtlCode){
                return shuttle;
            }
        }
        return null;
    }
    /**Given the card id, returns card **/
    private Card idfindcard(int cardid){   
        for (int i=0; i<resort_population.size(); i++){
            Card card = resort_population.get(i);      
            if (card.getcardid() == cardid){
                return card;
            }
        }
        return null;
    }
    /**Returns true if a Card is allowed to move using the shuttle, false otherwise
     * A move can be made if:  
     * the rating of the card  >= the rating of the destination world X
     * AND the destination world is not full X
     * AND the card has sufficient credits X
     * AND the card is currently in the source world X
     * AND the card id is for a card on the system X
     * AND the shuttle code is the code for a shuttle on the system X
     * @param crId is the id of the card requesting the move
     * @param shtlCode is the code of the shuttle journey by which the card wants to pCardel
     * @return true if the card is allowed on the shuttle journey, false otherwise 
     **/
    public boolean canTravel(int crId, String shtlCode)
    {   
        Card card = idfindcard(crId);
        Shuttle shuttle = findshuttle(shtlCode);
        if (card.checkcardid(crId)==true){
            if ((shuttle.getcode()).equalsIgnoreCase(shtlCode)){
                if(shuttle.checkcard(card)==true){
                            return true;
                 }else {
                     return false;
                 }
            } else {
                return false;
            }
        } else{
            return false;
        }    
    }
    
    /**Returns the result of a card requesting to move by Shuttle.
     * A move will be successful if:  
     * the luxury rating of the card  >= the luxury rating of the destination world
     * AND the destination world is not full
     * AND the card has sufficient credits
     * AND the card is currently in the source world
     * AND the card id is for a card on the system
     * AND the shuttle code is the code for a shuttle on the system
     * If the shuttle journey can be made, the card information is removed from the source
     * world, added to the destination world and a suitable message returned.
     * If shuttle journey cannot be made, the state of the system remains unchanged
     * and a message specifying the reason is returned.
     * @param pCardId is the id of the card requesting the move
     * @param shtlCode is the code of the shuttle journey by which the card wants to pCardel
     * @return a String giving the result of the request 
     **/
   public String travel(int pCardId, String shtlCode )
    {
       if(canTravel(pCardId, shtlCode)==true){
          Card card = idfindcard(pCardId);
          Shuttle shuttle = findshuttle(shtlCode);
          World source = shuttle.getsource();
          World destination = shuttle.getdestination();
          card.pay();
          source.leave(card);
          destination.enter(card);
          return "Travel was succesful.";
       } else{
          return "Travel was unsuccesful.";
        }
    } 
    
     
    // These methods are for Task 6 only and not required for the Demonstration 
    // If you choose to implement them, uncomment the following code    
    /** Allows a card to top up their credits.This method is not concerned with 
    / *  the cost of a credit as currency and prices may vary between resorts.
    / *  @param id the id of the card toping up their credits
    / *  @param creds the number of credits purchased to be added to cards information
     */
    public void topUpCredits(int id, int creds)
    {
        Card card = idfindcard(id);
        int x;
         for (int i=0; i<resort_population.size(); i++){
            Card cardx = resort_population.get(i);      
            if (cardx == card){
                resort_population.get(i).topup(creds);
            }
        }
    }
    
    /** Moves a card directly back to the home world without affecting credits
    / *  and not using existing shuttles
    */
    public void moveHome(int id)
    {
        Card card = idfindcard(id);
        World current = findworld(findCard(id));
        current.leave(card);
        resort_worlds.get(0).enter(card);
    }
  
    /** Converts a card's loyalty points into credits
    / * @param tr the id of the card whose points are to be converted
    */
    public void convertPoints(int id)
    {
        Card card = idfindcard(id);
        card.convertpoints();
    }
    
    /** In an emergency, evacuates all cards directly back to the home world without 
    * affecting credits or other information and not using existing shuttles
    */
    public void evacuateAll()
    {
      for(int y=0; y<resort_worlds.size();y++)
      {
            (resort_worlds.get(y)).world_population.clear();
      }   
      for(int x=0; x<resort_population.size();x++){
            resort_worlds.get(0).enter(resort_population.get(x));
      }   
    }
    
   
    
    
    //***************private methods**************
    // create all cards in Appendix A and addthem to their collection
    //public Card(String name,int rating, int id, int balance)
   private void loadCards()
   {
       Card card1000 = new Card("Lynn",5,1000,10);
       resort_population.add(card1000);
       Card card1001 = new Card("May",3,1001,30);
       resort_population.add(card1001);
       Card card1002 = new Card("Nils",10,1002,25);
       resort_population.add(card1002);
       Card card1003 = new Card("Olek",2,1003,12);
       resort_population.add(card1003);
       Card card1004 = new Card("Pan",3,1004,3);
       resort_population.add(card1004);
       Card card1005 = new Card("Quin",1,1005,30);
       resort_population.add(card1005);
       Card card1006 = new Card("Raj",10,1006,6);
       resort_population.add(card1006);
       Card card1007 = new Card("Sol",7,1007,20);
       resort_population.add(card1007);
       Card card1008 = new Card("Tel",6,1008,30);
       resort_population.add(card1008);
  }
    
    // create all worlds in Appendix A and addthem to their collection
    //public World(int number,String name,int rating,int capacity)
  private void loadWorlds()
  {
      World world0 = new World(0,"Home",0,1000);
      resort_worlds.add(world0);
      World world1 = new World(1,"Sprite",1,100);
      resort_worlds.add(world1);
      World world2 = new World(2,"Tropicana",3,10);
      resort_worlds.add(world2);
      World world3 = new World(3,"Fantasia",5,2);
      resort_worlds.add(world3);
      World world4 = new World(4,"Solo",1,1);
      resort_worlds.add(world4);
  }
    
    // create all shuttles in Appendix A and addthem to their collection
   //public Shuttle(World source, World destination, String code)
   private void setUpShuttles()
   {
       Shuttle shuttle0 = new Shuttle(resort_worlds.get(0), resort_worlds.get(1), "ABC1");
       resort_shuttles.add(shuttle0);
       Shuttle shuttle1 = new Shuttle(resort_worlds.get(1), resort_worlds.get(0), "BCD2");
       resort_shuttles.add(shuttle1);
       Shuttle shuttle2 = new Shuttle(resort_worlds.get(1), resort_worlds.get(2), "CDE3");
       resort_shuttles.add(shuttle2);
       Shuttle shuttle3 = new Shuttle(resort_worlds.get(2), resort_worlds.get(1), "DEF4");
       resort_shuttles.add(shuttle3);
       Shuttle shuttle4 = new Shuttle(resort_worlds.get(2), resort_worlds.get(3), "JKL8");
       resort_shuttles.add(shuttle4);
       Shuttle shuttle5 = new Shuttle(resort_worlds.get(3), resort_worlds.get(1), "EFG5");
       resort_shuttles.add(shuttle5);
       Shuttle shuttle6 = new Shuttle(resort_worlds.get(1), resort_worlds.get(4), "GHJ6");
       resort_shuttles.add(shuttle6);
       Shuttle shuttle7 = new Shuttle(resort_worlds.get(4), resort_worlds.get(1), "HJK7");
       resort_shuttles.add(shuttle7);
    }
    
}