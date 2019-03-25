package narmed;

import java.util.Random;

public class Agent implements BanditAgent {

	private int nbArmedBandit;
	
	public Agent(int nbArmedBandit) {
		this.nbArmedBandit = nbArmedBandit;
	}
	
	@Override
	public AgentAction action() {
		int choice = new Random().nextInt(nbArmedBandit);
		AgentCallback callback = new AgentCallback() {
			
			@Override
			public void reward(double reward) {
				
			}
		};
		return AgentAction.createAgentAction(choice, callback);
	}

}
