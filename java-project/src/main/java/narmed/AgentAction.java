package narmed;

public class AgentAction {

	public static AgentAction createAgentAction(int choice, AgentCallback callback) {
		return new AgentAction(choice, callback);
	}

	final int choice;
	final AgentCallback callback;
	
	private AgentAction(int choice, AgentCallback callback) {
		super();
		this.choice = choice;
		this.callback = callback;
	}
	
}
