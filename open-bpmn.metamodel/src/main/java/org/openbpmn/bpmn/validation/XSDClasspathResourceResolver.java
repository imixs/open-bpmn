package org.openbpmn.bpmn.validation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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

    /**
     * Inner class implementing a LSInput
     */
    class XSDLSInputImpl implements LSInput {
        private Reader characterStream;
        private InputStream byteStream;
        private String stringData;
        private String systemId;
        private String publicId;
        private String baseURI;
        private String encoding;
        private boolean certifiedText;

        // Getters and setters here
        public Reader getCharacterStream() {
            return characterStream;
        }

        public void setCharacterStream(Reader characterStream) {
            this.characterStream = characterStream;
        }

        public InputStream getByteStream() {
            return byteStream;
        }

        public void setByteStream(InputStream byteStream) {
            this.byteStream = byteStream;
        }

        public String getStringData() {
            return stringData;
        }

        public void setStringData(String stringData) {
            this.stringData = stringData;
        }

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }

        public String getBaseURI() {
            return baseURI;
        }

        public void setBaseURI(String baseURI) {
            this.baseURI = baseURI;
        }

        public String getEncoding() {
            return encoding;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public boolean isCertifiedText() {
            return certifiedText;
        }

        public void setCertifiedText(boolean certifiedText) {
            this.certifiedText = certifiedText;
        }

        @Override
        public boolean getCertifiedText() {
            throw new UnsupportedOperationException("Unimplemented method 'getCertifiedText'");
        }
    }

}