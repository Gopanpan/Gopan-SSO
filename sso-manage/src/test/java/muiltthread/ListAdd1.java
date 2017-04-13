package muiltthread;

import java.util.ArrayList;
import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/4/13 12:28
 * @desc :
 */
public class ListAdd1 {

    private volatile static List list = new ArrayList();

    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd1 list1 = new ListAdd1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i <10; i++){
                        list1.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(list1.size() == 5){
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }


}

