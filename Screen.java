import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Screen extends JFrame implements ActionListener {

    private JButton minusCol;
    private JButton plusCol;

    private JButton minusRow;
    private JButton plusRow;

    private ArrayList<JTextField> inputsA;
    private ArrayList<JTextField> inputsB;

    private JPanel mainPanel;

    private JPanel matrixA;
    private JPanel matrixB;

    private int ar;
    private int ac;

    private int br;
    private int bc;

    private JPanel commands;

    public Screen(int ar, int ac, int br, int bc) {

        //A matrix row and column
        this.ar = ar;
        this.ac = ac;

        //B matrix row and column
        this.br = br;
        this.bc = bc;
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Matrix Calculator");
        setLocationRelativeTo(null);

        matrixA = new JPanel(new GridLayout(ar,ac));
        inputsA = new ArrayList<>(ar*ac);

        for(int i=0; i<ar; i++) {
            for(int j=0; j<ac; j++){
                JTextField jtx = new JTextField();
                inputsA.add(jtx);
                matrixA.add(jtx);
            }
        }

        matrixB = new JPanel(new GridLayout(br, bc));
        inputsB = new ArrayList<>(br*bc);

        for(int i=0; i< br; i++) {
            for(int j=0; j< bc; j++) {
                JTextField jtx = new JTextField();
                inputsB.add(jtx);
                matrixB.add(jtx);
            }
        }

        commands = new JPanel();

        mainPanel = new JPanel(new GridLayout(1,3));
        mainPanel.add(matrixA);
        mainPanel.add(commands);
        mainPanel.add(matrixB);

        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JPanel matrixCommands = new JPanel(new GridLayout(1, 4));
        JPanel aCommands = new JPanel(new GridLayout(1, 4));

        JButton plusA = new JButton("+");
        plusA.addActionListener(this);
        aCommands.add(plusA);

        JButton minusA = new JButton("-");
        minusA.addActionListener(this);
        aCommands.add(minusA);

        JButton cleanA = new JButton("Clean");
        cleanA.addActionListener(this);
        aCommands.add(cleanA);

        JPanel bCommands = new JPanel(new GridLayout(1, 4));
        JButton plusB = new JButton("+");
        plusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen sc = new Screen(ar, ac, br+1,bc+1);
                sc.setVisible(true);
                dispose();
            }
        });
        bCommands.add(plusB);


        JButton minusB= new JButton("-");
        minusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(br > 1) {
                    Screen sc = new Screen(ar, ac, br - 1, bc - 1);
                    sc.setVisible(true);
                    dispose();
                }
            }
        });
        bCommands.add(minusB);

        JButton cleanB = new JButton("Clean");
        cleanB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JTextField jtx: inputsB){
                    jtx.setText("");
                }
            }
        });
        bCommands.add(cleanB);

        matrixCommands.add(aCommands);
        matrixCommands.add(new JPanel(new GridLayout(1,4)));
        matrixCommands.add(bCommands);

        bottomPanel.add(matrixCommands, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("+")) {
            Screen sc = new Screen(ar + 1, ac + 1, br, bc);
            sc.setVisible(true);
            dispose();
        } else if (e.getActionCommand().equals("-")){
            if(ar > 1){
                Screen sc = new Screen(ar-1, ac-1, br, bc);
                sc.setVisible(true);
                dispose();
            }
        } else if (e.getActionCommand().equals("Clean")){
            for(JTextField jtx: inputsA){
                jtx.setText("");
            }
        }

    }
}
