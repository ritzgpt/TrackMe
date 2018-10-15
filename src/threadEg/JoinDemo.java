package threadEg;
/*
 * 1) You have thread T1, T2, and T3. How will you ensure that thread T2 is run after T1 and thread T3 after T2?
 */
public class JoinDemo extends Thread {
	JoinDemo(String threadName){
		super(threadName);
	}
	public static void main(String[] args) {
		
		JoinDemo t1,t2,t3;
		t1=new JoinDemo("t1");
		t2=new JoinDemo("t2");
		t3=new JoinDemo("t3");
		try {
		t1.start();
		t2.join();
		t2.start();
		t2.join();
		t3.start();
		}
		catch (InterruptedException ie) {
			System.out.println(Thread.currentThread()+" : Interrupted");
		}
		
	}
	public void run() {
		System.out.println(Thread.currentThread());
	}

}


