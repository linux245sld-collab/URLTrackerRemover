# URLTrackerRemover

![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Windows%20%7C%20macOS%20%7C%20Linux-lightgrey.svg)

一個簡潔優雅的圖形化工具，用於去除 URL 中的跟蹤參數，保護您的網路隱私。

## ✨ 特點

- 🎨 **現代化界面** - 採用類似 GNOME 的簡潔設計風格，界面友好直觀
- 🔒 **隱私優先** - 自動識別並去除超過 20 種常見的跟蹤參數
- ⚡ **即時處理** - 點擊即可完成處理，無需等待
- 📋 **一鍵複製** - 處理後的乾淨 URL 可直接複製到剪貼板
- 💻 **跨平台** - 完全支援 Windows、macOS 和 Linux 系統
- 🚀 **輕量級** - 無需額外依賴，純 Java 實現

## 🖼️ 界面預覽

程式提供清晰的雙面板設計：
- **上方輸入區** - 貼上包含跟蹤參數的原始 URL
- **下方輸出區** - 顯示處理後的乾淨 URL
- **底部操作欄** - 「複製結果」和「現在去除」兩個功能按鈕

## 🎯 支援去除的跟蹤參數

### 📊 Google Analytics 系列
- `utm_source` - 流量來源
- `utm_medium` - 媒介類型
- `utm_campaign` - 活動名稱
- `utm_term` - 關鍵字
- `utm_content` - 內容標識
- `_ga`, `_gl` - Google Analytics 追蹤

### 📱 社交媒體追蹤
- `fbclid` - Facebook 點擊標識符
- `gclid` - Google Ads 點擊標識符
- `msclkid` - Microsoft Advertising 標識符
- `twclid` - Twitter 點擊標識符

### 📧 Email 營銷追蹤
- `mc_eid` - Mailchimp Email ID
- `mc_cid` - Mailchimp Campaign ID

### 🔍 其他常見追蹤參數
- `ref`, `referer`, `referrer` - 來源參照
- `source`, `campaign` - 通用來源和活動標識
- `ad_id`, `adset_id` - 廣告相關標識
- `yclid`, `wickedid`, `gbraid`, `wbraid` - 其他平台追蹤

## 🚀 快速開始

### 前置需求

- Java Development Kit (JDK) 8 或更高版本
- 任何支援 Java Swing 的作業系統

### 安裝步驟

#### 方法一：從源碼運行

1. **克隆倉庫**
   ```bash
   git clone https://github.com/linux245sld-collab/URLTrackerRemover.git
   cd URLTrackerRemover
   ```

2. **編譯程式**
   ```bash
   javac URLTrackerRemover.java
   ```

3. **啟動應用**
   ```bash
   java URLTrackerRemover
   ```

#### 方法二：打包為 JAR 檔案

1. **創建 Manifest 文件**
   ```bash
   echo "Main-Class: URLTrackerRemover" > manifest.txt
   ```

2. **編譯並打包**
   ```bash
   javac URLTrackerRemover.java
   jar cvfm URLTrackerRemover.jar manifest.txt URLTrackerRemover*.class
   ```

3. **運行 JAR**
   ```bash
   java -jar URLTrackerRemover.jar
   ```

或者直接雙擊 `URLTrackerRemover.jar` 檔案運行（需要系統已安裝 Java）。

## 📖 使用指南

### 基本操作流程

1. **啟動程式**
   - 在終端運行 `java URLTrackerRemover`
   - 或雙擊 JAR 檔案

2. **輸入 URL**
   - 複製包含跟蹤參數的 URL
   - 在「原鏈接」文本框中貼上（Ctrl+V 或 Cmd+V）

3. **處理 URL**
   - 點擊「現在去除」按鈕
   - 系統會自動識別並移除跟蹤參數
   - 彈出提示框確認處理完成

4. **使用結果**
   - 查看「處理後」區域的乾淨 URL
   - 點擊「複製結果」按鈕複製到剪貼板
   - 直接貼上使用

### 📝 實際範例

**處理前的 URL：**
```
https://www.example.com/product?id=12345&utm_source=facebook&utm_medium=social&utm_campaign=summer_sale&fbclid=IwAR1a2b3c4d5e6f7g8h9&ref=homepage
```

**處理後的 URL：**
```
https://www.example.com/product?id=12345
```

✅ 成功移除了 5 個跟蹤參數，保留了必要的產品 ID！

## 🔧 進階配置

### 自訂跟蹤參數列表

如果您需要添加特定的跟蹤參數，可以編輯源碼中的 `TRACKING_PARAMS` 列表：

```java
private static final List<String> TRACKING_PARAMS = Arrays.asList(
    // Google Analytics
    "utm_source", "utm_medium", "utm_campaign", "utm_term", "utm_content",
    // 添加您自己的參數
    "your_custom_param",
    "another_tracking_param"
);
```

重新編譯後即可生效。

### 批次處理（計劃中）

未來版本將支援：
- 批次輸入多個 URL
- 從檔案讀取 URL 列表
- 匯出處理結果

## 🏗️ 技術架構

### 專案結構
```
URLTrackerRemover/
├── URLTrackerRemover.java    # 主程式（GUI + 邏輯）
├── README.md                  # 專案說明文件
└── LICENSE                    # MIT 授權文件
```

### 技術棧
- **程式語言**: Java 8+
- **GUI 框架**: Java Swing
- **URL 處理**: Java URI 類
- **設計風格**: GNOME-inspired

### 核心實現邏輯

1. **URL 解析** - 使用 `java.net.URI` 解析 URL 結構
2. **參數過濾** - 遍歷查詢參數，過濾跟蹤參數
3. **URL 重建** - 重新組裝乾淨的 URL
4. **剪貼板操作** - 使用 `java.awt.datatransfer` 實現複製功能

## ⚠️ 注意事項

- ⚡ 本工具只處理 URL 查詢參數（`?` 後面的部分）
- 🔗 不會修改 URL 的協議、域名、路徑或錨點部分
- ✅ 保留所有非跟蹤類的功能性參數（如商品 ID、頁碼等）
- ⚠️ 某些網站可能依賴特定參數，處理後請確認功能正常
- 🔒 所有處理均在本地完成，不會上傳任何數據到網路

## 🤝 貢獻

歡迎各種形式的貢獻！無論是：

- 🐛 提交 Bug 報告
- 💡 建議新功能
- 📝 改進文檔
- 🔧 提交代碼修復

### 貢獻流程

1. Fork 本倉庫
2. 創建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 開啟 Pull Request

### 開發指南

- 遵循 Java 編碼規範
- 添加必要的註釋
- 測試新功能後再提交
- 更新相關文檔

## 📋 待辦事項 (TODO)

- [ ] 支援批次處理多個 URL
- [ ] 添加自訂參數配置界面
- [ ] 支援從檔案導入/導出
- [ ] 添加歷史記錄功能
- [ ] 支援正則表達式自訂規則
- [ ] 製作安裝程式（Linux .AppImage / Windows .exe / macOS .app）
- [ ] 添加深色模式支援

## 📄 授權

本專案採用 MIT 授權條款 - 詳見 [LICENSE](LICENSE) 文件

### 簡單來說：

✅ 可以自由使用、修改、分發
✅ 可以用於商業專案
✅ 只需保留版權聲明

## 👨‍💻 作者

**linux245sld-collab**

- GitHub: [@linux245sld-collab](https://github.com/linux245sld-collab)
- 專案連結: [URLTrackerRemover](https://github.com/linux245sld-collab/URLTrackerRemover)

## 🙏 致謝

- 感謝所有為網路隱私保護做出貢獻的開發者
- 靈感來源於 [ClearURLs](https://github.com/ClearURLs/Addon) 等瀏覽器擴展

## 📬 問題回報

如果您遇到問題或有任何建議，請：

1. 查看 [Issues](https://github.com/linux245sld-collab/URLTrackerRemover/issues) 頁面
2. 如果是新問題，請創建新的 Issue
3. 詳細描述問題或建議

---

⭐ 如果這個專案對您有幫助，請給個 Star 支持一下！

**隱私提醒**：本工具完全離線運行，不會收集或上傳任何用戶數據。
