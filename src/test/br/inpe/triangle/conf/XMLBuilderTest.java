package test.br.inpe.triangle.conf;

import java.awt.Color;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.XMLBuilder;

public class XMLBuilderTest {
	private XMLBuilder builder;
	private DataSource toXML;
	private DataSource fromXML;

	@Before
	public void init() {
		builder = XMLBuilder.getInstance();
		toXML = MockConf.createDefaultDataSource();
	}

	@Test
	public void testGenerateXML() {
		Boolean expected = true;
		Boolean actual =builder.buildXML(toXML, MockConf.DATA_SOURCE_XML, true);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateObject() {
		fromXML = builder.buildObjectFromXML(DataSource.class, MockConf.DATA_SOURCE_XML);
		Map<Double, Color> expected = toXML.getAwtColors();
		Map<Double, Color> actual = fromXML.getAwtColors();
		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		builder = null;
		toXML = null;
	}

}
