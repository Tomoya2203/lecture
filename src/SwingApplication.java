import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.awt.*;

public class SwingApplication {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private final String DB_NAME = "../lib/sample.db";
    private Connection conn;
    private Add add;

    public SwingApplication() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + this.DB_NAME);
            add = new Add(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Component createComponents() {
        // メインパネルとカードレイアウトを作成
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 各ページをインスタンス化
        HomePage homePage = new HomePage(
            e -> cardLayout.show(mainPanel, "View"),
            e -> cardLayout.show(mainPanel, "Edit"),
            e -> cardLayout.show(mainPanel, "Add")
        );
        ViewPage viewPage = new ViewPage(conn);
        EditPage editPage = new EditPage();
        AddPage addPage = new AddPage(add);

        // メインパネルに各ページを追加
        mainPanel.add(homePage, "Home");
        mainPanel.add(viewPage, "View");
        mainPanel.add(editPage, "Edit");
        mainPanel.add(addPage, "Add");

        // ボタンパネル
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton homeButton = new JButton("Home");
        JButton viewButton = new JButton("View");
        JButton editButton = new JButton("Edit");
        JButton addButton = new JButton("Add");

        // 各ボタンにアクションリスナーを設定
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        viewButton.addActionListener(e -> cardLayout.show(mainPanel, "View"));
        editButton.addActionListener(e -> cardLayout.show(mainPanel, "Edit"));
        addButton.addActionListener(e -> cardLayout.show(mainPanel, "Add"));

        buttonPanel.add(homeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(addButton);

        // コンテナパネルを作成して、ボタンパネルとメインパネルを配置
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(buttonPanel, BorderLayout.NORTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("講義管理システム");
        SwingApplication app = new SwingApplication();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
