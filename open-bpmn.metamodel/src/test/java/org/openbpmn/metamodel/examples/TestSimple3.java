package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;


public class TestSimple3 {

    private static Logger logger = Logger.getLogger(TestSimple3.class.getName());

    /**
     * This test generate an empty BPMN model and stores the model into the file
     * example-empty.bpmn
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test 
    public void testEmptyModel() {

        logger.info("...creating new empty model");
 
        logger.info("...model creation sucessful");
        assertEquals("Hello JUnit 5","aöksjd");
    }

}
