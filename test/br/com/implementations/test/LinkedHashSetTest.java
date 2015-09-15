package br.com.implementations.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import processing.core.PVector;

public class LinkedHashSetTest {
	public static void main(String[] args) {
		PVectorTransform vector = new PVectorTransform(new PVector(1f, 2f, 3f));
		PVectorTransform vector2 = new PVectorTransform(new PVector(3f, 2f, 1f));
		PVectorTransform vector3 = new PVectorTransform(new PVector(100f, 2f,1f));
		PVectorTransform vector4 = new PVectorTransform(new PVector(100f, 2f,1f));

		Collection<PVectorTransform> set = new LinkedHashSet<PVectorTransform>();
		set.add(vector4);
		set.add(vector3);
		set.add(vector2);
		set.add(vector);
		/**
		 * Imprimindo todos os elementos
		 */
		Iterator<PVectorTransform> i = set.iterator();
		while (i.hasNext()) {
			System.out.println("X:"+i.next().getX());
		}
		
		/**
		 * Imprimindo o primeiro elemento
		 */
		
		/*
		PVectorTransform first = null;
		for (Iterator<PVectorTransform> iterator = set.iterator(); iterator
				.hasNext();) {
			first = iterator.next();
			break;
		}
		System.out.println(first.getX());
		 */
		/**
		 * Imprimindo o ultimo elemento
		 */
		/*
		PVectorTransform t = (PVectorTransform) set.toArray()[set.size() - 1];
		System.out.println(!set.isEmpty() ? t.getX() : "vazio");
		*/
		/**
		 * Imprimindo o ultimo elemento
		 */
		/*
		List<PVectorTransform> list = new ArrayList<PVectorTransform>(set);
		Collections.reverse(list);
		System.out.println(!list.isEmpty() ? list.get(0).getX() : "vazio");
		*/
		
		/**
		 * Removendo primeiro elemento
		 */
		for (Iterator<PVectorTransform> iterator = set.iterator(); iterator
				.hasNext();) {
			set.remove(iterator.next());
			break;
		}
	}

}
