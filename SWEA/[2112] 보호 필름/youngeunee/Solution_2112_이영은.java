import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_이영은 {
    
	static int D, W, K, min;
	static int[][] film;
	static int[] A, B;

	
    public static void main(String[] args) throws NumberFormatException, IOException {
		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1; tc<=T; tc++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		D = Integer.parseInt(st.nextToken()); // 행
    		W = Integer.parseInt(st.nextToken()); // 열
    		K = Integer.parseInt(st.nextToken()); // 합격기준
    		
    		film = new int[D][W];
    		
    		A = new int[W]; // A약품 주입 시
    		B = new int[W]; // B약품 주입 시
    		Arrays.fill(B, 1);
    		
    		
    		// film 입력
    		for(int i=0; i<D; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j=0; j<W; j++) {
    				film[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		min = K;
    		dfs(0, 0);
    		System.out.println("#" + tc + " " + min);
    	}
    	
    	
    }
    
    
    public static void dfs(int r, int cnt) { // r: 지금 돌고있는 행, cnt: 약물 주입 횟수
    	// 이미 구한 최소 횟수보다 넘어가면 가지치기
    	if(cnt >= min) return;
    	
    	if(r == D) { // 모든 행 다 돈 경우 그만
    		if(!isPassed()) return;
  
    		min = Math.min(min, cnt);
    		return;
    	}
        	
    	// 넣지 않는 경우
    	dfs(r+1, cnt);
    	int []temp = film[r];
    	
    	// A(0) 넣는 경우
    	film[r] = A;
    	dfs(r+1, cnt+1);
    	
    	// B(1) 넣는 경우
    	film[r] = B;
    	dfs(r+1, cnt+1);
    	
    	film[r] = temp; // 원본 배열 되돌리기..
    }
    
    public static boolean isPassed() {
    	if(K==1) return true;
    	
    	for(int j=0; j<W; j++) {
    		boolean flag = false;
    		int cnt=1;
    		for(int i=1; i<D; i++) {
    			if(film[i][j] == film[i-1][j]) {
    				cnt++;
    			}else {
    				cnt=1;
    			}
    			
    			if(cnt==K) {
    				flag = true;
    				// K만족하면 남은 행 탐색 종료 -> 다음 열로 넘어감
    				break;
    			}
    		}
    		// 이걸 생략한다면 불합격->합격 순서일 때 그냥 합격으로 결과가 나올 수 있음.. 반드시 필요
    		if(!flag) { 
    			return false;
    		}
    	}
    	return true;
    }

}