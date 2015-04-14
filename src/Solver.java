import java.io.*;
import java.util.*;

/**
 * Created by Karolina_2 on 2015-04-12.
 */
public class Solver {

    static Stack stack;
    Solver(){
        stack = new Stack();
    }

    public void readFromFile(File file){
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            String[] variables = scanner.nextLine().split(" ");
            List<String> vars = Arrays.asList(variables);
            List<List<Integer>> domains = new ArrayList<>();
            List<String[]> constraints = new ArrayList<>();
            for(int i = 0; i < variables.length; i++){
                String[] domainString = scanner.nextLine().split(" ");
                List<Integer> domain = new ArrayList<>();
                for(int j = 0; j < domainString.length; j++){
                    domain.add(Integer.parseInt(domainString[j]));
                }
                domains.add(domain);
            }
            int[] values = new int[variables.length];
            List<Integer> vals = new ArrayList<>();
            for(int i = 0; i < domains.size(); i++){
                if(domains.get(i).size() == 1){
                    values[i] = domains.get(i).get(0);
                }
                else{
                    values[i] = -1;
                }
            }
            for(int i = 0; i < domains.size(); i++){
                vals.add(-1);
            }
            System.out.println(vals.size());
            for(int i = 0; i < domains.size(); i++){
                if(domains.get(i).size() == 1){
                    vals.set(i, domains.get(i).get(0));
                }
                else{
                    vals.set(i, -1);
                }
            }
            System.out.println();
            for(int val : vals){
                System.out.print(val + " ");
            }
            System.out.println();
            for(String var : vars){
                System.out.print(var + " ");
            }
            List<String> constraint = new ArrayList<>();
            while(scanner.hasNext()){
                constraint.add(scanner.nextLine());
            }
            for(String string : constraint){
                String[] strings = string.split(" ");
                constraints.add(strings);
            }

                //res = (String)stack.peek();
                //System.out.println(res);
            System.out.println(constraints.size());
            System.out.println(domains.size());
            System.out.println(variables.length);
            this.checkCorrectness(constraints, variables, values);
        }catch(IOException err){
            err.printStackTrace();
        }
    }

    private boolean checkCorrectness(List<String[]> constraints, String[] variables, int[] values){
        //przejezdzac po kazdym ograniczeniu i sprawdzac, czy spelnione
        for(String[] string : constraints) {
            String res = "";
            for (String string2 : string) {
                if (string2.startsWith("x") || isNumber(string2)) {
                    stack.push(string2);
                } else if (string2.equals("==") || string2.equals("<>") || string2.equals("-") || string2.equals("+") || string2.equals("*") || string2.equals("/")) {
                    String st1 = (String) stack.pop();
                    String st2 = (String) stack.pop();
                    String result = st2 + string2 + st1;
                    stack.push(result);
                    //System.out.println(result);
                } else if (string2.equals("||")) {
                    String st = (String) stack.pop();
                    String result = "|" + st + "|";
                    stack.push(result);
                    //System.out.println(result);
                } else if (string2.equals("rozne")) {
                    List<String> rozne = new ArrayList<>();
                    //while (!stack.isEmpty()) {
                      //  rozne.add((String) stack.pop());
                    //}
                    for (int i = 0; i < rozne.size() - 1; i++) {
                        for (int j = 0; j < rozne.size() - 1; j++) {
                            if (i != j && i < j) {
                                String resu = rozne.get(i) + " != " + rozne.get(j);
                                //System.out.println(resu);
                                stack.push(resu);
                            }
                        }
                    }
                }
            }
        }
        for(String[] cons : constraints){
            for(String con : cons){
                System.out.print(con);
            }
            System.out.println();
        }
        for(String var : variables){
            System.out.print(var + " ");
        }
        for(int i : values){
            System.out.print(i + " ");
        }

        return false;
    }

    static boolean isNumber(String string){
        if(string.isEmpty()) return false;
        for(int i = 0; i < string.length(); i++){
            if(!Character.isDigit(string.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean czySaRozne(int[] values){
        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values.length; j++){
                if(i != j && values[i] != -1 && values[j] != j){
                    if(values[i] == values[j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solver solver = new Solver();
        int[] values = {-1,-1,-1,5,5,7};
        System.out.print(solver.czySaRozne(values));
    }
}

//sudoku 10x10?
