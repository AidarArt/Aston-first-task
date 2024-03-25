package ArrayListMyRealisation;

import java.util.List;


public class QuickSort {
	
	public static <E extends Comparable<E>> void quickSort(List<E> list, int startIndex, int endIndex) {
		
		if (list.size() == 0) return;
		
		if (startIndex >= endIndex) return;
		
		int pivotIndex = startIndex + (endIndex - startIndex) / 2;
		E pivot = list.get(pivotIndex);
		
		int i = startIndex;
		int j = endIndex;
		
		while (i <= j) {
			while (list.get(i).compareTo(pivot) < 0) {
				i++;
			}
			
			while (list.get(j).compareTo(pivot) > 0) {
				j--;
			}
			
			if (i <= j) {
				E temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				i++;
				j--;
			}
			
			if (startIndex < j)
				quickSort(list, startIndex, j);
			
			if (endIndex > i) {
				quickSort(list, i, endIndex);
			}
		}
	}
	
	
}
