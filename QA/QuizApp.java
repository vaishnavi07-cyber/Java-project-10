import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    String[] questions = {
        "Which keyword is used to define a class in Java?",
        "Which method is the entry point for a Java program?",
        "Which of these is NOT a primitive data type?",
        "Which operator is used to compare two values?",
        "What does JVM stand for?"
    };

    String[][] options = {
        {"class", "Class", "define", "struct"},
        {"start()", "main()", "run()", "init()"},
        {"int", "float", "String", "char"},
        {"=", "==", "equals", "!="},
        {"Java Variable Method", "Java Virtual Machine", "Java Verified Mode", "Java Visual Module"}
    };

    int[] answers = {0, 1, 2, 1, 1};
    int index = 0, score = 0, timeLeft = 15;

    JLabel questionLabel, timerLabel, scoreLabel;
    JRadioButton[] optionsButtons = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton;
    Timer timer;

    public QuizApp() {
        setTitle("Java Quiz App");
        setSize(650, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 550, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel);

        timerLabel = new JLabel("Time Left: 15s");
        timerLabel.setBounds(500, 10, 150, 20);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 10, 150, 20);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setForeground(new Color(0, 128, 0));
        add(scoreLabel);

        group = new ButtonGroup();

        int y = 80;
        for (int i = 0; i < 4; i++) {
            optionsButtons[i] = new JRadioButton();
            optionsButtons[i].setBounds(70, y, 500, 30);
            optionsButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            group.add(optionsButtons[i]);
            add(optionsButtons[i]);
            y += 40;
        }

        nextButton = new JButton("Next");
        nextButton.setBounds(250, 250, 150, 40);
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextButton.addActionListener(this);
        add(nextButton);

      

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");
                if (timeLeft == 0) {
                    timer.stop();
                    nextButton.doClick();
                }
            }
        });
        timer.start();
        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
            for (int i = 0; i < 4; i++) {
                optionsButtons[i].setText(options[index][i]);
                optionsButtons[i].setSelected(false);
            }
            timeLeft = 15;
            timerLabel.setText("Time Left: 15s");
            timer.restart();
        } else {
            showResult();
        }
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (optionsButtons[i].isSelected()) {
                selected = i;
                break;
            }
        }
        if (selected == answers[index]) {
            score++;
            scoreLabel.setText("Score: " + score);
        }
        index++;
        loadQuestion();
    }

    void showResult() {
        JOptionPane.showMessageDialog(
            this,
            "✅ Quiz Completed!\nYour Score: " + score + " out of " + questions.length,
            "Quiz Score",
            JOptionPane.INFORMATION_MESSAGE
        );

        String[] columnNames = {"UID", "Name"};
        String[][] teamData = {
            {"24BCS80037", "Deeptanu Saha"},
            {"24BCS80056", "Aarooshee Doegar"}
            
        };

        JTable table = new JTable(teamData, columnNames);
        table.setEnabled(false);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(480, 120));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(520, 200));
        panel.add(new JLabel("👨‍💻 Developed By:", SwingConstants.LEFT), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel guideLabel = new JLabel("🎓 Under Guidance of: Er. Gyan Chand Yadav(E12247) 🎓", SwingConstants.CENTER);
        guideLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        guideLabel.setForeground(new Color(0, 153, 0)); // A rich green
        panel.add(guideLabel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Developer Info", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
