package org.imixs.emfcloud.bpmn2;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.glsp.graph.GraphExtension;
import org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Factory;
import org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package;

public class WFGraphExtension implements GraphExtension {

   @Override
   public EPackage getEPackage() { return Bpmn2Package.eINSTANCE; }

   @Override
   public EFactory getEFactory() { return Bpmn2Factory.eINSTANCE; }

}

