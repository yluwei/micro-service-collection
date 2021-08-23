package cn.ylw.common.design.observe;

import lombok.Data;

import java.util.Observable;

/**
 * broker
 *
 * @author yanluwei
 * @date 2021/8/23
 */
@Data
public class Subject extends Observable {
    private String message;

    public static void main(String[] args) {
        Subject observable = new Subject();
        observable.addObserver(new CustomObserver());
        observable.addObserver(new CustomObserver2());
        observable.setMessage("hello");
        observable.setChanged();
        observable.notifyObservers();
    }
}
