import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Solution {
	static int[][] board;
	static int N;
	static int maxPoint;
	static int start_x;
	static int start_y;
	static List<int[]>[] wormhole;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int test_case = Integer.parseInt(st.nextToken());
		for (int t = 1 ; t <= test_case ; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			wormhole = new ArrayList[11];
			maxPoint = 0;
			for(int i = 6 ; i <= 10 ; i++) {
				wormhole[i] = new ArrayList<>();
			}
			
			
			for (int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0 ; j < N ; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] >= 6 && board[i][j] <= 10) {
						wormhole[board[i][j]].add(new int[] {i,j});
					}
				}
			}
			
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < N ; j++) {
					if(board[i][j] == 0) {
					start_x = i;
					start_y = j;
					
					for(int d = 0 ; d < 4; d++) {
						int score = dfs(start_x, start_y, d, 0);
						maxPoint = Math.max(maxPoint, score);					
                    	}
					}
				}
			}
		System.out.println("#"+t +" "+maxPoint);
		}
	}
	
	static int dfs(int x, int y, int dir, int score) {
		int move_x = x;
		int move_y = y;
		int recent_dir = dir;
		int recent_score = score;
		
		while (true) {
			move_x += dx[recent_dir];
			move_y += dy[recent_dir];
			
			
			// 벽에 부딪혔는지 확인
			if(move_x < 0 || move_x >= N || move_y < 0 || move_y >= N ) {
				recent_score++;
				recent_dir = reverseDir(recent_dir);
				continue;
			}
			// 종료 조건
			if(board[move_x][move_y] == -1 || (move_x == start_x && move_y == start_y)) {
				return recent_score;
			}
			
			int cell = board[move_x][move_y];
			
			// 블록 (1~5 처리)
			if(cell >= 1 && cell <= 5) {
				recent_score++;
				recent_dir = changeDir(recent_dir, cell);
			}
			
			// 웜홀 (6~10 처리)
			if(cell >= 6 && cell <= 10) {
				List<int[]> wormholeList = wormhole[cell];
				
				if(wormholeList.size() == 2) {
					if(wormholeList.get(0)[0] == move_x && wormholeList.get(0)[1] == move_y) {
						move_x = wormholeList.get(1)[0];
						move_y = wormholeList.get(1)[1];
					} else {
						move_x = wormholeList.get(0)[0];
						move_y = wormholeList.get(0)[1];
					}
				}
				continue;
				
			}
			
		}
	}
	
	
	static int reverseDir(int dir) {
		if(dir == 0) return 1; // 아래 -> 위
		if(dir == 1) return 0; // 위 -> 아래
		if(dir == 2) return 3; // 오른쪽 -> 왼쪽
		if(dir == 3) return 2; // 왼쪽 -> 오른쪽
		return -1;
	}
	
	static int changeDir(int dir, int cell) {
		if(cell == 1) {
			if (dir == 0) return 2; // 아래 -> 오른쪽
			if (dir == 1) return 0; // 위 -> 아래
			if (dir == 2) return 3; // 오른쪽 -> 왼쪽
			if (dir == 3) return 1; // 왼쪽 -> 위
		} else if(cell == 2) {
			if (dir == 0) return 1; // 아래 -> 위
			if (dir == 1) return 2; // 위 -> 오른쪽
			if (dir == 2) return 3; // 오른쪽 -> 왼쪽
			if (dir == 3) return 0; // 왼쪽 -> 아래
		} else if(cell == 3) {
			if (dir == 0) return 1; // 아래 -> 위
			if (dir == 1) return 3; // 위 -> 왼쪽
			if (dir == 2) return 0; // 오른쪽 -> 아래
			if (dir == 3) return 2; // 왼쪽 -> 오른쪽
		} else if(cell == 4) {
			if (dir == 0) return 3; // 아래 -> 왼쪽
			if (dir == 1) return 0; // 위 -> 아래
			if (dir == 2) return 1; // 오른쪽 -> 위
			if (dir == 3) return 2; // 왼쪽 -> 오른쪽
		} else if(cell == 5) {
			if (dir == 0) return 1; // 아래 -> 위
			if (dir == 1) return 0; // 위 -> 아래
			if (dir == 2) return 3; // 오른쪽 -> 왼쪽
			if (dir == 3) return 2; // 왼쪽 -> 오른쪽
		} 
		return -1;
		
	}
	
}