package br.inpe.triangle.utils;

public class GenericFactory {
	private GenericFactory() {}
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> tipeOfClass) {
		T t = null;
		try {
			t = (T) Class.forName(tipeOfClass.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static String getSimpleName(Class<?> clazz){
		return clazz.getSimpleName();
	}
}
