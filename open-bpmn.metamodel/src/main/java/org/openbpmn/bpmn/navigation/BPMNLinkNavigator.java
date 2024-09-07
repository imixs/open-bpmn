package org.openbpmn.bpmn.navigation;

import java.util.Set;

import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;

/**
 * The BPMNLinkNavigator navigates from a Link Throw Event to a Link Catch event
 * 
 *
 */
public class BPMNLinkNavigator {

    public BPMNElementNode findNext(BPMNElementNode node) {

        // is it a throw event?
        if (node != null && BPMNTypes.THROW_EVENT.equals(node.getType())) {
            String linkName = node.getName();
            // now find the corresponding first catch event with the same name
            Set<? extends BPMNElementNode> filteredElementList = node.getBpmnProcess()
                    .findElementNodes(
                            n -> (BPMNTypes.CATCH_EVENT.equals(n.getType()) //
                                    && ((Event) n).getEventDefinitionsByType(BPMNTypes.EVENT_DEFINITION_LINK)
                                            .size() > 0 //
                                    && linkName.equals(n.getName())));

            // return the first one...
            if (filteredElementList != null && filteredElementList.size() > 0) {
                return filteredElementList.iterator().next();
            }

        }

        // no match
        return null;
    }

}
