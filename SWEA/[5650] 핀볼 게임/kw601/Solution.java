
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
	static HashMap<Integer, ArrayList<int[]>> wormholes = new HashMap<>();
	// �Ʒ� �� ������ ���� ����
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int maxCnt = 0;
	static int cnt, N;
	static int[][] map;
	static int sr, sc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
		    maxCnt = 0;
		    wormholes.clear();
		    
			N = Integer.parseInt(br.readLine());
			
			// ��Ȧ �ʱ� ����
			for(int i = 6; i <= 10; i++)
				wormholes.put(i, new ArrayList<int[]>());
			
			map = new int[N][N];

			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					// ��Ȧ ����
					if(temp == 6 || temp == 7 || temp == 8 || temp == 9 || temp == 10) {
						wormholes.get(temp).add(new int[] {i, j});
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int d = 0; d < 4; d++) {
						if(map[i][j] == 0) {
							cnt = 0;
							sr = i; sc = j;
							game(i, j, d);
							maxCnt = Math.max(maxCnt, cnt);
						}
					}
				}
			}
			System.out.println("#" + testCase + " " + maxCnt);
		}
	}
	
	public static void game(int cr, int cc, int delta) {
    int r = cr;
    int c = cc;
    int dir = delta;

    while (true) {
        int nr = r + deltas[dir][0];
        int nc = c + deltas[dir][1];

        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
            cnt++;
            if (dir == 0) dir = 1;
            else if (dir == 1) dir = 0;
            else if (dir == 2) dir = 3;
            else dir = 2;
            r = r;
            c = c;
            continue;
        }
        if (nr == sr && nc == sc) return;

        int cell = map[nr][nc];

        if (cell == -1) return;

        if (cell == 0) {
            r = nr; c = nc;
            continue;
        }

        // 블록(1~5)
        if (cell == 1) {
            cnt++;
            if (dir == 0) dir = 2;
            else if (dir == 1) dir = 0;
            else if (dir == 2) dir = 3;
            else dir = 1;
            r = nr; c = nc;
            continue;
        } else if (cell == 2) {
            cnt++;
            if (dir == 0) dir = 1;
            else if (dir == 1) dir = 2;
            else if (dir == 2) dir = 3;
            else dir = 0;
            r = nr; c = nc;
            continue;
        } else if (cell == 3) {
            cnt++;
            if (dir == 0) dir = 1;
            else if (dir == 1) dir = 3;
            else if (dir == 2) dir = 0;
            else dir = 2;
            r = nr; c = nc;
            continue;
        } else if (cell == 4) {
            cnt++;
            if (dir == 0) dir = 3;
            else if (dir == 1) dir = 0;
            else if (dir == 2) dir = 1;
            else dir = 2;
            r = nr; c = nc;
            continue;
        } else if (cell == 5) {
            cnt++;
            if (dir == 0) dir = 1;
            else if (dir == 1) dir = 0;
            else if (dir == 2) dir = 3;
            else dir = 2;
            r = nr; c = nc;
            continue;
        }

        // 6. 웜홀(6~10)
        if (cell >= 6 && cell <= 10) {
            ArrayList<int[]> wormhole = wormholes.get(cell);
            int mr = nr, mc = nc;
            for (int[] wh : wormhole) {
                if (wh[0] != nr || wh[1] != nc) {
                    mr = wh[0];
                    mc = wh[1];
                    break;
                }
            }
            if (r == sr && c == sc) return;

            r = mr; c = mc;
            continue;
        }
    }
}

}

// �Է�
// tc ��
// N
// �� ����

// ���
// #tc sco

// �ɺ��� ��� ��ġ�� ���ƿ��ų�, ����Ȧ�� ���� �� ����
// ���̳� ���Ͽ� �� �� �ε�������?
// ���ӿ��� ���� �� �ִ� ������ �ִ밪
// �ϳ��� �� �غ����ϳ�?

// ���� �� ����... ��...
// �� �̵��� �� �̵� ���⵵ ���� ������ �����ϳ�?
// dict�� ���: ����, �ٲ� ���� �־
// if d != ���� -> �״�� ����������
// else: �ٲ� ����?