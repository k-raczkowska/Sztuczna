import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Created by Karolina_2 on 2015-04-11.
 */
public class SudokuProblemBoard extends JFrame{

    JPanel mainPanel;
    JPanel boardPanel;
    JTextField[][] fieldsArray;
    JButton generate;
    JScrollPane scrollPane;
    JButton loadFile;

    public SudokuProblemBoard(int n){
        mainPanel = new JPanel();
        boardPanel = new JPanel();
        scrollPane = new JScrollPane();
        fieldsArray = new JTextField[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                JTextField field = new JTextField();
                field.setPreferredSize(new Dimension(30, 30));
                fieldsArray[i][j] = field;
                boardPanel.add(field);
            }
        }
        boardPanel.setPreferredSize(new Dimension(n*30 + 5*n, n*30 + 5*n));
        generate = new JButton("Generuj plik");
        loadFile = new JButton("Wczytaj plik");
        mainPanel.add(boardPanel);
        mainPanel.add(generate);
        mainPanel.add(loadFile);
        this.add(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.dodajListenery();
    }

    public void dodajListenery(){
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("sudoku.txt");
                boolean isDeleted = false;
                if(file.exists()){
                    isDeleted = file.delete();
                }
                try{
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    double length = Math.sqrt(fieldsArray.length);
                    for(int i = 0; i < fieldsArray.length; i++){
                        for(int j = 0; j < fieldsArray.length; j++) {
                            bw.write("x" + (i*fieldsArray.length + j + 1) + " ");
                        }
                    }
                    bw.newLine();
                    //for (JTextField[] aFieldsArray1 : fieldsArray) {
                        for (JTextField[] aFieldsArray : fieldsArray) {
                            for (int j = 0; j < fieldsArray.length; j++) {
                                String xx = aFieldsArray[j].getText();
                                if (!xx.equals("")) {
                                    bw.write(aFieldsArray[j].getText() + " ");
                                    bw.newLine();
                                    //break;
                                } else {
                                    for(int i = 0; i < fieldsArray.length; i++) {
                                        bw.write((i + 1) + " ");
                                    }
                                    bw.newLine();
                                }
                            }
                            //bw.newLine();
                        }
                    //}
                    double x = length*2;
                    double x2 = length;
                    List<List<Integer>> list = new ArrayList<>();
                    List<List<Integer>> lists = new ArrayList<>();
                    /*while(x <= fieldsArray.length && x2 <= fieldsArray.length){
                        for(int i = 0; i < fieldsArray.length; i++){
                            List<Integer> list1 = new ArrayList<Integer>();
                            for(int j = 0; j < length; j++){
                                int y = i*fieldsArray.length + j + 1;
                                if(y%fieldsArray.length >= x2 && y%fieldsArray.length <= x){
                                    list1.add(y);
                                    bw.write("x" + y);
                                }
                            }
                            list.add(list1);
                        }
                        x += length;
                        x2 += length;
                        bw.newLine();
                    }*/
                    int q = (int)(length * fieldsArray.length);
                    int w = q;
                    int r = 0;
                    int t = (int)length;
                    int p = 0;
                    //System.out.println(q);
                    List<List<Integer>> lista = new ArrayList<>();
                    while(q <= fieldsArray.length * fieldsArray.length){
                        List<Integer> lista1 = new ArrayList<>();
                        for(int i = 0; i < fieldsArray.length*fieldsArray.length; i++){
                            if((i+1) <= q && (i+1) > r){
                                //System.out.println(i+1);
                                lista1.add(i+1);
                                /*if(i%fieldsArray.length < t && i%fieldsArray.length >= p){
                                    System.out.println(i+1);
                                }*/
                            }
                            t += length;
                            p += length;
                        }
                        lista.add(lista1);
                        //System.out.println();
                        q += w;
                        r += w;
                        //System.out.println(q);
                    }
                    //System.out.println("Size: " + lista.size());
                    /*for(int k = 0; k < lista.size(); k++) {
                        int mo = (int) length;
                        int mo2 = 0;
                        int x1 = (int) length;
                        int mo3 = (int) length;
                            while (mo <= fieldsArray.length) {
                                //System.out.println(mo);
                                int f = (int)(lista.get(k).size()*mo);
                                for (int j = mo2; j < f/x1; j++) {
                                    if (j % fieldsArray.length < mo && j % fieldsArray.length >= mo2) {
                                        System.out.println(j + 1);
                                    }
                                }
                                System.out.println();
                                mo += length;
                                mo2 += length;
                                x1--;
                            }

                    }*/
                    List<List<Integer>> list2 = new ArrayList<>();
                    bw.write(fieldsArray.length + " ");
                    for(int i = 0; i < lista.size(); i++){
                        int mod1 = (int)length;
                        int mod2 = 0;
                        for(int j = 0; j < lista.get(i).size(); j++) {
                            boolean wstawiono = false;
                            boolean czyKoniec = false;
                            for (int k = 0; k < lista.get(i).size(); k++) {
                                List<Integer> lista1 = new ArrayList<>();
                                if (k % fieldsArray.length >= mod2 && k % fieldsArray.length < mod1) {
                                    System.out.print(lista.get(i).get(k) + " ");
                                    bw.write("x" + lista.get(i).get(k) + " ");
                                    wstawiono = true;
                                    if(lista.get(i).get(k) == fieldsArray.length * fieldsArray.length){
                                        czyKoniec = true;
                                    }
                                    //lista1.add(lista.get(i).get(k));
                                }
                                //list2.add(lista1);
                            }
                            mod1 += length;
                            mod2 += length;
                            if(wstawiono) {
                                System.out.println();
                                bw.write("rozne");
                                bw.newLine();
                                if(!czyKoniec)
                                    bw.write(fieldsArray.length + " ");
                                //bw.newLine();
                            }
                        }
                        //System.out.println(list2.size());
                        //System.out.println();
                    }

                    for(int i = 0; i < fieldsArray.length; i++){
                        bw.write(fieldsArray.length + " ");
                        for(int j = 0; j < fieldsArray.length; j++){
                            bw.write("x" + (i*fieldsArray.length + j + 1) + " ");
                        }
                        bw.write("rozne");
                        bw.newLine();
                    }

                    for(int i = 0; i < fieldsArray.length; i++){
                        bw.write(fieldsArray.length + " ");
                        for(int j = 0; j < fieldsArray.length; j++){
                            bw.write("x" + (j*fieldsArray.length + i + 1) + " ");
                        }
                        bw.write("rozne");
                        bw.newLine();
                    }

                    /*double j = Math.sqrt(length);
                    while(j < length){
                        for(int i = 0; i < fieldsArray.length; i++) {
                            //for(int j = 1; j <= Math.sqrt(length); j++){
                            if ((i + 1) % length <= Math.sqrt(length) && (i + 1) % length != 0) {
                                if((i+1) <= length*3) {
                                    System.out.print("x" + (i + 1) + " ");
                                }
                                else if((i+1) > length*3 && (i+1) <= length*6){
                                    System.out.println();
                                }
                            }
                            j += Math.sqrt(length);
                            //}
                        }
                    }*/
                    int i = 0;
                    //int w = 1;
                    double j = Math.sqrt(length);
                    /*while(j <= length && w <= Math.sqrt(length)) {
                            i = 0;
                            while (i < fieldsArray.length) {
                                double mod = (i + 1) % length;
                                if (mod <= Math.sqrt(length) && mod > 0 && (i+1) <= length*j && (i+1) > length*(j-Math.sqrt(length)))
                                    System.out.print("x" + (i + 1) + " ");

                                i++;
                            }
                        j += 3;
                            System.out.println();

                    }*/
                    //List<List<Integer>> list = new ArrayList<>();
                    /*for(int m = 3; m <= length; m+=3){
                        for(int k = 1; k <= Math.sqrt(length); k++) {
                            List<Integer> list1 = new ArrayList<>();
                            for (int l = 0; l < fieldsArray.length; l++) {
                                if ((l + 1) % length <= m && (l + 1) % length > Math.sqrt(length) * (k - 1) && (l + 1) <= length * k * m && (l + 1) > length * (k - 1) * m) {
                                    list1.add(l);
                                    System.out.print(l + " ");
                                }
                            }
                            list.add(list1);
                        }
                    }
                    System.out.println(list.size());*/
                    bw.close();
                }catch(IOException err){
                    err.printStackTrace();
                }
            }
        });

        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver solver = new Solver();
                solver.readFromFile(new File("sudoku.txt"));
            }
        });
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        SudokuProblemBoard board = new SudokuProblemBoard(n);
        board.setVisible(true);
    }
}
