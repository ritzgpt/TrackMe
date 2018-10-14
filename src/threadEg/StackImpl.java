package threadEg;
//Waiting and notifying
public class StackImpl {
	private Object[] stackArray;
	private volatile int topOfStack;
	
	StackImpl (int capacity) {
		stackArray=new Object[capacity];
		topOfStack=-1;
	}
	
	public synchronized Object pop(){
		System.out.println(Thread.currentThread() + ": popping");
		while(isEmpty()){
			try{
				System.out.println(Thread.currentThread() +":"
						+ "waiting to pop");
				wait();
			} catch(InterruptedException ie) {
				System.out.println(Thread.currentThread() +":"
						+"Interrupted");
			}
		}
		Object element=stackArray[topOfStack];
		stackArray[topOfStack--]=null;
		System.out.println(Thread.currentThread() + ": Noifying after popping");
		notify();
		return element;
		}
		
		public synchronized void push(Object element){
			System.out.println(Thread.currentThread() + ": pushing");
			while(isFull()){
				try{
					System.out.println(Thread.currentThread() +":"
							+ "waiting to push");
					wait();
				} catch(InterruptedException ie) {
					System.out.println(Thread.currentThread() +":"
							+"Interrupted");
				}
			}
			stackArray[++topOfStack]=element;
			System.out.println(Thread.currentThread() + ": Noifying after pushing");
			notify();
		}
		
		public boolean isFull(){
			return topOfStack >= stackArray.length-1;
		}
		
		public boolean isEmpty() {
			return topOfStack<0;
		}
		
	public static void main(String[] args) {
		try{
			StackImpl stack=new StackImpl(5);
			new StackPusher("A",stack);
			new StackPusher("B",stack);
			new StackPusher("C",stack);
			System.out.println("Main Thread Sleeping");
			Thread.sleep(10);
		System.out.println("Exist from Main Thread");
		} catch(InterruptedException ie){
			System.out.println(Thread.currentThread() +":"
					+"Interrupted");
		}

	}

}

abstract class StackUser implements Runnable {
	protected StackImpl stack;
	
	StackUser(String threadName, StackImpl stack) {
		this.stack=stack;
		Thread worker=new Thread(this,threadName);
		System.out.println(worker);
		worker.setDaemon(true);
		worker.start();
	}
}

class StackPopper extends StackUser {
	StackPopper(String threadName,StackImpl stack) {
		super(threadName,stack);
	}
	public void run() {
		while(true)
			stack.pop();
	}
}

class StackPusher extends StackUser {
	StackPusher(String threadName,StackImpl stack) {
		super(threadName,stack);
	}
	public void run() {
		while(true)
			stack.push(2008);
	}
}


