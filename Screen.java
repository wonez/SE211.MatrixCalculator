import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame implements ActionListener {

    private JTextField inputsA[][];
    private JTextField inputsB[][];

    private int ar;
    private int ac;

    private int br;
    private int bc;

    private JPanel commands;

    public Screen(int ar, int ac, int br, int bc, JTextField valsA[][], JTextField valsB[][]) {

        setSize(750, 250);
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
                Screen sc = new Screen(ar, ac, br + 1, bc + 1, inputsA, inputsB);
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
                    Screen sc = new Screen(ar, ac, br - 1, bc - 1, inputsA, inputsB);
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
            public void actionPerformed(ActionEvent e) { //neće da radi, isti kod, al drugačije učitava parametre iz nekog razloga?
                String constant = JOptionPane.showInputDialog(this, "Multiply by constant, input constant: ");
                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
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

        JPanel commandButtons = new JPanel(new GridLayout(3, 1));
        commandButtons.add(new JButton("A + B"));
        commandButtons.add(new JButton("A - B"));
        commandButtons.add(new JButton("<-->"));

        commands.add(commandButtons);

        for (int i = 0; i < 4; i++)
            commands.add(new JPanel(new BorderLayout()));
        //END COMMANDS

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(A);
        mainPanel.add(commands);
        mainPanel.add(B);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("+")) {
            Screen sc = new Screen(ar + 1, ac + 1, br, bc, inputsA, inputsB);
            sc.setVisible(true);
            dispose();
        } else if (e.getActionCommand().equals("-")) {
            if (ar > 1) {
                Screen sc = new Screen(ar - 1, ac - 1, br, bc, inputsA, inputsB);
                sc.setVisible(true);
                dispose();
            }
        } else if (e.getActionCommand().equals("Clean")) {
            for (JTextField jtx[] : inputsA) {
                for (JTextField jx : jtx)
                    jx.setText("");
            }
        } else if (e.getActionCommand().equals("Const")) {
            String constant = JOptionPane.showInputDialog(this, "Multiply by constant, input constant: ");
            for (JTextField jtx[] : inputsA) {
                for (JTextField jx : jtx) {
                    int multiplier = Integer.parseInt(constant);
                    int factor = Integer.parseInt(jx.getText());
                    jx.setText(multiplier * factor + "");
                }
            }

            } else if (e.getActionCommand().equals("A + B")) {

                JFrame R = new JFrame();
                R.setVisible(true);
                R.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JPanel resultingMatrix = new JPanel(new GridLayout(br, bc));

                int mA[][] = new int[ar][ac];
                int mB[][] = new int[br][bc];
                int tempRows = 0;
                int tempCols = 0;

                for (JTextField jtx[] : inputsA) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mA[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                tempCols = 0;
                tempRows = 0;

                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mB[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                for (int i = 0; i < ar; i++) {
                    for (int j = 0; j < ac; j++) {
                        String temp = new String();
                        JTextField jtx = new JTextField();
                        temp = mA[i][j] + mB[i][j] + "";
                        jtx.setText(temp);
                        resultingMatrix.add(jtx);
                    }
                }

                R.add(resultingMatrix, BorderLayout.CENTER);


            } else if (e.getActionCommand().equals("A - B")) {

                JFrame R = new JFrame();
                R.setVisible(true);
                R.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JPanel resultingMatrix = new JPanel(new GridLayout(br, bc));

                int mA[][] = new int[ar][ac];
                int mB[][] = new int[br][bc];
                int tempRows = 0;
                int tempCols = 0;

                for (JTextField jtx[] : inputsA) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mA[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                tempCols = 0;
                tempRows = 0;

                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mB[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                for (int i = 0; i < ar; i++) {
                    for (int j = 0; j < ac; j++) {
                        String temp = new String();
                        JTextField jtx = new JTextField();
                        temp = mA[i][j] - mB[i][j] + "";
                        jtx.setText(temp);
                        resultingMatrix.add(jtx);
                    }
            }
            R.add(resultingMatrix, BorderLayout.CENTER);


            } else if (e.getActionCommand().equals("<-->")) {

                int mA[][] = new int[ar][ac];
                int mB[][] = new int[br][bc];
                int mTemp[][] = new int[ar][bc];
                int tempRows = 0;
                int tempCols = 0;

                for (JTextField jtx[] : inputsA) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mA[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                tempCols = 0;
                tempRows = 0;

                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
                        int noM = Integer.parseInt(jx.toString());
                        tempRows++;
                        mB[tempRows][tempCols] = noM;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                for (int i=0; i<ac; i++) {
                    for (int j=0; i<ar; i++) {
                        mTemp[i][j] = mA[i][j];
                    }
                }

                for (int i=0; i<ac; i++) {
                    for (int j=0; i<ar; i++) {
                        mA[i][j] = mB[i][j];
                    }
                }

                for (int i=0; i<ac; i++) {
                    for (int j=0; i<ar; i++) {
                        mB[i][j] = mTemp[i][j];
                    }
                }

                for (JTextField jtx[] : inputsA) {
                    for (JTextField jx : jtx) {
                        jx.setText(mA[tempRows][tempCols] + "");
                        tempRows++;
                    }
                    tempCols++;
                    tempRows = 0;
                }

                tempRows = 0;
                tempCols = 0;

                for (JTextField jtx[] : inputsB) {
                    for (JTextField jx : jtx) {
                        jx.setText(mB[tempRows][tempCols] + "");
                        tempRows++;
                    }
                    tempCols++;
                    tempRows = 0;
                }
            }
    }
}

