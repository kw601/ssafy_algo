import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, D, W, K, minCnt = Integer.MAX_VALUE;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			minCnt = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			
			// 입력
			for(int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 초기 확인
			if(isPass()) {
				sb.append("#").append(testCase).append(" ").append(0).append('\n');
				continue;
			}
			
			dfs(0,0);
			sb.append("#").append(testCase).append(" ").append(minCnt).append('\n');
		}
		
		System.out.println(sb.toString());

	}
	static boolean isPass() {
		for(int c = 0; c < W; c++) {
			int count = 0;
			int cur = -1;
			
			for(int r = 0; r < D; r++) {
				if(cur != map[r][c]) {
					cur = map[r][c];
					count = 1;
				} else {
					count++;
				}
				if(count >= K) break;
			}
			// 만약 한 열에서 count < K면
			if(count < K) return false;
		}
		return true;
	}
	static void dfs(int count, int idx) {
		if(isPass()) {
			minCnt = Math.min(count, minCnt);
			return;
		}
		if(idx == D) return;
		if(count >= minCnt) return;
		int[] original = new int[W];
		for(int c = 0; c < W; c++) {
			original[c] = map[idx][c];
		}
		
		// 1. 안바꿈
		dfs(count, idx + 1);
				
		// 2. 0으로 바꿈
		change(idx, 0);
//		for(int c = 0; c < W; c++) {
//			map[idx][c] = 0;
//		}
		
		dfs(count + 1, idx + 1);
		
		// 3. 1로 바꿈
		change(idx, 1);
//		for(int c = 0; c < W; c++) {
//			map[idx][c] = 1;
//		}
		dfs(count + 1, idx + 1);
		
		// 백트래킹
		for(int c = 0; c < W; c++) {
			map[idx][c] = original[c];
		}
		
	}
	// 바꾸기
	static void change(int idx, int to) {
		for(int c = 0; c < W; c++) {
			map[idx][c] = to;
		}
	}
}

// 입력
// tc 개수
// D W K (두께, 가로크기, 합격 기준)
// D줄: W개의 특성 (A: 0, B: 1)

// 출력: 약품의 최소 투입 횟수

// 최소: bfs? 백트래킹이라 dfs...

// 문제 이해
// 세로 방향으로 K개 이상 동일한 특성이 등장해야 함
// 약품 투여시 -> 모든 행이 같은 속성으로

// TODO
// 변경 횟수: 최대 D(각 행을 0, 1로 바꿀 수 있음)
// visited[D][2] -> 0은 0, 1은 1로 바꿈
// 바꿀 때마다, cnt++
// if 통과: sb.append; return;
// if cnt == D: return;
// 1. A로 2. B로 3. 안바꾸고

// 확인하고

// 다음으로 넘기고
// 복구하고