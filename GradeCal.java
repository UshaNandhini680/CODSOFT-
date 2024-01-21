import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCal extends JFrame {

    private JTextField numSubjectsField;
    private JButton calculateButton;
    private JTextArea resultArea;

    public GradeCal() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Grade Calculator");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JLabel numSubjectsLabel = new JLabel("Enter the number of subjects:");
        panel.add(numSubjectsLabel);

        numSubjectsField = new JTextField();
        panel.add(numSubjectsField);

        calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
        panel.add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(resultArea);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateGrade() {
        try {
            int numSubjects = Integer.parseInt(numSubjectsField.getText());

            int totalMarks = 0;
            for (int i = 1; i <= numSubjects; i++) {
                String input = JOptionPane.showInputDialog("Enter marks obtained in Subject " + i + ":");
                int marks = Integer.parseInt(input);
                totalMarks += marks;
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            char grade;
            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            resultArea.setText("Results:\n" +
                    "Total Marks: " + totalMarks + "\n" +
                    "Average Percentage: " + averagePercentage + "%\n" +
                    "Grade: " + grade);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of subjects and marks.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCal();
            }
        });
    }
}
