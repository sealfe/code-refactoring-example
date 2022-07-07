package org.coderead.tennis;

/**
 * @author by fengww
 * @Classname TennisGame
 * @Description
 * @Date 2022/7/7 23:34
 */
public class TennisGame {


    private int selfPlayer;

    private int rivalPlayer;


    public void setSelfPlayer(int selfPlayer) {
        this.selfPlayer = selfPlayer;
    }

    public void setRivalPlayer(int rivalPlayer) {
        this.rivalPlayer = rivalPlayer;
    }

    public Boolean isAdvantage() {
        if(selfPlayer <3){
            return false;
        }
        if(rivalPlayer <3){
            return false;
        }
        if(selfPlayer - rivalPlayer !=1){
            return false;
        }
        return true;
    }

    public Boolean isDuece() {
        if(this.selfPlayer != this.rivalPlayer){
            return false;
        }
        if(this.selfPlayer <3){
            return false;
        }
        return true;
    }

    public Boolean isWin() {
        if(this.selfPlayer <4){
            return false;
        }
        if(this.selfPlayer - this.rivalPlayer <2){
            return false;
        }
        return true;
    }


    public String getResult() {
        if(isAdvantage()){
            return "Advantage";
        }
        if(isWin()){
            return "Win";
        }
        if(isDuece()){
            return "Duece";
        }
        return "";
    }
}
