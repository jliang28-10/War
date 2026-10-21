import java.util.Scanner;

public class Pile extends QueueArray<Card> {

    public Pile(String cards) {

        // Allows for the construction of a pile of cards from a string
        //
        // Example:
        //   Pile pile = new Pile("As 2s 3s Jh Qh Kh 4d 5d 6d 7c 8c 9c");

        // TODO: implement pile constructor

        String[] deck=cards.split(" ");
        for(String s:deck){
            if(s.length()>1)
                this.enqueue(new Card(s.charAt(0), s.charAt(1)));
        }

    }

    public String toString() {

        // Creates a string containing 10 cards per line, for as many lines
        // as necessary to show all cards in the pile
        //
        // Example:
        //   6h  3h  Jc  Ts  3c  7h  2h  9c  3s  7s
        //   8c  5d  Js  Jh  6d  Ah  Qh  9d  Ks  6c
        //   Kc  Td  Jc  Th  9s  Kd
        // TODO: implement toString. Use a  'for (Card card : this) {}' loop to print them
        String output = "";
        int count =0;
        for(Card card: this){
            output= output + card.toString() + "  ";
            count++;
            if(count%10==0&&count!=0)
                output = output + "\n";
        }
        return output;

    }

    /*
     * A riffle shuffle cuts the pile into two halves and then interleaves
     * them by alternating cards from each half.
     *
     * Hint: Use two temporary Pile objects (or QueueArray<Card> objects) to
     * hold the two halves while you rebuild this pile.
     */
    public void shuffle() {
        int half = this.size()/2;
        int full = this.size();
        QueueArray<Card> c1= new QueueArray<Card>();
        QueueArray<Card> c2= new QueueArray<Card>();
        for(int i=0; i<half;i++){
            c1.enqueue(this.dequeue());

        }
        for(int i=half; i<full;i++){
            c2.enqueue(this.dequeue());
        }
        while(!c1.isEmpty()&&!c2.isEmpty()){
            this.enqueue(c1.dequeue());
            this.enqueue(c2.dequeue());
        }
        if(!c1.isEmpty()){
            while(!c1.isEmpty())
                this.enqueue(c1.dequeue());
        }
        if(!c2.isEmpty()){
            while(!c2.isEmpty())
                this.enqueue(c2.dequeue());
        }

        // TODO: implement the riffle shuffle
    }
}
