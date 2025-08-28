import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 나무 (2~100)
 * 홀수날 물 -> +1
 * 짝수날 물 -> +2
 * 하루에 한 나무에 물 줄 수 있
 * 물 주지 않아도 ㄱㅊ
 * 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜 수
 * 나무의 초기 높이 (1~120)
 * 
 */


public class Solution_14510_이영은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] tree = new int[N];
			int max=0;
			
			// 나무 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(tree[i], max);
			}
			
			int odd=0; // 홀수
			int even=0; // 짝수
			
			// 높이 맞출때 필요한 홀수 짝수 갯수 구하기
			for(int i=0; i<N; i++) {
				odd += (max - tree[i]) % 2;
				even += (max - tree[i]) / 2;
			}
			
			int result=0;
			while(odd!=0 || even!=0) {
				result++;
				if(result%2==1) { // 홀수라면
					if(odd>0) {
						odd--;
					}else if(even>1) {
						even--;
						odd++;
					}
				}else { // 짝수라면
					if(even>0) {
						even--;
					}
				}
			}
			
			
			
			System.out.println("#" + tc + " " + result);
			
		}

	}

}
