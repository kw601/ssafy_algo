import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	static char[][] maps;
	static int[][] bombs;
	static boolean[][] visited;
	// 왼쪽위부터 시계방향
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int N, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			// 초기화
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			maps = new char[N][N];
			bombs = new int[N][N];
			visited = new boolean[N][N];
			
			// 입력
			for(int i = 0; i < N; i++) {
				maps[i] =br.readLine().toCharArray();
			}
			
			Deque<int[]> deq = new ArrayDeque<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 폭탄이 아니라면
					if(maps[i][j] == '.') {
						int bomb = countBomb(i, j);
						bombs[i][j] = bomb;
						// 하나면 앞에 아니면 뒤에 넣기
						if(bomb == 0) deq.addFirst(new int[] {i, j});
						else deq.addLast(new int[] {i, j});
					}
				}
			}
			
			// 확인하면서 꺼내기
			while(!deq.isEmpty()) {
				int[] cur = deq.pollFirst();
				if(!visited[cur[0]][cur[1]]) {
					check(cur[0], cur[1]);
					cnt++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	public static void check(int r, int c) {
		visited[r][c] = true;
		
		if(bombs[r][c] == 0) {
			for(int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isIn(nr, nc) && !visited[nr][nc]) {
					check(nr, nc);
				}
			}
		}
	}
	
	// 폭탄 개수 세기
	public static int countBomb(int r, int c) {
		// cur 포함
		int bomb = 0;
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(isIn(nr, nc)) {
				if(maps[nr][nc] == '*') bomb++;
			}
		}
		return bomb;
	}
	
	// 범위 체크
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

/*
입력
TC 수
N
map 정보(N*N)

* 지뢰 . 없음

출력
지뢰가 없는 모든 칸에 숫자가 표시되는 최소 cnt

이해 생각
지뢰가 없는 칸 클릭 -> 근처 8칸에 몇 개의 지뢰가 있는지 표시
if) 숫자 == 0:
	근처 8방향에 지뢰가 없으므로 8방향의 칸에도 자동 숫자 표시
else) 자기 칸에만 표시하고 끝

0인 곳에서 시작...
-> 주변 폭탄 개수가 0인 곳들을 따로 저장?
-> N*N*2(0부터 한 이후, 나머지 체크)
-> deque 만들어서 0은 앞에, 아니면 뒤에 넣고
-> 앞부터 꺼내면서 확인? 이렇게까지 해야하나
*/