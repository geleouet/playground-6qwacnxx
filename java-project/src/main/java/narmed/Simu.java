package narmed;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Simu {

	public static Simu create(int nbArmedBandit) {
		return new Simu(nbArmedBandit, ()-> Bandit.createBandit(new Random()), b-> {});
	}
	public static Simu createWalkingBandit(int nbArmedBandit) {
		Random r$= new Random();
		return new Simu(nbArmedBandit, ()-> new Bandit(new Random(), 0.), b-> b.mean+=r$.nextGaussian()/100.);
	}
	
	private final Bandit[] bandits;
	private final int nbArmedBandit;
	private Consumer<Bandit> updateBandit;
	
	public Simu(int nbArmedBandit, Supplier<Bandit> banditFactory, Consumer<Bandit> updateBandit) {
		this.nbArmedBandit = nbArmedBandit;
		this.updateBandit = updateBandit;
		Random r = new Random();
		bandits = new Bandit[nbArmedBandit];
		for (int i = 0; i < nbArmedBandit; i++) {
			bandits[i] = banditFactory.get();
		}
	}

	public double play(AgentAction action) {
		if (action.choice >=0 && action.choice < nbArmedBandit) {
			double reward = bandits[action.choice].next();
			action.callback.reward(reward);
			for (Bandit b : bandits) {
				updateBandit.accept(b);
			}
			return reward;
		}
		throw new RuntimeException("Invalid choice "+action.choice+" is not between [0, "+nbArmedBandit+"[");
	}
}