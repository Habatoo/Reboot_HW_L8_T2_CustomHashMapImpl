package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomHashMapImpl<Object>}.
 *
 * @author habatoo
 */
class CustomHashMapImplTest {

    CustomHashMap<Integer, String> customEmptyHashMap;
    CustomHashMap<Integer, String> customNotEmptyHashMap;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomHashMapImpl<Object>}.
     */
    @BeforeEach
    void setUp() {
        customEmptyHashMap = new CustomHashMapImpl<Integer, String>();

        customNotEmptyHashMap = new CustomHashMapImpl<Integer, String>();
        customNotEmptyHashMap.put(1, "first");
        customNotEmptyHashMap.put(20, "second");
        customNotEmptyHashMap.put(95, "third");
        customNotEmptyHashMap.put(2, "fourth");
        customNotEmptyHashMap.put(39, "last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomHashMapImpl<Object>}..
     */
    @AfterEach
    void tearDown() {
        customEmptyHashMap = null;
        customNotEmptyHashMap = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomHashMapImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию,
     * проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customHashSetImpl_Test() {
        customEmptyHashMap = new CustomHashMapImpl<Integer, String>();
        Assertions.assertEquals(0, customEmptyHashMap.size());
        Assertions.assertEquals(
                "[ ]", customEmptyHashMap.toString());
    }

    /**
     * Метод {@link CustomHashMapImplTest#size_Test()}
     * проверяет метод {@link CustomHashMapImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomHashMapImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой экземпляр возвращает 5.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyHashMap.size());
        Assertions.assertEquals(5, customNotEmptyHashMap.size());
    }

    /**
     * Метод  {@link CustomHashMapImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomHashMapImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomHashMapImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyHashMap.isEmpty());
        Assertions.assertEquals(false, customNotEmptyHashMap.isEmpty());
    }

    /**
     * Метод {@link CustomHashMapImplTest#getSuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#get(Object)}.
     * Сценарий, при котором проверяется наличие существующего ключа и
     * возвращает значение V соответсвующее этому ключу.
     */
    @Test
    void getSuccess_Test() {
        Assertions.assertEquals("first", customNotEmptyHashMap.get(1));
        Assertions.assertEquals("last", customNotEmptyHashMap.get(39));
    }

    /**
     * Метод {@link CustomHashMapImplTest#getFail_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#get(Object)}.
     * Сценарий, при котором проверяется наличие не существующего ключа и
     * возвращает null.
     */
    @Test
    void getFail_Test() {
        Assertions.assertEquals(null, customEmptyHashMap.get(999));
        Assertions.assertEquals(null, customNotEmptyHashMap.get(999));
    }

    /**
     * Метод {@link CustomHashMapImplTest#putSuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#put(Object, Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * сушествующего объекта и возвращает его старое значение.
     */
    @Test
    void putSuccess_Test() {
        Assertions.assertEquals(5, customNotEmptyHashMap.size());
        Assertions.assertEquals("first", customNotEmptyHashMap.put(1,"notFirst"));
        Assertions.assertEquals(5, customNotEmptyHashMap.size());
    }

    /**
     * Метод {@link CustomHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#put(Object, Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>null</code>.
     */
    @Test
    void putNull_Test() {
        Assertions.assertEquals(0, customEmptyHashMap.size());
        Assertions.assertEquals(null, customEmptyHashMap.put(15,"first"));
        Assertions.assertEquals(null, customNotEmptyHashMap.put(99, "null"));
    }

    /**
     * Метод {@link CustomHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого существующего объекта по ключу К и возвращает старое значение V.
     */
    @Test
    void removeSuccess_Test() {
        Assertions.assertEquals("first", customNotEmptyHashMap.remove(1));
    }

    /**
     * Метод {@link CustomHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого не существующего объекта по ключу К и возвращает null.
     */
    @Test
    void removeFail_Test() {
        Assertions.assertEquals(null, customNotEmptyHashMap.remove(999));
        Assertions.assertEquals(null, customEmptyHashMap.remove(999));
    }

    /**
     * Метод {@link CustomHashMapImplTest#containsKeySuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#containsKey(Object)}.
     * Сценарий, при котором проверяется наличие существующего ключа и
     * возвращает <code>true</code>.
     */
    @Test
    void containsKeySuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyHashMap.containsKey(1));
        Assertions.assertEquals(true, customNotEmptyHashMap.containsKey(20));
    }

    /**
     * Метод {@link CustomHashMapImplTest#containsKeyFail_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#containsKey(Object)}.
     * Сценарий, при котором проверяется наличие не существующего ключа и
     * возвращает <code></code>.
     */
    @Test
    void containsKeyFail_Test() {
        Assertions.assertEquals(false, customEmptyHashMap.containsKey(999));
        Assertions.assertEquals(false, customNotEmptyHashMap.containsKey(999));
    }

    /**
     * Метод {@link CustomHashMapImplTest#containsValueSuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется наличие существующего значения и
     * возвращает <code>true</code>.
     */
    @Test
    void containsValueSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyHashMap.containsValue("first"));
        Assertions.assertEquals(true, customNotEmptyHashMap.containsValue("last"));
    }

    /**
     * Метод {@link CustomHashMapImplTest#containsValueFail_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется наличие не существующего значения и
     * возвращает <code></code>.
     */
    @Test
    void containsValueFail_Test() {
        Assertions.assertEquals(false, customEmptyHashMap.containsValue("none"));
        Assertions.assertEquals(false, customNotEmptyHashMap.containsValue("none"));
    }

    /**
     * Метод {@link CustomHashMapImplTest#keys_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#keys()}.
     * Сценарий, при котором проверяется отображение массива всех ключей.
     */
    @Test
    void keys_Test() {
        Assert.assertEquals(Arrays.asList(1, 2, 20, 95, 39), Arrays.asList(customNotEmptyHashMap.keys()));
    }

    /**
     * Метод {@link CustomHashMapImplTest#containsValueFail_Test()}
     * Проверяет проверяет метод {@link CustomHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется отображение массива всех значений.
     */
    @Test
    void values_Test() {
        Assert.assertEquals(Arrays.asList(
                "first", "fourth", "second", "third", "last"), Arrays.asList(customNotEmptyHashMap.values()));
    }

    /**
     * Метод {@link CustomHashMapImplTest#testToStringEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomHashMapImpl}
     * методом {@link CustomHashMapImpl#toString()}.
     * Сценарий, при котором пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyHashMap.toString());
    }

    /**
     * Метод {@link CustomHashMapImplTest#testToStringNotEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomHashMapImpl}
     * методом {@link CustomHashMapImpl#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringNotEmpty_Test() {
        Assertions.assertEquals(
                "[ { key=1;value=first } { key=2;value=fourth } { key=20;value=second } { key=95;value=third } { key=39;value=last } ]",
                customNotEmptyHashMap.toString());
    }

    private CustomHashMap<Integer, String> map = new CustomHashMapImpl<>(16);

    @Test
    public void sizeTests() {

        Assert.assertEquals(0, map.size());
        Assert.assertTrue(map.isEmpty());

        for (int i = 1; i <= 10; ++i) {
            Assert.assertNull(map.put(i, "Value" + i));
            Assert.assertEquals(i, map.size());
        }

        for (int i = 10; i >= 1; --i) {
            Assert.assertEquals("Value" + i, map.put(i, "NewValue" + i));
        }
        Assert.assertEquals(10, map.size());

        for (int i = 10; i >= 1; --i) {
            Assert.assertEquals("NewValue" + i, map.remove(i));
            Assert.assertEquals(i - 1, map.size());
        }

        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void getTests() {

        for (int i = 0; i < 10; ++i) {
            Assert.assertNull(map.get(i));
            map.put(i, "V" + i);
            Assert.assertEquals("V" + i, map.get(i));
        }
    }

    @Test
    public void putTests() {

        Assert.assertEquals(0, map.keys().length);

        Assert.assertEquals(null, map.put(10, "Value10"));
        Assert.assertEquals(Arrays.asList(10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(5, "Value5"));
        Assert.assertEquals(Arrays.asList(5, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("Value5", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(15, "Value15"));
        Assert.assertEquals(Arrays.asList(15, 5, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("Value15", "Value5", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(null, "null1"));
        Assert.assertEquals("null1", map.put(null, "null2"));
        Assert.assertEquals(Arrays.asList(null, 15, 5, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value5", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals("Value15", map.put(15, "Value15"));
        Assert.assertEquals(Arrays.asList(null, 15, 5, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value5", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(7, "Value7"));
        Assert.assertEquals(Arrays.asList(null, 15, 5, 7, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value5", "Value7", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(1, "Value1"));
        Assert.assertEquals(Arrays.asList(null, 15, 1, 5, 7, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value1", "Value5", "Value7", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(9, "Value9"));
        Assert.assertEquals(Arrays.asList(null, 15, 1, 5, 7, 9, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value1", "Value5", "Value7", "Value9", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(20, "Value20"));
        Assert.assertEquals(Arrays.asList(null, 15, 1, 5, 20, 7, 9, 10), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value1", "Value5", "Value20", "Value7", "Value9", "Value10"), Arrays.asList(map.values()));

        Assert.assertEquals(null, map.put(100, "Value100"));
        Assert.assertEquals(Arrays.asList(null, 15, 1, 5, 20, 7, 9, 10, 100), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("null2", "Value15", "Value1", "Value5", "Value20", "Value7", "Value9", "Value10", "Value100"), Arrays.asList(map.values()));

        Assert.assertEquals(9, map.size());
    }

    @Test
    public void removeTests() {

        map.put(10, "Value10");
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(10));

        map.remove(10);
        Assert.assertFalse(map.containsKey(10));
        Assert.assertEquals(0, map.size());

        map.put(30, "V30");

        map.put(50, "V50");
        map.put(10, "V10");

        map.put(5, "V5");
        map.put(20, "V20");
        map.put(70, "V70");
        map.put(100, "V100");
        map.put(60, "V60");
        map.put(65, "V65");
        map.put(55, "V55");
        map.put(56, "V56");
        map.put(19, "V19");

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 20, 65, 10, 70, 100, 55, 56), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("V30", "V60", "V19", "V50", "V5", "V20", "V65", "V10", "V70", "V100", "V55", "V56"), Arrays.asList(map.values()));

        map.remove(20);
        map.remove(100);

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 65, 10, 70, 55, 56), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("V30", "V60", "V19", "V50", "V5", "V65", "V10", "V70", "V55", "V56"), Arrays.asList(map.values()));

        map.remove(65);

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 10, 70, 55, 56), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("V30", "V60", "V19", "V50", "V5", "V10", "V70", "V55", "V56"), Arrays.asList(map.values()));

        map.remove(50);
        Assert.assertEquals(Arrays.asList(30, 60, 19, 5, 10, 70, 55, 56), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("V30", "V60", "V19", "V5", "V10", "V70", "V55", "V56"), Arrays.asList(map.values()));

        map.remove(30);
        Assert.assertEquals(Arrays.asList(60, 19, 5, 10, 70, 55, 56), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList("V60", "V19", "V5", "V10", "V70", "V55", "V56"), Arrays.asList(map.values()));

        map.remove(5);
        map.remove(10);
        map.remove(70);
        map.remove(19);
        map.remove(55);
        map.remove(56);
        map.remove(60);

        Assert.assertEquals(0, map.size());
        Assert.assertEquals(Arrays.asList(), Arrays.asList(map.keys()));
        Assert.assertEquals(Arrays.asList(), Arrays.asList(map.values()));

    }

    @Test
    public void containsKeyTests() {

        for (int i = 0; i < 10; ++i) {
            map.put(i, "V1");
            Assert.assertTrue(map.containsKey(i));
        }
        map.put(null, "Vnull");

        Assert.assertTrue(map.containsKey(null));
        Assert.assertFalse(map.containsKey(1000));
    }

    @Test
    public void containsValueTests() {

        for (int i = 0; i < 10; ++i) {
            map.put(i, "V" + i);
            Assert.assertTrue(map.containsValue("V" + i));
            Assert.assertTrue(map.containsKey(i));
        }
        map.put(null, "Vnull");

        Assert.assertTrue(map.containsValue("Vnull"));
        Assert.assertFalse(map.containsValue("V1000"));
    }

    @Test
    public void keysTests() {

        map.put(10, "Value10");
        Assert.assertEquals(1, map.size());

        map.remove(10);
        Assert.assertFalse(map.containsKey(10));

        map.put(30, "V30");
        map.put(null, "Vnull");

        map.put(50, "V50");
        map.put(10, "V10");

        map.put(5, "V5");
        map.put(20, "V20");
        map.put(70, "V70");
        map.put(100, "V100");
        map.put(60, "V60");
        map.put(65, "V65");
        map.put(55, "V55");
        map.put(56, "V56");
        map.put(19, "V19");

        Assert.assertEquals(Arrays.asList(null, 30, 60, 19, 50, 5, 20, 65, 10, 70, 100, 55, 56), Arrays.asList(map.keys()));
    }

    @Test
    public void valuesTests() {

        map.put(10, "Value10");
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(10));

        map.remove(10);
        Assert.assertFalse(map.containsKey(10));
        Assert.assertEquals(0, map.size());

        map.put(30, "V30");

        map.put(50, "V50");
        map.put(10, "V10");

        map.put(5, "V5");
        map.put(20, "V20");
        map.put(70, "V70");
        map.put(100, "V100");
        map.put(60, "V60");
        map.put(65, "V65");
        map.put(55, "V55");
        map.put(56, "V56");
        map.put(19, "V19");

        Assert.assertEquals(Arrays.asList("V30", "V60", "V19", "V50", "V5", "V20", "V65", "V10", "V70", "V100", "V55", "V56"), Arrays.asList(map.values()));
    }
}