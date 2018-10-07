package threadEg;

public class ThreadCreation {

	public static void main(String[] args) {
		myThreadClass myObjT=new myThreadClass();
		myObjT.start();
		myThreadClass myObjT2=new myThreadClass("Eg1Thread");
		myObjT2.start();
		
		myRunnable myObjR=new myRunnable();
		Thread threadObj=new Thread(myObjR);
		threadObj.start();
	}

	
}

/*
 * Using Thread Class
 */
class myThreadClass extends Thread{
	/*Default Constructor*/
	myThreadClass(){
		
	}
	myThreadClass(String threadName){
		/*Assigning name to thread*/
		super(threadName);
	}
	public void run(){
		System.out.println("Inside run() thread Name : "+this.getName());
	}
}

/*
 * Thread creation through Runnable
 */
class myRunnable implements Runnable{
	public void run(){
		System.out.println("Inside run () : Runnable Interface");
	}
}