package br.com.inpe.memorytest;

/**
 * @author Heitor Guerra Carneiro
 * @since May 18, 2015
 * @version 1.0
 */
public class MemoryTest {
	private Runtime runtime;
	private Long usedMemoryBefore;
	private Long usedMemoryAfter;
	private static final Long MEGABYTE = 1024L * 1024L;

	/**
	 * Before
	 */
	public void memoryBefore() {
		runtime = Runtime.getRuntime();
		setUsedMemoryBefore(runtime.totalMemory() - runtime.freeMemory());
		System.out.println("Used Memory before: " + getUsedMemoryBefore());
	}

	/**
	 * Run the code
	 */

	/**
	 * After run the code
	 */
	public void memoryAfter() {
		setUsedMemoryAfter(runtime.totalMemory() - runtime.freeMemory());
		System.out.println("Memory increased:"
				+ (getUsedMemoryAfter() - getUsedMemoryBefore()));
	}

	public static void calculateusedMemory() {
		// Get the Java runtime
		Runtime runtime = Runtime.getRuntime();
		// Run the garbage collector
		runtime.gc();
		// Calculate the used memory
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: "
				+ bytesToMegabytes(memory));
	}

	/**
	 * Clear
	 */
	public void clearTest() {
		runtime = null;
		setUsedMemoryBefore(null);
		setUsedMemoryAfter(null);
		System.gc();

	}

	// Gets and Sets

	public Long getUsedMemoryBefore() {
		return usedMemoryBefore;
	}

	public void setUsedMemoryBefore(Long usedMemoryBefore) {
		this.usedMemoryBefore = usedMemoryBefore;
	}

	public Long getUsedMemoryAfter() {
		return usedMemoryAfter;
	}

	public void setUsedMemoryAfter(Long usedMemoryAfter) {
		this.usedMemoryAfter = usedMemoryAfter;
	}

	public static Long bytesToMegabytes(Long bytes) {
		return bytes / MEGABYTE;
	}
}
