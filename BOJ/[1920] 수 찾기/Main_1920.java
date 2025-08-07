// 수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 아이디어 
// - 완탐을 하는 경우 100,000^2이므로 시간초과
// - 배열 정렬을 통한 이분 탐색이 필요함
// 자료형 범위
// - 모든 정수 범위는 int로 해결 가능
// 알고리즘 분류
// - 정렬, 이분 탐색, 해시, 집합과 맵
public class Main_1920 {

	static int N,M;
	static int[] narr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		// N과 N배열 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		narr = new int[N];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			narr[i] = Integer.parseInt(st.nextToken());
		}
		
		// M과 M배열 입력
		// 시간초과 계속떠서 M배열 저장을 없애고 바로 처리로 변경
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		// 1. n배열 정렬
		Arrays.sort(narr);
		
		// 2. 이분 탐색 함수 실행해서
		// 			true -> 1
		// 			false -> 0 출력
		
		
		StringBuilder sb = new StringBuilder(); // 시간초과떠서 변경
		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			if(binarySearch(target)) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.print(sb.toString()); // ln으로했을 때 개행문자 조심
		
	}
	
	// 이분 탐색 함수
	// 타겟과 배열을 받아옴
	// target이 arr에 존재하는 지 확인 후
	// 존재하면 true, 없으면 false
	public static boolean binarySearch(int target) {
		int start = 0;  // 탐색 시작 인덱스
		int end = narr.length - 1; // 탐색 끝 인덱스
		
		// 시작이 끝보다 커지지 않는 동안 계속 
		while(start <= end) {
			
			// 중간 위치를 구한다. 짝수인 경우 더 작은 값
			int mid = (start+end)/2;
			
			// 타겟이 중간보다 작아
			// => mid보다 더 작은 곳을 탐색해야해
			if(narr[mid] > target) {
				end = mid - 1;
			// 타겟이 중간보다 커
			// => mid보다 더 큰 곳을 탐색해야해
			} else if(narr[mid] < target) {
				start = mid + 1;
			} else {
				return true;
			}
		}
		return false;
	}
}
