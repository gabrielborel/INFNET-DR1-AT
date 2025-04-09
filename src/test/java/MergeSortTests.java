import org.INFNET.AT.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTests {
    @Test
    public void testMergeSortVazio() {
        int[] array = {};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    public void testMergeSortUmElemento() {
        int[] array = {5};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    public void testMergeSortDoisElementosOrdernados() {
        int[] array = {2, 5};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{2, 5}, array);
    }

    @Test
    public void testMergeSortDoisElementosDesordenados() {
        int[] array = {5, 2};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{2, 5}, array);
    }

    @Test
    public void testMergeSortVariosElementos() {
        int[] array = {5, 2, 8, 1, 9};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, array);
    }

    @Test
    public void testMergeSortElementosRepetidos() {
        int[] array = {5, 2, 8, 2, 9};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{2, 2, 5, 8, 9}, array);
    }

    @Test
    public void testMergeSortNegativos() {
        int[] array = {-5, 2, -8, 1, -9};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{-9, -8, -5, 1, 2}, array);
    }
}
