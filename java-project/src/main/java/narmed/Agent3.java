package narmed;

import java.util.Random;

public class Agent3 implements BanditAgent{

	private int nbArmedBandit;

	double q[] ;
	int actions[];
	
	public Agent3(int nbArmedBandit) {
		this.nbArmedBandit = nbArmedBandit;
		q = new double[nbArmedBandit];
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
				if (q[i]>q[choice]) {
					choice = i;
				}
			}
		}
		int choice$ = choice;
		AgentCallback callback = new AgentCallback() {

			@Override
			public void reward(double reward) {
				q[choice$] += 0.1*(reward-q[choice$]);
				actions[choice$]++;
			}
		};
		return AgentAction.createAgentAction(choice, callback);
	}
}
