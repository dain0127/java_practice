import java.util.Scanner;
import static java.lang.System.out;

class MyQuiz {
    public static vodi main(String args[]){
        //String 배열 길이 100개를 만들고, 각각 index에 해당하는 문자열을 저장한다.
        //이후 index에 해당하는 문자열에 들어간 숫자를 모두 합한 결과를 출력해라.
        Scanner sc = new Scanner(System.in);
        out.println("정수 입력 : ");
        int arr1Len = sc.nextInt();

        String[] strArr = new String[arr1Len];
        for(int i=0;i<arr1Len;i++){
            strArr[i] = String.valueOf(i);
        }

        for(int i=0;i<arr1Len;i++){
            int strLen = strArr[i].length();
            //out.println("str : "+ strArr[i] + "\nlen : "+ strLen);
            StringBuilder sb = new StringBuilder(strArr[i]);
            for(int j=0;j<strLen-1;j++){
                sb.insert(j*2+1, " ");
            }
            strArr[i] = sb.toString();
        }

        for(int i=0;i<arr1Len;i++)
            out.println(strArr[i]);

        int sum = 0;
        for(int i=0;i<arr1Len;i++){
            sc = new Scanner(strArr[i]);
            int strLen = strArr[i].length();
            for(int j=0;j<strLen/2 + 1;j++){

                sum += sc.nextInt();
            }
        }
        out.println("sum : " + sum);
    }
}
