import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test = 0; test < T; test++) {
			// 큐 생성
			Queue<int[]> que = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 개수
			int M = Integer.parseInt(st.nextToken()); // 타겟
			
			st = new StringTokenizer(br.readLine()); // 중요도
			int cnt = 0;
			int[] importances = new int[N];
			// 큐에 넣기
			for(int i = 0; i < N; i++) {
				int importance = Integer.parseInt(st.nextToken());
				que.offer(new int[] {importance, i}); // 중요도, 문서번호
				importances[i] = -importance; // 내림차순 정렬을 위해서 - 붙여서 저장
			}
			Arrays.sort(importances);
			
			while(!que.isEmpty()) {
				int[] top = que.poll();
				int ipt = top[0];
				int idx = top[1];
				
				// 종요도 확인
				if(ipt != -importances[cnt]) {
					que.offer(top);
				} else { // 가장 높대!
					cnt++;

					// 찾던 애면
					if(idx == M) {
						sb.append(cnt);
						sb.append("\n");
						break;
					}
				}
			}
		}
		System.out.println(sb.toString());
	}

}

// 입력
// TC 수
// 문서개수 찾을문서
// 중요도들

// 출력
// 각 문서가 몇 번째로 인쇄되는지

// 이해
// 출력 순서: 중요도 가장 높은 문서
// 가장 중요도 높은 문서를 뽑고 그 다음 중요도 뽑고...
