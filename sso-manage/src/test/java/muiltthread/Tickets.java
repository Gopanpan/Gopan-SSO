package muiltthread;

import java.util.Vector;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/4/13 13:13
 * @desc : 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 */
public class Tickets {

    public static void main(String[] args) {
        //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap

        final Vector<String> tickets = new Vector<String>();
        for(int i = 1; i<= 1000; i++){
            tickets.add("火车票"+i);
        }

        for(int i = 1; i <=10; i ++){
            new Thread("线程"+i){
                public void run(){
                    while(true){
                        if(tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}

