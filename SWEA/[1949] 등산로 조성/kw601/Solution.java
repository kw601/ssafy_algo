import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<int[]> peeks;
	static int N, K, maxPeek, maxLen = 0;
	static boolean[][] visited;
	public static void main(String args[]) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			// 봉우리: 최대 5개
			peeks = new ArrayList<>();
			maxLen = 0;
			maxPeek = 0;
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					
					int peek = Integer.parseInt(st.nextToken());
					
					// 봉우리 확인
					if(maxPeek < peek) {
						peeks.clear();
						peeks.add(new int[] {i, j});
						maxPeek = peek;
					} else if(maxPeek == peek) {
						peeks.add(new int[] {i, j});
					}
					
					map[i][j] = peek;
				}
			}
			
			for(int[] peek: peeks) {
				dfs(peek[0], peek[1], 1, false);
			}
			
			sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
		}
		
		System.out.println(sb);

	}
	static void dfs(int r, int c, int len, boolean isBroken) {
		maxLen = Math.max(len, maxLen);
		visited[r][c] = true;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			// 범위 내면
			if(0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
				if(map[nr][nc] < map[r][c]) { // 이동할 수 있으면
					
					dfs(nr, nc, len + 1, isBroken);
				} else if(!isBroken){ // 이동할 수 없고, 공사 가능하면
					if (map[nr][nc] - K >= map[r][c]) continue;
					
					for(int toBreak = 1; toBreak <= K; toBreak++) {
						if(map[nr][nc] - toBreak < map[r][c]) {
							map[nr][nc] = map[nr][nc] - toBreak;
							dfs(nr, nc, len + 1, true);
							map[nr][nc] =  map[nr][nc] + toBreak;
                            break;
						}
					}
				}
			}
		}
		visited[r][c] = false;
	}
}