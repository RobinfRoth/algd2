/*
 * Created on 21.03.2014
 */
package ch.fhnw.algd.sortalgs;

import ch.fhnw.algd.sortdemo.framework.SortAlg;
import ch.fhnw.algd.sortdemo.framework.SortData;

public class HeapSort implements SortAlg {
	@Override
	public void run(SortData data) {
		for (int i = data.size() / 2; i >= 0; i--){
			siftDown(data, i, data.size());
		}

		int size = data.size();
		for (int end = data.size() - 1; end >= 0; end--) {
			data.swap(0, end);
			siftDown(data, 0, size-1);
			size--;
		}

	}

	private void siftDown(SortData data, int start, int size) {
		int j = 2 * start + 1;
		while (j + 1 < size &&
				(data.less(start, j) || data.less(start,j+1))) {
			if (data.less(j, j+1)) {
				data.swap(start,j+1);
				start = j+1;
			} else {
				data.swap(start, j);
				start = j;
			}
			j = 2 * start + 1;
		}
	}

}
