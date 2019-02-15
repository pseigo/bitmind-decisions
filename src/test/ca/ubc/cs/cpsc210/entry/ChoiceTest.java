package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
public class ChoiceTest extends ModelTest {
    private Choice choice;

    @BeforeEach
    public void setup() {
        choice = new Choice("Choice description");
    }
    
    @Test
    public void testConstructor() {
        assertEquals("Choice description", choice.description());
        assertEquals(0, choice.prosCount());
        assertEquals(0, choice.consCount());
        assertEquals(0, choice.regretsCount());
        assertEquals(0, choice.regretValue());
    }

    @Test
    public void testCopyConstructor() {
        // make a choice with some properties
        for (int i = 0; i != 3; ++i) {
            choice.addPro(new Consequence.Builder("Pro #" + i).isLongTerm().build());
        }
        for (int i = 0; i != 6; ++i) {
            choice.addCon(new Consequence.Builder("Con #" + i).isShortTerm().build());
        }
        for (int i = 0; i != 2; ++i) {
            choice.addRegret(new Consequence.Builder("Regret #" + i).build());
        }
        choice.setRegretValue(43);

        Choice deepCopy = new Choice(choice);

        // compare properties of deep copy to original
        assertEquals(choice.description(), deepCopy.description());
        assertEquals(43, deepCopy.regretValue());
        for (int i = 0; i != 3; ++i) {
            try {
                assertEquals(choice.getPro("Pro #" + i),
                        deepCopy.getPro("Pro #" + i));
            } catch (ElementNotFoundException e) {
                fail("caught " + e.toString() + " when unexpected");
            }
        }
        for (int i = 0; i != 6; ++i) {
            try {
                assertEquals(choice.getCon("Con #" + i),
                        deepCopy.getCon("Con #" + i));
            } catch (ElementNotFoundException e) {
                fail("caught " + e.toString() + " when unexpected");
            }
        }
        for (int i = 0; i != 2; ++i) {
            try {
                assertEquals(choice.getRegret("Regret #" + i),
                        deepCopy.getRegret("Regret #" + i));
            } catch (ElementNotFoundException e) {
                fail("caught " + e.toString() + " when unexpected");
            }
        }

        // assert that they are different objects
        assertNotSame(choice, deepCopy);
    }
    
    @Test
    public void testSetRegretValue() {
        try {
            choice.setRegretValue(100);
            assertEquals(100, choice.regretValue());
            choice.setRegretValue(53);
            assertEquals(53, choice.regretValue());
            choice.setRegretValue(0);
            assertEquals(0, choice.regretValue());
        } catch (OutOfBoundsException e) {
            fail("caught " + e.toString() + " when regret value should have been within bounds");
        }
    }

    @Test
    public void testSetRegretValueInvalid() {
        try {
            choice.setRegretValue(101);
            fail("did not catch OutOfBoundsException when expected");
        } catch (OutOfBoundsException e) {
            // Expected behaviour
        }
        assertEquals(0, choice.regretValue());

        try {
            choice.setRegretValue(-1);
            fail("did not catch OutOfBoundsException when expected");
        } catch (OutOfBoundsException e) {
            // Expected behaviour
        }
        assertEquals(0, choice.regretValue());
    }

    // START PROS TESTS
    @Test
    public void testAddPro() {
        Consequence pro = new Consequence.Builder("Some description")
                .isLongTerm()
                .build();
        choice.addPro(pro);
        assertEquals(1, choice.prosCount());

        try {
            Consequence proGet = choice.getPro("Some description");
            assertEquals(pro.description(), proGet.description());
            assertTrue(proGet.isLongTerm());
            assertFalse(proGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddProMany() {
        for (int i = 0; i != 100; ++i) {
            Consequence pro = new Consequence.Builder("Consequence #" + i)
                    .isLongTerm()
                    .build();
            choice.addPro(pro);
        }
        assertEquals(100, choice.prosCount());

        try {
            Consequence proGet = choice.getPro("Consequence #54");
            assertEquals("Consequence #54", proGet.description());
            assertTrue(proGet.isLongTerm());
            assertFalse(proGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddDuplicatePro() {
        for (int i = 0; i != 3; ++i) {
            choice.addPro(new Consequence.Builder("Duplicate description").build());
            assertEquals(1, choice.prosCount());
        }
    }

    @Test
    public void testRemovePro() {
        choice.addPro(new Consequence.Builder("Some description").build());
        assertEquals(1, choice.prosCount());
        choice.removePro("This was never added");
        assertEquals(1, choice.prosCount());
        choice.removePro("Some description");
        assertEquals(0, choice.prosCount());
    }

    @Test
    public void testGetProNotInList() {
        try {
            choice.getPro("This is not in the collection");
            fail("did not catch ElementNotFoundException when expected");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }

    // START CONS TESTS
    @Test
    public void testRegretCon() {
        Consequence con = new Consequence.Builder("Some description")
                .isLongTerm()
                .build();
        choice.addCon(con);
        assertEquals(1, choice.consCount());

        try {
            Consequence conGet = choice.getCon("Some description");
            assertEquals(con.description(), conGet.description());
            assertTrue(conGet.isLongTerm());
            assertFalse(conGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddConMany() {
        for (int i = 0; i != 100; ++i) {
            Consequence con = new Consequence.Builder("Consequence #" + i)
                    .isLongTerm()
                    .build();
            choice.addCon(con);
        }
        assertEquals(100, choice.consCount());

        try {
            Consequence conGet = choice.getCon("Consequence #54");
            assertEquals("Consequence #54", conGet.description());
            assertTrue(conGet.isLongTerm());
            assertFalse(conGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddDuplicateCon() {
        for (int i = 0; i != 3; ++i) {
            choice.addCon(new Consequence.Builder("Duplicate description").build());
            assertEquals(1, choice.consCount());
        }
    }

    @Test
    public void testRemoveCon() {
        choice.addCon(new Consequence.Builder("Some description").build());
        assertEquals(1, choice.consCount());
        choice.removeCon("This was never added");
        assertEquals(1, choice.consCount());
        choice.removeCon("Some description");
        assertEquals(0, choice.consCount());
    }

    @Test
    public void testGetConNotInList() {
        try {
            choice.getCon("This is not in the collection");
            fail("did not catch ElementNotFoundException when expected");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }

    // START REGRETS TESTS
    @Test
    public void testAddRegret() {
        Consequence regret = new Consequence.Builder("Some description")
                .isLongTerm()
                .build();
        choice.addRegret(regret);
        assertEquals(1, choice.regretsCount());

        try {
            Consequence regretGet = choice.getRegret("Some description");
            assertEquals(regret.description(), regretGet.description());
            assertTrue(regretGet.isLongTerm());
            assertFalse(regretGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddRegretMany() {
        for (int i = 0; i != 100; ++i) {
            Consequence regret = new Consequence.Builder("Consequence #" + i)
                    .isLongTerm()
                    .build();
            choice.addRegret(regret);
        }
        assertEquals(100, choice.regretsCount());

        try {
            Consequence regretGet = choice.getRegret("Consequence #54");
            assertEquals("Consequence #54", regretGet.description());
            assertTrue(regretGet.isLongTerm());
            assertFalse(regretGet.isShortTerm());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddDuplicateRegret() {
        for (int i = 0; i != 3; ++i) {
            choice.addRegret(new Consequence.Builder("Duplicate description").build());
            assertEquals(1, choice.regretsCount());
        }
    }

    @Test
    public void testRemoveRegret() {
        choice.addRegret(new Consequence.Builder("Some description").build());
        assertEquals(1, choice.regretsCount());
        choice.removeRegret("This was never added");
        assertEquals(1, choice.regretsCount());
        choice.removeRegret("Some description");
        assertEquals(0, choice.regretsCount());
    }

    @Test
    public void testGetRegretNotInList() {
        try {
            choice.getPro("This is not in the collection");
            fail("did not catch ElementNotFoundException when expected");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }
    
}