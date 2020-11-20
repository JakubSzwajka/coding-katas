package BowlingKata;

import java.security.KeyPair;

public class BowlingGame {

    private int score;
    private char status;
    // could be:
    // "/" for spare
    // "X" for strike

    private int[][] frames = new int[13][2];
    private int[] points = new int[13];

    private int[] currentFrame = new int[2];
    private int currentFrameNum = 1;

    public BowlingGame(){
        this.status = ' ';
    }

    public int getScore() {
        int totalScore = 0;

        for( int i = 10; i >= 0 ; i-- ){
            points[i] = this.getPointsFromFrame(i);
        }

        for( int j = 0; j < points.length; j++){
            System.out.println("Frame " + j + "Points: " + points[j]);
            totalScore += points[j];
        }

        return totalScore;
    }

    private int getPointsFromFrame( int frameNum ){
        int points = 0;

        if (this.checkForSpare(frameNum)){
            points += this.getNextRollPoints(frameNum);

        }else if(this.checkForStrike(frameNum)){
            if(this.checkForStrike(frameNum + 1)){
                points += this.getNextRollPoints(frameNum);
                points += this.frames[frameNum + 2][0];
            }
        }
        points += this.frames[frameNum][0] + this.frames[frameNum][1];
        return  points;
    }

    private int getNextRollPoints(int frame){
        return this.frames[frame + 1][0];
    }

    private int getNextTwoRollPoints(int frame){
        return points[frame + 1];
    }

    private void strike() throws BowlingGameException{
        this.roll(10);
        this.currentFrameNum += 1;
    }

    private void spare() throws  BowlingGameException {
        int points = 10 - this.frames[this.currentFrameNum][0];
        this.roll(points);
    }

    public void roll(String strikeOrSpare) throws  BowlingGameException {
        if (strikeOrSpare == "X"){
            this.strike();
        }else if( strikeOrSpare == "/"){
            this.spare();
        }
    }

    public void roll(int pins) throws BowlingGameException {

        if (pins > 10){throw new BowlingGameException("There is only 10 Pins");}

        if (this.frames[this.currentFrameNum][0] == 0){
            this.frames[this.currentFrameNum][0] = pins;
        }else{
            this.frames[this.currentFrameNum][1] = pins;
            this.currentFrameNum += 1;
        }
    }

    private boolean checkForSpare( int frame){
        return this.frames[frame][0] + this.frames[frame][1] == 10
                && this.frames[frame][0] != 10;
    }

    private boolean checkForStrike(int frame ){
        return this.frames[frame][0] == 10;
    }

    public void runFrame( String frame ) throws BowlingGameException {
        for(int i=0; i<2;i++){
            int x = (int)frame.charAt(i) - '0';
            if ( x > 0 && x < 10){
                this.roll(x);
            }else if( frame.charAt(i) == 'X' &&
                     (frame.charAt(i) == '/' &&
                     (frame.charAt(i) == '-'))){
                this.roll( frame.charAt(i));
            }
        }
    }

    public int getFrameNumber() {
        return this.currentFrameNum;
    }
}
