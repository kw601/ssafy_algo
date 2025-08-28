import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static int H;
	static int W;
	static int cx;
	static int cy;
	
	public static void main(String[] argus) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		// 1 ~ T번 반복
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 지도 입력
			char[][] maps = new char[H][W];
			for(int i = 0; i < H; i++) {
				String row = br.readLine();
				for(int j = 0; j < W; j++) {
					char cur = row.charAt(j);
					maps[i][j] = cur;
					
					// 전차의 위치 확인
					if(cur == '^' || cur == 'v' || cur == '<' || cur == '>') {
						cx = i;
						cy = j;
					
					}
				}
			}
			// 길이 입력
			int len = Integer.parseInt(br.readLine());
			// 명령어 입력
			String command = br.readLine();
			
			for(int i = 0; i < len; i++) {
				// 한 글자씩 명령어 꺼내기
				char cmd = command.charAt(i);
				
				// cmd가 S면 제자리에서 발사
				if (cmd == 'S') {
					shoot(cx, cy, maps);
				} else { // 나머지는 각자 방향으로 이동
					move(cmd, maps);
				}
			}
			System.out.print("#" + test_case+ " ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					System.out.print(maps[i][j]);
				}
				System.out.println();
			}
		}
	}
	public static void move(char cmd, char[][] maps) {
		char cur = maps[cx][cy];

		// 움직일 방향
		Direction dir = Direction.valueOf(Character.toString(cmd));
		
		int dx = dir.getDx();
		int dy = dir.getDy();
		
		int nx = cx + dx;
		int ny = cy + dy;
		
		// 움직일 수 있으면
		if(nx >= 0 && nx < H && ny >= 0 && ny < W && maps[nx][ny] == '.') {
			maps[cx][cy] = '.';
			cx = nx;
			cy = ny;
			maps[cx][cy] = dir.getSymbol();
		} else {
			maps[cx][cy] = dir.getSymbol();
		}
	}
	public static void shoot(int cx, int cy, char[][] maps) {
		char cur = maps[cx][cy];
		
		// 바라보는 방향 꺼내기
		int dx = Direction.fromSymbol(cur).getDx();
		int dy = Direction.fromSymbol(cur).getDy();
		
		int nx = cx + dx;
		int ny = cy + dy;
		
		// 맵 밖으로 나가지 않는 동안
		while(nx >= 0 && nx < H && ny >= 0 && ny < W) {
			if (maps[nx][ny] == '*') {
				maps[nx][ny] = '.';
				break;
			} else if(maps[nx][ny] == '#') {
				break;
			}
			 nx += dx;
			 ny += dy;
		}
	}
}
enum Direction{
	U('^', -1, 0),
    D('v', 1, 0),
    L('<', 0, -1),
    R('>', 0, 1);
	
	private final char symbol;
	private final int dx;
	private final int dy;
	
	Direction(char symbol, int dx, int dy){
		this.symbol = symbol;
		this.dx = dx;
		this.dy = dy;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	
	public static Direction fromSymbol(char symbol) {
        for (Direction d : values()) {
            if (d.symbol == symbol) return d;
        }
        
        return null;
	}
}
// 문제 이해
// . 평지
// * 벽돌 벽
// # 강철 벽
// - 물(출입 불가능)
// ^	위쪽을 바라보는 전차(아래는 평지이다.)
// v	아래쪽을 바라보는 전차(아래는 평지이다.)
// <	왼쪽을 바라보는 전차(아래는 평지이다.)
// >	오른쪽을 바라보는 전차(아래는 평지이다.)

//U	Up : 방향을 위쪽으로 바꾸고, 평지라면 위로 한 칸 이동
//D	Down : 방향을 아래쪽으로 바꾸고 이동
//L	Left : 방향을 왼쪽으로 바꾸고 이동
//R	Right : 방향을 오른쪽으로 바꾸고 이동
//S	Shoot : 바라보는 방향으로 빵

// 포탄은 벽을 만날 때까지 직진
// if) 벽돌 벽이면 평지가 됨
// 모든 일이 일어난 후 상태는?