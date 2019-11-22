package com.bjsxt.base.coll013;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {  
    
    private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();  
    
    public boolean yinye =true;  
      
    public void shangji(String name,String id,int money){  
        Wangmin man = new Wangmin(name, id, 1000 * money + System.currentTimeMillis());  
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块,开始上机...");  
        this.queue.add(man);  
    }  
      
    public void xiaji(Wangmin man){  
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");  
    }  
  
    @Override  
    public void run() {
        while(yinye){
            try {
                Wangmin man = queue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String args[]){  
        try{  
            System.out.println("网吧开始营业");  
            WangBa siyu = new WangBa();  
            Thread shangwang = new Thread(siyu);  
            shangwang.start();

            for (int x  = 0 ;x < 10000; x ++) {
                siyu.shangji("路人甲"+x, "123", 1+x);
                siyu.shangji("路人乙"+x, "123", 1+x);
                siyu.shangji("路人丙"+x, "234", 10+x);
                siyu.shangji("路人丁"+x, "345", 5+x);
                Thread.sleep(1000);

            }
        }
        catch(Exception e){  
            e.printStackTrace();
        }  
  
    }  
}  