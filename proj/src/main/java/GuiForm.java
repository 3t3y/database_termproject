
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiForm {
    private JPanel panel1;
    private JButton button1;  // 查询按钮
    private JTextField textField2;
    private JButton button2;  // 删除按钮
    private JTextField textField1;
    private JButton button3;  // 插入按钮
    private JButton button4;
    private JTextField textField3;
    private JTextField textField4;
    private JTextArea textArea1;
    private JLabel label;


    public GuiForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // 执行查询操作
                String queryText = textField1.getText();
                // 在textArea1中显示查询结果
                textArea1.setText("Executing query: " + queryText );
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = textField2.getText();

                // 检查文本框是否为空
                if (!inputText.isEmpty()) {
                    // 切分输入文本，以空格为分隔符
                    String[] inputArray = inputText.split(" ");

                    // 检查是否有足够的元素，假设格式为 "tableName primaryKey"
                    if (inputArray.length == 2) {
                        String tableName = inputArray[0];
                        String primaryKey = inputArray[1];

                        // 调用删除数据的方法，将表名和主键名作为删除条件
                        JDbctest.Delete(tableName,primaryKey);

                        // 刷新界面或进行其他操作
                    } else {
                        JOptionPane.showMessageDialog(textArea1, "Invalid input format. Please enter 'tableName primaryKey'.");
                    }
                } else {
                    JOptionPane.showMessageDialog(textArea1, "Please enter 'tableName primaryKey' to delete.");
                }
            }

        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 执行插入操作
                String insertText = textField3.getText();
                // 在textArea1中显示插入结果
                textArea1.setText("Executing insert: " + insertText);
            }
        });


        // 如果需要，您可以在button4的ActionListener中添加其他逻辑
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 执行其他操作
                // ...
            }
        });

    }


    public void loadAndSetImage(String imagePath) {
        try {
            // 从文件加载图片
            File file = new File(imagePath);
            BufferedImage image = ImageIO.read(file);

            // Resize the image if needed
            Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
            label.repaint();

            // Set the image to the JLabel

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel1, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void write(int b) {
        // 将字节写入JTextArea
        textArea1.append(String.valueOf((char) b));
        // 滚动到文本区域的末尾
        textArea1.setCaretPosition(textArea1.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuiForm guiForm = new GuiForm();  // 创建 GuiForm 的一个实例
            JFrame frame = new JFrame("GuiForm");
            frame.setContentPane(guiForm.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(1000, 1000);

            frame.setVisible(true);

            // 在 GuiForm 的实例上调用实例方法
            guiForm.loadAndSetImage("C:\\Users\\11369\\IdeaProjects\\proj\\src\\Mcdonals.png");
        });
    }
}