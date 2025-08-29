import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	// enum...으로 관리하기... 아무래도... 잘못된 선택....
	// 지금이라도 배열로 갈아타겠습니다...?

	static int N, M, R, C, L, places = 0;
	static boolean[][] visited;
	static int[][] map;
	
	// 방향 인덱스: 0:U, 1:D, 2:L, 3:R
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0,-1, 1};
	static final int[] opposite = {1, 0, 3, 2};
	static final boolean[][] tunnel = new boolean[8][4];
	static {
//		tunnel[0]
		tunnel[1] = new boolean[]{true,  true,  true,  true }; // 상하좌우
		tunnel[2] = new boolean[]{true,  true,  false, false}; // 상하
		tunnel[3] = new boolean[]{false, false, true,  true }; // 좌우
		tunnel[4] = new boolean[]{true,  false, false, true }; // 상우
		tunnel[5] = new boolean[]{false, true,  false, true }; // 하우
		tunnel[6] = new boolean[]{false, true,  true,  false}; // 하좌
		tunnel[7] = new boolean[]{true,  false, true,  false}; // 상좌
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 시작 위치
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 길이
			L = Integer.parseInt(st.nextToken());
			places = 0;
			visited = new boolean[N][M];
			map = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(R, C);
			sb.append("#").append(tc).append(" ").append(places).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void bfs(int sr, int sc) {
		int time = 0;
		Queue<int[]> que = new ArrayDeque<>(); 

		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			// 시간 별로 체크
			if(time == L) return;
			
			int size = que.size();
			for(int s = 0; s < size; s++) {
				int[] cur = que.poll();
				int r = cur[0];
				int c = cur[1];
				places++;
				int idx = map[r][c];
				
				for(int d = 0; d < 4; d++) {
					// 이동 가능한 조건
					// 1. 이동가능한 방향
					// 2. map 범위
					// 3. 다음 칸에 터널 있음
					// 4. 아직 방문 안함 
					// 5. 터널 방향 일치함
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(tunnel[idx][d] && isin(nr, nc) && map[nr][nc] != 0 && !visited[nr][nc] && tunnel[map[nr][nc]][opposite[d]]) {
						que.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
					
				}
				
			}
			time++;
		}
	}
	public static boolean isin(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

// 입력
// TC 개수
// N, M, R, C, L(세로크기 가로크기 맨홀뚜껑위치R C 소요시간L)
// N줄: 지하 터널 정보(M개)

// 1~7: 터널 구조물 타입, 0: 터널 X

// 출력
// 탈주범이 위치할 수 있는 장소의 개수 계산

// 생각
// L 시간동안 이동할 수 있는 모든 경로 -> 완탐
// delta 배열 만들어서 움직일 수 있는 경우 탐색하기
// 생각해보니까 하나가 아니라 enum에 저장하기로...

// dfs,,,bfs,,, 상관 없을 것 같긴 한데
// 1일차 -> 2일차 -> 3일차 -> 에서 겹칠 수 있으므로 bfs, 도착여부 체크
// !모양따라 위치따라 이동할 수 있는 경우가 나뉨... 체크하기...
