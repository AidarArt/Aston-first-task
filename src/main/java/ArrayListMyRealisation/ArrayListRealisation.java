package ArrayListMyRealisation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Custom implementation of the ArrayList class
 * 
 * @param <E> the type of elements in this list
 * 
 * @author Artamonov Aidar
 */
public class ArrayListRealisation <E> implements List<E> {
	
	/**
	 * Default initial capacity.
	 */
	private final int DEFAULT_CAPACITY = 10;
	/**
	 * array length increase factor when overflowing
	 */
	private final double MAGNITION_FACTOR = 1.5;
	
	/**
	 * An array of values stored in the list
	 */
	private E[] values;
	/**
	 * Number of values in the list
	 */
	private int size;
	
	/**
	 * Default constructor
	 */
	@SuppressWarnings("unchecked")
	public ArrayListRealisation() {
		this.values = (E[]) new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}
	
	/**
	 * Constructor with custom parameters
	 * 
	 * @param capacity is responsible for the initial number of list elements
	 * this value may change in the future if the array overflows
	 */
	@SuppressWarnings("unchecked")
	public ArrayListRealisation(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity cannot be <= 0");
		this.values = (E[]) new Object[capacity];
		this.size = 0;
	}
	
	/**
	 * returns the number of list elements
	 */
	public int size() {
		return size;
	}

	/**
	 * returns true if list is empty, or true if there is at least 1 element
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks if an element is in an array
	 * @return true if present, false otherwise
	 * @param o desired object
	 */
	public boolean contains(Object o) {		
		for (Object elem : values) {
			if (o.equals(elem)) return true;
		}
		return false;
	}

	/**
	 * @return Itr Implementation to be able to iterate through values
	 */
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Converts a list to an array
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = values[i];
		}
		return array;
	}

	public <T> T[] toArray(T[] a) {
//		TODO: Not implemented!
		return null;
	}

	/**
	 * adds an element to the end of the list
	 * @return true if the value is added successfully, false otherwise
	 * @param e element that is added
	 */
	public boolean add(E e) {
		if (size == values.length) resize();
		
		values[size] = e;
		size++;
		
		return true;
	}
	
	/**
	 * removes an element from the list
	 * @param o element to be removed
	 * @return true if element removed, false otherwise
	 */
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index == -1)
			return false;
		remove(index);
		return true;
	}

	/**
	 * Checks for the presence of all elements of the collection passed in parameters
	 * @param c entrance collection
	 * @return true if all elements found, false otherwise
	 */
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) return false;
		}
		return true;
	}

	/**
	 * Adds all elements of the passed collection to the end of the list
	 * @param c entrance collection
	 * @return true if all elements added, false otherwise
	 */
	public boolean addAll(Collection<? extends E> c) {
		boolean added = false;
		for (E elem : c) {
			added = add(elem);
		}
		return added;
	}

	/**
	 * Adds all elements of the passed collection to positions starting from the passed index
	 * In this case, not a single element is overwritten or removed from the list
	 * @param index from what position to insert
	 * @param c entrance collection
	 * @return true if all elements added, false otherwise 
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean added = false;
		for (E elem : c) {
			add(index, elem);
			index++;
		}
		return added;
	}

	/**
	 * Remove all elements of the passed collection from the list
	 * @param c entrance collection
	 * @return true if all elements is removed, false otherwise
	 */
	public boolean removeAll(Collection<?> c) {
		boolean removed = false;
		for (Object o : c) {
			removed = remove(o);
		}
		return removed;
	}

	/**
	 * removes all objects from the current collection except those contained in the collection c
	 * @param c entrance collection
	 * @return true if the current collection has changed after deletion, false otherwise
	 */
	public boolean retainAll(Collection<?> c) {
		boolean removed = false;
		for (Object o : c) {
			if (!contains(o))
				removed = remove(o);
		}
		return removed;
	}

	/**
	 * completely clears the list
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		values = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * Returns the element by index
	 * @param index of the element
	 * @throws IllegalArgumentException
	 * @throws IndexOutOfBoundsException
	 * @return element by index in list
	 */
	public E get(int index) {
		if (index < 0)
			throw new IllegalArgumentException("Index cannot be < 0");
		if (index >= size)
			throw new IndexOutOfBoundsException(String.format("Index %s out of biunds for length %s", index, size));
		return values[index];
	}
	
	/**
	 * Replaces the element at the specified index
	 * @param index of the element
	 * @param element new element
	 * @throws IllegalArgumentException
	 * @throws IndexOutOfBoundsException
	 * @return replaced element
	 */
	public E set(int index, E element) {
		if (index < 0)
			throw new IllegalArgumentException("Index cannot be < 0");
		if (index >= size)
			throw new IndexOutOfBoundsException(String.format("Index %s out of biunds for length %s", index, size));
		E replacedElement = values[index];
		values[index] = element;
		return replacedElement;
	}

	/**
	 * inserts element into the calling list at the position specified in index.
	 * Any previously inserted elements beyond the specified insertion position are moved up.
	 * That is, no elements are overwritten.
	 * @param index of the element
	 * @param element new element
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, E element) {
		if (index < 0)
			throw new IllegalArgumentException("Index cannot be < 0");
		if (index >= size)
			throw new IndexOutOfBoundsException(String.format("Index %s out of biunds for length %s", index, size));
		E[] prevValues = values;
		values = (E[]) new Object[prevValues.length];
		System.arraycopy(prevValues, 0, values, 0, index);
		values[index] = element;
		System.arraycopy(prevValues, index, values, index + 1, size - index);
		size++;
	}

	/**
	 * Removes an element by index
	 * @throws IllegalArgumentException
	 * @throws IndexOutOfBoundsException
	 * @param index index of the element to be removed
	 * @return removed element
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if (index < 0)
			throw new IllegalArgumentException("Index cannot be < 0");
		if (index >= size)
			throw new IndexOutOfBoundsException(String.format("Index %s out of biunds for length %s", index, size));
		E[] prevValues = values;
		values = (E[]) new Object[prevValues.length];
		System.arraycopy(prevValues, 0, values, 0, index);
		System.arraycopy(prevValues, index + 1, values, index, size - index);
		size--;
		return prevValues[index];
	}

	/**
	 * Returns the index of an element in a list
	 * @param o search element
	 * @return index of an element if exists, -1 otherwise
	 */
	public int indexOf(Object o) {		
		for (int i = 0; i < size; i++) {
			if (values[i].equals(o)) return i;
		}
		
		return -1;
	}

	/**
	 * returns the index of the last instance of object in the calling list
	 * @param o index of an element
	 * @return returns the index of the last instance, -1 otherwise
	 */
	public int lastIndexOf(Object o) {
		int index = -1;
		
		for (int i = 0; i < size; i++) {
			if (values[i].equals(o)) index = i;
		}
		
		return index;
	}

	
	public ListIterator<E> listIterator() {
		// TODO: Not implemented!
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		// TODO: Not implemented!
		return null;
	}

	/**
	 * returns a list containing the elements start through end-1 from the calling list.
	 * Items from the returned list also retain references in the calling list.
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		List<E> list = new ArrayListRealisation<>();
		for (int i = fromIndex; i <= toIndex; i++) {
			if (i < size && i > 0)
				list.add(values[i]);
			else
				throw new IndexOutOfBoundsException();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] prevValues = values;
		values = (E[]) new Object[(int)(prevValues.length * MAGNITION_FACTOR) + 1];
		System.arraycopy(prevValues, 0, values, 0, size);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ ");
		for (int i = 0; i < size; i++) {
			builder.append(String.format("'%s' ", values[i]));
		}
		builder.append("]");
		return builder.toString();
	}
	
	private class Itr implements Iterator<E> {
		private int cursor;

		@Override
		public boolean hasNext() {
			return cursor == size ? false : true;
		}

		@Override
		public E next() {
			return values[cursor++];
		}
		
	}
}
