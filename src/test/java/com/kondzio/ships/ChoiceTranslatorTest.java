package com.kondzio.ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceTranslatorTest {
    @Test
    void translate() {
        ChoiceTranslator instance = new ChoiceTranslator();
        assertEquals(new Point(0,0), instance.translate("a1"));

    }

    @Test
    void getAlphabetOrder() {
        ChoiceTranslator instance = new ChoiceTranslator();

        int a1 = instance.getAlphabetOrder("a");
        assertEquals(5,instance.getAlphabetOrder("e"));
        assertEquals(1, a1);
        assertFalse(instance.getAlphabetOrder("e") != 5);
        assertTrue(instance.getAlphabetOrder("a") == 1);
    }

}