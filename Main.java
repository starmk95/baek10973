import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] seq = new int[n];
        for (int i=0;i<n;i++) seq[i] = sc.nextInt();
        int[] ans = prev_permutation(seq);
        if (ans == null) System.out.println(-1);
        else {
            for (int i=0;i<ans.length;i++) System.out.print(ans[i] + " ");
            System.out.println();
        }
    }

    // 이전 순열(permutation) 구하는 알고리즘(메소드)
    // 다음 순열을 구하는 알고리즘에서 부들호들만 반대로 바꾸어서 구현해주면 된다.
    // 1. A[i-1] > A[i]를 만족하는 가장 큰 i를 찾는다.
    //   (뒤에 오른차순의 수만 남는 첫번째 자리를 구하기)
    // 2. j<=i이면서 A[j]<A[i-1]을 만족하는 가장 큰 j를 찾는다.
    //   (i 뒤에 있는 수들 중 i자리 값과 차이가 가장 적게 작은 값을 찾기)
    // 3. A[i-1]과 A[j]를 swap한다.
    // 4. A[i]부터 순열을 뒤집는다.
    //   (i자리부터 내림차순으로 만들어줌 -> 해당 자리 기준 마지막 순열로 만듬)
    static int[] prev_permutation(int[] seq) {
        int indexI = 0;
        // 1단계 (i 찾기)
        for (int i=seq.length-1;i>0;i--) {
            if (seq[i-1] > seq[i]) {
                indexI = i;
                break;
            }
        }
        if (indexI == 0) {
            return null;
        }
        // 2단계 (j 찾기)
        int maxJ = -1;
        int indexJ = indexI;
        for (int j=indexI;j<seq.length;j++) {
            if (seq[indexI-1] > seq[j]) {
                if (maxJ == -1 || seq[j] > maxJ) {
                    maxJ = seq[j];
                    indexJ = j;
                }
            }
        }
        // 3단계 (swap)
        int temp = seq[indexI-1];
        seq[indexI-1] = seq[indexJ];
        seq[indexJ] = temp;

        // 4단계 (순열 뒤집기)
        int[] tempArr = new int[seq.length-indexI];
        for (int i=indexI;i<seq.length;i++) tempArr[i-indexI] = seq[i];
        int k = seq.length-indexI-1;
        for (int i=indexI;i<seq.length;i++) {
            seq[i] = tempArr[k];
            k-=1;
        }
        return seq;
    }
}
