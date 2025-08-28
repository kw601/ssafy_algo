
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, start, maxDep = 0;
	static ArrayList<ArrayList<Integer>> people;
	static ArrayList<Integer> last;
	
	public static void main(String args[]) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			
			people = new ArrayList<>();
			
			for (int i = 0; i < 101; i++) {
			    people.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N / 2; i++) {
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if(!people.get(from).contains(to))
					people.get(from).add(to);
			}
			maxDep = 0;
			last = new ArrayList<Integer>();
			bfs(start);
			
			sb.append("#").append(tc).append(" ").append(Collections.max(last)).append("\n");
		}
		
		System.out.println(sb);

	}
	
	
	public static void bfs(int start) {
		Queue<int[]> que = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		
		que.offer(new int[] {start, 0});
		visited[start] = true;
		last.add(start);
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			int person = cur[0];
			int depth = cur[1];
			
			if (depth > maxDep) {
	            maxDep = depth;
	            last.clear();
	            last.add(person);
	        } else if (depth == maxDep) {
	            last.add(person);
	        }
			
			for(int next: people.get(person)) {
				if(!visited[next]) {
					que.offer(new int[] {next, depth + 1});
					visited[next] = true;
				}
				
			}
			
		}
	} 
}

// 입력
// 길이, 시작
// from, to, from, to ,...
// 
