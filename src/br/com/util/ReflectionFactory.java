package br.com.util;

public class ReflectionFactory {
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
}
