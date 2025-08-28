import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] board;
	static int N, W, H, minBlock;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
	
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 1 <= N <= 4
			W = Integer.parseInt(st.nextToken()); // 2 <= W <= 12
			H = Integer.parseInt(st.nextToken()); // 2 <= H <= 15
			
			board = new int[H][W];
			minBlock = W * H;
			
			// 입력
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						board[i][j] = n;
					}
				}
			}
			
			dfs(0);
			
			sb.append("#").append(tc).append(" ").append(minBlock).append('\n');
		}
		
		
		System.out.println(sb);
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
	private static void shoot(int c) {
		// 쏠 자리 찾기
		int r;
		for(r = 0; r < H; r++) {
			if(board[r][c] != 0) {
				break;
			}
		}
		// 못 찾으면 그냥 넘어가기
		if (r==H) return;
		
		// 연쇄반응
		bomb(r, c);
		
	}
	private static void bomb(int r, int c) {
		int power = board[r][c];
		board[r][c] = 0;
		
		for(int d = 0; d < 4; d++){
			for(int p = 1; p < power; p++) {
				int nr = r + (dr[d] * p);
				int nc = c + (dc[d] * p);
				
				if(!isIn(nr, nc)) break;
				
				if(board[nr][nc] > 1) bomb(nr, nc);
				else board[nr][nc] = 0;
			}
		
		}
	}
	private static void drop() {
		/*
		 * 생각
		 * board[i][j]: i = H-1부터 보면서 0이 아니라면
		 * newBoard[cnt][j] cnt = H-1부터 채움
		 */
		int[][] newBoard = new int[H][W];
		// 남은 벽돌 채우기
		for(int j = 0; j < W; j++) {
			int cnt = H - 1;
			for(int i = H - 1; i >= 0; i--) {
				if(board[i][j] != 0) {
					newBoard[cnt--][j] = board[i][j];
				}
			}
		}
		board= newBoard;
	}
	
	private static int[][] copy(int[][] origin){
		int[][] c = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				c[i][j] = origin[i][j];
			}
		}
		return c;
	}
	
	private static int countBlock() {
		int c = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(board[i][j] != 0) c++;
			}
		}
		return c;
	}
	
	private static void dfs(int cnt) {
		int remain = countBlock();
		if (remain == 0) { minBlock = 0; return; }
		if(cnt == N) {
			minBlock = Math.min(minBlock, remain);
			return;
		};
		
		for(int w = 0; w < W; w++) {
			int r = 0; while (r < H && board[r][w] == 0) r++;
		    if (r == H) continue;
		    
			int[][] back = copy(board);
			shoot(w);
			drop();
			dfs(cnt + 1);
			board = back;
		}
	}
}

/*
 * 입력 
 * T
 * N W H
 * H: 벽돌 정보
 * 
 * 출력
 * 최대한 많이 깨고 남은 벽돌의 수
 * 
 * 이해
 * 1. 구슬 쏘면
 * 2. 삭제
 * 3. 연쇄반응 -> 구슬이 명중한 벽돌: 상하좌우로 (벽돌 숫자 - 1)만큼 같이 제거
 * 4. 벽돌 떨어짐
 * 
 * 
 * 
 * 생각
 * 시뮬레이션인줄 알았는데 시뮬레이션이 아닌 것 같다...
 * 그리디... 최적 선택 보장이 안되니까 아닌 것 같고
 * 완탐인가
 * 
 * 
 * dfs로... 한다고 치면...
 * 다 범위 작아서 될 것 같긴 하다
 * 바꾸고 -> 백트래킹하고
 */
