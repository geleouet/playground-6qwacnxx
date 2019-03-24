// { autofold
package com.yourself;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Universe {
// }

public static int countAllStars(int... galaxies) {
     File logFile = new File("datas.json");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) 
        {
            
            bw.write("[[1,12],[2,10],[3,7],[4,4],[5,9],[6,13]]");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	int totalStars = 0;
	for(int stars : galaxies) {
		totalStars = stars; // fix me!
	} 
	return totalStars;
}

//{ autofold
}
//}