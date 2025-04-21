



### 📘 README.md 內容

# EspressoPracticeApp

一個專為練習 Android Espresso UI 自動化測試所設計的教學專案，使用 Java 語言與 View-Based 架構（非 Compose），適合初學者逐步學習 Espresso 的各種操作。



## 🛠 專案特色

- 使用 Java 開發，無需 Kotlin 知識即可上手
- 每個畫面對應一種 Espresso 測試場景
- 完整五個功能頁面 + 主畫面切換架構
- 所有頁面皆有「返回主畫面」按鈕，方便測試整體流程



## 📱 頁面列表與功能說明

| 畫面名稱         | 功能概述                             | 對應測試主題             |
|------------------|--------------------------------------|--------------------------|
| MainActivity      | 三個按鈕導向 Login/List/Calculator   | Intent、跳頁驗證         |
| LoginActivity     | 輸入帳密 → 驗證 → 顯示登入結果       | `typeText`、`check`      |
| ListActivity      | RecyclerView 顯示使用者 → 點擊跳轉   | `RecyclerViewActions`    |
| DetailActivity    | 顯示點擊的使用者名稱                 | `hasExtra`、`checkText`  |
| CalculatorActivity| 輸入兩數並做加減乘除 → 顯示結果      | 邏輯運算、錯誤處理測試   |



## 🧪 Espresso 測試規劃

本專案未內建測試檔案，建議依下列順序撰寫對應測試類別：

1. `LoginActivityTest` - 練習輸入與文字驗證
2. `ListActivityTest` - 練習滾動、點選 RecyclerView
3. `DetailActivityTest` - 驗證 Intent 是否帶值與顯示正確
4. `CalculatorActivityTest` - 測試不同組合輸入與例外處理



## 🧰 開發工具與版本

- Android Studio Hedgehog+
- Java 語言
- Espresso 3.5.1
- Min SDK 21
- Gradle Wrapper 8.x



## 🚀 快速啟動

bash
git clone https://github.com/你的帳號/EspressoPracticeApp.git
cd EspressoPracticeApp


打開 Android Studio → 開啟專案 → Build & Run 即可！



## 📄 授權

本專案僅供教學與練習用途，歡迎自由修改與擴充。


