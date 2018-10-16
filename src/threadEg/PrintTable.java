package threadEg;

import java.util.Scanner;

public class PrintTable {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your number : ");
		int num=sc.nextInt();
		Thread t1=new Thread(()-> {
			try {
				for(int i=2;i<=10;i++) {
					System.out.println(i*num);
					Thread.sleep(1000);
				}	
			} catch(InterruptedException ie ) {
				System.out.println(Thread.currentThread());
			}
		});
		
		t1.start();

	}

}
