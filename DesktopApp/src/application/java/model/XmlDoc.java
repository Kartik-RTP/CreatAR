package application.java.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;

/**
 * Created by Rajat on 5/3/2017.
 */
public class XmlDoc {
    public static void main(String[] args) throws Exception{
        JAXBContext contextObj = JAXBContext.newInstance(MagicManifest.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        MagicManifest m = new MagicManifest();
        m.addMarker(new Marker("hello","world"));

        marshallerObj.marshal(m, new FileOutputStream("magic.xml"));
    }

}
