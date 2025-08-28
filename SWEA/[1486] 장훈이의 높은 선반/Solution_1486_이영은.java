import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_이영은 {
	
	static int N, B, min;
	static int[] person;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			person = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				person[i] = Integer.parseInt(st.nextToken());
			}
			
			
			min = B;
			dfs(0, 0);
			
			System.out.println("#" + tc + " " + min);
		}
		
		

	}
	
	public static void dfs(int cnt, int total) { // cnt: 점원, total: 키 총 합
		// 종료 조건: 모두 탐색했다면 종료
		if(total-B>=min) return; // 가지치기..
		
		if(cnt == N) {
			if(total>=B) { // 합이 선반을 넘는 경우
				min = Math.min(min, total-B);
			}
			return;
		}
		
		// 지금 점원의 키 포함하는 경우
		dfs(cnt+1, total+person[cnt]);
		
		// 점원의 키 포함하지 않는 경우
		dfs(cnt+1, total);
		
	}

}
