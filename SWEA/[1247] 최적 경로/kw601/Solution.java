import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<int[]> edges;
	static int N, answer;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] company, home;
	static int[][] customers;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
            company = new int[2];
            home = new int[2];

            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());

            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());
			
            customers = new int[N][2];
    		for(int i = 0; i < N; i++) {
    			customers[i][0] = Integer.parseInt(st.nextToken());
    			customers[i][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		visited = new boolean[N];
    		answer = Integer.MAX_VALUE;
    		
    		dfs(company[0], company[1], 0, 0);
    		
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int x, int y, int cnt, int dist) {
		if(cnt == N) {
			dist += Math.abs(x - home[0]) + Math.abs(y - home[1]);
            answer = Math.min(answer, dist);
            return;
		}
		if(dist >= answer) return;
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(customers[i][0], customers[i][1], cnt + 1, dist + Math.abs(x - customers[i][0]) + Math.abs(y - customers[i][1]));
				visited[i] = false;
			}
		}
	}
}

/*
 * 입력
 * T
 * N
 * 회사좌표 집좌표 N명고객좌표
 * 
 * 출력
 * 최단 경로
 * 
 * 생각
 * 완탐으로 풀면 될 것 같은데
 * 크루스칼...? 왜 크루스칼이지
 * 1. 회사 -> 모든 고객 집 -> 최단거리 하나 
 * 2. 고객들끼리(시작: 회사에서 최단거리 끝: 집에서 최단거리)
 * 3. 고객집 -> 내집 -> 최단거리 하나
 * 을 간선 넣어주고 해야하나?
 * 이러면 완탐과 다를게 뭐지...?
 * 
 * 거리: 절대값 거리
 */
 