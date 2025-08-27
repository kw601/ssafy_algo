import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{

	static int N, M; // 지도 세로 N, 가로 M (5<=N, M <= 50) // 최대 5x50
	static int R, C; // 맨홀위치 (R,C) // (0 <= R <= N-1, 0 <= C <= M-1)
	static int L; // 탈출 소요 시간 (1 <= L <= 20)
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] opp = { 1, 0, 3, 2 };
	static int[][] pipe = { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };
	static int[][] visited;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M]; // 맵 생성 및 초기화
			visited = new int[N][M]; // 방문처리 배열 생성 및 초기화

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝

			// 탈주범이 있을 수 있는 장소
			ans = 1; // 현재위치 1개 초기화
			bfs();

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	private static void bfs() {
		Queue<Integer[]> queue = new ArrayDeque<>();
//		System.out.println("시작으로 큐 넣을 위치 : (" + R + ", " + C + "): " + map[R][C]);
		queue.offer(new Integer[] { R, C, 1 });
		visited[R][C] = 1;

		// 시간이 있고, 큐가 남아있으면 돌아
		while (!queue.isEmpty()) {
//			System.out.println("time:" + time + " queue.size: " + queue.size());

			Integer[] cur = queue.poll();
			// 직접 사용할 때 카운팅을 해
			int curR = cur[0];
			int curC = cur[1];
			int time = cur[2];

//			int[] pipeDir = pipe[map[curR][curC]];

			for (int d : pipe[map[curR][curC]]) {
				int newR = curR + dx[d];
				int newC = curC + dy[d];

				// 오답노트 map[newR][newC] 가 0인 경우 더 갈 수 없는 조건을 추가하지 않았음
				if (check(newR, newC) && map[newR][newC] != 0 && visited[newR][newC] == 0 && time < L) { // check를 나중에하면
																											// OutOfBounds걸림
					for (int d2 : pipe[map[newR][newC]]) { //다음 위치 파이프 정보 가져오기 => 파이프 방향 정보 1개씩 확인
						if (d == opp[d2]) { //현재 가려는 방향(d)와 다음 위치의 파이프 방향(d2)이 이동할 수 있는 상태면 진행
							queue.offer(new Integer[] { newR, newC, time + 1 });
							visited[newR][newC] = 1; // 방문처리
							ans++;
							// 갈 수 있는 위치 +1
//							System.out.println("time:" + (time + 1) + " queue.size: " + queue.size());
//							System.out.println("큐에 넣을 위치 : (" + newR + ", " + newC + "): " + map[newR][newC]);
//							System.out.println("현재 ans : " + ans);
						}
					}
				}

			}

//			System.out.println("---------------------------------------");
		}
	}

	private static boolean check(int newR, int newC) {
//		if (newR >= 0 && newR < N && newC >= 0 && newC < M) {
////			System.out.println(newR +" " +newC + "는 범위에 맞다");
//			return true;
//		}
//		return false;
		return newR >= 0 && newR < N && newC >= 0 && newC < M;
	}

}
