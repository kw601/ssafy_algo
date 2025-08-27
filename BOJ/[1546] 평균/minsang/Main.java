import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 과목 수 N 입력받기
        int N = Integer.parseInt(br.readLine());
        // 점수 배열 생성
        int[] scores = new int[N];
        // 공백 기준으로 점수 배열 나누기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;

        // 점수 입력 받으면서 최댓값 갱신 -> 최댓값 찾기
        for(int i = 0; i < N; i++){
            scores[i] = Integer.parseInt(st.nextToken());
            if(scores[i] > max){
                max = scores[i];
            }
        }

        // 모든 점수 : 점수/M*100 => 합
        double sum = 0;
        for (double score : scores){
            sum += (double) score / max * 100;
        }

        double avg = sum / N;

        System.out.println(avg);
    }
}