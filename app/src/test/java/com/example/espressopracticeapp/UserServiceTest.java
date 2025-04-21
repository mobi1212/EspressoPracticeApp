// ✅ UserServiceTest.java
// 含 Mock、Stub、Fake 三種測試替身使用方式

package com.example.espressopracticeapp;

import static org.mockito.Mockito.times; // ✅ 正確的
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.espressopracticeapp.data.UserRepository;
import com.example.espressopracticeapp.service.UserService;

import org.junit.Test;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {

    // ✅ 1. Mock：檢查方法是否被呼叫
    @Test
    public void testUserServiceCallsRepository_getAllUsers() {
        // 建立 mock
        UserRepository mockRepo = mock(UserRepository.class);
        UserService service = new UserService(mockRepo);

        // 執行方法
        service.loadUsers();

        // 驗證是否呼叫過一次 getAllUsers
        verify(mockRepo, (VerificationMode) times(1)).getAllUsers();
    }

    // ✅ 2. Stub：提供固定回傳值，不檢查是否被呼叫
    @Test
    public void testUserServiceReturnsStubData() {
        UserRepository stubRepo = mock(UserRepository.class);
        when(stubRepo.getAllUsers()).thenReturn(Arrays.asList("測試用戶 A", "測試用戶 B"));

        UserService service = new UserService(stubRepo);
        List<String> result = service.loadUsers();

        assertEquals(2, result.size());
        assertEquals("測試用戶 A", result.get(0));
    }

    // ✅ 3. Fake：用自製的簡易版本實作資料邏輯
    public static class FakeUserRepository implements UserRepository {
        private final List<String> fakeData = Arrays.asList("假資料用戶1", "假資料用戶2", "假資料用戶3");


        @Override
        public List<String> getAllUsers() {
            return fakeData;
        }
    }

    @Test
    public void testUserServiceWithFakeRepository() {
        UserService service = new UserService(new FakeUserRepository());
        List<String> result = service.loadUsers();

        assertEquals(3, result.size());
        assertEquals("假資料用戶2", result.get(1));
    }
}

/*
🧠 原理說明：

✅ Mock
- 用 Mockito 建立模擬物件，不真正執行邏輯，只記錄呼叫狀態
- 搭配 verify(...) 來「驗證某個方法是否被呼叫過」

✅ Stub
- 回傳固定資料，忽略邏輯與互動，只在乎回傳值
- 你可以用 when(...).thenReturn(...) 模擬資料庫或 API 回應

✅ Fake
- 手動寫一個實作 UserRepository 的類別，用簡化邏輯模擬實際功能（如 ArrayList 模擬 DB）
- 可獨立執行，不依賴外部框架（適合單元測試）

這三種方式可以依照測試需求自由切換，提高可測性與穩定性。
*/
