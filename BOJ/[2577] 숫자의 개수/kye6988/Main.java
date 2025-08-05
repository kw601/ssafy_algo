package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		int b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		int c = Integer.parseInt(st.nextToken());
		int num = a*b*c;
		String num_s = Integer.toString(num);
		int[] res = new int[10];
		for (char string : num_s.toCharArray()) {
			res[string-'0'] += 1;
		}
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}

}
