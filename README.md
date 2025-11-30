# URL 跟蹤參數清除工具

一個簡潔易用的圖形化工具，用於去除 URL 中的跟蹤參數，保護您的隱私。

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)

## 📋 功能特點

- 🎨 **現代化界面**：採用類似 GNOME 的簡潔設計風格
- 🔒 **隱私保護**：自動去除各種跟蹤參數
- 📋 **一鍵複製**：處理後的 URL 可直接複製到剪貼板
- 🚀 **快速處理**：即時去除跟蹤參數，無需等待
- 💻 **跨平台**：支援 Windows、macOS、Linux

## 🎯 支援去除的跟蹤參數

### Google Analytics
- `utm_source`, `utm_medium`, `utm_campaign`
- `utm_term`, `utm_content`
- `_ga`, `_gl`

### 社交媒體追蹤
- `fbclid` (Facebook)
- `gclid` (Google Ads)
- `msclkid` (Microsoft Ads)
- `twclid` (Twitter)

### 其他常見追蹤參數
- `ref`, `referer`, `referrer`
- `source`, `campaign`
- `ad_id`, `adset_id`
- `mc_eid`, `mc_cid` (Mailchimp)
- `yclid`, `wickedid`, `gbraid`, `wbraid`

## 🚀 快速開始

### 系統需求

- Java 8 或更高版本
- 任何支援 Java Swing 的作業系統

### 安裝步驟

1. **下載源代碼**
   ```bash
   # 如果使用 Git
   git clone <repository-url>
   cd url-tracker-remover
   ```

2. **編譯程式**
   ```bash
   javac URLTrackerRemover.java
   ```

3. **運行程式**
   ```bash
   java URLTrackerRemover
   ```

## 📖 使用說明

### 基本使用

1. **啟動程式**
   - 運行 `java URLTrackerRemover` 命令
   - 程式視窗將會打開

2. **輸入 URL**
   - 在「原鏈接」區域貼上您要處理的 URL
   - 可以使用 `Ctrl+V` (Windows/Linux) 或 `Cmd+V` (macOS) 貼上

3. **去除跟蹤參數**
   - 點擊「現在去除」按鈕
   - 處理後的乾淨 URL 將顯示在「處理後」區域

4. **複製結果**
   - 點擊「複製結果」按鈕
   - 乾淨的 URL 已複製到剪貼板，可以直接貼上使用

### 使用範例

**處理前：**
```
https://example.com/page?utm_source=twitter&utm_medium=social&fbclid=IwAR123456&id=100
```

**處理後：**
```
https://example.com/page?id=100
```

## 🔧 進階使用

### 自訂跟蹤參數列表

如果您想要添加更多跟蹤參數，可以編輯源代碼中的 `TRACKING_PARAMS` 列表：

```java
private static final List<String> TRACKING_PARAMS = Arrays.asList(
    "utm_source", "utm_medium", "utm_campaign",
    // 在這裡添加您自己的參數
    "your_custom_param"
);
```

### 打包為可執行 JAR

```bash
# 創建 manifest 文件
echo "Main-Class: URLTrackerRemover" > manifest.txt

# 打包
jar cvfm URLTrackerRemover.jar manifest.txt URLTrackerRemover.class

# 運行
java -jar URLTrackerRemover.jar
```

## 🛠️ 技術細節

### 專案結構

```
url-tracker-remover/
├── URLTrackerRemover.java    # 主程式文件
├── README.md                  # 本文件
└── LICENSE                    # 授權文件
```

### 技術棧

- **語言**：Java
- **GUI 框架**：Java Swing
- **最低 JDK 版本**：Java 8

### 工作原理

1. 解析輸入的 URL
2. 提取查詢參數
3. 過濾掉預定義的跟蹤參數
4. 重建乾淨的 URL
5. 保留 URL 的核心功能參數

## ⚠️ 注意事項

- 本工具只去除 URL 查詢參數中的跟蹤參數
- 不會修改 URL 的路徑或域名部分
- 某些網站可能依賴特定參數正常運作，請謹慎使用
- 處理後請確認 URL 仍然可以正常訪問

## 🤝 貢獻

歡迎提交問題報告和功能建議！

### 如何貢獻

1. Fork 本專案
2. 創建您的功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 開啟一個 Pull Request

## 📝 授權

本專案採用 MIT 授權 - 詳見 [LICENSE](LICENSE) 文件

## 📧 聯繫方式

如有問題或建議，請通過以下方式聯繫：

- 提交 Issue
- 發送 Pull Request

## 🙏 致謝

- 感謝所有為隱私保護做出貢獻的開發者
- 靈感來源於各種瀏覽器擴展和隱私工具

---

**免責聲明**：本工具僅供學習和個人使用。使用者需自行承擔使用本工具的風險。
