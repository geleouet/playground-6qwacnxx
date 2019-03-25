package narmed;

import java.util.Random;

public class Bandit {
	
	public static Bandit createBandit(Random r) {
		return new Bandit(r, r.nextGaussian());
	}

	Random r;
	double mean;
	
	Bandit(Random r, double mean) {
		super();
		this.r = r;
		this.mean = mean;
	}
	
	public double next() {
		return r.nextGaussian()+mean;
	}
	
	
}