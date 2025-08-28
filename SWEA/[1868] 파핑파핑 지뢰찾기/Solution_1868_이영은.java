import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  인접 지뢰 갯수가 0인 칸 먼저 체크
 *  지뢰 개수 저장 배열? (지뢰 있는 칸이면 -1...?)
 *  나머지 칸은 그냥 클릭..
 */

public class Solution_1868_이영은 {
	// 상 하 좌 우 좌상 우상 좌하 우하
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static boolean[][] visited;
	static int[][] land;
	
	static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			ans=0;
			N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			land = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				map[i] = (br.readLine()).toCharArray();
				for(int j=0; j<N; j++) {
				}
			}
			
			// 방향 돌며 지뢰 갯수 세기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int count=0;
					
					if(map[i][j]=='*') { // 지뢰라면 탐색필요X 바로 -1 넣기
						land[i][j]=-1;
						continue;
					}
					
					for(int d=0; d<8; d++) {
						int nx = i+dx[d];
						int ny = j+dy[d];
						
						if(!isIn(nx, ny)) continue;
						if(map[nx][ny]=='*') count++;
						
					}
					land[i][j] = count;
				}
			}
			
			// 0개인 위치 처리
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(land[i][j]==0 && !visited[i][j]) {
						bfs(i, j);
						ans++;
					}
				}
			}
			
			// 방문 안 한 나머지 처리
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(land[i][j]>0 && !visited[i][j]) {
						ans++; // 어차피 본인만 클릭하기 때문에 bfs 호출할 필요X
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
	
	static void bfs(int a, int b) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{a, b});
		visited[a][b] = true;
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int y = arr[1];
			
			for(int d=0; d<8; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(!isIn(nx, ny)) continue;
				if(land[nx][ny]<0) continue; // 지뢰인 경우X
				
				if(!visited[nx][ny]) { // 방문하지 않았고 쭉 이어지는 경우에
					visited[nx][ny] = true;
					if(land[nx][ny]==0) { // 0이 있다면
						q.add(new int[]{nx, ny});
					}
				}
			}
		}
	}

	static boolean isIn(int x, int y) { // 범위 체크
		if(x>=0 && x<N && y>=0 && y<N) {
			return true;
		}
		return false;
	}
}
