import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static char[][] map;
	static PointZero[] pointZeros;
	static int N, index;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 0 , 1 , 2,  3,  4,  5,  6,  7
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1}; // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int result;
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/data/input1868.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1 ; test_case <= T; test_case++) {
			index = 0;
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			pointZeros = new PointZero[N * N];
			map = new char[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				char[] arr = str.toCharArray();
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = arr[j];
				}
			}
			
			findZeroPoint();
			
			for (int i = 0; i < index; i++) {
//				PointZero p = pointZeros[i];
//				System.out.println(p.x + " " + p.y);
				PointZero p = pointZeros[i];
				if (map[p.x][p.y] == '.') {
					bfs(p);
					result++;
				}
			}
			
			for(char[] a : map) {
//				System.out.println(Arrays.toString(a));
				for(char b : a) {
					if(b == '.') {
						result++;
					}
				}
			}
			
			System.out.println("#"+test_case+ " " +result);
			
//			bfs();
		}
	}
	
	
	private static void bfs(PointZero p1) {
		boolean[][] visited = new boolean[N][N];
		Queue<PointZero> queue = new ArrayDeque<>();
		
		queue.offer(p1);
		visited[p1.x][p1.y] = true;
		
		while(!queue.isEmpty()) {
			PointZero p = queue.poll();
			int num = findPoint(p.x,p.y);
			map[p.x][p.y] = (char)('0' + num);
			if (num == 0) {
				for(int i = 0 ; i < 8 ; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != '.' || visited[nx][ny] ) {
						continue;
					}
					visited[nx][ny] = true;
					queue.offer(new PointZero(nx, ny));
				}
			}
		}
		
	}
	
	private static int findPoint(int x, int y) {
		int count = 0;
		
		for(int a = 0 ; a < 8 ; a++) {
			int nx = x + dx[a];
			int ny = y + dy[a];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == '.') {
				continue;
			}
			
			if(map[nx][ny] == '*') {
				count++;
			}
		}
		return count;
	}
	
	
	
	private static void findZeroPoint() {
		boolean[][] visited = new boolean[N][N];
//		int index = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				int zeroCheck = 0;
				
				if (map[i][j] != '.') continue;
				
				for(int a = 0 ; a < 8 ; a++) {
					int nx = i + dx[a];
					int ny = j + dy[a];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == '.' || visited[nx][ny]) {
						continue;
					}
					if(map[nx][ny] == '*') {
						zeroCheck++;
						continue;
					}
					visited[nx][ny] = true;
				}
				if(zeroCheck == 0) {
//					System.out.println("i: " + i + ", j: " + j + " -> index: " + index);
					pointZeros[index++] = new PointZero(i, j);
				}
			}
		}
	}
	
	static class PointZero {
		int x;
		int y;
		public PointZero(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
