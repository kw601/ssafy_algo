import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	// '*' 지뢰 있음
	// '.' 지뢰 없음
	// BFS  
	
	static char[][] map;
	static boolean[][] visited;
	static int N;
	static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
	static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for(int c = 0; c < N; c++) {
				map[c] = br.readLine().toCharArray();
			}// 입력끝
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(bombCheck(i,j) == 0 && map[i][j] != '*'
							&& visited[i][j] == false) {
						++ans;
						bfs(i,j);
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == '.') {
						map[i][j] = (char)(bombCheck(i,j) + '0');
						visited[i][j] = true;
						++ans;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans);
		}
		
		System.out.println(sb);
	}
	private static int bombCheck(int r, int c) {
		int cnt = 0;
		for(int d = 0; d < 8; d++) {
			int ni = r+dx[d];
			int nj = c+dy[d];
			if(ni>=0 &&ni< N && nj>=0 && nj<N) {
				if (map[ni][nj] == '*') {
					cnt +=1;
				}
			}
		}
		return cnt;
		
	}
	private static void bfs(int r, int c) {
		Queue<char[]> deq = new ArrayDeque<>();
		visited[r][c] = true;
		deq.offer(new char[] {(char) (r+'0'),(char) (c+'0')});
        
        //입력을 받을 때 char[] 로 받아와서 int로 바꿔주는 과정이 필요
		while(!deq.isEmpty()) {
			char[] oj = deq.poll();
			int i = oj[0]-'0';
			int j = oj[1]-'0';
			int cnt = bombCheck(i,j);
			map[i][j] = (char)(cnt+'0');
			if (cnt>0) {
				continue;
			}
			for(int d=0;d<8;d++) {
				int ni = i + dx[d];
				int nj = j + dy[d];
				if(ni>=0 &&ni<N && nj>=0 && nj<N && visited[ni][nj] == false && map[ni][nj]!='*') {
					visited[ni][nj] = true;
					deq.offer(new char[] {(char)(ni+'0'),(char)(nj+'0')});
				}
			}
		}
	}
}
