package ArrayListMyRealisation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuickSortTest {

    @Test
    void quickSort() {
        List<Integer> integers = new ArrayListRealisation<>();
        integers.add(86);
        integers.add(5);
        integers.add(17);
        integers.add(4);
        integers.add(7);
        integers.add(6);
        integers.add(80);
        integers.add(0);
        integers.add(11);
        integers.add(-23);

        Assertions.assertTrue(QuickSort.quickSort(integers, 0, integers.size() - 1));
        Assertions.assertFalse(QuickSort.quickSort(integers, integers.size() - 1, 0));
        Assertions.assertFalse(QuickSort.quickSort(new ArrayList<Integer>(), 0, 10));
    }
}
