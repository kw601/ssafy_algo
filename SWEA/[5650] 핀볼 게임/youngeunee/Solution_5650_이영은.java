import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_5650_이영은 {
	
	final static int TOP=0, RIGHT=1, BOT=2, LEFT=3;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N;
	static int[][] map = new int[100][100];
	
	static int[][] wormhole = new int[11][2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			for(int i=6; i<=10; i++){
				Arrays.fill(wormhole[i], -1);
			}
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					//웜홀 저장
					if(map[i][j] >= 6){
						int num = map[i][j];
						
						// 2차원 정보 -> 1차원 정보
						if(wormhole[num][0] == -1) {
							wormhole[num][0] = i*N + j;
						} else {
							wormhole[num][1] = i*N + j;
						}
					}
				}
			}
			
			int maxScore=0; 
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!=0) continue;
					
					for(int dir=0; dir<4; dir++) {
						
						int score = getScore(i,j,dir);
						
						maxScore = Math.max(maxScore, score);
					}
				}
			}
			System.out.println("#" + tc + " " + maxScore);
		}
	}
	
	private static int getScore(int sr, int sc, int dir) {
		int score = 0;
		
		int r = sr;
		int c = sc;
		while(true) {
			
			r += dr[dir];
			c += dc[dir];
			
			
			if(r<0 || r>=N || c<0 || c>=N) {
				dir = (dir+2)%4;
				score++;
				continue;			
			}
			
			// 종료
			if(map[r][c] == -1) break;
			if(r == sr && c == sc) break;
			
			// 블록 1~5
			if(map[r][c]==1){
				if(dir == TOP || dir == RIGHT) dir = (dir+2)%4;
				else if(dir == BOT) dir = RIGHT;
				else dir = TOP;
				score++;
			}else if(map[r][c] == 2){
				if(dir == BOT || dir == RIGHT) dir = (dir+2)%4;
				else if(dir == LEFT) dir = BOT;
				else dir = RIGHT;
				score++;
			}else if(map[r][c]==3){
				if(dir == BOT || dir == LEFT) dir = (dir+2)%4;
				else if(dir == RIGHT) dir = BOT;
				else dir = LEFT;
				score++;
			}else if(map[r][c] == 4){
				if(dir == TOP || dir == LEFT) dir = (dir+2)%4;
				else if(dir == RIGHT) dir = TOP;
				else dir = LEFT;
				score++;
			}else if(map[r][c] == 5) {
				dir = (dir+2)%4;
				score++;
			}
			
			// 웜홀
			else if(6<=map[r][c]) {
				int num = map[r][c];
				if( wormhole[num][0] == (r*N+c) ) {
					r = wormhole[num][1] / N;
					c = wormhole[num][1] % N;
				}else{
					r = wormhole[num][0] / N;
					c = wormhole[num][0] % N;
				}
			}
		}
		return score;
	}
}