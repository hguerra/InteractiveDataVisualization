package br.com.util;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CachedLinkedHashSet<E> extends LinkedHashSet<E> {
	private static final long serialVersionUID = 1L;
	private E last = null;

	@Override
	public boolean add(E e) {
		synchronized (this) {
			last = e;
		}
		return super.add(e);
	}

	public E getFirst() {
		E first = null;
		Iterator<E> i = super.iterator();
		while (i.hasNext()) {
			first = i.next();
			break;
		}
		return first;
	}

	public E getLast() {
		return last;
	}
}
