package br.inpe.triangle.postgis;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {
	void persist(T t);

	void update(T t);

	void remove(T t);

	T search(Serializable id, Class<?> clazz);

	List<T> getAll(Class<?> clazz);

	List<T> searchFilter(T filter);
}
