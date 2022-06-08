package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This test class tests the creation of a BPMN Model with elements containing a
 * documentation element
 * 
 * @author rsoika
 *
 */
public class TestDocumentation {

    private static Logger logger = Logger.getLogger(TestDocumentation.class.getName());

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows
     */
    @Test
    public void testCreateModelWithElementAndDocumentation() {
        String out = "src/test/resources/create-process_documentation_1.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            BPMNProcess processContext = model.buildProcess("process_1");

            assertNotNull(processContext);

            // add a start and end event
            BPMNEvent bpmnElement = processContext.buildEvent("start_1", "Start", BPMNTypes.START_EVENT);

            // add a documentation - we expect a CDATA tag

            String value = "Some text with \n<markup>code</markup>\n Finish!";
        
            // create new node
            Element documentation = bpmnElement.getModel().createElement(BPMNNS.BPMN2, "documentation");
            documentation.setAttribute("id", BPMNModel.generateShortID("documentation"));
            bpmnElement.getElementNode().appendChild(documentation);

            CDATASection cdata = bpmnElement.getDoc().createCDATASection(value);

            documentation.appendChild(cdata);
            // .setTextContent(value);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

}
