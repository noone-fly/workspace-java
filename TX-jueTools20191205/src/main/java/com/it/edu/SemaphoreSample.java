package com.it.edu;

import java.util.concurrent.Semaphore;

/**
 * @author ：图灵-杨过
 * @date：2019/7/11
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :可用于流量控制，限制最大的并发访问数
 */
public class SemaphoreSample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); //创建两个许可证
        for (int i=0;i<5;i++){ //创建5个线程，每次只能两个线程拿到许可证
            new Thread(new Task(semaphore,"yangguo+"+i)).start();
        }
    }

    static class Task extends Thread{
        Semaphore semaphore;

        public Task(Semaphore semaphore,String tname){
            this.semaphore = semaphore;
            this.setName(tname);
        }

        public void run() {
            try {
                semaphore.acquire(); //获取公共资源
                System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());

                Thread.sleep(1000);

                semaphore.release(); //释放公共资源
                System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
