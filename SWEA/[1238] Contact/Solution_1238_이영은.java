import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 이미 연락 받은 상태 -> 다시 연락하지 X (방문배열 관리)
 * 마지막에 동시에 연락 받은 사람 중 가장 숫자가 큰 사람 반환
 * 시간이 지나도 연락 받지 못하는 경우 존재
 * 연락 인원 최대 100명, 부여될 수 있는 번호 1~100, 중간에 비어있는 번호가 있을 수 있음
 * 
 * T=10
 * 
 * 입력 받는 데이터의 길이, 시작점
 * 입력받는 데이터는 {from, to, from, to ... } 의 순으로 해석
 */

public class Solution_1238_이영은 {
	static ArrayList<ArrayList<Integer>> list;
	static ArrayList<Integer> last; // depth가 제일 큰 노드 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1; tc<=10; tc++) {
			list = new ArrayList<>(); // 인접 리스트 저장
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int data = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			// 리스트 초기화
			for(int i=0; i<101; i++) {
				list.add(new ArrayList<>());
			}
			
			// 연결 관계 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<data/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list.get(from).add(to);
			}
			
			bfs(start);
			System.out.println("#" + tc + " " + Collections.max(last));
						
		}
	}
	
	static void bfs(int start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[101];
				
		visited[start]=true;
		q.add(new int[]{start, 0});
		int max=-1;
		last = new ArrayList<>();
		
		while(!q.isEmpty()) {
			
			int[] pollQ = q.poll();
			int cur=pollQ[0];
			int curDepth=pollQ[1];
			
			// depth 비교
			if(max<curDepth) {
				max=curDepth;
				last.clear();
				last.add(cur);
			}else if(max==curDepth) {
				last.add(cur);
			}
			
			for(int i=0; i<list.get(cur).size(); i++) {
				int next = list.get(cur).get(i);
				if(!visited[next]) {
					visited[next]=true;
					q.add(new int[] {next, curDepth+1});
				}
			}
			
			
		}
		
	}

}
