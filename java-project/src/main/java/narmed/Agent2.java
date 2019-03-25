package narmed;

import java.util.Random;

public class Agent2 {

	private int nbArmedBandit;

	double rewards[] ;
	double means[];
	int actions[];
	
	public Agent2(int nbArmedBandit) {
		this.nbArmedBandit = nbArmedBandit;
		rewards = new double[nbArmedBandit];
		means = new double[nbArmedBandit];
		actions = new int[nbArmedBandit];
	}
	
	public AgentAction actionRandom() {
		int choice = new Random().nextInt(nbArmedBandit);
		AgentCallback callback = new AgentCallback() {
			
			@Override
			public void reward(double reward) {
				
			}
		};
		return AgentAction.createAgentAction(choice, callback);
	}

	public AgentAction action() {
		int choice = 0;
		double epsilon = 0.1;
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
