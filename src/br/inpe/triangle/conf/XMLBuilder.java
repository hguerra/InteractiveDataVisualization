package br.inpe.triangle.conf;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLBuilder {
	public static Object buildObjectFromXML(Class<?> typeOfClass, String filepath) {
		try {
			File file = new File(filepath);
			JAXBContext jaxbContext = JAXBContext.newInstance(typeOfClass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> boolean buildXML(T object, String filepath, boolean printOut) {
		boolean success = true;
		try {
			File file = new File(filepath);
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, file);
			if (printOut)
				jaxbMarshaller.marshal(object, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

}
