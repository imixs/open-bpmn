package org.openbpmn.metamodel.examples;

import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.openbpmn.bpmn2.Bpmn2Factory;
import org.openbpmn.bpmn2.Definitions;
import org.openbpmn.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.openbpmn.bpmndi.BPMNDiagram;
import org.openbpmn.bpmndi.BpmndiFactory;
import org.openbpmn.di.DiFactory;

public class TestSimple {

    private static Logger logger = Logger.getLogger(TestSimple.class.getName());

    /**
     * This test generate an empty BPMN model and stores the model into the file
     * example-empty.bpmn
     */
    @Test
    public void testEmptyModel() {

        logger.info("...creating new empty model");

        // create some definitions...
        Definitions definitions = Bpmn2Factory.eINSTANCE.createDefinitions();
        definitions.setExporter("demo");
        definitions.setExpressionLanguage("java");
        
        
        BPMNDiagram bpmdiagram=   BpmndiFactory.eINSTANCE.createBPMNDiagram();
       
        definitions.getDiagrams().add(bpmdiagram);
        
        URI uri = URI.createURI("src/test/resources/example-empty.bpmn");
        Bpmn2ResourceFactoryImpl resFactory = new Bpmn2ResourceFactoryImpl();
        Resource resource = resFactory.createResource(uri);
        resource.getContents().add(definitions);
        Definitions def = (Definitions) resource.getContents().get(0);
        Assert.assertNotNull(def);
        try {
            resource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        
        // now read the model again form the file sytem.
        uri = URI.createURI("src/test/resources/example-empty.bpmn");
        resFactory = new Bpmn2ResourceFactoryImpl();
        resource = resFactory.createResource(uri);
        try {
            logger.info("...reading new empty model");
            resource.load(null);
            definitions= (Definitions) resource.getContents().get(0);
            Assert.assertEquals("demo",definitions.getExporter());
            Assert.assertEquals("java",definitions.getExpressionLanguage());            
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        logger.info("...model creation sucessful");
    }

}
