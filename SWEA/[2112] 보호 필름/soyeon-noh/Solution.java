package com.codingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{ 
	// 이런 static 으로 만들어야하는지 어떻게 아는걸까. 일단 input값들을 넣으시는 듯
	
	static int D, W; // 행, 열
	static int K; // 합격 기준
	static int[][] film; // 2차원배열 필름 정보 
	
	static int[] A, B; // 각각 A, B 약품 주입시 갈아끼울 배열 
	
	static int ans; // 약품 최소 주입 횟수
	// 매번 목표 값을 ans로 명명하고 static 으로 넣어두신다
	// min으로 하는 게 코드만 읽었을 땐 나을 것 같은데 한번 이대로 해보겠다. 

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(input));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc = 1; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
		
			for(int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < W; c++) {
					film[r][c] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			// A, B 배열 준비 
			A = new int[W]; // 0으로 초기화되니 상관 x
			B = new int[W];
			Arrays.fill(B, 1); // 1로 Arrays.fill() 함수이용해 채우기 
			
			// 큰수로 초기화 
			ans = D;// 약품을 전부 다 넣었을 때의 값
			
			make(0,0);
			
			System.out.println("#" + tc + " " + ans);
			
			
		}
	}

	// cnt : 지금까지 약품을 넣은 횟수
	// r : 현재 행
	private static void make(int r, int cnt) {
		if(check()) {
			ans =  Math.min(ans, cnt);
			return;
		}
		if(ans < cnt) return; // 가지치기 - 이미 최소답을 초과한 경우 
		if(r == D) return; // 종결조건 - 행을 전부 탐색한 경우
		
		// 재귀 파트
		int[] tmp = film[r]; // 원상복구를 위해 행을 보관하기
		
		// 1. 주입 X
		make(r + 1, cnt); // 행은 +1, 주입수는 그대로 
		
		// 2. A주입
		film[r] = A; // r행을 A배열로 대채
		make(r + 1, cnt + 1);
		
		// 3. B주입
		film[r] = B; // r행을 B배열로 대체
		make(r + 1, cnt + 1); 
		
		// 4. 원상 복구
		film[r] = tmp;
	}

	// 막검사 
	private static boolean check() {
		// 열우선순회방식 사용 
		for(int c = 0; c < W; c++) {
			boolean flag = false; // 통과했는지 플래그 
			int cnt  = 1; // 연속된 값이 K개 있는지 확인 
			for(int r = 1; r < D; r++) { // 윗줄과 비교하기 위해 1부터 반복 
				if(film[r][c] == film[r - 1][c]) {// 윗줄과 내가 똑같아. 
					cnt++;
				} else {
					cnt = 1; // 윗줄이랑 다르면 나부터 카운팅 1시작 
				}
				
				if(cnt == K) { // 품질 통과하면 
					flag = true;
					break;
				}
				
			}// 한개의 열검사를 끝냄 
			if (!flag) { //통과하지 못한 경우
				return false; // 다른 열을 검사해도 의미가 없음. 
			}
		}// 모든 열의 검사를 끝냄 
		return true; // return을 만나지 않았다면 전부 통과된 것 
	}
	
	static String input = "10\r\n" + "6 8 3\r\n" + "0 0 1 0 1 0 0 1\r\n" + "0 1 0 0 0 1 1 1\r\n" + "0 1 1 1 0 0 0 0\r\n"
            + "1 1 1 1 0 0 0 1\r\n" + "0 1 1 0 1 0 0 1\r\n" + "1 0 1 0 1 1 0 1\r\n" + "6 8 3\r\n"
            + "1 1 1 1 0 0 1 0\r\n" + "0 0 1 1 0 1 0 1\r\n" + "1 1 1 1 0 0 1 0\r\n" + "1 1 1 0 0 1 1 0\r\n"
            + "1 1 0 1 1 1 1 0\r\n" + "1 1 1 0 0 1 1 0\r\n" + "6 8 4\r\n" + "1 1 0 0 0 1 1 0\r\n"
            + "1 0 1 0 0 1 1 1\r\n" + "0 1 0 0 1 1 0 0\r\n" + "1 0 1 0 0 0 0 0\r\n" + "1 1 0 0 0 0 0 0\r\n"
            + "1 0 0 0 1 1 1 1\r\n" + "6 4 4\r\n" + "1 1 0 0\r\n" + "0 1 0 1\r\n" + "0 0 0 1\r\n" + "1 1 1 1\r\n"
            + "1 1 0 1\r\n" + "1 0 1 0\r\n" + "6 10 3\r\n" + "0 1 0 0 0 1 0 0 1 1\r\n" + "0 1 1 0 0 1 0 0 1 0\r\n"
            + "0 1 0 0 1 0 1 1 1 1\r\n" + "0 0 0 0 0 1 1 1 1 0\r\n" + "0 1 0 0 1 1 1 1 1 1\r\n"
            + "1 0 0 0 1 1 0 0 1 1\r\n" + "6 6 5\r\n" + "0 0 0 0 0 0\r\n" + "0 0 0 0 0 0\r\n" + "0 0 0 0 0 0\r\n"
            + "0 0 0 0 0 0\r\n" + "0 0 0 0 0 0\r\n" + "0 0 0 0 0 0\r\n" + "6 6 4\r\n" + "1 1 1 1 1 1\r\n"
            + "0 0 0 0 0 1\r\n" + "0 1 1 1 0 1\r\n" + "0 1 0 1 0 1\r\n" + "0 1 0 0 0 1\r\n" + "0 1 1 1 1 1\r\n"
            + "8 15 3\r\n" + "0 1 1 0 0 1 1 0 1 1 0 0 0 0 0\r\n" + "1 0 0 0 1 1 0 0 0 0 0 1 0 1 1\r\n"
            + "1 1 0 1 0 1 0 1 0 1 0 1 0 0 0\r\n" + "0 1 1 1 0 0 1 0 0 0 0 1 0 0 0\r\n"
            + "0 0 0 0 0 0 1 0 0 0 1 1 0 0 1\r\n" + "1 0 1 0 0 1 0 1 1 1 1 0 1 1 1\r\n"
            + "0 0 0 0 0 1 1 1 0 0 0 0 0 1 0\r\n" + "0 0 1 0 1 1 0 1 1 0 0 0 1 0 0\r\n" + "10 20 4\r\n"
            + "1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1\r\n" + "1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0\r\n"
            + "1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0\r\n" + "0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0\r\n"
            + "0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1\r\n" + "1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0\r\n"
            + "0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1\r\n" + "1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0\r\n"
            + "0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1\r\n" + "0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0\r\n"
            + "13 20 5\r\n" + "1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0\r\n"
            + "1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0\r\n" + "1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0\r\n"
            + "0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1\r\n" + "0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1\r\n"
            + "0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1\r\n" + "0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0\r\n"
            + "1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1\r\n" + "0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1\r\n"
            + "0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1\r\n" + "0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0\r\n"
            + "0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0\r\n" + "0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0\r\n" + "";

}
