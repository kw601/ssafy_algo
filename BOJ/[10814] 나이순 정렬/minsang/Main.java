import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 회원 수 N 입력받기
        int N = Integer.parseInt(br.readLine());

        // 배열 선언
        String[][] arr = new String[N][2];

        // br.readLine() 한 줄 전체 입력 받아오기
        // 공백 기준으로 나눠서 토큰을 만들어줌
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();
        }

        // Arrays.sort(정렬하려는 변수 이름, new Comparator<타입>())
        Arrays.sort(arr, new Comparator<String[]>() { // 배열 정렬
            @Override
            public int compare(String[] o1, String[] o2){ // 두 사람의 정보 비교
                // 나이가 같으면 o1 먼저 정렬
                if(o1[0].equals(o2[0])){
                    return 0;
                } else {
                    // 아닌 경우 (ex: 21 - 20 = 1 -> o2 먼저)
                    return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
                }
            }
            
        });

        for(int i = 0; i <N; i++){
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}