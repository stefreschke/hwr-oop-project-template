package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.RE;
import static org.assertj.core.api.Assertions.assertThat;

class PointsTest {
  @Test
  void ReGot121PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(121);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(119);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(121);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(119);
    assertThat(game.calculateTeamPoints(RE)).isEqualTo(1);
    assertThat(game.calculateTeamPoints(CONTRA)).isZero();
    assertThat(game.findWinningTeam()).isEqualTo(RE);
  }

  @Test
  void ReGot210PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(210);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(30);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(210);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(30);
    assertThat(game.calculateTeamPoints(RE)).isEqualTo(3);
    assertThat(game.calculateTeamPoints(CONTRA)).isZero();
    assertThat(game.findWinningTeam()).isEqualTo(RE);
  }

  @Test
  void ReGot211PointsTest() {

    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(211);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(29);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(211);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(29);
    assertThat(game.calculateTeamPoints(RE)).isEqualTo(4);
    assertThat(game.calculateTeamPoints(CONTRA)).isZero();
    assertThat(game.findWinningTeam()).isEqualTo(RE);
  }

  @Test
  void ReGot181PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(181);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(59);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(181);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(59);
    assertThat(game.calculateTeamPoints(RE)).isEqualTo(3);
    assertThat(game.calculateTeamPoints(CONTRA)).isZero();
    assertThat(game.findWinningTeam()).isEqualTo(RE);
  }

  @Test
  void ContraGot120PointsTest() {
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
  }

  @Test
  void ContraGot240PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");
    game.getPlayers().getFirst().setScore(0);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(240);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isZero();
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(240);
    assertThat(game.calculateTeamPoints(RE)).isZero();
    assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(5);
    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }

  @Test
  void ContraGot150PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(90);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(150);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(90);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(150);
    assertThat(game.calculateTeamPoints(RE)).isZero();
    assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(1);
    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }

  @Test
  void ContraGot151PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(89);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(151);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(89);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(151);
    assertThat(game.calculateTeamPoints(RE)).isZero();
    assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(2);
    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }

  @Test
  void ContraGot180PointsTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.getPlayers().getFirst().setScore(60);
    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setScore(0);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setScore(0);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setScore(180);
    game.getPlayers().get(3).setTeam(CONTRA);

    assertThat(game.calculateTeamScore(RE)).isEqualTo(60);
    assertThat(game.calculateTeamScore(CONTRA)).isEqualTo(180);
    assertThat(game.calculateTeamPoints(RE)).isZero();
    assertThat(game.calculateTeamPoints(CONTRA)).isEqualTo(2);
    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }
}
