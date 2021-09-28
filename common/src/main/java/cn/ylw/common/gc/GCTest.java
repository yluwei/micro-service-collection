package cn.ylw.common.gc;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * 测试gc
 *
 * @author yanluwei
 * @date 2021/9/28
 */
public class GCTest {

    private void testObjectSize() {
        int[] arr = new int[1024];
        Integer i = new Integer(100000000);
        long a1 = RamUsageEstimator.shallowSizeOf(arr);
        long a2 = RamUsageEstimator.sizeOf(arr);
        String a3 = RamUsageEstimator.humanSizeOf(arr);

        long i1 = RamUsageEstimator.shallowSizeOf(i);
        String i2 = RamUsageEstimator.humanSizeOf(i);
        long i3 = RamUsageEstimator.sizeOf(i);
        System.out.println(a3);
    }

    public static void main(String[] args) {

    }
}
