package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class GameResults {

        public Integer endPoints;

        public Player player;
        public GameResults(Integer endPoints, Player player) {
            this.endPoints = endPoints;
            this.player = player;
        }
}
