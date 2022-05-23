import javax.swing.JFrame;

public class Window extends JFrame{
    public Window(){
        setTitle("주사위 게임");
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Window();
    }
}
