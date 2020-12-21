package reports.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class ReportGenerator {
    private static final Log log = LogFactory.getLog(ReportGenerator.class);

    public static String generate(Report report) {
        String result = "";
        try {
            StringWriter resultWriter = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().
                    newTransformer(new StreamSource(new StringReader(report.getReportStylesheet())));

            transformer.transform(new DOMSource(report.getReportData()), new StreamResult(resultWriter));
            result = resultWriter.toString();
        } catch (TransformerConfigurationException e) {
            log.error(e);
        } catch (TransformerException e) {
            log.error(e);
        }
        return result;
    }
}
