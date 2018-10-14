package codechef;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
class puzzle_game {
	 
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
 
    public static boolean isPrime(int a) {
        if (a == 2 || a == 3 || a == 5 || a == 7 || a == 11 || a == 13 || a == 17)
            return true;
        return false;
    }
 
    /*public Board clone() {
        int[] matrixCopy = new int[9];
        for (int i = 0; i < 9; i++) {
            matrixCopy[i] = matrix[i];
        }
        return new Board(matrixCopy);
    }*/
 
    public static void main(String[] args)throws IOException {
        int h, ta, val, nv, k, temp, t, q[], a[][];
        h = 0;
        ta = 1;
        a = new int[3][3];
        short d[] = new short[98765444];
        d[12345678] = 1;
        q = new int[1000000];
        q[0] = 123456789;
 
 
        while (ta > h) {
            val = q[h];
            for (int i = 2; i >= 0; i--)
                for (int j = 2; j >= 0; j--) {
                    a[i][j] = val % 10;
                    val /= 10;
                }
            val = q[h];
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if (i + 1 < 3 && isPrime(a[i][j] + a[i + 1][j])) {
                        for (k = 0, temp = 1; k < 5 - i * 3 - j; k++, temp *= 10) ;
                        nv = val + (a[i + 1][j] - a[i][j]) * temp * 1000 + (a[i][j] - a[i + 1][j]) * temp;
                        if (d[nv / 10] == 0) {
                            q[ta++] = nv;
                            //typecast to short
                            d[nv / 10] = (short) (d[val / 10] + 1);
                        }
 
                        /*for (int[] swap : swaps) {
                            int sum = current.matrix[swap[0]] + current.matrix[swap[1]];
                            if (sum == 3 || sum == 5 || sum == 7 || sum == 11 || sum == 13 || sum == 17) {
                                Board adjacent = current.swap(swap[0], swap[1]);
                                if (!visited.containsKey(adjacent)) {
                                    fringe.add(adjacent);
                                    visited.put(adjacent, visited.get(current) + 1);*/
                    }
                    if (j + 1 < 3 && isPrime(a[i][j] + a[i][j + 1])) {
                        for (k = 0, temp = 1; k < 7 - i * 3 - j; k++, temp *= 10) ;
                        nv = val + (a[i][j + 1] - a[i][j]) * temp * 10 + (a[i][j] - a[i][j + 1]) * temp;
                        if (d[nv / 10] == 0) {
                            q[ta++] = nv;
                            //typecast to short
                            d[nv / 10] = (short) (d[val / 10] + 1);
                        }
                    }
                }
            h++;
        }
        Reader sc = new Reader();
        t = sc.nextInt();
        System.out.println();
        while (t-- > 0) {
            k = 0;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    a[i][j] = sc.nextInt();
                    ;
                    k = k * 10 + a[i][j];
                }
            k /= 10;
            if (d[k] > 0)
                System.out.println(d[k] - 1);
            else
                System.out.println("-1");
        }
    }
}
