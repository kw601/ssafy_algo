package study.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1546_이영은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] score = new int[N];

		// 점수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		// 최댓값 구하기
		Arrays.sort(score);
		int M = score[N-1];
		
		double[] nScore = new double[N];
		double sum = 0;
		
		// 새로운 점수 구하기
		for(int i=0; i<N; i++) {
			nScore[i] = (double)score[i]/M*100;
			sum += nScore[i];
		}
		
		System.out.println(sum/N);
		

	}

}
