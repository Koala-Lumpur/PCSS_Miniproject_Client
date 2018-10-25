import java.util.Random;

public class Dice {
	public int roll2() {
		Random rand = new Random();
		int n = rand.nextInt(3);
		while (n == 0) {
			n = rand.nextInt(3);
		} // 1-6
		return n;
	}
	public int roll3() {
		Random rand = new Random();
		int n = rand.nextInt(4);
		while (n == 0) {
			n = rand.nextInt(4);
		} // 1-6
		return n;
	}
	public int roll4() {
		Random rand = new Random();
		int n = rand.nextInt(5);
		while (n == 0) {
			n = rand.nextInt(5);
		} // 1-6
		return n;
	}
	public int roll5() {
		Random rand = new Random();
		int n = rand.nextInt(6);
		while (n == 0) {
			n = rand.nextInt(6);
		} // 1-6
		return n;
	}
	public int roll6() {
		Random rand = new Random();
		int n = rand.nextInt(7);
		while (n == 0) {
			n = rand.nextInt(7);
		} // 1-6
		return n;
	}

	public int roll10() {
		Random rand = new Random();
		int n = rand.nextInt(11);
		while (n == 0) {
			n = rand.nextInt(11);
		}
		return n;
	}// 1-10

	public int roll20() {
		Random rand = new Random();
		int n = rand.nextInt(21);
		while (n == 0) {
			n = rand.nextInt(21);
		} // 1-20
		return n;
	}

}
