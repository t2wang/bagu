import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadingPrint {

    public static int state = 0;

    public static final ReentrantLock lock = new ReentrantLock();

    public static final Condition condA = lock.newCondition();
    public static final Condition condB = lock.newCondition();
    public static final Condition condC = lock.newCondition();

    public static void main(String[] args) {
    
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {
                    for(int i = 0; i < 100; i++) {
                        lock.lock();
                        // try {
                        //     while(state % 3 != 0) {
                        //         condA.await();
                        //     }
                        //     System.out.print("1");
                        //     state++;
                        //     condB.signal();
                        // } finally {
                        //     lock.unlock();
                        // }
                        while(state % 3 != 0) {
                            condA.await();
                        }
                        System.out.print("1");
                        state++;
                        condB.signal();
                        lock.unlock();
                    }
                } catch (Exception e) {
                    e.printStackTrace();;
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                try {
                    for(int i = 0; i < 100; i++) {
                        lock.lock();
                        // try {
                        //     while(state % 3 != 1) {
                        //         condB.await();
                        //     }
                        //     System.out.print("2");
                        //     state++;
                        //     condC.signal();
                        // } finally {
                        //     lock.unlock();
                        // }
                        while(state % 3 != 0) {
                            condA.await();
                        }
                        System.out.print("1");
                        state++;
                        condB.signal();
                        lock.unlock();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            public void run() {
                try {
                    for(int i = 0; i < 100; i++) {
                        lock.lock();
                        // try {
                        //     while(state % 3 != 2) {
                        //         condC.await();
                        //     }
                        //     System.out.print("3");
                        //     state++;
                        //     condA.signal();
                        // } finally {
                        //     lock.unlock();
                        // }
                        while(state % 3 != 0) {
                            condA.await();
                        }
                        System.out.print("1");
                        state++;
                        condB.signal();
                        lock.unlock();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

}
