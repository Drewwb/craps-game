/*
 * TCSS 305
 *
 * Craps Controller that holds the UI logic
 */
package Controller;

import Model.Craps;
import View.CrapsView;

import javax.swing.JOptionPane;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a Craps Controller
 *
 * @author Drew Brown
 * @version Autumn 2023
 */
public class CrapsController {
    /** field to hold an instance of Model.Craps value*/
    private Craps craps;
    /** field to hold an instance of View.CrapsView value*/
    private CrapsView crapsView;

    /**
     * explicit constructor for the CrapsController Class
     *
     * @param craps the current craps instance
     * @param crapsView the current crapsView instance
     * */
    public CrapsController(Craps craps, CrapsView crapsView) {
        this.craps = craps;
        this.crapsView = crapsView;

        crapsView.getRollButton().addActionListener(new RollButtonListener());
        crapsView.getPlayAgainButton().addActionListener(new PlayAgainButtonListener());
        crapsView.getSetBankButton().addActionListener(new SetBankButtonListener());
        crapsView.getBetFiveButton().addActionListener(new BetFiveItemListener());
        crapsView.getBetTenButton().addActionListener(new BetTenItemListener());
        crapsView.getBetTwentyButton().addActionListener(new BetTwentyItemListener());
        crapsView.getBetFiftyButton().addActionListener(new BetFiftyItemListener());
        crapsView.getStartMenuItem().addActionListener(new StartMenuItemListener());
        crapsView.getResetMenuItem().addActionListener(new ResetMenuItemListener());
        crapsView.getExitMenuItem().addActionListener(new ExitMenuItemListener());
        crapsView.getAboutMenuItem().addActionListener(new AboutMenuItemListener());
        crapsView.getRulesMenuItem().addActionListener(new RulesMenuItemListener());
        crapsView.getClearBet().addActionListener(new ClearBetItemListener());
        crapsView.getAllIn().addActionListener(new AllInItemListener());
        crapsView.updateUI();
    }


    /**
     * ActionListener for the rollButton implements ActionListener
     * */
    private class RollButtonListener implements ActionListener {
        /**
         * actionPerformed is called whenever rollButton is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            String betText = crapsView.getAddBetText();
            int betAmount = Integer.parseInt(betText);
            craps.setBetAmount(betAmount);
            craps.roll();
            crapsView.updateUI();
        }

    }

    /**
     * ActionListener for the playButton implements ActionListener
     * */
    private class PlayAgainButtonListener implements ActionListener {
        /**
         * actionPerformed is called whenever playButton is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = crapsView.getAddBetAmountTextField().getText();
            try {
                int betAmount = Integer.parseInt(input);
                if (betAmount <= 0 || betAmount > craps.getBankAccount()) {
                    JOptionPane.showMessageDialog(crapsView,
                        "Invalid Bet. Please enter a valid number.");
                } else {
                    craps.setBetAmount(betAmount);
                    craps.setBankAmount(craps.getBankAccount() - betAmount);
                    crapsView.updateUI();
                    craps.resetGame();
                    craps.startGame();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(crapsView,
                    "Invalid Bet. Please enter a valid number.");
            }
        }
    }

    /**
     * ActionListener for the setBankButton implements ActionListener
     * */
    private class SetBankButtonListener implements ActionListener {
        /**
         * actionPerformed is called whenever setBankButton is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            String input =
                JOptionPane.showInputDialog(crapsView, "Enter the initial bank amount:");

            try {
                int bankAmount = Integer.parseInt(input);
                if (bankAmount <= 0) {
                    JOptionPane.showMessageDialog(crapsView,
                        "Invalid input. Please enter a valid number.");
                } else {
                    craps.setBankAmount(bankAmount);
                    crapsView.updateUI();

                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(crapsView,
                    "Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * ActionListener for the start JMenuItem implements ActionListener
     * */
    private class StartMenuItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever start is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (craps.getBankAccount() <= 0) {
                JOptionPane.showMessageDialog(crapsView,
                    "Cannot start a game with a bank account of 0.");
            } else {
                craps.startGame();
                crapsView.enablePlayAgainButton(true);
                crapsView.enableRollButton(false);
                crapsView.enableBetButtons(true);
                crapsView.getStartMenuItem().setEnabled(false);
                crapsView.getSetBankButton().setEnabled(true);
            }

        }
    }

    /**
     * ActionListener for the reset JMenuItem implements ActionListener
     * */
    private class ResetMenuItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever reset is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            craps.refresh();
            crapsView.getStartMenuItem().setEnabled(true);
        }
    }

    /**
     * ActionListener for the exit JMenuItem implements ActionListener
     * */
    public class ExitMenuItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever exit is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            int response =
                JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * ActionListener for the about JMenuItem implements ActionListener
     * */
    private class AboutMenuItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever about is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            crapsView.showAboutDialog();
        }
    }

    /**
     * ActionListener for the rules JMenuItem implements ActionListener
     * */
    private class RulesMenuItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever rules is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            crapsView.showRulesDialog();
        }
    }

    /**
     * ActionListener for the betFive JButton implements ActionListener
     * */
    private class BetFiveItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever betFive is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(crapsView.getAddBetAmountTextField().getText().isEmpty()){
                    craps.setBetAmount(0);
                    craps.setBetAmount(craps.getBetAmount() + 5);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                } else {
                    craps.setBetAmount(
                        Integer.parseInt(crapsView.getAddBetAmountTextField().getText()));
                    craps.setBetAmount(craps.getBetAmount() + 5);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(crapsView,
                    "ERROR Total Bet is invalid");
            }
        }
    }

    /**
     * ActionListener for the betTen JButton implements ActionListener
     * */
    private class BetTenItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever betTen is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(crapsView.getAddBetAmountTextField().getText().isEmpty()){
                    craps.setBetAmount(0);
                    craps.setBetAmount(craps.getBetAmount() + 10);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                } else {
                    craps.setBetAmount(
                        Integer.parseInt(crapsView.getAddBetAmountTextField().getText()));
                    craps.setBetAmount(craps.getBetAmount() + 10);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(crapsView,
                    "ERROR Total Bet is invalid");
            }
        }
    }

    /**
     * ActionListener for the betTwenty JButton implements ActionListener
     * */
    private class BetTwentyItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever betTwenty is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(crapsView.getAddBetAmountTextField().getText().isEmpty()){
                    craps.setBetAmount(0);
                    craps.setBetAmount(craps.getBetAmount() + 20);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                } else {
                    craps.setBetAmount(
                        Integer.parseInt(crapsView.getAddBetAmountTextField().getText()));
                    craps.setBetAmount(craps.getBetAmount() + 20);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(crapsView,
                    "ERROR Total Bet is invalid");
            }
        }
    }

    /**
     * ActionListener for the betFifty JButton implements ActionListener
     * */
    private class BetFiftyItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever betFifty is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(crapsView.getAddBetAmountTextField().getText().isEmpty()){
                    craps.setBetAmount(0);
                    craps.setBetAmount(craps.getBetAmount() + 50);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                } else {
                    craps.setBetAmount(
                        Integer.parseInt(crapsView.getAddBetAmountTextField().getText()));
                    craps.setBetAmount(craps.getBetAmount() + 50);
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(crapsView,
                    "ERROR Total Bet is invalid");
            }
        }
    }

    /**
     * ActionListener for the clearBet JButton implements ActionListener
     * */
    private class ClearBetItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever clearBet is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            craps.setBetAmount(0);
            crapsView.getAddBetAmountTextField().setText("0");
            crapsView.updateUI();
            crapsView.enableBetButtons(true);
            crapsView.enablePlayAgainButton(true);
            crapsView.enableRollButton(false);
            crapsView.getSetBankButton().setEnabled(true);
        }
    }

    /**
     * ActionListener for the allIn JButton implements ActionListener
     * */
    private class AllInItemListener implements ActionListener {
        /**
         * actionPerformed is called whenever allIn is pressed
         *
         * @param e ActionEvent that happened
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(crapsView.getAddBetAmountTextField().getText().isEmpty()){
                    craps.setBetAmount(0);
                    craps.setBetAmount(craps.getBankAccount());
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);
                } else {
                    craps.setBetAmount(
                        Integer.parseInt(crapsView.getAddBetAmountTextField().getText()));
                    craps.setBetAmount(craps.getBankAccount());
                    crapsView.getAddBetAmountTextField().setText(String.valueOf(craps.getBetAmount()));
                    craps.updateUI();
                    crapsView.enableBetButtons(true);
                    crapsView.enablePlayAgainButton(true);
                    crapsView.enableRollButton(false);
                    crapsView.getSetBankButton().setEnabled(true);

                }
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(crapsView,
                    "ERROR Total Bet is invalid");
            }
        }
    }
}
