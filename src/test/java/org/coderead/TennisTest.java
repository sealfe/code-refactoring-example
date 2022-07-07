package org.coderead;

import org.coderead.tennis.TennisGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author by fengww
 * @Classname TennisTest
 * @Description
 * @Date 2022/7/7 23:33
 */
public class TennisTest {


    private int selfPlayer;

    private int rivalPlayer;

    @BeforeEach
    void beforeEach() {
        rivalPlayer = 0;
        selfPlayer = 0;
    }

    @Test
    void test_advantage_if_all_are_zero() {
        TennisGame tennisGame = getTennisGame(selfPlayer,rivalPlayer);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    @Test
    void test_advantage_if_one_zero_and_one_less_three() {
        TennisGame tennisGame = getTennisGame(0, 1);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    private TennisGame getTennisGame(int selfPlayer, int rivalPlayer) {
        TennisGame tennisGame = new TennisGame();
        tennisGame.setRivalPlayer(rivalPlayer);
        tennisGame.setSelfPlayer(selfPlayer);
        return tennisGame;
    }

    @Test
    void test_advantage_if_all_less_three() {
        TennisGame tennisGame = getTennisGame(2, 1);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    @Test
    void test_advantage_if_all_less_three_and_eq() {
        TennisGame tennisGame = getTennisGame(2, 2);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }


    @Test
    void test_advantage_if_one_zero_and_one_ge_three() {
        TennisGame tennisGame = getTennisGame(0, 4);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    @Test
    void test_advantage_if_one_less_three_and_one_ge_three() {
        TennisGame tennisGame = getTennisGame(1, 4);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    @Test
    void test_advantage_if_all_ge_three_and_self_less() {
        TennisGame tennisGame = getTennisGame(4, 5);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }

    @Test
    void test_advantage_if_all_ge_three_and_eq() {
        TennisGame tennisGame = getTennisGame(5, 5);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }


    @Test
    void test_advantage_if_all_ge_three_and_self_more_than_two() {
        TennisGame tennisGame = getTennisGame(7, 5);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isFalse();
    }
    @Test
    void test_advantage_if_all_ge_three_and_self_more_than_one_and_rival_is_three() {
        TennisGame tennisGame = getTennisGame(4, 3);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isTrue();
    }


    @Test
    void test_advantage_if_all_ge_three_and_self_more_than_one() {
        TennisGame tennisGame = getTennisGame(6, 5);
        Boolean result = tennisGame.isAdvantage();
        assertThat(result).isTrue();
    }





    @Test
    void test_duece_and_all_are_zero(){
        TennisGame tennisGame = getTennisGame(selfPlayer, rivalPlayer);
        Boolean result = tennisGame.isDuece();
        assertThat(result).isFalse();
    }

    @Test
    void test_duece_and_are_diff(){
          TennisGame tennisGame = getTennisGame(10, 11);
          Boolean result = tennisGame.isDuece();
        assertThat(result).isFalse();
    }

     @Test
    void test_duece_and_all_are_eq_and_less_three(){
         TennisGame tennisGame = getTennisGame(2, 2);
         Boolean result = tennisGame.isDuece();
        assertThat(result).isFalse();
    }

     @Test
    void test_duece_and_all_are_eq_and_more_than_three(){
         TennisGame tennisGame = getTennisGame(4, 4);
         Boolean result = tennisGame.isDuece();
        assertThat(result).isTrue();
    }
     @Test
    void test_duece_and_all_are_eq_and_all_are_three(){
         TennisGame tennisGame = getTennisGame(3, 3);
         Boolean result = tennisGame.isDuece();
        assertThat(result).isTrue();
    }



    @Test
    void test_win_and_all_are_zero(){
        TennisGame tennisGame = getTennisGame(selfPlayer, rivalPlayer);
        Boolean result = tennisGame.isWin();
        assertThat(result).isFalse();
    }

     @Test
    void test_win_and_self_less_four(){
         TennisGame tennisGame = getTennisGame(2, 1);
         Boolean result = tennisGame.isWin();
        assertThat(result).isFalse();
    }

  @Test
    void test_win_and_self_ge_four_and_more_than_rival_less_two(){
      TennisGame tennisGame = getTennisGame(4, 3);
      Boolean result = tennisGame.isWin();
        assertThat(result).isFalse();
    }

    @Test
    void test_win_and_self_ge_four_and_more_than_rival_are_two(){
        TennisGame tennisGame = getTennisGame(4, 2);
        Boolean result = tennisGame.isWin();
        assertThat(result).isTrue();
    }

      @Test
    void test_win_and_self_ge_four_and_more_than_rival_ge_two(){
          TennisGame tennisGame = getTennisGame(4, 1);
          Boolean result = tennisGame.isWin();
        assertThat(result).isTrue();
    }


    @Test
    void test_result_all_are_false(){
        TennisGame tennisGame = Mockito.spy(new TennisGame());
        Mockito.doReturn(false).when(tennisGame).isWin();
        Mockito.doReturn(false).when(tennisGame).isDuece();
        Mockito.doReturn(false).when(tennisGame).isAdvantage();
        assertThat(tennisGame.getResult()).isEqualTo("");
    }




    @Test
    void test_result_if_advantage_is_true(){
        TennisGame tennisGame = Mockito.spy(new TennisGame());
        Mockito.doReturn(false).when(tennisGame).isWin();
        Mockito.doReturn(false).when(tennisGame).isDuece();
        Mockito.doReturn(true).when(tennisGame).isAdvantage();
        assertThat(tennisGame.getResult()).isEqualTo("Advantage");
    }


       @Test
    void test_result_if_win_is_true(){
        TennisGame tennisGame = Mockito.spy(new TennisGame());
        Mockito.doReturn(true).when(tennisGame).isWin();
        Mockito.doReturn(false).when(tennisGame).isDuece();
        Mockito.doReturn(false).when(tennisGame).isAdvantage();
        assertThat(tennisGame.getResult()).isEqualTo("Win");
    }

  @Test
    void test_result_if_duece_is_true(){
        TennisGame tennisGame = Mockito.spy(new TennisGame());
        Mockito.doReturn(false).when(tennisGame).isWin();
        Mockito.doReturn(true).when(tennisGame).isDuece();
        Mockito.doReturn(false).when(tennisGame).isAdvantage();
        assertThat(tennisGame.getResult()).isEqualTo("Duece");
    }
























}
