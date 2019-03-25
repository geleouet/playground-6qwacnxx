package narmed;

import java.util.Arrays;

public class ResultTime {
	
	final double[] result;
	
	public ResultTime(int nbSimu) {
		result = new double[nbSimu]; 
	}

	public void mean(ResultTime[] results) {
		int n = results.length;
		for (int i = 0; i < result.length; i++) {
			int i$=i;
			result[i] = Arrays.stream(results).mapToDouble(a->a.result[i$]).sum() / n;
		}
	}

}