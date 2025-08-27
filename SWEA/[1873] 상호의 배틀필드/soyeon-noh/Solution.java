import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static char[][] map;
	static int nowR, nowC;
	
	static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우 
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int H, W, N; // (2<=H, W<=20, 0<N<=100)
		
		

		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// 사방을 강철로 막을거야 
			map = new char[H+2][W+2];
			for (char[] m : map) {
				Arrays.fill(m, '#');	
			}
			
			
			// 인덱스 0과 H+1엔 강철이 들어있으므로
			// 1~H까지 for문 돌려서 입력받기 
			
			for(int r = 1; r < H+1; r++) {
				String stringInputLine = br.readLine();
//				map[r] = br.readLine().toCharArray(); // 추가 피드백 : 이걸 사용하면 2중for문없이 입력 가능 
				for(int c = 1; c < W+1; c++) {
					map[r][c] = stringInputLine.charAt(c - 1);
					if(map[r][c] == '^' || map[r][c] == 'v' || map[r][c] == '<' || map[r][c] == '>') {
						nowR = r;
						nowC = c;
					}
				}
			} // 필드 입력 끝
			
			// 추가 피드백 : 입력 확인 해보기
//			System.out.println(Arrays.deepToString(map)); // 2차원배열 확인 
			
			N = Integer.parseInt(br.readLine());
//			st = new StringTokenizer(br.readLine());
//			input = new int[N];
			char input;
			
			String command = br.readLine();
			for(int n = 0; n <N; n++) {
				input = command.charAt(n);
				
//				System.out.println("input: " + input);
				// 이동 구현 
				if(input == 'U') {
					move('^');
				} else if(input == 'D') {
					move('v');
				} else if(input == 'L') {
					move('<');
				} else if(input == 'R') {
					move('>');
				// 포탄 구현
				} else if (input == 'S') {
					shoot();
				}
			}// 사용자 입력 끝 
			
			
			
			sb.append("#").append(tc).append(" ");
			for(int i = 1; i < H+1; i++) {
				for(int j = 1; j < W+1; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}// 출력 끝
			
			
		}
		System.out.println(sb.toString()); // 오답노트 : 출력위치 주의하자 
	}

	private static void shoot() {
		int index = -1;
		if(map[nowR][nowC] == '^') index = 0;
		else if(map[nowR][nowC] == 'v') index = 1;
		else if(map[nowR][nowC] == '<') index = 2;
		else if(map[nowR][nowC] == '>') index = 3;
		
		// 포탄 좌표 
		int bx = nowR + dx[index];
		int by = nowC + dy[index];
		
		while(true) {
			if(map[bx][by] == '#') {
				break;
			}
			if(map[bx][by] == '*') {
				map[bx][by] = '.';
				break; // 오답노트 : 벽돌에부딪혔을 때 종료해줘야함. 
			}
			bx += dx[index];
			by += dy[index];
		}
	}

	private static void move(char way) { // 추가 피드백 : 파라미터가 index인게 깔끔하지 않니? 
		int index = -1; // 말도 안되는 값으로 초기화
		
		if(way == '^') index = 0;
		else if(way == 'v') index = 1;
		else if(way == '<') index = 2;
		else if(way == '>') index = 3;
		
		int nx = nowR + dx[index];
		int ny = nowC + dy[index];
		
		map[nowR][nowC] = way;
		if(map[nx][ny] == '.') {
			map[nowR][nowC] = '.';
			map[nx][ny] = way;
			
			nowR = nx;
			nowC = ny;
		}
	}
}
