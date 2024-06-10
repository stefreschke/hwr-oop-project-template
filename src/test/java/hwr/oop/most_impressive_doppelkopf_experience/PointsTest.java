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

        game.getPlayers().getFirst().setScore(100);
        game.getPlayers().getFirst().setTeam(RE);
        game.getPlayers().get(1).setScore(20);
        game.getPlayers().get(1).setTeam(RE);
        game.getPlayers().get(2).setScore(30);
        game.getPlayers().get(2).setTeam(CONTRA);
        game.getPlayers().get(3).setScore(90);
        game.getPlayers().get(3).setTeam(CONTRA);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(120);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(120);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(1);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);

        game.getPlayers().getFirst().setScore(121);
        game.getPlayers().get(1).setScore(0);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(119);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(121);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(119);
        assertThat(game.calculateTeamPoints(RE)).isEqualTo(1);
        assertThat(game.calculateTeamPoints(CONTRA)).isZero();
        assertThat(game.findWinningTeam()).isEqualTo(RE);

        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(0);
        game.getPlayers().get(2).setScore(120);
        game.getPlayers().get(3).setScore(120);

        assertThat(game.calculateTeamScore(RE)).isZero();
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(240);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(5);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);

        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(90);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(150);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(90);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(150);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(1);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);


        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(89);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(151);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(89);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(151);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(2);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);

        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(60);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(180);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(60);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(180);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(2);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);

        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(59);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(181);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(59);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(181);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(3);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);


        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(30);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(210);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(30);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(210);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(3);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);

        game.getPlayers().getFirst().setScore(0);
        game.getPlayers().get(1).setScore(29);
        game.getPlayers().get(2).setScore(0);
        game.getPlayers().get(3).setScore(211);

        assertThat(game.calculateTeamScore(RE)).isEqualTo(29);
        assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(211);
        assertThat(game.calculateTeamPoints(RE)).isZero();
        assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(4);
        assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
    }
}
