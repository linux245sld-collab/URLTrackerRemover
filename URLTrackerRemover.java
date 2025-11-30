import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class URLTrackerRemover extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton cleanButton;
    private JButton copyButton;
    
    // 常見的跟蹤參數
    private static final List<String> TRACKING_PARAMS = Arrays.asList(
        "utm_source", "utm_medium", "utm_campaign", "utm_term", "utm_content",
        "fbclid", "gclid", "msclkid", "mc_eid", "mc_cid",
        "_ga", "_gl", "ref", "referer", "referrer",
        "source", "campaign", "ad_id", "adset_id",
        "yclid", "wickedid", "twclid", "gbraid", "wbraid"
    );
    
    public URLTrackerRemover() {
        initializeUI();
    }
    
    private void initializeUI() {
        // 設置窗口基本屬性
        setTitle("URL 跟蹤參數清除工具");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        // 使用系統外觀
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(250, 250, 250));
        
        // 頂部標題
        JLabel titleLabel = new JLabel("URL 跟蹤參數清除工具");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // 中央內容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1, 10, 10));
        contentPanel.setBackground(new Color(250, 250, 250));
        
        // 輸入區域
        JPanel inputPanel = createTextPanel("原鏈接:", true);
        inputArea = (JTextArea) ((JScrollPane) inputPanel.getComponent(1)).getViewport().getView();
        
        // 輸出區域
        JPanel outputPanel = createTextPanel("處理後:", false);
        outputArea = (JTextArea) ((JScrollPane) outputPanel.getComponent(1)).getViewport().getView();
        
        contentPanel.add(inputPanel);
        contentPanel.add(outputPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // 底部按鈕面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(new Color(250, 250, 250));
        
        // 清除按鈕
        cleanButton = new JButton("現在去除");
        cleanButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        cleanButton.setPreferredSize(new Dimension(120, 40));
        cleanButton.setBackground(new Color(53, 132, 228));
        cleanButton.setForeground(Color.WHITE);
        cleanButton.setFocusPainted(false);
        cleanButton.setBorderPainted(false);
        cleanButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cleanButton.addActionListener(e -> cleanURL());
        
        // 複製按鈕
        copyButton = new JButton("複製結果");
        copyButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        copyButton.setPreferredSize(new Dimension(120, 40));
        copyButton.setBackground(new Color(120, 120, 120));
        copyButton.setForeground(Color.WHITE);
        copyButton.setFocusPainted(false);
        copyButton.setBorderPainted(false);
        copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyButton.addActionListener(e -> copyToClipboard());
        
        buttonPanel.add(copyButton);
        buttonPanel.add(cleanButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createTextPanel(String labelText, boolean editable) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(new Color(250, 250, 250));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(editable);
        textArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        if (!editable) {
            textArea.setBackground(new Color(245, 245, 245));
        }
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void cleanURL() {
        String input = inputArea.getText().trim();
        
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "請輸入要處理的 URL", 
                "提示", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String cleaned = removeTrackingParams(input);
            outputArea.setText(cleaned);
            
            // 顯示結果提示
            if (cleaned.equals(input)) {
                JOptionPane.showMessageDialog(this, 
                    "此 URL 沒有檢測到跟蹤參數", 
                    "完成", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "跟蹤參數已成功去除！", 
                    "完成", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "處理 URL 時出錯：" + e.getMessage(), 
                "錯誤", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String removeTrackingParams(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String query = uri.getQuery();
        
        if (query == null || query.isEmpty()) {
            return url;
        }
        
        // 分割查詢參數
        String[] params = query.split("&");
        StringBuilder cleanQuery = new StringBuilder();
        
        for (String param : params) {
            String paramName = param.split("=")[0].toLowerCase();
            
            // 檢查是否為跟蹤參數
            boolean isTracking = false;
            for (String trackingParam : TRACKING_PARAMS) {
                if (paramName.equals(trackingParam.toLowerCase())) {
                    isTracking = true;
                    break;
                }
            }
            
            // 保留非跟蹤參數
            if (!isTracking) {
                if (cleanQuery.length() > 0) {
                    cleanQuery.append("&");
                }
                cleanQuery.append(param);
            }
        }
        
        // 重建 URL
        StringBuilder result = new StringBuilder();
        result.append(uri.getScheme()).append("://");
        if (uri.getAuthority() != null) {
            result.append(uri.getAuthority());
        }
        if (uri.getPath() != null) {
            result.append(uri.getPath());
        }
        if (cleanQuery.length() > 0) {
            result.append("?").append(cleanQuery);
        }
        if (uri.getFragment() != null) {
            result.append("#").append(uri.getFragment());
        }
        
        return result.toString();
    }
    
    private void copyToClipboard() {
        String output = outputArea.getText().trim();
        
        if (output.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "沒有可複製的內容", 
                "提示", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        StringSelection selection = new StringSelection(output);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        
        JOptionPane.showMessageDialog(this, 
            "已複製到剪貼板！", 
            "成功", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            URLTrackerRemover frame = new URLTrackerRemover();
            frame.setVisible(true);
        });
    }
}
