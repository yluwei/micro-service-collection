package cn.ylw.common.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序
 *
 * @author yanluwei
 * @date 2021/9/18
 */
@Slf4j
public class SortTest {

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int key = arr[begin];
        int i = begin;
        int j = end;
        while (i < j) {
            while (i < j && arr[j] > key) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < key) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = key;
        quickSort(arr, begin, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static void popSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        int[] arr = new int[100000];
        Random random = new Random();
        int i = 0;
        while (i < 100000) {
            arr[i] = random.nextInt(100000);
            i++;
        }

        int[] pop = Arrays.copyOf(arr, 100000);
        int[] select = Arrays.copyOf(arr, 100000);

//        log.info("原数组：{}", arr);
        stopWatch.start("quickSort");
        quickSort(arr, 0, arr.length - 1);
        stopWatch.stop();
//        log.info("快排：{}", arr);


        stopWatch.start("popSort");
        popSort(pop);
        stopWatch.stop();
//        log.info("泡排：{}", pop);


        stopWatch.start("selectSort");
        selectSort(select);
        stopWatch.stop();
//        log.info("选排：{}", select);
        System.out.println(stopWatch);
    }
}
