package hwr.oop;

import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter


public class GameResult {

    public Integer endPoints;

    public Player player;

    public static Game game;

    public GameResult(Integer endPoints, Player player) {
            this.endPoints = endPoints;
            this.player = player;
        }

        public static List<GameResult> sortResults(List<GameResult> gameResults){
            gameResults= gameResults.stream().sorted().collect(Collectors.toList());
            boolean sorted = false;
            int temp;

                while(!sorted) {
                    sorted = true;
                    for (int i = 0; i < game.getNumberOfPeople() - 1; i++) {
                        if (gameResults.get(i).endPoints > gameResults.get(i+1).endPoints ) {
                            temp = gameResults.get(i).endPoints;
                            gameResults.get(i).endPoints = gameResults.get(i+1).endPoints;
                            gameResults.get(i+1).endPoints = temp;
                            sorted = false;
                        }
                    }
                }
            return gameResults;
        }
}
