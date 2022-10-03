import java.util.*;
import java.io.*;
class Item{
    String name;
    int price;
    Item(String name,int price){
        this.name = name;
        this.price = price;

    }
   public String toString(){
        return this.name + ": "+ this.price;
    }

}
public class Main_Java {
    public static void main(String[] args) throws Exception {
        FileInputStream fis=new FileInputStream("input.txt");
        Scanner in=new Scanner(fis);
        int number_of_employees = Integer.parseInt(in.nextLine().split(": ")[1]);
        in.nextLine(); in.nextLine(); in.nextLine();

        ArrayList<Item> goodies_items = new ArrayList<Item>();

        while(in.hasNextLine())
        {
            String current[] = in.nextLine().split(": ");
            goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
        }
        in.close();

        Collections.sort(goodies_items, new Comparator<Item>(){
            public int compare(Item a, Item b) {
                return a.price - b.price;
            }
        });

        int min_diff = goodies_items.get(goodies_items.size()-1).price;
        int min_index = 0;
        for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
            int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

            if(diff<=min_diff) {
                min_diff = diff;
                min_index = i;
            }
        }
        FileWriter fw = new FileWriter("output.txt");
        fw.write("Goodies and Prices: \n");
        for(int i=min_index;i<min_index + number_of_employees; i++) {
            fw.write(goodies_items.get(i).toString() + "\n");
        }
        fw.write("\n And the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
        fw.close();
    }
}