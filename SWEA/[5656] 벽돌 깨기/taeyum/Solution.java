import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int W;
	static int H;
	static int min;
	static int[] dx = {-1,1,0,0}; // 상, 하, 좌, 우 
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			
			int[][] map = new int[H][W];
			for(int i = 0 ; i < H ; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0 ; j < W ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(map, 0);
			System.out.println("#" + test_case + " " + min);
			
		}
	}
	
	// 백트래킹으로 dfs
	// blockBreak 으로 배열 map 부숴버림 (제일 적게 count 되면 됨)
	static void dfs(int[][] map, int cnt) {
		
		if(cnt == N) {
			min = Math.min(min, blockCount(map));
			return;
		}
		
		for (int i = 0 ; i < W ; i++) {
			int[][] nextMap = copyMap(map);
			
			int j = 0;
			while(j < H && nextMap[j][i] == 0) {
				j++;
			}
			
			if(j < H) {
				BlockBreak(nextMap, j, i);
				afterMap(nextMap);
			}
			
			dfs(nextMap, cnt + 1);
		}
		
		
		
	}
	
	
	// 매개변수로 좌표를 받아올까? -> ㅇㅇ 그러자
	// 이걸 재귀, bfs 로 해야하나? -> 그래야할거같은데?
	// -> 매개변수로 반복 횟수도 넘겨야해? -> 아마도?
	// 이걸 메인 문에서 N번 반복해? -> 근데 주의해야할게 좌우로만 움직임 -> 그건 여기서 생각해야할까? -> 아닌거같음
	// 일단 여기는 십자 형태로 부수는 것만 생각하자
	static void BlockBreak(int[][] map, int x, int y) {
		int range = map[x][y];
				
		Queue<int[]> queue = new ArrayDeque();
		queue.offer(new int[] {x,y,range});
		map[x][y] = 0;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int length = current[2];
			
			for(int i = 0 ; i < 4 ; i++) {
				for(int j = 1 ; j < length ; j++) {
					int nr = r + dx[i] * j;
					int nc = c + dy[i] * j;
					
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
						int nextLength = map[nr][nc];
						
						if(nextLength > 1) {
							queue.offer(new int[] {nr, nc, nextLength});
						}
					map[nr][nc] = 0;
					}
				}
			}
		}
	}
	
	
	
	// 전체 블럭 갯수 세기
	static int blockCount(int[][] map) {
		int count = 0;
		for (int[] is : map) {
			for (int num : is) {
				if(num != 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	
	
	// 구슬로 폭파 후 맵 상태 업뎃
	// 원복 상태는...? 이거 안되면 원복해야하는뎅 새로운 배열 만들어서 그걸로할까?
	// 새로운 배열을 만든다고 되냐? 음... 원래상태 맵 두고 -> 새로운 배열로 하다가
	// 안되겠네 폭파 -> 흠...
	// 일단 해보죠
	// 내 생각으로는 copyMap 쓴다고 해도 다시 초기화... 어우 대가리아프네...
	// 일단 다른거 부터 ㄱㄱ 가 아니라 이거 부터 해야 할거같은데 
	// 폭파된 블럭은 0으로 바꾸고 map 에 넣어줘야 됨
	static void afterMap(int[][] map) {
		for(int c = 0 ; c < W ; c++) {
			int emptyRow = H - 1;
			for(int r = H - 1 ; r >= 0 ; r--) {
				if(map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[emptyRow][c] = temp;
					emptyRow--;
				}
			}
		}
	}
	
	static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[H][W];
		for(int i = 0 ; i < H ; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, W);
		}
		return newMap;
	}
}
