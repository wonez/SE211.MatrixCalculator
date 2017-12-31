import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Matrix Calculator");
        setLocationRelativeTo(null);

        JPanel nums = new JPanel(new GridLayout(3,3));

        for(int i=0; i<9; i ++) {
            JTextField jtx = new JTextField();
            nums.add(jtx);
        }

        add(nums, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(3,3));
        bottom.add(new JButton("+"));

        add(bottom, BorderLayout.SOUTH);

        JPanel right = new JPanel(new GridLayout(3, 3));
        right.add(new JButton("+"));

        add(right, BorderLayout.EAST);
    }
}
