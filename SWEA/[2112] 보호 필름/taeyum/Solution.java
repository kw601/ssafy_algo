import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int W;
	static int D; 
	static int K; 
	static int Min;
	static int[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			Min = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (K == 1 || check()) {
				System.out.println("#" + test_case + " " + 0);
			} else {
				dfs(0, 0);
				System.out.println("#" + test_case + " " + Min);
			}

		}
	}

	static void dfs(int row, int inputCnt) {
		// 투약 횟수가 Min 보다 많아지면 의미가 없으므로 return 한다.
		if (inputCnt >= Min) {
			return;
		}

		if (row == D) {
			if (check()) {
				Min = Math.min(Min, inputCnt);
			}
			return;
		}

		int[] originalRow = new int[W];
		for (int i = 0; i < W; i++) {
			originalRow[i] = film[row][i];
		}

        // 약품 투여 하지 않고 넘어감
		dfs(row + 1, inputCnt);

		// A 약품 
		for (int i = 0; i < W; i++) {
			film[row][i] = 0;
		}
		dfs(row + 1, inputCnt + 1);

        // B 약품
		for (int i = 0; i < W; i++) {
			film[row][i] = 1;
		}
		dfs(row + 1, inputCnt + 1);

        // 원래상태
		for (int i = 0; i < W; i++) {
			film[row][i] = originalRow[i];
		}
	}

	static boolean check() {
		for (int col = 0; col < W; col++) {
			int count = 1;
			boolean passed = false;
            
			for (int row = 0; row < D - 1; row++) {
				if (film[row][col] == film[row + 1][col]) {
					count++;
				} else {
					count = 1;
				}
                
				if (count >= K) {
					passed = true;
					break;
				}
			}
            
			if (!passed) {
				return false;
			}
		}
		return true; 
	}
}