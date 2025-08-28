import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 미생물
 * 이동방향: 상1 하2 좌3 우4
 * 1시간마다 이동방향에 있는 다음 셀로 이동
 * 약품이 칠해진 곳에 도착:미생물수/2 (0이되면 사라짐) -> 방향 이동 반대
 * 이동 후 두 개 이상 군집 한 셀에 모이면 합쳐짐 - 미생물 수 모두 합. 미생물 수 많은 군집의 이동방향
 * 최초에 약품 칠해진 곳에 미생물 군집 배치X
 * 최초에 둘 이상 군집이 동일한 셀에 배치되는 경우X
 * 
 * 테두리 약품 처리 -> 미생물수, 방향 처리
 * 방향별로 이동할 수 있도록
 * 위치별로 미생물 수와 이동방향 어떻게 저장..
 * 군집들 합쳐지는 것? 누가 제일 많은가?->방향 설정 위해
 * M시간 후 남아 있는 미생물의 수 합 출력
 * 
 * T
 * 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K 
 * K줄에 걸쳐 미생물 군집 정보 - 세로위치, 가로위치, 미생물 수, 이동 방향 (4개정수)
 */

public class Solution_2382_이영은 {
	// 0 상 하 좌 우
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] micro = new int[K][4]; // 미생물 좌표, 갯수, 방향 저장
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				micro[i][0] = Integer.parseInt(st.nextToken()); // x
				micro[i][1] = Integer.parseInt(st.nextToken()); // y
				micro[i][2] = Integer.parseInt(st.nextToken()); // 미생물 수
				micro[i][3] = Integer.parseInt(st.nextToken()); // 방향
			}
			
			for(int t=0; t<M; t++) {
				
				// 시간 지날때마다 미생물 방향대로 이동
				for(int m=0; m<K; m++) {
					int nx = micro[m][0] + dx[micro[m][3]];
					int ny = micro[m][1] + dy[micro[m][3]];
					
					if(isIn(nx, ny)) {
						micro[m][0] = nx;
						micro[m][1] = ny;
					}
					
					// 미생물이 약품에 닿을 경우
					if(nx==0 || nx==(N-1) || ny==0 || ny==(N-1)) {
						micro[m][2]/=2; // 미생물 반절로 바꿈
						micro[m][3]=reverse(micro[m][3]); // 방향 바꿔주기
					}
				}
				
				// 합치기 위한 정보 저장 위해 배열 생성..				
				int[][] sum = new int[N][N]; // 그 칸에서 미생물 합
				int[][] max = new int[N][N]; // 그 칸에서 최대값
				int[][] dir = new int[N][N]; // 최대 군집의 방향
				
				// 미생물끼리 합쳐지는 경우
				for(int m=0; m<K; m++) {
					if(micro[m][2]==0) continue; // 미생물 없는 칸 건너뛰기
					
					int x = micro[m][0];
					int y = micro[m][1];
					
					sum[x][y] += micro[m][2]; // (x,y)에서의 미생물 합 -> 겹친다면 합쳐야 하기 때문에
					
					// 최댓값을 가져와야 하기 때문에 비교
					if(micro[m][2]>max[x][y]) {
						max[x][y] = micro[m][2];
						dir[x][y] = micro[m][3];
					}
				}
				
				// 합쳐진 결과 반영
				for(int m=0; m<K; m++) {
					if(micro[m][2]==0) continue;
					
					int x = micro[m][0];
					int y = micro[m][1];
					
					if(micro[m][2]==max[x][y]) { // 가장 많은 군집이라면 더해진 값 가져가기
						micro[m][2] = sum[x][y];
						micro[m][3] = dir[x][y];
					} else { // 수 적은 군집은 사라짐
						micro[m][2] = 0;
					}
				}
			}
			
			int total=0;
			for(int i=0; i<K; i++) {
				total+=micro[i][2];
			}
			
			System.out.println("#" + tc + " " + total);
			
		}
		
	}
	
	static int reverse(int dir) {
		if(dir==1) return 2;
		else if(dir==2) return 1;
		else if(dir==3) return 4;
		return 3;
	}
	
	static boolean isIn(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<N) {
			return true;
		}
		return false;
	}

}
