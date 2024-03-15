import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class MineSweeper extends JFrame {
    private JPanel mainPane;
    GridBagConstraints gbc = new GridBagConstraints();
    Container con = getContentPane();
    private JButton[][] buttons;
    private JLabel[][] label;

    public MineSweeper(int rows, int colls)
    {
        super("Mine Sweeper");
        Components();
        MineGen(rows,colls);
    }

    public void Components()
    {
        setMinimumSize(new Dimension(600,600));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagLayout gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        setLocationRelativeTo(null);
    }

    public void NumberGen(int rows, int colls)
    {
        for(int y = 0; y < colls; y++)
        {
            for (int x = 0; x < rows; x++)
            {
                if(!(label[x][y].getText().equals("Bomba")))
                {
                    label[x][y] = new JLabel();
                    label[x][y].setPreferredSize(new Dimension(50,50));
                    label[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridx = x;
                    gbc.gridy = y;
                    int sum = 0;
                    for(int zy = y-1; zy <= y+1; zy++)
                    {
                        for (int zx = x-1; zx <= x+1; zx++)
                        {
                            try
                            {
                                if(label[zx][zy].getText().equals("Bomba"))
                                {
                                    sum++;
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("Problem");
                            }
                        }
                    }
                    label[x][y].setText(String.valueOf(sum));
                    con.add(label[x][y], gbc);
                }
            }
        }

    }
    public void MineGen(int rows, int colls)
    {
        buttons = new JButton[rows][colls];
        label = new JLabel[rows][colls];
        int a = new Random().nextInt(10);
        for (int y = 0; y < colls; y++) {
            for (int x = 0; x < rows; x++) {
                buttons[x][y] = new JButton("-----");
                buttons[x][y].setPreferredSize(new Dimension(50,50));


                //button disappears when clicked
                int finalX = x;
                int finalY = y;
                buttons[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttons[finalX][finalY].setEnabled(false);
                        buttons[finalX][finalY].setVisible(false);

                        if(label[finalX][finalY].getText().equals("Bomba"))
                        {
                            JOptionPane.showMessageDialog(null,"Prohrál");
                            new MineSweeper(rows,colls);
                            dispose();
                        }
                    }
                });


                //label under button
                label[x][y] = new JLabel();
                label[x][y].setPreferredSize(new Dimension(50,50));
                label[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                gbc.gridx = x;
                gbc.gridy = y;


                //percentage chance to generate mine or not
                if (a < 2)
                {
                    label[x][y].setText("Bomba");
                }

                //adds things to scene
                con.add(buttons[x][y], gbc);
                con.add(label[x][y], gbc);
                a = new Random().nextInt(10);
            }
        }
        NumberGen(rows,colls);
    }
    public static void main(String[] args) {
        MineSweeper mines = new MineSweeper(10,10);
    }
}

