import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_5567_이영은 {
	
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 상근이 동기 
		int m = Integer.parseInt(br.readLine()); // 리스트의 길이
		
		list = new ArrayList<>();
		visited = new boolean[n+1];
		
		// 리스트 초기화
		for(int i=0; i<n+1; i++) {
			list.add(new ArrayList<>());
		}
		
		// 친구 관계 리스트 만들기
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 서로 친구
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		// 친구의 친구만 초대 상근이부터 시작
		Find(0, 1);
		
		int result=0;
		
		for(int i=2; i<visited.length; i++) {
			if(visited[i]) {
				result++;
			}
		}
		System.out.println(result);
		
	}
	
	public static void Find(int cnt, int start) { // cnt 돈 횟수 start 시작 번호
		// 다 돌면 종료? cnt>2면 종료
		if(cnt==2) return;
		
		// 친구의 친구만 돌기
		for(int i=0; i< list.get(start).size(); i++) {
			int next = list.get(start).get(i);
			visited[next] = true;
			Find(cnt+1, next);
		}
		
	}

}
