import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class CardDemo {

    class Card {
        public int rank;
        public String suit;

        public Card(int rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return ""+suit+" "+rank;
        }
    }
    public static final String[] suits = {"♥","♠","♣","♦"};

    public List<Card> buyDeskCard() {
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i,suits[j]);
                cards.add(card);
            }
        }
        return cards;
    }

    public void shuffle(List<Card> cards) {
        for (int i = cards.size()-1; i > 0 ; i--) {
            Random random = new Random();
            int index = random.nextInt(i);
            swap(cards,index,i);
        }
    }
    private void swap(List<Card> cards,int i,int j) {
        //Card tmp = cards[i];
        Card tmp = cards.get(i);
        //cards[i] = cards[j];
        cards.set(i,cards.get(j));
        //cards[j] = tmp;
        cards.set(j,tmp);
    }

    public List<List<Card>> test(List<Card> cards) {
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> hand3 = new ArrayList<>();

        List<List<Card>> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);

        //3个人 每个人轮流抓5张牌
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                //你怎么知道 哪张牌 放到哪个hand中
                Card card = cards.remove(0);
                hands.get(j).add(card);
            }
        }
        return hands;
    }

}