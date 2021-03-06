package br.inpe.triangle.conf;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.XMLBuilder;

import java.awt.*;
import java.util.Map;

public class XMLBuilderTest {
	private XMLBuilder builder;
	private Data toXML;
	private Data fromXML;

	@Before
	public void init() {
		builder = XMLBuilder.getInstance();
		toXML = MockConf.createDefaultData();
	}

	@Test
	public void testGenerateXML() {
		Boolean expected = true;
		Boolean actual =builder.buildXML(toXML, MockConf.DATA_XML, true);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateObject() {
		fromXML = builder.buildObjectFromXML(Data.class, MockConf.DATA_XML);
		Map<Object, Color> expected = toXML.getAwtColors();
		Map<Object, Color> actual = fromXML.getAwtColors();
		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		builder = null;
		toXML = null;
	}

}
