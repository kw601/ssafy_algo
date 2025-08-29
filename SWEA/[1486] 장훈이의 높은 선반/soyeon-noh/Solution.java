package com.codingtest.swea;

import java.util.Scanner;

public class Solution {
    static int N, B, ans; // 몇명인지, 높이제한은 몇인지, 정답은 뭔지...
    static int[] H; // 점원들의 키

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            B = sc.nextInt();
            H = new int[N];
            ans = 987654321;

            int rs = 0; //모든 점원들의 키합
            for (int i = 0; i < N; i++) {
                H[i] = sc.nextInt();
                rs += H[i];
            }
            
            powerset(0, 0, rs);
                
            // 입력끝
            System.out.println("#" + tc + " " + ans);
        } // tc
    }// main
    
    //idx : 현재 참여시킬지 말지 결정하는 점원
    //sum : 내가 지금까지 쌓아온 탑의 높이
    //rsum : 앞으로 내가 쌓을 수 있는 탑의 높이
    public static void powerset(int idx, int sum, int rsum) {
        if(sum > ans) return; //내가 이미 알고 있는 높이가 지금 만들어진 경우보다 더 작다면 할필요없음
        if(sum + rsum < B) return; //지금까지 쌓아온 탑의 높이와 남아있는 탑의 높이를 더했는데 B(최소기준치) 넘지 못한다면
        
        if(idx == N) {
            //이번에 만들어진 연산 해가지고 체킹해서 블라블라
            if(sum>=B) ans=Math.min(ans, sum);
            return;
        }
        
        powerset(idx+1, sum+H[idx], rsum-H[idx]); //쌓고
        powerset(idx+1, sum, rsum-H[idx]);          //안쌓고
    }
    
    
    
    
    
}

