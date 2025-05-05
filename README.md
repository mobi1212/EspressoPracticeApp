



### 📘 `README.md`


# EspressoPracticeApp

一個專為練習 Android Espresso UI 自動化測試所設計的教學專案，使用 Java 語言與 View-Based 架構（非 Compose），適合初學者逐步學習 Espresso 的各種操作。



## 🛠 專案特色

- ✅ Java 編寫
- ✅ 使用 Espresso 編寫完整 UI 測試
- ✅ 所有頁面皆可透過主畫面導覽並返回
- ✅ 支援 IdlingResource、RecyclerView、Intent 驗證等實用測試場景


## 📱 頁面與功能說明

| 畫面名稱            | 功能概述                                  | 對應測試主題                     |
|---------------------|-------------------------------------------|----------------------------------|
| MainActivity         | 四個按鈕導向 Login/List/Calculator/Load   | Intent 跳轉、跳頁驗證             |
| LoginActivity        | 輸入帳密後驗證並顯示登入結果             | `typeText`       |
| ListActivity         | 顯示 RecyclerView，點擊項目開啟 Detail   | `RecyclerViewActions`    |
| DetailActivity       | 顯示點擊使用者名稱                       | `Intent hasExtra`、資料驗證      |
| CalculatorActivity   | 輸入兩數並做加減乘除 → 顯示結果          | 邏輯運算測試、錯誤處理           |
| LoadActivity         | 模擬資料載入（2 項）+ ProgressBar 控制   | `IdlingResource` 等待測試        |
| DataRequestActivity         | 點擊跳LoadActivity   | `Intent攔截`         |




## 🧪 分支說明與測試策略

本專案採用雙分支架構以利版本管理與測試分離：

### 🔵 `main` 分支（純功能版）
- ❌ 不含任何 Espresso 測試碼
- 專注於 UI 跟功能開發

### 🧪 `Expresso_test` 分支（測試版）
- ✅ 包含所有 Espresso 測試檔案
- androidTest測試：
  - `MainActivityTest`
  - `LoginActivityTest`
  - `ListActivityTest`
  - `DetailActivityTest`
  - `CalculatorActivityTest`
  - `LoadActivityTest`（IdlingResource）
  - `UserServiceTest`（Mock/Fake/Stub 單元測試）
  - `AppFlowTest`（整合流程測試）
  -  `DataRequestActivityTest`
  -  `CustomMatchers`
-  test測試：
    -  `UserServiceTest`



## 🚀 使用方式

切換至測試版本：

```bash
git checkout Expresso_test
```

執行專案：

```bash
git clone https://github.com/mobi1202/EspressoPracticeApp.git
cd EspressoPracticeApp
```

然後用 Android Studio 開啟專案 → Build & Run 即可！



## 🧰 開發工具與版本

| 工具名稱       | 版本說明         |
|----------------|------------------|
| Android Studio | 21.0.4       |
| 語言           | Java             |
| 測試框架       | Espresso 3.5.1   |
| Gradle         | Wrapper 8.10.2      |
| 最低支援版本   | minSdk 29        |



## 📄 授權 License

本專案供教學與測試練習用途，歡迎 fork、擴充與學習。





