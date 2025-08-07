import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        // 나중에 계산 하니까 double 저장
        ArrayList<Double> scores = new ArrayList<>();
        
        // 읽어서 sco 배열에 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            scores.add(Double.parseDouble(st.nextToken()));
        }
        
        double maxScore = Collections.max(scores);
        // 평균 다시 계산
        double sum = 0;
        for(int i = 0; i < n; i++){
            scores.set(i, scores.get(i) / maxScore * 100);
            sum += scores.get(i);
        }

        double avg = sum / n;
        
        System.out.println(avg);
    }
}

// 풀이 생각
// 입력 받고 -> Max 찾고 -> 다시 계산해서 넣어주기 -> 평균 찾기
// 시간복잡도... 정렬 함수의 시간 복잡도를 모르겠는데.... O(n + n) -> O(n)?