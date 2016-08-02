package br.inpe.triangle.data;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLBuilder {
	private static XMLBuilder uniqueInstance;

	private XMLBuilder() {
	}

	public static XMLBuilder getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new XMLBuilder();
		}
		return uniqueInstance;
	}

	@SuppressWarnings("unchecked")
	public synchronized <T> T buildObjectFromXML(Class<?> typeOfClass, String filepath) {
		try {
			File file = new File(filepath);
			JAXBContext jaxbContext = JAXBContext.newInstance(typeOfClass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (T) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public synchronized <T> boolean buildXML(T object, String filepath, boolean printOut) {
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
