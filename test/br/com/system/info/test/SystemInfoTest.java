package br.com.system.info.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.system.info.SystemInfo;

public class SystemInfoTest {
	/**
	 * Instance Variable
	 */
	private SystemInfo info;

	/**
	 * Initialize Variable
	 */
	@Before
	public void build() {
		info = new SystemInfo();
	}

	@Test
	public void OSnameTest() {
		String expected = "Windows 7";
		String actual = info.OSname();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void OSversionTest() {
		String expected = "6.1";
		String actual = info.OSversion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void OsArchTest() {
		String expected = "x86";
		String actual = info.OsArch();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void totalMemTest() {
		Long expected = (long) 16318464;
		Long actual = (long) info.totalMem();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void usedMemTest() {
		Long expected = null;
		Long actual = info.usedMem();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Clear Variable
	 */
	@After
	public void end() {
		info = null;
		System.gc();
	}
}
