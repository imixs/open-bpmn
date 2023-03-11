package org.openbpmn.output;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.util.BPMNXMLUtil;

/**
 * Test Class to test clean up of cdata whitespace
 */
public class TestCleanCDATA {

    @Test
    public void testSimple() {

        String xml = "<imixs:value>\n<![CDATA[ some data ]]>\n</imixs:value>";

        System.out.println(xml);
        String result = BPMNXMLUtil.cleanCDATAWhiteSpace(xml);

        System.out.println(result);

    }

    @Test
    public void testNoneWhitespace() {

        String xml = "<imixs:value><![CDATA[ some data ]]></imixs:value>";

        System.out.println(xml);
        String result = BPMNXMLUtil.cleanCDATAWhiteSpace(xml);

        System.out.println(result);

    }

    @Test
    public void testMoreWhitespace() {

        String xml = "<imixs:value>   \n                              <![CDATA[ some data ]]>\n     </imixs:value>";

        System.out.println(xml);
        String result = BPMNXMLUtil.cleanCDATAWhiteSpace(xml);

        System.out.println(result);

    }
}
