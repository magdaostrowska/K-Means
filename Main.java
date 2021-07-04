import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        int quantity = 0;
        int capacity = 0;
        int a = 0;

        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<Integer> sizesList = new ArrayList<>();
        ArrayList<Integer> valuesList = new ArrayList<>();
        ArrayList<Knapsack> dataSetList = new ArrayList<>();

        String fileName = JOptionPane.showInputDialog(jFrame, "Podaj nazwę pliku");

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = (line.split(" "));

                if(a < 1) {

                    //pobieranie ilości przedmiotów i pojemności plecaka
                    String[] lengthAndCapacity = line.split(",");
                    quantity = Integer.parseInt(lengthAndCapacity[0]);
                    capacity = Integer.parseInt(lengthAndCapacity[1]);
                }
                else{

                    //pobieranie id
                    if(lines.length == 2) {
                       String idString = lines[1];
                       int id = Integer.parseInt(idString.replace(":",""));
                       idList.add(id);
                    }

                    //pobieranie wag
                    if(line.split("sizes = ").length == 2){
                        String[] sizesString = line.split("sizes = ");
                        String[] s=sizesString[1].split(",");

                        for(int i=0;i < quantity; i++){

                            if(i == 0) {
                                sizesList.add(Integer.valueOf(s[i].replace("{", "")));
                            }
                            if(i == (quantity-1)) {
                                String k=s[i].replace("}", "");
                                sizesList.add(Integer.valueOf(k.replaceAll("\\s", "")));
                            }
                            if(i < (quantity-1) && i>0) {
                                sizesList.add(Integer.valueOf(s[i].replaceAll("\\s","")));
                            }
                        }
                    }

                    //pobieranie wartości przedmiotów
                    if(line.split("values = ").length==2){
                        String[] valuesString=line.split("values = ");
                        String[] v = valuesString[1].split(",");

                        for(int i=0; i < quantity; i++){

                            if(i == 0) {
                                String l = v[i].replace("{", "");
                                valuesList.add(Integer.valueOf(l.replaceAll("\\s", "")));
                            }
                            if(i == (quantity-1)) {
                                String k=v[i].replace("}", "");
                                valuesList.add(Integer.valueOf(k.replaceAll("\\s", "")));
                            }
                            if(i < (quantity-1) && i>0) {
                                valuesList.add(Integer.valueOf(v[i].replaceAll("\\s","")));
                            }
                        }
                    }
                }
                a++;
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //stworzenie listy z plecakami

        int[] sizes;
        int[] values;

        for(int i=0; i < idList.size(); i++){
            sizes = new int[quantity];
            values = new int[quantity];

            for(int j=0; j < quantity; j++){
                sizes[j] = sizesList.get(j);
                values[j] = valuesList.get(j);
            }

            Knapsack knapsack = new Knapsack(idList.get(i),sizes,values);
            dataSetList.add(knapsack);
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for(int i = 0; i < dataSetList.size(); i++){
            System.out.println(dataSetList.get(i).toString()+"\n"+"\n");
            Algorithm algorithm = new Algorithm(dataSetList.get(i),quantity,capacity);
        }
    }
}