package ArrayListMyRealisation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
}
