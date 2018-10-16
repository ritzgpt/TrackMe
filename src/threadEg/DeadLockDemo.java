package threadEg;

public class DeadLockDemo {

	public static void main(String[] args) {
		DeadLock dl = new DeadLock();
		// uncomment below code for deadlock fix
		// DeadLockFix dl = new DeadLockFix();

		Thread t1 = new Thread(() -> {
			dl.methodOne();
		});
		Thread t2 = new Thread(() -> {
			dl.methodTwo();
		});
		t1.start();
		t2.start();

	}

}

class DeadLock {
	void methodOne() {
		synchronized (String.class) {
			System.out.println("Sunchronized on methodOne String block");
			synchronized (Integer.class) {
				System.out.println("Sunchronized on methodOne Integer block");
			}
		}
	}

	void methodTwo() {
		synchronized (Integer.class) {
			System.out.println("Sunchronized on methodTwo Integer block");
			synchronized (String.class) {
				System.out.println("Sunchronized on methodTwo String block");
			}
		}
	}
}

class DeadLockFix {
	void methodOne() {
		synchronized (String.class) {
			System.out.println("Sunchronized on methodOne String block");
			synchronized (Integer.class) {
				System.out.println("Sunchronized on methodOne Integer block");
			}
		}
	}

	void methodTwo() {
		synchronized (String.class) {
			System.out.println("Sunchronized on methodTwo Integer block");
			synchronized (Integer.class) {
				System.out.println("Sunchronized on methodTwo String block");
			}
		}
	}
}
