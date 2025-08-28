import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 회사, 집, N명 고객들
 * 두 위치 사이의 거리는 |x1-x2| + |y1-x2|
 * 완전탐색..
 * dfs -> 회사부터 시작 -> 1~N 모두 돌기 -> 가장 짧은 거리 픽.. (짧은 걸 이미 넘었다면 가지치기?) 
 */

public class Solution_1247_이영은 {
	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N+2][2];
			visited = new boolean[N+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N+2; i++) {
				int px = Integer.parseInt(st.nextToken());
				int py = Integer.parseInt(st.nextToken());
				map[i][0] = px;
				map[i][1] = py;
			}
			
			min = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			System.out.println("#" + tc + " " + min);
			
		}
	}
	
	static void dfs(int idx, int depth, int distance) {
		if(distance>=min) return; // 가지치기
		
		if(depth==N) {
			distance += Math.abs(map[idx][0]-map[1][0]) + Math.abs(map[idx][1]-map[1][1]); // 끝났으니 집과 비교 map[1]
			min = Math.min(distance, min);
			return;
		}
		
		for(int i=2; i<N+2; i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(i, depth+1, distance + Math.abs(map[idx][0]-map[i][0]) + Math.abs(map[idx][1]-map[i][1]));
				visited[i]=false;
			}
		}
		
	}

}
