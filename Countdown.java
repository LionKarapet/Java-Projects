package Multithreading.Task;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Countdown extends JFrame {

    private JPanel contentPane;
    private JTextField MinTextField;
    private JTextField SecTextField;
    private JLabel MinLabel;
    private JLabel SecLabel;
    private JLabel lblColon;

    /**
     * Create the frame.
     */
    public Countdown() {
        setResizable(false);
        setTitle("Countdown");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        MinTextField = new JTextField();
        MinTextField.setBounds(74, 54, 86, 20);
        contentPane.add(MinTextField);
        MinTextField.setColumns(10);

        SecTextField = new JTextField();
        SecTextField.setBounds(273, 54, 86, 20);
        contentPane.add(SecTextField);
        SecTextField.setColumns(10);

        JLabel lblMinutes = new JLabel("Minutes");
        lblMinutes.setHorizontalAlignment(SwingConstants.CENTER);
        lblMinutes.setBounds(74, 23, 86, 20);
        contentPane.add(lblMinutes);

        JLabel lblSeconds = new JLabel("Seconds");
        lblSeconds.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeconds.setBounds(273, 23, 86, 20);
        contentPane.add(lblSeconds);

        JButton btnClickToStart = new JButton("Click to Start");
        btnClickToStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                Thread CountThread = new Thread(new Runnable() {

                    private boolean isFinished = false;
                    private int Minutes;
                    private int Seconds;

                    @Override
                    public void run() {

                        boolean isInputValidated = false;
                        if (Integer.parseInt(MinTextField.getText()) >= 0 && Integer.parseInt(SecTextField.getText()) <= 59) {
                            Minutes = Integer.parseInt(MinTextField.getText());
                            Seconds = Integer.parseInt(SecTextField.getText());
                            isInputValidated = true;
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Invalid output try again");
                        }

                        if (isInputValidated) {
                            while (!isFinished) {
                                MinLabel.setText(Minutes + "");
                                SecLabel.setText(Seconds + "");
                                if (Minutes < 10) {
                                    MinLabel.setText("0" + Minutes + "");
                                }
                                if (Seconds < 10) {
                                    SecLabel.setText("0" + Seconds + "");
                                }

                                if (Seconds > 0)
                                    Seconds--;
                                if (Seconds == 0) {
                                    if (Minutes == 0) {
                                        isFinished = true;
                                    } else {
                                        Seconds = 59;
                                        Minutes--;
                                    }
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            SecLabel.setText("0" + Seconds + "");
                            JOptionPane.showMessageDialog(contentPane, "Countdown is finished");

                        }
                    }

                });
                CountThread.start();
            }
        });
        btnClickToStart.setBounds(159, 228, 110, 32);
        contentPane.add(btnClickToStart);

        MinLabel = new JLabel("00");
        MinLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        MinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MinLabel.setBounds(55, 85, 122, 132);
        contentPane.add(MinLabel);

        SecLabel = new JLabel("00");
        SecLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SecLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        SecLabel.setBounds(253, 85, 122, 132);
        contentPane.add(SecLabel);

        lblColon = new JLabel(":");
        lblColon.setFont(new Font("Tahoma", Font.PLAIN, 50));
        lblColon.setHorizontalAlignment(SwingConstants.CENTER);
        lblColon.setBounds(197, 94, 46, 114);
        contentPane.add(lblColon);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Countdown frame = new Countdown();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
