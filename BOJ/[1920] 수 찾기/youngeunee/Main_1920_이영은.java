package study.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1920_이영은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N 개의 정수
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int B = Integer.parseInt(st.nextToken());
			
			if(check(A, B)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}

	}
	
	// 방식을 생각하긴 했는데 구현하는 방법을 몰라서 구글링 했습니
	public static boolean check(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		
		while(left <= right) {			
			int mid = (left+right) / 2;
			
			if(arr[mid]==target) {
				return true;
			} else if(arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return false;
	}

}
