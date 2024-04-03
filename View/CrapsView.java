/*
 * TCSS 305
 *
 * Craps View that establishes all the UI
 */
package View;

import Controller.CrapsController;
import Model.Craps;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a Craps View that extends from JFrame
 *
 * @author Drew Brown
 * @version Autumn 2023
 */
public class CrapsView extends JFrame {

    /**
     * Holds an instance of the Craps Model
     * */
    private Craps craps;

    /** Holds an instance of the Craps Controller */
    private CrapsController crapsController;

    /**
     * JBUtton representing a roll dice button
     * */
    private JButton rollButton;
    /**
     * JBUtton representing a play again button
     * */
    private JButton playAgainButton;
    /**
     * JBUtton representing a set bank button
     * */
    private JButton setBankButton;

    /**
    * JLabel representing die 1
    * */
    private JLabel die1Label;
    /**
     * JLabel representing die 2
     * */
    private JLabel die2Label;
    /**
     * JLabel representing the total of the 2 dice
     * */
    private JLabel totalLabel;
    /**
     * JLabel representing the point (first roll)
     * */
    private JLabel pointLabel;
    /**
     * JLabel representing who the winner is
     * */
    private JLabel resultStatusLabel;
    /**
     * JLabel representing the bank total
     * */
    private JLabel bankLabel;
    /**
     * JLabel representing how many wins the player has
     * */
    private JLabel playerWins;

    /** JLabel representing how many wins the house has*/
    private JLabel houseWins;
    /**
     * JTextfield for the user to enter a bet amount
     * */
    private JTextField addBetText;
    /**
     * JLabel representing the "Total Bet: " text
     * */
    private JLabel addBetLabel;
    /**
     * JButton representing the bet five button
     * */
    private JButton betFive;
    /**
     * JButton representing the bet ten button
     * */
    private JButton betTen;
    /** JButton representing the bet twenty button*/
    private JButton betTwenty;
    /**
     * JButton representing the bet fifty button
     * */
    private JButton betFifty;
    /**
     * JButton representing the clear bet button
     * */
    private JButton clearBet;
    /**
     * JButton representing the ALL IN bet button
     * */
    private JButton allIn;
    /** JMenuItem representing the start game button*/
    private JMenuItem startMenuItem;
    /** JMenuItem representing the reset session option*/
    private JMenuItem resetMenuItem;
    /** JMenuItem representing the exit game option*/
    private JMenuItem exitMenuItem;
    /**
     * JMenuItem representing the about section in the menu
     * */
    private JMenuItem aboutMenuItem;
    /**
     * JMenuItem representing the rules section in the menu
     * */
    private JMenuItem rulesMenuItem;

    /**
     * Craps view explicit constructor that builds the initial UI
     * for the craps game.
     *
     * @param craps passed in the current model.Craps instance */
    public CrapsView(Craps craps) {
        this.craps = craps;

        setTitle("Craps Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rollButton = new JButton("Roll Dice");
        rollButton.setMnemonic('R');
        rollButton.setToolTipText("ALT + R");

        playAgainButton = new JButton("Play");
        playAgainButton.setMnemonic('P');
        playAgainButton.setToolTipText("ALT + P");

        setBankButton = new JButton("Set Bank");
        playerWins = new JLabel("Player Wins: " + craps.getPlayerWins());
        houseWins = new JLabel("House Wins: " + craps.getHouseWins());
        die1Label = new JLabel("Die 1: ");
        die2Label = new JLabel("Die 2: ");
        totalLabel = new JLabel("Total: ");
        pointLabel = new JLabel("Point: ");
        resultStatusLabel = new JLabel();
        bankLabel = new JLabel("Bank Account: $" + craps.getBankAccount());
        addBetLabel = new JLabel("                                          " +
                                         "                            Total Bet:");
        addBetText = new JTextField("0");
        betFive = new JButton("+$5");
        betTen = new JButton("+$10");
        betTwenty = new JButton("+$20");
        betFifty = new JButton("+$50");
        clearBet = new JButton("CLEAR BET");
        allIn = new JButton("ALL IN");


        setLayout(new GridLayout(10, 4));
        add(createMenuBar());
        add(rollButton);
        add(playAgainButton);
        add(setBankButton);
        add(die1Label);
        add(houseWins);
        add(die2Label);
        add(playerWins);
        add(totalLabel);
        add(pointLabel);
        add(resultStatusLabel);
        add(bankLabel);
        add(addBetLabel);
        add(addBetText);
        add(betFive);
        add(betTen);
        add(betTwenty);
        add(betFifty);
        add(clearBet);
        add(allIn);

        playAgainButton.setEnabled(false);
        rollButton.setEnabled(false);
        enableBetButtons(false);

        updateUI();

        setVisible(true);

        craps.setCrapsView(this);
        craps.startGame();

        crapsController = new CrapsController(craps, this);
    }

    /**
     * Creates a new Main JMenu bar with different options
     *
     * @return menuBar representing a new JMenuBar with the added options
     * */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        startMenuItem = new JMenuItem("Start");
        resetMenuItem = new JMenuItem("Reset Session");
        exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(startMenuItem);
        gameMenu.add(resetMenuItem);
        gameMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        rulesMenuItem = new JMenuItem("Rules");
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    /**
     * Getter for the 5 dollar bet button
     *
     * @return JButton betFive instance
     * */
    public JButton getBetFiveButton() {
        return betFive;
    }

    /**
     * Getter for the 10 dollar bet button
     *
     * @return JButton betTen instance
     * */
    public JButton getBetTenButton() {
        return betTen;
    }

    /**
     * Getter for the 20 dollar bet button
     *
     * @return JButton betTwenty instance
     * */
    public JButton getBetTwentyButton() {
        return betTwenty;
    }

    /**
     * Getter for the 50 dollar bet button
     *
     * @return JButton betFifty instance
     * */
    public JButton getBetFiftyButton() {
        return betFifty;
    }

    /**
     * Getter for the JButton representing the Set Bank button
     *
     * @return setBankButton JButton for setBank
     * */
    public JButton getSetBankButton() {
        return setBankButton;
    }

    /**
     * Getter for the JButton roll button
     *
     * @return rollButton JButton
     * */
    public JButton getRollButton() {
        return rollButton;
    }

    /**
     * Getter for the JButton play again button
     *
     * @return playAgainButton JButton
     * */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    /**
     * Getter for the JMenuItem start option
     *
     * @return startMenuItem JMenuItem
     * */
    public JMenuItem getStartMenuItem() {
        return startMenuItem;
    }

    /**
     * Getter for the Clear Bet JButton
     *
     * @return clearBet JButton
     * */
    public JButton getClearBet() {
        return clearBet;
    }

    /**
     * Getter for the All In Bet JButton
     *
     * @return clearBet JButton
     * */
    public JButton getAllIn() {
        return allIn;
    }

    /**
     * Getter for the Reset Session Menu option
     *
     * @return resetMenuItem JMenuItem
     * */
    public JMenuItem getResetMenuItem() {
        return resetMenuItem;
    }

    /**
     * Getter for the exit option in the menu
     *
     * @return exitMenuItem JMenuItem
     * */
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    /**
     * Getter for the exit option in the menu
     *
     * @return exitMenuItem JMenuItem
     * */
    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    /**
     * Getter for the rules option in the menu
     *
     * @return rulesMenuItem JMenuItem
     * */
    public JMenuItem getRulesMenuItem() {
        return rulesMenuItem;
    }

    /**
     * Getter for the JTextField add bet
     *
     * @return addBetText JTextField
     * */
    public JTextField getAddBetAmountTextField() {
        return addBetText;
    }

    /**
     * Getter for the text in the addBetText JTextField
     *
     * @return String representing the text in the JTextField
     * */
    public String getAddBetText() {
        return addBetText.getText();
    }

    /**
     * Method called to update the UI mainly based on what the
     * current state of the game is
     * */
    public void updateUI() {
        if (craps != null) {
            die1Label.setText("Die 1: " + craps.getDie1());
            die2Label.setText("Die 2: " + craps.getDie2());
            totalLabel.setText("Total: " + craps.getTotal());
            pointLabel.setText("Point: " + craps.getPoint());
            resultStatusLabel.setText(craps.getResult());
            bankLabel.setText("Bank Account: $" + craps.getBankAccount());
            playerWins.setText("Player Wins: " + craps.getPlayerWins());
            houseWins.setText("House Wins: " + craps.getHouseWins());
            enableBetButtons(!craps.isGameInProgress() && craps.getBankAccount() > 0 &&
                             (craps.getPlayerWins() > 0 || craps.getHouseWins() > 0));

            rollButton.setEnabled(craps.isGameInProgress());
            playAgainButton.setEnabled(!craps.isGameInProgress() && craps.getBankAccount() > 0 &&
                                        (craps.getPlayerWins() > 0 || craps.getHouseWins() > 0));
            setBankButton.setEnabled(!craps.isGameInProgress());
            startMenuItem.setEnabled(!craps.isGameInProgress() && (craps.getPlayerWins() == 0 && craps.getHouseWins() == 0));
        }
    }

    /**
     *  Method that holds the dialogue for the About JMenuItem
     * */
    public void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "Craps Game\nVersion 1.0\nJava Version: " +
            System.getProperty("java.version") + "\nAuthor: Drew Brown", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method that holds the dialogue for the rules JMenuItem
     * */
    public void showRulesDialog() {
        String rules = "Rules of the Game of Craps:\n\n" +
            "1. A player rolls two dice where each die has six faces in the usual way (values 1 through 6).\n" +
            "2. After the dice have come to rest, the sum of the two upward faces is calculated.\n" +
            "3. The first roll/throw:\n" +
            "   - If the sum is 7 or 11, the player wins.\n" +
            "   - If the sum is 2, 3, or 12, the player loses (the house wins).\n" +
            "   - If the sum is 4, 5, 6, 8, 9, or 10, that sum becomes the player's 'point'.\n" +
            "4. Continue rolling given the player's point:\n" +
            "   - The player must roll the 'point' total before rolling a 7 to win.\n" +
            "   - If they roll a 7 before rolling the point value, the player loses (the house wins).";

        JOptionPane.showMessageDialog(this, rules, "Rules of the Game", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method that gets called when there is a winner
     *
     * @param winnerMessage holding the string for who won
     * */
    public void showWinnerDialog(String winnerMessage) {
        JOptionPane.showMessageDialog(this, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        enablePlayAgainButton(true);
        enableBetButtons(true);
        enableRollButton(false);
        updateUI();
    }

    /**
     * setter to state the status of the roll button
     *
     * @param enable boolean representing true/false roll button state
     * */
    public void enableRollButton(boolean enable) {
        rollButton.setEnabled(enable);
    }

    /**
     * setter to state the status of the play button
     *
     * @param enable boolean representing true/false play button state
     * */
    public void enablePlayAgainButton(boolean enable) {
        playAgainButton.setEnabled(enable);
    }

    /**
     * setter to state the status of the bet buttons
     *
     * @param enable boolean representing true/false bet buttons state
     * */
    public void enableBetButtons(boolean enable) {
        betFive.setEnabled(enable);
        betTen.setEnabled(enable);
        betTwenty.setEnabled(enable);
        betFifty.setEnabled(enable);
        addBetText.setEnabled(enable);
        clearBet.setEnabled(enable);
        allIn.setEnabled(enable);
    }

    /**
     * setter to state the status of the start JMenuItem option
     *
     * @param enable boolean representing true/false start option state
     * */
    public void enableStartButton(boolean enable) {
        getStartMenuItem().setEnabled(enable);
    }

    /**
     * main method for the entirety of the program
     *
     * @param args Main method param
     * */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Craps craps = new Craps();
            CrapsView crapsView = new CrapsView(craps);
            craps.setCrapsView(crapsView);
        });
    }
}