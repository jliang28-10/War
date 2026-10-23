import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    // ADD ADDITIONAL CONSTANTS HERE, IF DESIRED...

    static Pile pile1;
    static Pile pile2;
    static Pile misc= new Pile("");
    static int numTurns;

    // ADD ADDITIONAL VARIABLES HERE, IF DESIRED...

    public static void printPiles() {

        // For your convenience, this prints the cards in
        // pile1 and pile2 along with the turn number, as specified
        // by numTurns

        System.out.println("Turn: " + numTurns + System.lineSeparator());
        System.out.println("player1:");
        System.out.println(pile1.toString() + System.lineSeparator());
        System.out.println("player2:");
        System.out.println(pile2.toString());
        System.out.println("--------------------------------------");
    }

    public static void printWar() {

        // Call this method every time there is a tie in the ranks
        // of cards compared

        System.out.println("                                        *** WAR! ***" + System.lineSeparator());
    }

    // ADD ADDITIONAL METHODS HERE, IF DESIRED...

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java War <deck-file>");
            return;
        }

        // args[0] is the deck filename.
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            System.out.println("Loading deck from: " + args[0]);
            pile1 = new Pile(scanner.nextLine());
            pile2 = new Pile(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            return;
        }

        numTurns = 1;
        outer:
        while(numTurns<5000&&!pile1.isEmpty()&&!pile2.isEmpty()){
            Card c1 = pile1.dequeue();
            Card c2 = pile2.dequeue();
            if(c1.compareTo(c2)>0){
                pile1.enqueue(c1);
                pile1.enqueue(c2);
                numTurns++;

            }
            if(c2.compareTo(c1)>0){
                pile2.enqueue(c1);
                pile2.enqueue(c2);
                numTurns++;
            }
            else{
                printWar();
                boolean won = false;
                while(!won){
                    misc.enqueue(c1);misc.enqueue(c2);
                    for(int i=0; i<3; i++){
                        misc.enqueue(pile1.dequeue());
                        if(pile1.isEmpty()){
                            System.out.println("player 2 wins!");
                            break outer;
                        }
                        misc.enqueue(pile2.dequeue());
                        if(pile2.isEmpty()){
                            System.out.println("player 2 wins!");
                            break outer;
                        }
                    }
                     c1 = pile1.dequeue();
                    if(pile1.isEmpty()){
                        System.out.println("player 2 wins!");
                        break outer;
                    }
                     c2 = pile2.dequeue();
                    if(pile2.isEmpty()){
                        System.out.println("player 2 wins!");
                        break outer;
                    }
                    if(c1.compareTo(c2)>0){
                        while(!misc.isEmpty())
                            pile1.enqueue(misc.dequeue());
                        won=true;
                    }
                    if(c2.compareTo(c1)>0){
                        while(!misc.isEmpty())
                            pile2.enqueue(misc.dequeue());
                        won=true;
                    }

                }
                numTurns++;
            }


            printPiles();
        }
        if(numTurns==5000){
            System.out.println("draw!");
        }
        if(pile2.isEmpty()){
            System.out.println("player 2 wins!");

        }
        if(pile1.isEmpty()){
            System.out.println("player 2 wins!");

        }
        // TODO: ADD CODE HERE TO COMPLETE THIS METHOD...

    }
}
