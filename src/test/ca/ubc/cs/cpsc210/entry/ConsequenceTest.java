package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.EmptyDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
public class ConsequenceTest extends ModelTest {
    private Consequence consequence;

    @Test
    public void testConstructorValidDescription() {
        String expectedDescription = "Hello, World! This is my description.";
        consequence = new Consequence.Builder(expectedDescription)
                .build();

        assertEquals(expectedDescription, consequence.description());
        assertFalse(consequence.isLongTerm());
        assertFalse(consequence.isShortTerm());
    }

    @Test
    public void testConstructorEmptyDescription() {
        try {
            consequence = new Consequence.Builder("")
                    .build();
            fail("did not catch EmptyDescriptionException when expected");
        } catch(EmptyDescriptionException e) {
            // Expected behaviour
        }
        assertNull(consequence);
    }

    @Test
    public void testBuildShortTerm() {
        consequence = new Consequence.Builder("Hello, World!")
                .isShortTerm()
                .build();

        assertEquals("Hello, World!", consequence.description());
        assertFalse(consequence.isLongTerm());
        assertTrue(consequence.isShortTerm());
    }

    @Test
    public void testBuildLongTerm() {
        consequence = new Consequence.Builder("Hello, World!")
                .isLongTerm()
                .build();

        assertEquals("Hello, World!", consequence.description());
        assertTrue(consequence.isLongTerm());
        assertFalse(consequence.isShortTerm());
    }

    @Test
    public void testBuildShortAndLongTerm() {
        consequence = new Consequence.Builder("Hello, World!")
                .isShortTerm()
                .isLongTerm()
                .build();

        assertEquals("Hello, World!", consequence.description());
        assertTrue(consequence.isLongTerm());
        assertTrue(consequence.isShortTerm());
    }
}