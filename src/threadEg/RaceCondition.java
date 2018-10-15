package threadEg;

public class RaceCondition {

	public static void main(String[] args) {
		RaceCondtionEg raceEg = new RaceCondtionEg();
		Thread t1 = new Thread(() -> {
			raceEg.methodOne(14);
		});
		Thread t2 = new Thread(() -> {
			raceEg.methodOne(15);
		});
		t1.start();
		t2.start();

	}

}

class RaceCondtionEg {
	static int x=0;
	
	void methodOne(int y) {
		x+=y;
		System.out.println("Value Of X : "+x+" :: "+Thread.currentThread()+" \nValue of y :" +y);
		
	}

}
