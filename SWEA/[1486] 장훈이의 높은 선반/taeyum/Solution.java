import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 장훈이의 선반 
public class Solution {
	
	static int N;
	static int B;
	static int[] employee;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1 ; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			employee = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ; i < N ; i++) {
				employee[i] = Integer.parseInt(st.nextToken());
			}
			for(int i : employee) {
				min += i;
			}
			dfs(0,0);
			
			System.out.println("#"+test_case+" "+(min - B));
		}
	}
	
	public static void dfs(int idx, int currentHigh) {
		if(idx == N) {
			if(currentHigh >= B) {
				min = Math.min(min, currentHigh);
			}
			return;
		}
		
		dfs(idx + 1, currentHigh + employee[idx]);
		dfs(idx + 1, currentHigh);
	}
}