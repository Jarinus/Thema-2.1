package nl.jgermeraad;

public class Test {
	private Object lock;
	
	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		lock = new Object();
		new Thread(new TestThread(1)).start();
		new Thread(new TestThread(2)).start();
	}
	
	private class TestThread implements Runnable {
		private int number;
		
		public TestThread(int number) {
			this.number = number;
		}
		@Override
		public void run() {
			while(true) {
				System.out.println(number);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
