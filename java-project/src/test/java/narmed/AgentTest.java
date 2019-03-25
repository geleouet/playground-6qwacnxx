package narmed;

import static org.assertj.core.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

import org.junit.Test;

public class AgentTest {

	@Test
	public void test() throws FileNotFoundException {
		int NB_SIMU = 2000;
		int NB_TURN = 1000;
		int NB_BANDIT = 10;
		
		Supplier<BanditAgent> agentFactory = () -> new Agent(NB_BANDIT);
		ResultTime result = simulate(NB_SIMU, NB_TURN, NB_BANDIT, agentFactory);
		writeResult(result, "datas.json");
		System.out.println("TECHIO> open -s /project/target index.html");
		
		double m = 0.;
		for (int j = 100; j<NB_TURN; j++) {
			m+=result.result[j];
		}
		m=m/(NB_TURN-100);
		assertThat(m).isBetween(0.9, 1.1).as("Mean should be 1. %f", m);
		
	}
	@Test
	public void testEpsilon() throws FileNotFoundException {
		int NB_SIMU = 2000;
		int NB_TURN = 1000;
		int NB_BANDIT = 10;
		
		Supplier<BanditAgent> agentFactory = () -> new Agent(NB_BANDIT);
		ResultTime result = simulate(NB_SIMU, NB_TURN, NB_BANDIT, agentFactory);
		writeResult(result, "datas.json");
		System.out.println("TECHIO> open -s /project/target index.html");
		
		double m = 0.;
		for (int j = 400; j<NB_TURN; j++) {
			m+=result.result[j];
		}
		m=m/(NB_TURN-400);
		assertThat(m).isBetween(1.3, 1.4).as("Mean should be 1.4 %f", m);
		
	}
	@Test
	public void testEpsilon2() throws FileNotFoundException {
		int NB_SIMU = 2000;
		int NB_TURN = 1000;
		int NB_BANDIT = 10;
		
		Supplier<BanditAgent> agentFactory = () -> new Agent(NB_BANDIT);
		ResultTime result = simulate(NB_SIMU, NB_TURN, NB_BANDIT, agentFactory);
		writeResult(result, "datas.json");
		System.out.println("TECHIO> open -s /project/target index.html");
		
		double m = 0.;
		for (int j = 100; j<NB_TURN; j++) {
			m+=result.result[j];
		}
		m=m/(NB_TURN-100);
		assertThat(m).isBetween(1.1, 1.3).as("Mean should be between 1. and 1.5 %f", m);
		
	}

	private ResultTime simulate(int NB_SIMU, int NB_TURN, int NB_BANDIT, Supplier<BanditAgent> agentFactory) {
		ResultTime[] results = new ResultTime[NB_SIMU];
		for (int i =0; i < NB_SIMU; i++) {
			results[i] = new ResultTime(NB_TURN);
			Simu simu = Simu.create(NB_BANDIT);
			BanditAgent agent = agentFactory.get();
			for (int k = 0; k <NB_TURN; k++) {
				AgentAction action = agent.action();
				double reward = simu.play(action);
				results[i].result[k] = reward;
			}
		}
		ResultTime result = new ResultTime(NB_TURN);
		result.mean(results);
		return result;
	}
	
	private void writeResult(ResultTime result, String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < result.result.length; i++) {
			if (i > 0) sb.append(",");
			sb.append("["+i+","+result.result[i]+"]");
		}
		sb.append("]");
		
		File logFile = new File(fileName);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) 
		{
			bw.write(sb.toString());
			//bw.write("[[1,12],[2,10],[3,7],[4,4],[5,9],[6,13]]");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void writeResult() {
		File logFile = new File("datas.json");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) 
		{

			bw.write("[[1,12],[2,10],[3,7],[4,4],[5,9],[6,13]]");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private static void msg(String channel, String msg) {
		System.out.println(String.format("TECHIO> message --channel \"%s\" \"%s\"", channel, msg));
	}

	private static void success(boolean success) {
		System.out.println(String.format("TECHIO> success %s", success));
	}
}
