package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.RE;
import static org.assertj.core.api.Assertions.assertThat;

 class PointsTest {
    @Test
    void calculatePointsTest(){
        var game = new Game();
        game.addPlayer("Mugtaba");
        game.addPlayer("Simon");
        game.addPlayer("Galatea");
        game.addPlayer("Hajer");

        game.players.getFirst().setScore(100);
        game.players.getFirst().setTeam(RE);
        game.players.get(1).setScore(20);
        game.players.get(1).setTeam(RE);
        game.players.get(2).setScore(30);
        game.players.get(2).setTeam(CONTRA);
        game.players.get(3).setScore(90);
        game.players.get(3).setTeam(CONTRA);

        game.calculateTeamScore(RE);
        game.calculateTeamScore(CONTRA);
        game.calculatePoints();
        assertThat(game.players.getFirst().getPoints()).isZero();
        assertThat(game.players.get(3).getPoints()).isEqualTo(4);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);



    }
}
