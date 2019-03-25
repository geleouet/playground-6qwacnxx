package narmed;

import java.util.Random;
import java.util.function.Consumer;

public class Simu {

	public static Simu create(int nbArmedBandit) {
		return new Simu(nbArmedBandit, b-> {});
	}
	public static Simu createWalkingBandit(int nbArmedBandit) {
		return new Simu(nbArmedBandit, b-> b.mean+=new Random().nextGaussian()/100.);
	}
	
	private final Bandit[] bandits;
	private final int nbArmedBandit;
	
	public Simu(int nbArmedBandit, Consumer<Bandit> updateBandit) {
		this.nbArmedBandit = nbArmedBandit;
		Random r = new Random();
		bandits = new Bandit[nbArmedBandit];
		for (int i = 0; i < nbArmedBandit; i++) {
			bandits[i] = Bandit.createBandit(new Random(r.nextLong()));
		}
	}

	public double play(AgentAction action) {
		if (action.choice >=0 && action.choice < nbArmedBandit) {
			double reward = bandits[action.choice].next();
			action.callback.reward(reward);
			return reward;
		}
		throw new RuntimeException("Invalid choice "+action.choice+" is not between [0, "+nbArmedBandit+"[");
	}
}