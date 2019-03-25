package narmed;

import java.util.Random;

public class GreedyAgent implements BanditAgent {

	private int nbArmedBandit;
	double rewards[] ;
	double means[];
	int actions[];
	double epsilon = 0.0;
	
	
	public GreedyAgent(int nbArmedBandit, double epsilon) {
		this.nbArmedBandit = nbArmedBandit;
		this.epsilon = epsilon;
		rewards = new double[nbArmedBandit];
		means = new double[nbArmedBandit];
		actions = new int[nbArmedBandit];
	}
	
	@Override
	public AgentAction action() {
		int choice = 0;
		if (new Random().nextDouble() < epsilon) {
			choice = new Random().nextInt(nbArmedBandit);
		}
		else {
			for (int i = 0; i < nbArmedBandit; i++) {
				if (means[i]>means[choice]) {
					choice = i;
				}
			}
		}
		int choice$ = choice;
		AgentCallback callback = new AgentCallback() {

			@Override
			public void reward(double reward) {
				rewards[choice$] += reward;
				actions[choice$]++;
				means[choice$] = rewards[choice$]/ actions[choice$];
			}
		};
		return AgentAction.createAgentAction(choice, callback);
	}

}