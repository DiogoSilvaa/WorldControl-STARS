 



 
/**
 * This class implements Cwk 2
 *
 * @diogosilva
 * @26/04/2020
 */
/*Tests card functions, by creating some cards and calling card's functions */
public class Tester
{   
    public static void main(){
        ResortManager resort = new ResortManager("wayward");
        /*Testing toString()*/
        System.out.println(resort.toString());
        /*Testing getAllCardsOnEachWorld()*/
        System.out.println(resort.getAllCardsOnEachWorld());
        /*Testing findCard(cr)*/
        System.out.println("World that card with id 1004 is at: "+resort.findCard(1004));
        System.out.println("World that card with id 1005 is at: "+resort.findCard(1005));
        /*Testing getWorldNumber*/
        System.out.println("World number of the world with Home as name: "+resort.getWorldNumber("Home"));
        System.out.println("World number of the world with Sprite as name: "+resort.getWorldNumber("Sprite"));
        /*Testing getAllCardsOnWorld()*/        
        System.out.println("All cards on Home: "+resort.getAllCardsOnWorld("Home"));
        System.out.println("All cards on Solo: "+resort.getAllCardsOnWorld("Solo"));
        System.out.println("All cards on Fantasia: "+resort.getAllCardsOnWorld("Fantasia"));
        /*Testing canTravel(crId,shtlCode)*/
        System.out.println("Checking if cardid 1002 can travel in ABC1: "+resort.canTravel(1002, "ABC1"));
        System.out.println("Checking if cardid 1002 can travel in EFG5 : "+resort.canTravel(1002, "EFG5"));
        /*Testing canTravel(crId,shtlCode)*/
        System.out.println("Travel of 1002 in ABC1 outcome: "+resort.travel(1002, "ABC1"));
        System.out.println("Travel of 1002 in ABC1 outcome: "+resort.travel(1002, "ABC1"));
        System.out.println("Travel of 1004 in BCD2  outcome: "+resort.travel(1004, "BCD2"));
        System.out.println("Travel of 1004 in ABC1 outcome: "+resort.travel(1004, "ABC1"));
        System.out.println("Travel of 1004 in CDE3  outcome: "+resort.travel(1004, "CDE3"));
        /*Testing topUpCredits(id,amount)*/
        resort.topUpCredits(1004, 300);
        /*Testing if id 1004 can travel after the topup*/
        System.out.println("Travel of 1004 in CDE3  outcome: "+resort.travel(1004,"CDE3"));
        System.out.println("World that card with id 1004 is at: "+resort.findCard(1004));
        /*Testing moveHome(id)*/
        resort.moveHome(1004);
        System.out.println("World that card with id 1004 is at: "+resort.findCard(1004));
        /*Testing travel outcome of 1004 in JKL8 */
        System.out.println("Travel of 1004 in JKL8  outcome: "+resort.travel(1004, "JKL8"));  
        /*Testing convertPoints*/
        resort.travel(1006,"ABC1");//travels 1006 to world1
        resort.travel(1006,"CDE3");//travels 1006 to world2
        System.out.println("Travel attempt:"+resort.travel(1006,"JKL8"));//attemps to travel 1006 to world 3
        resort.topUpCredits(1006,3);//tops up 3 credits to allow one more trip
        System.out.println("Travel attempt:"+resort.travel(1006,"JKL8"));
        System.out.println("Travel attempt:"+resort.travel(1006,"EFG5"));//attemps to travel 1006 to world 3        
        resort.convertPoints(1006);
        System.out.println("Travel attempt:"+resort.travel(1006,"EFG5"));//attemps to travel 1006 to world 3        
        /*Testing evacuteAll()*/
        System.out.println(resort.getAllCardsOnEachWorld());
        resort.evacuateAll();
        System.out.println(resort.getAllCardsOnEachWorld());        
    }
}
