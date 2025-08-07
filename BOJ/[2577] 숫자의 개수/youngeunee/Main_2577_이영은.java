package study.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2577_이영은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] numbers = new int[10];
		int answer = 1;
		
		// 숫자 입력 후 곱하기
		for(int i=0; i<3; i++) {
			answer *= Integer.parseInt(br.readLine());
		}
		
		String str = Integer.toString(answer);
		
		// 숫자 카운트
		for(int i=0; i<str.length(); i++) {
			for(int j=0; j<10; j++) {
				if((str.charAt(i)-'0')==j) {
					numbers[j]++;
				}
			}
		}
		
		// 정답 출력
		for(int number : numbers) {
			System.out.println(number);
		}
		
	}

}
