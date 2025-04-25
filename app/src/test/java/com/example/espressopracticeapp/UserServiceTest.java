package com.example.espressopracticeapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.example.espressopracticeapp.data.UserRepository;
import com.example.espressopracticeapp.service.UserService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserServiceTest {

    // ✅ 1. Mock：檢查方法是否被呼叫
    @Test
    public void testUserServiceCallsRepository_getAllUsers() {
        UserRepository mockRepo = org.mockito.Mockito.mock(UserRepository.class);
        UserService service = new UserService(mockRepo);

        //呼叫方法
        service.loadUsers();

        //驗證是否有進行呼叫
        verify(mockRepo, times(1)).getAllUsers();
    }

    // ✅ 2. Stub：提供固定回傳值，不檢查是否被呼叫
    @Test
    public void testUserServiceReturnsStubData() {
        UserRepository stubRepo = org.mockito.Mockito.mock(UserRepository.class);
        when(stubRepo.getAllUsers()).thenReturn(Arrays.asList("測試用戶 A", "測試用戶 B"));

        UserService service = new UserService(stubRepo);
        List<String> result = service.loadUsers();

        assertEquals(2, result.size());
        assertEquals("測試用戶 A", result.get(0));
    }

    // ✅ 3. Fake：用自製的簡易版本實作資料邏輯
    public static class FakeUserRepository implements UserRepository {
        private final List<String> fakeData;

        public FakeUserRepository() {
            fakeData = new ArrayList<>();
            fakeData.add("假資料用戶1");
            fakeData.add("假資料用戶2");
            fakeData.add("假資料用戶3");
        }
        @Override
        public List<String> getAllUsers() {
            return new ArrayList<>(fakeData);
        }
        @Override
        public List<String> getUsersReversed() {
            List<String> reversed = new ArrayList<>(fakeData);
            Collections.reverse(reversed);
            return reversed;
        }
        @Override
        public List<String> resetToOriginalOrder() {
            return getAllUsers();
        }
    }

    @Test
    public void testUserServiceWithFakeRepository() {
        UserService service = new UserService(new FakeUserRepository());
        List<String> result = service.loadUsers();

        assertEquals(3, result.size());
        assertEquals("假資料用戶2", result.get(1));
    }

    // ✅ 追加：測試反轉排序功能
    @Test
    public void testUserServiceWithFakeRepositoryReversed() {
        UserService service = new UserService(new FakeUserRepository());
        List<String> reversed = service.loadUsersReversed();

        assertEquals("假資料用戶3", reversed.get(0));
        assertEquals("假資料用戶1", reversed.get(reversed.size() - 1));
    }

    // ✅ 追加：測試 reset 回原始順序功能
    @Test
    public void testUserServiceWithFakeRepositoryReset() {
        UserService service = new UserService(new FakeUserRepository());
        List<String> reset = service.resetToOriginalOrder();

        assertEquals("假資料用戶1", reset.get(0));
        assertEquals("假資料用戶3", reset.get(2));
    }
}
