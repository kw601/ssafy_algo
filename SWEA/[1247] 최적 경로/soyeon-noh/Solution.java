import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최적경로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
// 문제 이해
// - 회사에서 출발 -> '모든' 고객 집 방문 -> 집 도달하는 최단 경로 구하기
// - 시작, 종료 지점 고정. N명의 고객의 집을 방문하는 순서만 고려하면됨
// - 고객의 수 N의 크기가 최대 10. 
// - 순열 (완전탐색) = n! (순열로 풀었을 때 10!. 아슬아슬 가능!)
// - + 백트래킹으로 최적화

/**
 * 풀이 방법
 * 1. 순열 O(N!*N)
 * - 입력처리
 * - 순열생성(permutation) : 고객 방문순서로 재귀
 * - 기저조건 : 각 순열에 대한 거리 계산, 최단 거리 갱신 및 리턴
 * 
 * 2. DFS + prunning(백트래킹) : 가중치 없는 최단거리지만, + 순서가 중요하기 때문
 * - 입력처리
 * - 모든 순열 탐색 대신 현재 경로 누적거리가 최소거리보다 크면 탐색 중지 (가지치기) 
 * - visited[] : 고객 방문 체크 
 * - 재귀로 고객 방문하면서 누적 거리 계산 
 * - sum >= minDistance 일 경우 탐색 중단 
 * 
 * 3. 만약 N이 10보다 큰 경우 
 * - DP + bitmask (TSP :Traveling Saleman Problem 외판원 문제)
 * -> 순열,백트래킹으로 안 풀림.
 * 
 * 
 * 좌표 : java.awt.Point
 * 
 */
public class Solution{

	static int N; // 고객의 수 (2<=N<=10)
	static Pos[] pos; // 좌표
	static boolean[] isSelected;
	static Pos home;
	static Pos company;
	static int cnt;
	static int ans = Integer.MAX_VALUE;
	
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		// 두 위치 사이의 거리 계산 
		// |x1-x2| + |y1-y2|
		public int getDistance(Pos o) {
			return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			// N, posN, isSelcted 초기화
			N = Integer.parseInt(br.readLine());
			pos = new Pos[N];
			isSelected = new boolean[N];
			
			
			int x, y;
			st = new StringTokenizer(br.readLine());	
			
			//회사 좌표
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			company = new Pos(x,y);
			//집 좌표
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Pos(x,y);
			
			// 고객 좌표
			for(int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				pos[i] = new Pos(x,y);
			}//입력 완료
			
			// 아무곳도 방문하지 않았고, 첫 시작인 company 좌표, 거리합 0 을 넘겨준다
			dfs(0, company, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			ans = Integer.MAX_VALUE; // 초기화
		}
		
		System.out.println(sb);
		
	}

	// 재귀를 종료시킬 cnt == N 조건을 위한 cnt
	// 거리를 구하기 위해 이전 pos를 저장할 prev
	// 지금까지의 거리를 합산한 sumDist
	private static void dfs(int cnt, Pos prev, int sumDist) {
//		System.out.println("[dfs 시작, cnt: " + cnt + ", sumDist: " + sumDist);
		if(sumDist >= ans) return;
		
		// 모든 곳을 방문했다.
		if(cnt == N) {
			// 마지막 지점인 집까지의 거리를 합산하고 return
			ans = Math.min(ans, sumDist + prev.getDistance(home)); // 오답노트 : sumDist를 안 더해줌
//			System.out.println(" => 선택 끝 : " + ans + " ");
			return;
		}
		
		for(int i = 0 ; i < N; i ++) {
			if(isSelected[i]) continue;
			
			// 방문처리
			isSelected[i] = true; // 방문처리배열
//			System.out.print(i + " -> ");
			
			// 하나를 더 선택했고, 
			// 이번에 선택한 좌표 pos[i], 
			// 직전좌표와 선택좌표의 거리를 기존 거리합에 더해준다
			// (현재 상태: pos[i]를 선택했음, 거리 총합을 넘겨줌)
			dfs(cnt + 1, pos[i], sumDist + prev.getDistance(pos[i]));
			
			// 방문처리 취소
			isSelected[i] = false; // 이걸 주의하자 제발
			
		}
		
		
		
	}
	
}

