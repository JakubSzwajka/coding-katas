package BowlingKata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BowlingGameTest {

    BowlingGame bowlingGame;

    @BeforeEach
    void beforeAll() {
        this.bowlingGame = new BowlingGame();
    }

    @Test
    void shouldReturnScore0WhenNoRolls(){
        assertThat(bowlingGame.getScore()).isEqualTo(0);
    }

    @Test
    void shouldReturnScoreOneWhenOneRollWithOnePinDown() throws BowlingGameException {
        bowlingGame.roll(1);
        assertThat(bowlingGame.getScore()).isEqualTo(1);
    }

    @Test
    void shouldReturnScore10After2RollsOf5PinsDown() throws BowlingGameException {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        assertThat(bowlingGame.getScore()).isEqualTo(10);
    }

    @Test
    void shouldRaiseExceptionWhenMoreThen10PinsDown(){
        assertThatExceptionOfType(BowlingGameException.class).isThrownBy(() -> {
            bowlingGame.roll(14);
        });
    }

    @Test
    void shouldReturnFirstFrameNumberWhenNoRolls(){
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(1);
    }

    @Test
    void shouldReturnFirstFrameNumberAfterOneRoll() throws BowlingGameException {
        bowlingGame.roll(5);
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(1);
    }

    @Test
    void shouldReturnSecondFrameNumberAfterTwoRolls3PinsEvery() throws BowlingGameException {
        bowlingGame.roll(3);
        bowlingGame.roll(3);
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(2);
    }

    void shouldReturn7thFrameNumberAfter15Rolls2PinsEvery() throws BowlingGameException {
        for(int i=0; i<15; i++){
            bowlingGame.roll(2);
        }
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(7);
    }

    @Test
    void shouldReturn21ScoreAfter7Rolls3PinsEach() throws BowlingGameException {
        for(int i=0 ; i<7 ; i++){
            bowlingGame.roll(3);
        }
        assertThat(bowlingGame.getScore()).isEqualTo(21);
    }

    @Test
     void shouldReturn10AfterRunningFrame55() throws BowlingGameException {
        bowlingGame.runFrame("55");
        assertThat(bowlingGame.getScore()).isEqualTo(10);
    }

    @Test
    void shouldReturn26AfterStrikeAnd2Rolls4PinsEach() throws BowlingGameException {
        bowlingGame.roll("X");
        bowlingGame.roll(4);
        bowlingGame.roll(4);
        assertThat(bowlingGame.getScore()).isEqualTo(26);
    }

//    @Test
//    void shouldReturnStrikeStateAfterX() throws BowlingGameException {
//        bowlingGame.roll("X");
//        assertThat(bowlingGame.getStatus()).isEqualTo('X');
//    }

//    @Test
//    void shouldReturnSpareStateAfterSpareSign() throws BowlingGameException {
//        bowlingGame.roll("/");
//        assertThat(bowlingGame.getStatus()).isEqualTo('/');
//    }

    @Test
    void  shouldReturn10PointsAfterStrikeOnly() throws BowlingGameException {
        bowlingGame.roll("X");
        assertThat(bowlingGame.getScore()).isEqualTo(10);
    }

    @Test
    void  shouldReturn20PointsAfter5Spare5() throws BowlingGameException {
        bowlingGame.roll(5);
        bowlingGame.roll("/");
        bowlingGame.roll(5);
        assertThat(bowlingGame.getScore()).isEqualTo(20);
    }

    @Test
    void shouldReturn60After3Strike() throws BowlingGameException {
        bowlingGame.roll("X");
        bowlingGame.roll("X");
        bowlingGame.roll("X");
        assertThat(bowlingGame.getScore()).isEqualTo(60);
    }

    @Test
    void twelveStrikesShouldReturn300Points() throws  BowlingGameException{
        for( int i = 0 ; i < 12 ; i++ ){
            bowlingGame.roll("X");
        }
        assertThat(bowlingGame.getScore()).isEqualTo(300);
    }

    @Test
    void tenPairsOf9AndMissShouldReturn90() throws BowlingGameException {
        for( int i = 0; i < 10 ; i++ ){
            bowlingGame.roll(9);
            bowlingGame.roll("-");
        }
        assertThat(bowlingGame.getScore()).isEqualTo(90);
    }

    @Test
    void tenPairsOf5AndSparePlus5ShouldReturn150() throws BowlingGameException {
        for( int i = 0; i < 10 ; i++ ){
            bowlingGame.roll(5);
            bowlingGame.roll("/");
        }
        bowlingGame.roll(5);
        assertThat(bowlingGame.getScore()).isEqualTo(150);
    }
}
