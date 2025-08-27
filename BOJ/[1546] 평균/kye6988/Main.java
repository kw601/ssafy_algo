package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

// 아이디어: 모든 점수를 저 방정식을 활용해서 계산한다음 평균을 내면 될듯
// 사용자에게 받을 때 먼저 String으로 받은 다음 토크나이저만 for로 리스트에 받아서 저장시키면서 max값을 뽑기
// 다시 리스트로 for문 돌려서 integer로 바꿔서 다시 계산해서 바로 double 변수 하나에 저장
// 평균낼 때 double형으로 써야 할 듯

// 시간복잡도: for 각각 두 번이니까 O(n)일 듯? 10^3*2

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		double max = 0;
		double new_num = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			max = Math.max(max, now);
			list.add(now);
		}
		for (int i = 0; i < N; i++) {
			new_num += list.get(i)/max*100;
		}
		new_num /= N;
		System.out.println(new_num);
	}

}
