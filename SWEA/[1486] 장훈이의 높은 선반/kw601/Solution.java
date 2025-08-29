import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, B, minDif = Integer.MAX_VALUE;
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {		
			minDif = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 키 입력
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("#").append(tc).append(" ").append(minDif).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int idx, int hei) {
		// 조건 만족 검사
		if(hei >= B) {
			minDif = Math.min(minDif, hei - B);
			// 더 이상 더할 필요 없음
			return;
		}
		// 종결조건
		if(idx == N) {
			return;
		}
		
		// 선택
		dfs(idx + 1, hei + heights[idx]);
		// 선택 X
		dfs(idx + 1, hei);
	}
}

// SWEA 1486. 장훈이의 높은 선반


// 입력
// TC
// N B(점원의 수, 선반 높이)
// 각 점원의 키

// 출력
// 만들 수 있는 높이가 B 이상인 탑에서 높이와 B의 차가 가장 작은 것을 출력

// 생각
// 슬라이딩 윈도우 -> 연속한 숫자 선택 아니니까 아니고
// dfs, B 미만이면 pass, B 이상이면 차 계산하고 출력