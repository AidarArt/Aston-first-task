package ArrayListMyRealisation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class ArrayListRealisationTest {
	
	ArrayListRealisation<Integer> integers;
	ArrayListRealisation<String> strings;
	
	
	@BeforeEach
	void setUp() {
		integers = new ArrayListRealisation<Integer>();
		integers.add(1);
		integers.add(2);
		integers.add(2);
		integers.add(3);
		
		strings = new ArrayListRealisation<String>();
		strings.add("first");
		strings.add("second");
		strings.add("third");
		strings.add("fourth");
		strings.add("fifth");
		strings.add("first");
		
	}

	@Test
	void size() {
		Assertions.assertEquals(4, integers.size());
		Assertions.assertEquals(6, strings.size());
	}

	@Test
	void isEmpty() {
		List <Integer> integers1 = new ArrayListRealisation<>();
		Assertions.assertFalse(integers.isEmpty());
		Assertions.assertFalse(strings.isEmpty());
		Assertions.assertTrue(integers1.isEmpty());
	}

	@Test
	void containsAll() {
		Collection <Integer> integers1 = new ArrayList<>();
		integers1.add(1);
		integers1.add(2);
		integers1.add(2);
		integers1.add(3);

		Assertions.assertTrue(integers.containsAll(integers1));

		Collection <String> strings1 = new ArrayList<>();
		strings1.add("first");
		strings1.add("nine");

		Assertions.assertFalse(strings.containsAll(strings1));
	}

	@Test
	void addAll() {
		Collection <Integer> integers1 = new ArrayList<>();
		integers1.add(1);
		integers1.add(2);
		integers1.add(2);
		integers1.add(3);

		Assertions.assertTrue(integers.addAll(integers1));
	}

	@Test
	void addAllByIndex() {
		Collection <Integer> integers1 = new ArrayList<>();
		integers1.add(1);
		integers1.add(2);
		integers1.add(2);
		integers1.add(3);

		Assertions.assertTrue(integers.addAll(2, integers1));
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.addAll(5, integers1));
	}

	@Test
	void addByIndex() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.add(8, 5));
	}

	@Test
	void removeByIndex() {
		Assertions.assertEquals(2, integers.remove(2));
	}

	@Test
	void removeAll() {
		Collection <Integer> integers1 = new ArrayList<>();
		integers1.add(1);
		integers1.add(2);

		Assertions.assertTrue(integers.removeAll(integers1));
	}

	@Test
	void retainAll() {
		Collection <Integer> integers1 = new ArrayList<>();
		integers1.add(1);
		integers1.add(2);

		Assertions.assertTrue(integers.retainAll(integers1));
	}

	@Test
	void clear() {
		integers.clear();
		Assertions.assertEquals(0, integers.size());
	}


	@Test
	void get() {
		Assertions.assertEquals(1, integers.get(0));
		Assertions.assertEquals("fourth", strings.get(3));
	}
	
	@Test
	void getNegativeIndex() {		
		Assertions.assertThrows(IllegalArgumentException.class, () -> integers.get(-1));
	}
	
	@Test
	void getIndexOutOfBounds() {		
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.get(4));
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.get(21));
	}
	
	@Test
	void contains() {
		Assertions.assertEquals(true, integers.contains(2));
		Assertions.assertEquals(false, strings.contains("hello"));
	}
	
	@Test
	void add() {
		for (int i = 0; i < 1000; i++) {
			Assertions.assertTrue(integers.add(i));
			Assertions.assertTrue(strings.add(Integer.toString(i)));
		}
	}
	
	@Test
	void remove() {
		Assertions.assertTrue(integers.remove((Object)2));
		Assertions.assertTrue(strings.remove("first"));
		Assertions.assertFalse(integers.remove((Object) 108));
		Assertions.assertFalse(strings.remove("hello"));
	}
	
	@Test
	void set() {
		Assertions.assertEquals(2, integers.set(1, 8));
		Assertions.assertEquals("fourth", strings.set(3, "hello"));
	}
	
	@Test
	void indexOf() {
		Assertions.assertEquals(-1, integers.indexOf(11));
		Assertions.assertEquals(0, integers.indexOf(1));
		Assertions.assertEquals(-1, strings.indexOf("hello"));
		Assertions.assertEquals(1, strings.indexOf("second"));
	}
	
	@Test
	void lastIndexOf() {
		Assertions.assertEquals(2, integers.lastIndexOf(2));
		Assertions.assertEquals(-1, integers.lastIndexOf(11));
		Assertions.assertEquals(-1, strings.lastIndexOf("hello"));
		Assertions.assertEquals(5, strings.lastIndexOf("first"));
	}

	@Test
	void sublist() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.subList(2, 4));
	}

	@Test
	void resize() {
		integers.add(5);
		integers.add(6);
		integers.add(7);
		integers.add(8);
		integers.add(9);
		integers.add(10);
		integers.add(11);


		Assertions.assertEquals(11, integers.size());
	}

	@Test
	void itrHasNext() {
		Iterator <Integer> itr = integers.iterator();
		Assertions.assertTrue(itr.hasNext());
	}

	@Test
	void itrNext() {
		Iterator <Integer> itr = integers.iterator();
		Assertions.assertEquals(2, itr.next());
	}
	
}
