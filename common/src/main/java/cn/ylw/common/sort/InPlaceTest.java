package cn.ylw.common.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 原地空间
 *
 * @author yanluwei
 * @date 2021/9/18
 */
@Slf4j
public class InPlaceTest {

    public static void main(String[] args) {
        String ori = "hello my good girl";
        char[] oriChar = ori.toCharArray();
        reverse(oriChar, 0, oriChar.length - 1);
        String first = new String(oriChar);
        log.info("第一次翻转：{}", first);
        int begin = 0;
        int end = first.indexOf(" ");
        while (end != -1) {
            reverse(oriChar, begin, end - 1);
            begin = end + 1;
            end = first.indexOf(" ", begin);
            if (end == -1) {
                reverse(oriChar, begin, oriChar.length - 1);
            }
        }
        log.info("第二次翻转：{}", new String(oriChar));
    }

    private static void reverse(char[] oriChar, int begin, int end) {
        while (begin < end) {
            char tmp = oriChar[begin];
            oriChar[begin] = oriChar[end];
            oriChar[end] = tmp;
            begin++;
            end--;
        }
    }
}
