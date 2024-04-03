/*
 * TCSS 305
 *
 * Craps Model that holds most of the logic for the game
 */
package Model;

import View.CrapsView;

import javax.swing.JOptionPane;

import java.util.Random;

/**
 * Represents a Craps Model
 *
 * @author Drew Brown
 * @version Autumn 2023
 */
public class Craps {
    /**
     * boolean representing if the game has started or not
     * */
    private boolean gameStarted;
    /**
     * integer representing the value of die 1
     * */
    private int die1;
    /**
     * integer representing the value of die 2
     * */
    private int die2;
    /**
     * integer representing the total of die 1 and die 2
     * */
    private int total;
    /**
     * integer representing the value of die 1 and die 2 after the first roll
     * */
    private int point;
    /**
     * integer representing the user bank account
     * */
    private int bankAccount;
    /**
     * integer representing how many wins the player has
     * */
    private int playerWins;
    /**
     * integer representing how many wins the house has
     * */
    private int houseWins;
    /**
     * boolean representing if the game is in progress
     * */
    private boolean gameInProgress;
    /**
     * CrapsView instance creating a new class view
     * */
    private CrapsView crapsView;
    /**
     * String representing the result of the game (who won)
     * */
    private String myResult;
    /**
     * integer representing the amount the user bet
     * */
    private int betAmount;

    /**
     * Craps constructor that sets the base values for starting fields
     * */
    public Craps() {
        bankAccount = 0;
        playerWins = 0;
        houseWins = 0;
        gameInProgress = false;
    }

    /**
     * Method that is called whenever the game starts
     **/
    public void startGame() {
        gameInProgress = true;
        crapsView.enableRollButton(true);
        crapsView.enablePlayAgainButton(false);
        resetGame();
        myResult = null;
    }

    /**
     * Method is called whenever the dice are rolled, handles game logic,
     * handles winners, and handles bank account
     * */
    public void roll() {
        die1 = rollDie();
        die2 = rollDie();
        total = die1 + die2;
        updateUI();

        if (gameInProgress) {
            if (point == 0) { //first roll
                if (total == 7 || total == 11) {
                    handleWin("Player Wins!", getBetAmount() * 2);
                } else if (total == 2 || total == 3 || total == 12) {
                    handleWin("House Wins!",  0); //(getBetAmount() * -1));
                } else {
                    point = total;
                }
            } else { //subsequent rolls
                if (total == 7) {
                    handleWin("House Wins!", 0); //(getBetAmount() * -1));
                } else if (total == point) {
                    handleWin("Player Wins!", getBetAmount() * 2);
                }
            }
            updateUI();
        }
    }

    /**
     * method is called in the roll method whenever there is a winner,
     * displays a message and edits the bank account
     *
     * @param resultMessage holds the message that will be shown
     * @param bankChange holds the integer that will change the bank amount
     * */
    private void handleWin(String resultMessage, int bankChange) {
        if (resultMessage.equalsIgnoreCase("player wins!")) {
            playerWins++;
        } else {
            houseWins++;
        }

        myResult = resultMessage;
        bankAccount += bankChange;
        crapsView.showWinnerDialog(resultMessage);
        point = 0;
        die1 = 0;
        die2 = 0;
        total = 0;
        myResult = null;
        gameInProgress = false;

        //if last bet is worth more than bank account then clear
        if(getBetAmount() > getBankAccount()) {
            crapsView.getAddBetAmountTextField().setText("0");
        }
    }

    /**
     * method called from the CrapsTest class, same as handleWin but
     * doesn't call showWinnerDialogue
     *
     * @param resultMessage holds the message that will be shown
     * @param bankChange holds the integer that will change the bank account
     * */
    public void handleWinForTestClass(String resultMessage, int bankChange) {
        if (resultMessage.equalsIgnoreCase("player wins!")) {
            playerWins++;
        } else {
            houseWins++;
        }

        myResult = resultMessage;
        bankAccount += bankChange;
        //crapsView.showWinnerDialog(resultMessage);
        point = 0;
        die1 = 0;
        die2 = 0;
        total = 0;
        myResult = null;
        gameInProgress = false;

        //if last bet is worth more than bank account then clear
        if(getBetAmount() > getBankAccount()) {
            crapsView.getAddBetAmountTextField().setText("0");
        }
    }

    /**
     * called whenever the game has concluded to a winner, resets necessary
     * values
     * */
    public void resetGame() {
        point = 0;
        die1 = 0;
        die2 = 0;
        total = 0;
        myResult = null;
        if(getBankAccount() > 0 || (getBankAccount() == 0 && getBetAmount() > 0)) {
            crapsView.enableRollButton(true);
            crapsView.enablePlayAgainButton(false);
            gameInProgress = true;
        } else {
            crapsView.enableRollButton(false);
            crapsView.enablePlayAgainButton(true);
            gameInProgress = false;
        }
        updateUI();
    }

    /**
     * is called whenever the whole session needs to reset, resets pretty much every
     * value
     * */
    public void refresh() { //resetter for controller
        point = 0;
        die1 = 0;
        die2 = 0;
        total = 0;
        playerWins = 0;
        houseWins = 0;
        myResult = null;
        bankAccount = 0;
        betAmount = 0;
        crapsView.getAddBetAmountTextField().setText("0");
        gameInProgress = false;
        crapsView.enableBetButtons(false);
        crapsView.enableRollButton(false);
        crapsView.enablePlayAgainButton(false);
        updateUI();
    }

    /**
     * called whenever the dice needs a random number
     *
     * @return int holding a number from 1 through 6
     * */
    private int rollDie() {
        return new Random().nextInt(6) + 1;
    }

    /**
     * takes the current crapsView and sets it to the local variable
     *
     * @param crapsView representing the current crapsView instance
     * */
    public void setCrapsView(CrapsView crapsView) {
        this.crapsView = crapsView;
    }

    /**
     * getter for the die 1 value
     *
     * @return integer that die 1 holds
     * */
    public int getDie1() {
        return die1;
    }

    /**
     * getter for the die 2 value
     *
     * @return integer that die 2 holds
     * */
    public int getDie2() {
        return die2;
    }

    /**
     * getter for the total value
     *
     * @return integer that total holds
     * */
    public int getTotal() {
        return total;
    }

    /**
     * getter for the point value
     *
     * @return integer that point holds
     * */
    public int getPoint() {
        return point;
    }

    /**
     * getter for the bankAccount value
     *
     * @return integer that bankAccount holds
     * */
    public int getBankAccount() {
        return bankAccount;
    }

    /**
     * getter for the playerWins value
     *
     * @return integer that playerWins holds
     * */
    public int getPlayerWins() {
        return playerWins;
    }

    /**
     * getter for the betAmount value
     *
     * @return integer that betAmount holds
     * */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * getter for the houseWins value
     *
     * @return integer that houseWins holds
     * */
    public int getHouseWins() {
        return houseWins;
    }

    /**
     * setter for the gameInProgress value
     *
     * @param enable boolean representing the state of gameInProgress
     * */
    private void setGameInProgress(boolean enable) {
        gameInProgress = enable;
    }

    /**
     * getter for the value gameInProgress
     *
     * @return gameInProgress
     * */
    public boolean isGameInProgress() {
        return gameInProgress;
    }

    /**
     * setter for the bankAccount field
     *
     * @param bankAmount integer representing the new bankAmount
     * */
    public void setBankAmount(int bankAmount) {
        this.bankAccount = bankAmount;
    }

    /**
     * setter for the betAmount field
     *
     * @param betAmount integer representing the new betAmount
     * */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    /** Calls the updateUI method in crapsView*/
    public void updateUI() {
        crapsView.updateUI();
    }

    /** Calls the showAboutDialogue method in crapsView*/
    public void showAboutDialog() {
        crapsView.showAboutDialog();
    }

    /** Calls the showRulesDialogue method in crapsView*/
    public void showRulesDialog() {
        crapsView.showRulesDialog();
    }

    /**
     * getter for the myResult field
     *
     * @return myResult
     * */
    public String getResult() {
        return myResult;
    }
}
