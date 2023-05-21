package org.openbpmn.glsp.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.glsp.server.actions.ActionDispatcher;

import com.google.inject.Inject;

/**
 * A custom SetDirtyCommand
 * 
 * See:
 * https://github.com/eclipse-glsp/glsp/discussions/1000#discussioncomment-5955729
 */
public class SetDirtyCommand extends AbstractCommand {
    private static Logger logger = LogManager.getLogger(BPMNSourceModelStorage.class);
    @Inject
    protected ActionDispatcher actionDispatcher;

    @Override
    public void execute() {
        // no operation needed, we just want to get the isdirty flag in this way.
        logger.debug("Execute custom setDirtyCommand....");

    }

    @Override
    protected boolean prepare() {
        return true;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public void redo() {
    }
}