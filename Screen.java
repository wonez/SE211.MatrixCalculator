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
        JButton plusA = new JButton("+");
        plusA.addActionListener(this);

        commands.add(plusA);

        mainPanel = new JPanel(new GridLayout(1,3));
        mainPanel.add(matrixA);
        mainPanel.add(commands);
        mainPanel.add(matrixB);

        add(mainPanel, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Screen sc = new Screen(ar+1, ac+1, br+1,bc+1);
        sc.setVisible(true);
        dispose();

    }
}
