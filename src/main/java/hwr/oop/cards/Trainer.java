package hwr.oop.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Trainer {
    private final List<Box> boxList;

    private Trainer(List<Box> boxList) {
        this.boxList = boxList;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    // loading new Topic
    void loadTopic(Topic topic) {
        ArrayList<Card> cardList = topic.getCardList();
        for (Card card : cardList) {
            boxList.get(0).addCard(card);
        }
    }

    /* Da man nach dem removen einer Card aus einer Box nicht weiß,
    woher sie kommmt, würde ich die boxNummer als int passen.
    Somit kann man z.b. beim Checken der Antwort dann die boxNummer
    in- bzw. dekrementieren je nachdem, ob die Anwort richtig oder falsch war
     */
    void moveCardIntoBox(Card card, int boxNumber) {
        getBoxList().get(boxNumber).addCard(card);
    }
    /*
    void moveCard(Card card, boolean answer) {
        int length = getBOxList().size()
        if (answer == true){
            currentbox + 1
        getBoxList().get(boxNumber).addCard(card);
    }

    boolean checkAnswer(String answer){
        if answer == card.getAnswer(){
            moveCardUp();
        }
    }
    */
    public static class TrainerBuilder{
        private final PersistenceLoadPort persistenceLoadPort;
        public TrainerBuilder(){

        }
        public TrainerBuilder(PersistenceLoadPort persistenceLoadPort){
            this.persistenceLoadPort = persistenceLoadPort;
        }

        public Trainer buildTrainerFromSave(){
            //JsonPersistenceAdapter adapter = new JsonPersistenceAdapter();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            List<Box> boxList;
            try{
                String filename = reader.readLine();
                boxList = (List)persistenceLoadPort.loadTrainingInstance(filename);//unschön
                Trainer trainer = new Trainer(boxList);
                return trainer;
            }catch(IOException error){
                error.printStackTrace();
            }
        }
        public Trainer buildTrainerWith3Boxes(){
            Trainer trainer = new Trainer(List.of(new Box(), new Box(), new Box()));
            return trainer;
        }
        public Trainer buildTrainerWith5Boxes(){
            Trainer trainer = new Trainer(List.of(new Box(), new Box(), new Box(), new Box(), new Box()));
            return trainer;
        }
        public Trainer buildTrainerWith7Boxes(){
            Trainer trainer = new Trainer(List.of(new Box(), new Box(), new Box(),new Box(), new Box(), new Box(), new Box()));
            return trainer;
        }
    }

}
