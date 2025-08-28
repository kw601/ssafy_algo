import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  . 평지 (전차 들어갈 수 있음)
 *  * 벽돌벽
 *  # 강철벽
 *  - 물 (전차 들어갈 수 없음)
 *  ^ 위쪽을 바라보는 전차
 *  V 아래쪽 바라보는 전차
 *  < 왼쪽 바라보는 전차
 *  > 오른쪽 바라보는 전차
 *  
 *  U 방향 위쪽으로 바꾸고 위칸이 평지라면 그 칸으로 이동
 *  D 방향 아래쪽으로 바꾸고 아래칸 평지라면 그 칸으로 이동
 *  L 방향 왼쪽으로 바꾸고 왼쪽칸 평지라면 그 칸으로 이동
 *  R 방향 오른쪽으로 바꾸고 오른쪽칸 평지라면 그 칸으로 이동
 *  S 현재 바라보고 있는 방향으로 포탄 발사
 *  
 *  포탄
 *  벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진
 *  벽에 부딪히면 - 소멸
 *  벽돌벽 - 파괴-> 평지로 바뀜
 *  강철벽 : 아무일도 일어나지 않음
 *  
 *  T
 *  H, W // 높이, 너비
 *  
 */
public class Solution_1873_이영은 {
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int H, W, tx, ty;
	static char[][] field;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			field = new char[H][W];
			
			// 필드 저장하며 전차 좌표 저장
			for(int i=0; i<H; i++) {
				field[i] = (br.readLine()).toCharArray();
				for(int j=0; j<W; j++) {
					if(field[i][j]=='^' || field[i][j]=='v' || field[i][j]=='<' || field[i][j]=='>') {
						tx = i;
						ty = j;
						break;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			for(int i=0; i<N; i++) {
				char c = str.charAt(i);
				switch(c) {
				case 'U' :
					field[tx][ty] = '^';
					move(0);
					break;
				case 'D' :
					field[tx][ty] = 'v';
					move(1);
					break;
				case 'L' :
					field[tx][ty] = '<';
					move(2);
					break;
				case 'R' :
					field[tx][ty] = '>';
					move(3);
					break;
				case 'S' :
					shoot();
					break;
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(field[i][j]);
				}
				System.out.println();
			}
			
			
		}
	}
	
	public static void move(int dir) {
		int nx = tx + dx[dir];
		int ny = ty + dy[dir];
		
		// 범위 안에 있다면
		if(isIn(nx, ny)) {
			if(field[nx][ny] == '.') {
				field[nx][ny] = field[tx][ty];
				field[tx][ty] = '.';
				
				// 좌표 새로 바꾸기
				tx = nx;
				ty = ny;
			}
		}
		
	}
	
	
	public static void shoot() {
		int dir=0;
		
		// 전차 방향대로 dir 설정
		switch(field[tx][ty]) {
		case '^':
			dir=0;
			break;
		case 'v':
			dir=1;
			break;
		case '<':
			dir=2;
			break;
		case '>':
			dir=3;
			break;
		}
		
		int bx = tx;
		int by = ty;
		
		// 계속 돌면서 포탄 발사
		while(true) {
			bx = bx+dx[dir];
			by = by+dy[dir];
			
			//경계 나가면 리턴
			if(!isIn(bx, by)) {
				return;
			}
			
			if(field[bx][by] == '*') {
				field[bx][by] = '.';
				return;
			}else if(field[bx][by]=='#') {
				return;
			}
			
		}
	}
	
	static boolean isIn(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < H && ny < W);
	}
	

}
