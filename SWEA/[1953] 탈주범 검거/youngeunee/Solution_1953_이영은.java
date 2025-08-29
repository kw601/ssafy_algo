import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 터널
 * 1:상하좌우
 * 2:상하
 * 3:좌우
 * 4:상우
 * 5:하우
 * 6:하좌
 * 7:상좌
 * 
 * T
 * N, M, R, C, L 지도세로, 가로, 맨홀뚜껑 세로, 가로, 탈출 후 소요된 시간
 *  
 * 파이프 정보 어떻게 저장?
 * 양쪽 파이프 연결 어떻게 확인? -> 내가 가려는 방향의 반대방향이 열려있는지 확인
 */

public class Solution_1953_이영은 {
	// 상 우 하 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// 파이프 정보
	static boolean[][] pipe = {
			{},
			// 상 우 하 좌
			{true, true, true, true}, // 1
			{true, false, true, false}, // 2
			{false, true, false, true}, // 3
			{true, true, false, false}, // 4
			{false, true, true, false}, // 5
			{false, false, true, true}, // 6
			{true, false, false, true} // 7
	};
	
	static int[][] map;
	static boolean[][] visited;
	static int N, M, L, cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(R, C); // 맨홀뚜껑부터 시작
			
			System.out.println("#" + tc + " " + cnt);
		}
		
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0});
		visited[x][y] = true;
		cnt = 0;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();

			x = current[0];
			y = current[1];
			int depth = current[2];
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				
				if(isIn(nx, ny)) {
					
					if(!pipe[map[x][y]][d]) continue; // 파이프 이동 방향 아니면
					
					if(map[nx][ny]==0) continue; // 파이프 없으면
					
					int next = map[nx][ny];				
				
					if(!visited[nx][ny] && pipe[next][(d+2)%4]) { // 방문 안 했고 파이프도 연결 된다면
						q.add(new int[] {nx, ny, depth+1});
						visited[nx][ny]=true;
						}
				}
				
			}
			
			if(depth==L) return;
			cnt++;
			
		}
		
		
	}
	
	static boolean isIn(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<M) {
			return true;
		}
		return false;
	}
	
}