import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set =  new HashSet<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 넣어 주기
        for(int i = 0; i < N; i++) {
        	set.add(Integer.parseInt(st.nextToken()));
        }
        
        int M = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
        	boolean isFound = set.contains(Integer.parseInt(st.nextToken()));
        	if(isFound) {
        		System.out.println(1);
        	} else {
        		System.out.println(0);
        	}
        }
	}
}
// 입력
// N
// 숫자 N개 <- db
// M
// 숫자 M개 <- 찾을 친구

// 출력
// 있으면 1 없으면 0

// 생각
// num in arr1 -> 1 else 0
