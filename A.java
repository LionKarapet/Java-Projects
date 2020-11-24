import java.util.Random;
/*
Multithreading example using wait notify and synchronization
 */
public class A {
    int a1, a2, a3;
    boolean valueSet = true;

    public synchronized void getD() {
        valueSet = true;
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        valueSet = true;
        int a3 = a1 + a2;
        System.out.println("" + a1 + " + " + a2 + " = " + a3);
        notifyAll();
    }

    public synchronized void putB(int k) {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        valueSet = false;
        a1 = k;
        notify();
    }

    public synchronized void putC(int m) {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        valueSet = false;
        a2 = m;
        notify();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            A a = new A();
            (new Thread(new B(a))).start();
            (new Thread(new C(a))).start();
            (new Thread(new D(a))).start();
        }

    }
}

class B implements Runnable {
    private A a;

    public B(A a) {
        this.a = a;
    }

    public void run() {
        Random random = new Random();
        this.a.putB(random.nextInt(10));
    }
}

class C implements Runnable {
    private A a;

    public C(A a) {
        this.a = a;
    }

    public void run() {
        Random random = new Random();
        this.a.putC(random.nextInt(10));
    }
}

class D implements Runnable {
    private A a;

    public D(A a) {
        this.a = a;
    }

    public void run() {
        a.a3 = a.a1 + a.a2;
        a.getD();
    }
}


