package assignment10;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import studio4.SerialComm;
import studio4.SerialTestInput;

public class CountStepsHealth {


	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		//String csvFile = "/Users/kaywiz/Documents/workspace/CSE132-Wisneski-437588/java/Steps.csv";
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = "";
		String csvSplitBy = ",";
		int counter = 0;
		
		try {
			SerialComm s = new SerialComm();
			s.connect("/dev/cu.usbserial-DN00MZW8");
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();		
			bw = new BufferedWriter(new OutputStreamWriter(out));
			br = new BufferedReader(new InputStreamReader(in, "US-ASCII"));	
			
			ArrayList<Double> dataX = new ArrayList<Double>();
			int i=0;

			while ((line = br.readLine()) != null) {
				String[] initial = new String[3];
				initial = line.split(csvSplitBy);
				//System.out.println("X: " + initial[0] + ", Y: "+ initial[1] + " , Z: " + initial[2]);
				dataX.add(Double.parseDouble(initial[2]));
				//System.out.println("X: " + data[i][0] + ", Y: "+ data[i][1] + " , Z: " + data[i][2]);
				if(i>1){
					if(dataX.get(i) == 2){
						counter = 0;
					}
					if((dataX.get(i) > 0) && (dataX.get(i-1) < 0)){
						//System.out.println("Z: " + dataX.get(i) + ", is a step");
						counter++;
						String str = counter + " steps";
						System.out.println(str);
						//bw.write(str);
					}
					else{
						//System.out.println("Z: " + dataX.get(i) + ", is NOT a step");
						String str = counter + " steps";
						System.out.println(str);
						//bw.write(counter + "wut");
					}
				}
				++i;
			}	
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}
}




