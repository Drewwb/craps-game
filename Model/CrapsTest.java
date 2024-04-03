package Model;

import static org.junit.jupiter.api.Assertions.*;

import View.CrapsView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Craps;

class CrapsTest {

    Craps craps = new Craps();
    CrapsView crapsView = new CrapsView(craps);

    @Test
    void testRoll() {
        craps.roll();
        if (craps.isGameInProgress()) {
            int one = craps.getDie1();
            int two = craps.getDie2();
            int point = one + two;
            assertEquals(point, craps.getPoint());
        } else {
            int one = craps.getDie1();
            int two = craps.getDie2();
            int point = one + two;
            assertEquals(point, craps.getTotal());
        }
    }

    @Test
    void testGetBankAccountWin() {
        craps.setBankAmount(10);
        craps.setBetAmount(10);
        craps.handleWinForTestClass("player wins!", craps.getBetAmount());
        assertEquals(craps.getBankAccount(), 20);
    }

    @Test
    void testGetBankAccountLoss() {
        craps.setBankAmount(10);
        craps.setBetAmount(10);
        craps.handleWinForTestClass("house wins!", -10);
        assertEquals(0, craps.getBankAccount());
    }


}