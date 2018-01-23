import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame implements ActionListener {

    private JTextField inputsA[][];
    private JTextField inputsB[][];
    private JTextField valuesR[][];

    private int ar;
    private int ac;

    private int br;
    private int bc;

    private JPanel commands;

    public Screen(int ar, int ac, int br, int bc, JTextField valsA[][], JTextField valsB[][], JTextField valsR[][]) {

        setSize(750, 600);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Matrix Calculator");
        setLocationRelativeTo(null);

        //A matrix row and column
        this.ar = ar;
        this.ac = ac;

        //B matrix row and column
        this.br = br;
        this.bc = bc;

        //inputs

        //A PANEL
        JPanel A = new JPanel(new BorderLayout());

        JPanel matrixA = new JPanel(new GridLayout(ar, ac));
        inputsA = new JTextField[ar][ac];

        for (int i = 0; i < ar; i++) {
            for (int j = 0; j < ac; j++) {
                JTextField jtx = new JTextField();
                jtx.setHorizontalAlignment(SwingConstants.CENTER);
                inputsA[i][j] = jtx;
                matrixA.add(jtx);
            }
        }

        if (valsA != null) {

            for (int i = 0; i < valsA.length; i++) {
                for (int j = 0; j < valsA[i].length; j++) {
                    if (i >= ar || j >= ac)
                        continue;
                    inputsA[i][j].setText(valsA[i][j].getText());
                }
            }
        }


        A.add(matrixA, BorderLayout.CENTER);

        //A commands
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

        JButton constA = new JButton("Const");
        constA.addActionListener(this);
        aCommands.add(constA);

        A.add(aCommands, BorderLayout.SOUTH);
        //END OF A PANEL

        //B PANEL
        JPanel B = new JPanel(new BorderLayout());

        JPanel matrixB = new JPanel(new GridLayout(br, bc));
        inputsB = new JTextField[br][bc];

        for (int i = 0; i < br; i++) {
            for (int j = 0; j < bc; j++) {
                JTextField jtx = new JTextField();
                jtx.setHorizontalAlignment(SwingConstants.CENTER);
                matrixB.add(jtx);
                inputsB[i][j] = jtx;
            }
        }

        if (valsB != null) {
            for (int i = 0; i < valsB.length; i++) {
                for (int j = 0; j < valsB[i].length; j++) {
                    if (i >= br || j >= bc)
                        continue;
                    inputsB[i][j].setText(valsB[i][j].getText());
                }
            }
        }

        B.add(matrixB, BorderLayout.CENTER);

        //B commands
        JPanel bCommands = new JPanel(new GridLayout(1, 4));

        JButton plusB = new JButton("+");
        plusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen sc = new Screen(ar, ac, br + 1, bc + 1, inputsA, inputsB, valuesR);
                sc.setVisible(true);
                dispose();
            }
        });
        bCommands.add(plusB);


        JButton minusB = new JButton("-");
        minusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (br > 1) {
                    Screen sc = new Screen(ar, ac, br - 1, bc - 1, inputsA, inputsB, valuesR);
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
                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx)
                        jx.setText("");
                }
            }
        });
        bCommands.add(cleanB);

        JButton constB = new JButton("Const");
        constB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String constant = JOptionPane.showInputDialog("Multiply by constant, input constant: ");
                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
                        if(jx.getText().equals("")) continue;
                        int multiplier = Integer.parseInt(constant);
                        int factor = Integer.parseInt(jx.getText());
                        jx.setText(multiplier * factor + "");
                    }
                }
            }
        });
        bCommands.add(constB);

        B.add(bCommands, BorderLayout.SOUTH);
        //END OF B PANEL


        //COMMANDS
        commands = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 4; i++)
            commands.add(new JPanel(new BorderLayout()));

        JPanel commandButtons = new JPanel(new GridLayout(4, 1));

        JButton aPlusB = new JButton("A + B");
        aPlusB.addActionListener(this);
        commandButtons.add(aPlusB);

        JButton aMinusB = new JButton("A - B");
        aMinusB.addActionListener(this);
        commandButtons.add(aMinusB);

        JButton aTimesB = new JButton("A * B");
        aTimesB.addActionListener(this);
        commandButtons.add(aTimesB);

        JButton change = new JButton("<-->");
        change.addActionListener(this);
        commandButtons.add(change);

        commands.add(commandButtons);

        for (int i = 0; i < 4; i++)
            commands.add(new JPanel(new BorderLayout()));
        //END COMMANDS

        JPanel mainPanel = new JPanel(new GridLayout(2, 3));
        mainPanel.add(A);
        mainPanel.add(commands);
        mainPanel.add(B);

        mainPanel.add(new JPanel());

        JPanel R = new JPanel(new BorderLayout());
        int rr, rc;

        if(ar < br){
            rr = ar;
            rc = ac;
        } else {
            rr = br;
            rc = bc;
        }

        JPanel matrixR = new JPanel(new GridLayout(rr, rc));
        valuesR = new JTextField[rr][rc];

        for (int i = 0; i < rr; i++) {
            for (int j = 0; j < rc; j++) {
                JTextField jtx = new JTextField();
                jtx.setHorizontalAlignment(SwingConstants.CENTER);
                jtx.setEditable(false);
                valuesR[i][j] = jtx;
                matrixR.add(jtx);
            }
        }

        if (valsR != null) {

            for (int i = 0; i < valsR.length; i++) {
                for (int j = 0; j < valsR[i].length; j++) {
                    if (i >= rr || j >= rc)
                        continue;
                    valuesR[i][j].setText(valsR[i][j].getText());
                }
            }
        }

        R.add(matrixR, BorderLayout.CENTER);

        mainPanel.add(R);

        mainPanel.add(new JPanel());

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("+")) {
            Screen sc = new Screen(ar + 1, ac + 1, br, bc, inputsA, inputsB, valuesR);
            sc.setVisible(true);
            dispose();
        } else if (e.getActionCommand().equals("-")) {
            if (ar > 1) {
                Screen sc = new Screen(ar - 1, ac - 1, br, bc, inputsA, inputsB, valuesR);
                sc.setVisible(true);
                dispose();
            }
        } else if (e.getActionCommand().equals("Clean")) {
            for (JTextField jtx[] : inputsA) {
                for (JTextField jx : jtx)
                    jx.setText("");
            }
        } else if (e.getActionCommand().equals("Const")) {

            String constant = JOptionPane.showInputDialog("Multiply by constant, input constant: ");

            for (JTextField jtx[] : inputsA) {
                for (JTextField jx : jtx) {
                    if(jx.getText().equals("")) continue;
                    int multiplier = Integer.parseInt(constant);
                    int factor = Integer.parseInt(jx.getText());
                    jx.setText(multiplier * factor + "");
                }
            }
        } else if (e.getActionCommand().equals("A + B")) {

            if(ar != br){
                JOptionPane.showMessageDialog(this,"Matrices need to have the same number of rows and columns\nin order to perform any operations with them!");
                return;
            }

            for(int i=0; i<ar; i++){
                for(int j=0; j<ac; j++) {

                    String a = inputsA[i][j].getText();
                    String b = inputsB[i][j].getText();
                    if(a.equals(""))
                        a = "0";
                    if(b.equals(""))
                        b = "0";
                    valuesR[i][j].setText(Integer.parseInt(a) + Integer.parseInt(b) + "");
                }
            }


        } else if (e.getActionCommand().equals("A - B")) {

            if(ar != br){
                JOptionPane.showMessageDialog(this,"Matrices need to have the same number of rows and columns\nin order to perform any operations with them!");
                return;
            }

            for(int i=0; i<ar; i++){
                for(int j=0; j<ac; j++) {

                    String a = inputsA[i][j].getText();
                    String b = inputsB[i][j].getText();
                    if(a.equals(""))
                        a = "0";
                    if(b.equals(""))
                        b = "0";
                    valuesR[i][j].setText(Integer.parseInt(a) - Integer.parseInt(b) + "");
                }
            }

        } else if (e.getActionCommand().equals("<-->")) {

            Screen sc = new Screen(br, bc , ar, ac, inputsB, inputsA, valuesR);
            sc.setVisible(true);
            dispose();

        } else if (e.getActionCommand().equals("A * B")){
            System.out.println("snovi od stakla");
        }
    }
}

