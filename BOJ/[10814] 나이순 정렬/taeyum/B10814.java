import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N개의 줄
// 회원의 나이, 이름
// 나이순, 나이가 같으면 가입한 순으로 출력
public class B10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N줄 입력
        int N = Integer.parseInt(st.nextToken());

        // 회원의 이름과 나이를 저장할 User 클래스 배열
        User[] users = new User[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            users[i] = new User(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        // Comparable 구현된 클래스이므로 sort 사용가능
        Arrays.sort(users);

        // 출력쓰
        for (User user : users) {
            System.out.println(user.age + " " + user.name);
        }


    }
    // User 클래스 -> Comparable 구현
    static class User implements Comparable<User>{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return Integer.compare(this.age, o.age);
        }
    }
}
