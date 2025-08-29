import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N; // 나무 수
	static int[] trees;
	static int[] oddEven = new int[3]; // oddEven[1] : 필요한 1의 수, oddEven[2] 필요한 2의 수
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int max = -1;
			int num;
			trees = new int[N];
			
			for(int n = 0; n < N; n++) {
				trees[n] = Integer.parseInt(st.nextToken());
				if(max < trees[n]) {
					max = trees[n];
				}
			}
			
			for(int n = 0; n < N; n++) {
				oddEven[2] += (max - trees[n])/2;
				oddEven[1] += (max - trees[n])%2;
			}
			
			int day = 0;

			while(oddEven[1] != 0 || oddEven[2] != 0) {
				
				++day;
				
				if(day%2 == 1) {
					if(oddEven[1] > 0) {
						oddEven[1] -= 1;
						
					}else if(oddEven[2] > 1) {
						oddEven[2] -= 1;
						oddEven[1] += 1;
					}
					
					
				} else if(day%2 == 0 && oddEven[2] > 0){
					oddEven[2] -= 1;
				}
			}
			
			// 초기화
			Arrays.fill(oddEven, 0);
			
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		System.out.println(sb.toString());
	}
}
