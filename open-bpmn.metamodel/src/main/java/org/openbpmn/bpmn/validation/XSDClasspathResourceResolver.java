package org.openbpmn.bpmn.validation;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class XSDClasspathResourceResolver implements LSResourceResolver {
    @Override
    public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
        XSDLSInputImpl input = new XSDLSInputImpl();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(systemId);
        input.setPublicId(publicId);
        input.setSystemId(systemId);
        input.setBaseURI(baseURI);
        input.setCharacterStream(new InputStreamReader(stream));
        return input;
    }
}