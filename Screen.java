import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Screen extends JFrame {

    private JButton minusCol;
    private JButton plusCol;

    private JButton minusRow;
    private JButton plusRow;

    private ArrayList<JTextField> inputs;

    public Screen() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Matrix Calculator");
        setLocationRelativeTo(null);

        JPanel nums = new JPanel(new GridLayout(3,3));
        inputs = new ArrayList<>(9);

        for(int i=0; i<9; i ++) {
            JTextField jtx = new JTextField();
            inputs.add(jtx);
            nums.add(jtx);
        }

        add(nums, BorderLayout.CENTER);

        JPanel columns = new JPanel(new GridLayout(1,3));
        minusCol = new JButton("-");
        plusCol = new JButton("+");
        columns.add(minusCol);
        columns.add(new JLabel("Columns", SwingConstants.CENTER));
        columns.add(plusCol);
        add(columns, BorderLayout.NORTH);

        JPanel rows = new JPanel(new GridLayout(1,3));
        minusRow = new JButton("-");
        plusRow = new JButton("+");
        rows.add(minusRow);
        rows.add(new JLabel("Rows", SwingConstants.CENTER));
        rows.add(plusRow);
        add(rows, BorderLayout.SOUTH);
    }
}
