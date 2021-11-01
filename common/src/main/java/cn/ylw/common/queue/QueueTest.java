package cn.ylw.common.queue;

import lombok.Data;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 队列测试
 *
 * @author yanluwei
 * @date 2021/9/29
 */
public class QueueTest {

    public static void delayQueue() {
        DelayQueue<Book> books = new DelayQueue<>();
        Book b1 = new Book(100, "红楼梦", 5L);
        Book b2 = new Book(200, "西游记", 2L);
        Book b3 = new Book(50, "水浒传", 4L);
        Book b4 = new Book(70, "水浒传", 10L);
        books.put(b1);
        books.put(b2);
        books.put(b3);
        books.put(b4);
        while (true) {
            try {
                System.out.println(books.poll(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        delayQueue();
    }
}

@Data
class Book implements Delayed {
    private Integer id;
    private String name;
    private long delay;
    private long expire;

    public Book(Integer id, String name, long delay) {
        this.id = id;
        this.name = name;
        this.delay = delay;
        this.expire = System.currentTimeMillis() + delay * 1000;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.expire - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof Book)) {
            return -1;
        }
        Book book = (Book) o;
        return this.id.compareTo(book.id);
    }
}
