package narmed;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class AgentTest {

	@Test
	public void test() throws FileNotFoundException {
		int NB_SIMU = 2000;
		int NB_TURN = 1000;
		int NB_BANDIT = 10;
		ResultTime[] results = new ResultTime[NB_SIMU];
		for (int i =0; i < NB_SIMU; i++) {
			results[i] = new ResultTime(NB_TURN);
			Simu simu = Simu.create(NB_BANDIT);
			Agent agent = new Agent(NB_BANDIT);
			for (int k = 0; k <NB_TURN; k++) {
				AgentAction action = agent.action();
				double reward = simu.play(action);
				results[i].result[k] = reward;
			}
		}
		ResultTime result = new ResultTime(NB_TURN);
		result.mean(results);
		writeResult(result);
		System.out.println("TECHIO> open -s /project/target index.html");
	}
	
	private void writeResult(ResultTime result) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < result.result.length; i++) {
			if (i > 0) sb.append(",");
			sb.append("["+i+","+result.result[i]+"]");
		}
		sb.append("]");
		
		File logFile = new File("datas.json");
		
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
