import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static class Microbe{
		int r, c, num, dir, total; // total: 군집 합쳐지는 상황의 미생물 수
		boolean isDead;
		
		public Microbe(int r, int c, int num, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
			this.isDead = false;
			this.total = num;
		}

//		public void changeDir() {
//			if(dir % 2 == 0) {
//				dir++;
//			} else {
//				dir--;
//			}
//		}
//		
//		public void move() {
//			r = r + dr[dir];
//			c = c + dc[dir];
//		}
//		
	}
	

	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Microbe[] microbes;
	static int N, M, K;

	
	static Microbe[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			microbes = new Microbe[K];
			map = new Microbe[N][N];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				microbes[i] = new Microbe(r, c, n, d - 1);
			}
			
			sb.append("#").append(tc).append(" ").append(move()).append('\n');
		}
		System.out.println(sb);
	}
	
	static int move() {
		int time = M, nr, nc, remainCnt = 0;
		
		while(time-- > 0) {
			for(Microbe cur: microbes){
				if(cur.isDead) continue;
				// Step 1) 한 칸 이동
				nr = cur.r += dr[cur.dir];
				nc = cur.c += dc[cur.dir];
				
				// Step 2) 약품 칸 커리
				if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
					// 군집 크기 절반으로 줄이고 방향 바꿈, 크기가 0이면 소멸
					cur.total = cur.num = cur.num/2;
					
					if(cur.num == 0) {
						cur.isDead = true; 
						continue;
					}
					
					// 방향 반대로
					if(cur.dir % 2 == 0) {
						cur.dir++;
					} else {
						cur.dir--;
					}
					
				}
				// Step 3) 군집 병합 관련 처리
				if(map[nr][nc] == null) { // 그 셀에 처음 도착하는 군집
					map[nr][nc] = cur;
				 } else { // 그 셀에 나중에 도착하는 군집(이미 군집이 있는 경우)
					 // 군집의 미생물 크기를 비교해서 큰 쪽으로 흡수시키고 작은 크기의 군집은 소멸
					 if(map[nr][nc].num > cur.num) {
						 map[nr][nc].total += cur.num; // cur은 이동하고 있는 애라서 가능
						 cur.isDead = true;
					 } else {
						 cur.total += map[nr][nc].total;
						 map[nr][nc].isDead = true;
						 map[nr][nc] = cur;
					 }
				 }
			}
			remainCnt = reset();
		}
		
		return remainCnt;
	}
	private static int reset() {
		int t = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == null) continue;
				// 군집이 있다면 정리
				 map[r][c].num = map[r][c].total;
				 t += map[r][c].num;
				 map[r][c] = null;
			}
		}
		return t;
	}
	
}
