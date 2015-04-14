import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Karolina_2 on 2015-04-11.
 */
public class NQueenProblemBoard extends JFrame {

    JPanel buttonsPanel;
    JPanel mainPanel;
    JButton backtrackGenerate;
    JButton[] buttonsArray;

    public NQueenProblemBoard(int n){
        buttonsPanel = new JPanel();
        mainPanel = new JPanel();
        buttonsArray = new JButton[n*n];
        backtrackGenerate = new JButton("Generuj plik");
        for(int i = 0; i < n*n; i++){
            final JButton button = new JButton();
            button.setPreferredSize(new Dimension(50,50));
            button.setBackground(Color.WHITE);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setBackground(Color.GREEN);
                    button.setEnabled(false);
                }
            });
            buttonsArray[i] = button;
            buttonsPanel.add(button);
        }
        buttonsPanel.setPreferredSize(new Dimension(50*n + 6*n,50*n + 6*n));
        mainPanel.setPreferredSize(new Dimension(80*n, 80*n));
        mainPanel.add(buttonsPanel);
        mainPanel.add(backtrackGenerate);
        //mainPanel.add(forwardGenerate);
        this.add(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        //this.setAlwaysOnTop(true);
        this.dodajListenery();
    }

    public void dodajListenery(){
        backtrackGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("nqueen.txt");
                boolean isDeleted = false;
                if(file.exists()){
                    isDeleted = file.delete();
                    System.out.println(isDeleted);
                }
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    double length = Math.sqrt(buttonsArray.length);
                    for(int i = 0; i < length; i++){
                        bw.write("x" + (i+1) + " ");
                    }
                    bw.newLine();
                    for(int j = 0; j < length; j++) {
                        for (int i = 0; i < length; i++) {
                            bw.write((i + 1) + " ");
                        }
                        bw.newLine();
                    }
                    bw.write((int)length + " ");
                    for(int i = 0; i < length; i++){
                        bw.write("x" + (i+1) + " ");
                    }
                    bw.write("rozne");
                    bw.newLine();
                    for(int i = 0; i < length; i++){
                        for(int j = 0; j < length; j++){
                            if(i < j){
                                bw.write("x" + (i+1) + " x" + (j+1) + " - || " + Math.abs((i+1) - (j+1)) + " <> ");
                                bw.newLine();
                            }
                        }
                    }
                    bw.close();
                }catch (IOException err){
                    err.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        NQueenProblemBoard board = new NQueenProblemBoard(n);
        board.setVisible(true);
        Solver solver = new Solver();
        solver.readFromFile(new File("nqueen.txt"));
    }
}
