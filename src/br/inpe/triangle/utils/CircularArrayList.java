package br.inpe.triangle.utils;
import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Heitor
 * @since 18/06/2016
 */

public class CircularArrayList<E> extends ArrayList<E> {

    private static final long serialVersionUID = -2573855929861889907L;

    private transient int actualIndex;

    /**
     * Simple constructor, with no element at all.
     */
    public CircularArrayList() {
        super();
    }

    /**
     * Constructs a circular list containing the elements of the specified
     * collection, in the order they are returned by the collection's iterator.
     *
     * @param collection
     *            The collection whose elements are to be placed into this list.
     */
    public CircularArrayList(final Collection<E> collection) {
        super(collection);
    }

    @Override
    public E get(int index) {
        index = index % this.size();
        return super.get(index);
    }

    @Override
    public void add(final int index, final E element) {
        if (index <= this.actualIndex) {
            this.actualIndex++;
        }
        super.add(index, element);
    };

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index <= this.actualIndex) {
            this.actualIndex = this.actualIndex + collection.size();
        }
        return super.addAll(index, collection);
    }

    @Override
    public E remove(final int index) {
        final E element = super.remove(index);

        if (index == actualIndex) {
            this.actualIndex = 0;
        } else if (index < this.actualIndex) {
            this.actualIndex--;
        }

        return element;
    }

    @Override
    public boolean remove(final Object object) {
        final int index = this.indexOf(object);
        return this.remove(index) != null;
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        int nextIndex;
        if (collection.contains(this.getActual())) {
            nextIndex = 0;
        } else {
            int discount = 0;
            for (Object object : collection) {
                if (this.contains(object)) {
                    final int objectIndex = this.indexOf(object);
                    if (objectIndex < this.actualIndex) {
                        discount++;
                    }
                }
            }
            nextIndex = this.actualIndex - discount;
        }

        final boolean result = super.removeAll(collection);
        if (result) {
            this.actualIndex = nextIndex;
        }
        return result;
    }

    @Override
    public void clear() {
        this.actualIndex = 0;
        super.clear();
    }

    /**
     * <p>
     * Gets the exact previous element in the list. If the actual element is the
     * first one, then the last element is returned. If the list has just been
     * created, then the next element is the second one, the actual element is
     * the first, and the previous element is the last.
     * </p>
     * <p>
     * Note that this is not a Thread-safe method! A bad use of it may cause
     * race condition errors and, eventually, throw an
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     *
     * @return The exact previous element in the circular list.
     */
    public E previous() {
        if (this.actualIndex == 0) {
            this.actualIndex = this.size() - 1;
        } else {
            this.actualIndex--;
        }

        return super.get(actualIndex);
    }

    /**
     * <p>
     * Gets the exact actual element in the list, without moving its index. If
     * the list has just been created, then the actual element is the first one.
     * </p>
     * <p>
     * Note that this is not a Thread-safe method! A bad use of it and other
     * methods in this class may cause race condition errors and, eventually,
     * throw an {@link ArrayIndexOutOfBoundsException}.
     * </p>
     *
     * @return The exact actual element in the circular list.
     */
    public E getActual() {
        return super.get(actualIndex);
    }

    /**
     * <p>
     * Gets the exact next element in the list. If the actual element is the
     * last one, then the first element is returned. If the list has just been
     * created, then the next element is the second one, the actual element is
     * the first, and the previous element is the last.
     * </p>
     * <p>
     * Note that this is not a Thread-safe method! A bad use of it may cause
     * race condition errors and, eventually, throw an
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     *
     * @return The exact next element in the circular list.
     */
    public E next() {
        if (this.actualIndex == this.size() - 1) {
            this.actualIndex = 0;
        } else {
            this.actualIndex++;
        }

        return super.get(actualIndex);
    }
}