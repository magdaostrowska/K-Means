import java.util.ArrayList;

public class Algorithm {

    public Algorithm(Knapsack knapsack, int length, int capacity){

        String s = "";
        int sizSum;
        int valSum;
        int a, n;
        int maxVal = 0;
        int maxSiz = 0;
        ArrayList<Integer> numbers = new ArrayList<>();

        n = (int) Math.pow(2,length); // maksymalna liczba możliwości
        for(int i = 0; i < n ;i++){
            sizSum = 0;
            valSum = 0;

            int[] binary = new int[length];
            int index = length - 1;
            a = i;

            while(a > 0){
                binary[index--] = a%2;
                a = a/2;
            }

            //zliczanie sum wag i wartości
            for(int j=0; j < length; j++){
                if(binary[j] == 1){
                    sizSum += knapsack.sizes[j];
                    valSum += knapsack.values[j];
                    numbers.add(j+1);
                }
            }

            //wybranie rozwiązanie
            if(sizSum <= capacity){
                if(valSum > maxVal) {
                    maxVal = valSum;
                    maxSiz = sizSum;
                    ArrayList<Integer> numbersFinal = numbers;
                    s = "";
                    for (int x : numbersFinal) {
                        s += x + ",";
                    }
                }
            }
            numbers.clear();
        }

        System.out.println("-=-=- Optymalny sklad plecaka:-=-=-");

        for(int i=0; i< s.split(",").length; i++){
            int val = Integer.parseInt(s.split(",")[i]);
            System.out.println("Item number: " + val + " value: " + knapsack.values[(val-1)] + " size: " + knapsack.sizes[(val-1)]);
        }
        System.out.println("Sum of values: " + maxVal + " size: " + maxSiz);
    }
}