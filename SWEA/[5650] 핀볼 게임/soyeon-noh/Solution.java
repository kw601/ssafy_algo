import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map; // NxN 크기의 배열 
	static int N, ans; // N : 5 ~ 100 사이, ans 최대 점수  
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	// 블럭 번호 1~5와 각 방향에서 다가왔을 때 변화되는 방향의 인덱스를 작성해놓은 배열
	// ex) 1번블록은 상->하(아래로 이동)로 이동하다 만나면 3번 인덱스인 오른쪽으로 꺾인다. 
	static int[][] block = {
			{}, // 0번 블록은 인덱스상 헷갈리므로 사용하지 않음
            {1,3,0,2},
            {3,0,1,2},
            {2,0,3,1},
            {1,2,3,0},
            {1,0,3,2}
	};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TEST CASE
		int T = Integer.parseInt(br.readLine());
	
		
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); 
			ans = 0; // 가장 적은 점수 0을 저장해서 대기 
			map = new int[N+2][N+2]; // 맵 주위를 5번 블록으로 감싸서 처리하는 방식을 위해 +2 
			// 핀볼 판 배열 저장
			for(int r = 1; r <= N; r++) { //범위에 주의하자. 1~N까지 포함이다. 
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c = 1; c <= N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // 입력끝 
			
			// 5번 블럭으로 맵 주위 사방을 둘러싼다 
			for(int i = 0; i <= N+1; i++) {
				map[i][0] = map[i][N + 1] = map[0][i] = map[N+1][i] = 5;
			}
			
			// 모든 곳에서 발사 
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++) {
					if(map[r][c] == 0) { // 0일때 출발 가능! 
						// 4방향 발사 
						for(int d = 0; d < 4; d++) {
							shoot(r, c, d);
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}

	}

	// 시작 r,c와 진행 방향을 매개변수로 받는 shoot 메서드 
	private static void shoot(int stR, int stC, int d) {
		// 현재 r,c를 변수로 따로 선언 
		int r = stR;
		int c = stC;
		
		int score = 0;
		
		// 이 경로가 얼마나 반복될지 아무도 모르니 무한루프를 돈다 
		while(true) {
			// 다음 좌표를 구함 
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			// 종료 조건 
			// 시작장소에 돌아오거나 블랙홀을 만나면 종료 
			if((nr == stR && nc == stC) || map[nr][nc] == -1) {
				// 점수가 최고점수보다 높은지 체크 
				if(ans < score) {
					ans = score;
				}
				// 그리고 무한루프 탈출 
				return;
			}
			
			// 블록 만남
			if(map[nr][nc] > 0) { // 0보다 크고 
				if(map[nr][nc] < 6) { // 6보다 작아 그럼 블록이다 
					int blockNum = map[nr][nc]; // 블록 넘버 가져와 
					d = block[blockNum][d]; // 블럭별 방향 전환을 기존 방향을 토대로 결정
					score++; // 부딪혔으니 +1해준다 
				} else { // 0보다큰데 6보다 크거나 같으면 웜홀임
					out : for(int i = 1; i <= N; i++) {
						for (int j = 1; j <= N; j++) {
							// 여기 설명을 너무 빠르게 하셔서 이해를 못하겠음 
							if((nr != i || nc != j) && map[i][j] == map[nr][nc]) {
								nr = i;
								nc = j;
								break out; // 이게..뭐임? 
							}
						}
					}// 웜홀찾기 
				}//웜홀
			} 
			
			r = nr; 
			c = nc;
		} // 게임중 
		
	}
}
