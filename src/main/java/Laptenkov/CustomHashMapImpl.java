package Laptenkov;

import java.util.Objects;

/**
 * Класс {@link CustomHashMapImpl<K, V>} отображение на основе хеш-таблицы.
 * Класс {@link CustomHashMapImpl<K, V>} реализует интерфейс {@link CustomHashMap<K, V>}.
 * Класс {@link CustomHashMapImpl<K, V>} может хранить объекты любого типа.
 *
 * @param <K> ключ
 * @param <V> значение
 */
public class CustomHashMapImpl<K, V> implements CustomHashMap{

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int DEFAULT_CAPACITY = 16;
    private Node<K, V> table[] = new Node[DEFAULT_CAPACITY];
    private int size;
    private int capacity;

    /**
     * Конструктор пустого объекта {@link CustomHashMapImpl<K, V>}.
     */
    CustomHashMapImpl() {
        this.capacity = DEFAULT_CAPACITY;
    }

    /**
     * Конструктор пустого объекта {@link CustomHashMapImpl<K, V>}.
     */
    CustomHashMapImpl(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Метод {@link CustomHashMapImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomHashMapImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomHashMapImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomHashMapImpl} на пустоту.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomHashMapImpl#get(Object)} возвращает V - значение
     * по переданному ключу K
     *
     * @param key ключ К.
     * @return V - значение если ключу К соотвествует значение,
     * null если такого ключа нет.
     */
    @Override
    public Object get(Object key) {

        if (null == key && table[0] != null) {
            return null;
        }

        Node<K, V> tmp = table[getBucketId((K) key)];
        while (tmp != null) {
            if (getItemHash(tmp.key) == getItemHash((K)key) && Objects.equals(tmp.key, key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }

        return null;
    }

    /**
     * Метод {@link CustomHashMapImpl#get(Object)} устанавливает V - значение
     * по переданному ключу K, если значение уже есьб заменяет его.
     *
     * @param key ключ.
     * @param value значение
     * @return замененное значение или null.
     */
    @Override
    public Object put(Object key, Object value) {

        int bucketId = getBucketId((K) key);

        Node<K, V> tmp = table[bucketId];
        Node<K, V> prev = null;

        while (tmp != null) {
            if (getItemHash(tmp.key) == getItemHash((K)key) && Objects.equals(tmp.key, key)) {
                V oldValue = tmp.value;
                tmp.value = (V) value;

                return oldValue;
            }

            prev = tmp;
            tmp = tmp.next;
        }

        Node<K, V> insertedNode = new Node<>((K)key, (V) value);
        if (prev == null) {
            table[bucketId] = insertedNode;
            size++;
        } else {
            prev.next = insertedNode;
            size++;
        }

        return null;
    }

    private int getItemHash(K item) {
        if (item == null) {
            return 0;
        }
        return item.hashCode();
    }

    private int getBucketId(K key) {

        if (key == null) {
            return 0;
        }
        return 1 + key.hashCode() % (table.length - 1);
    }

    /**
     * Метод {@link CustomHashMapImpl#remove(Object)} удаляет объект по переданному
     * значению ключа К при наличия ключа K объекта в {@link CustomHashMapImpl} или
     * возвращает null.
     *
     * @param key передаваемый ключ.
     * @return удаленное значение или null если объект не существует.
     */
    @Override
    public Object remove(Object key) {
        if (!containsKey(key)) {
            return null;
        }

        if (null == key && table[0] != null) {
            table[0] = null;
            return null;
        }

        Node<K, V> tmp = table[getBucketId((K) key)];
        Node<K, V> tmpPrev = null;

        while (tmp != null) {
            if (getItemHash(tmp.key) == getItemHash((K)key) && Objects.equals(tmp.key, key)) {
                break;
            }
            tmpPrev = tmp;
            tmp = tmp.next;
        }

        Node<K, V> result = tmp;
        if (tmpPrev == null) {
            table[getBucketId((K) key)] = tmp.next;
        } else {
            tmpPrev.next = tmp.next;
        }
        size--;

        return result.value;
    }

    /**
     * Метод {@link CustomHashMapImpl#containsKey(Object)} возвращает булево значение
     * при проверке наличия ключа K объекта в {@link CustomHashMapImpl}.
     *
     * @param key ключ типа К для проверки.
     * @return возвращает <code>true</code> если ключ присутствует,
     * возвращает <code>false</code> если ключ отсутствует.
     */
    @Override
    public boolean containsKey(Object key) {

        if (null == key && table[0] != null) {
            return true;
        }

        return get(key) != null ? true : false;
    }

    /**
     * Метод {@link CustomHashMapImpl#containsValue(Object)} возвращает булево значение
     * при проверке наличия значения V объекта в {@link CustomHashMapImpl}.
     *
     * @param value - значение V для проверки.
     * @return возвращает <code>true</code> если значение присутствует,
     * возвращает <code>false</code> если значение отсутствует.
     */
    @Override
    public boolean containsValue(Object value) {

        if (null == value && table[0] != null) {
            return true;
        }

        for (int i = 0; i < table.length; ++i) {

            Node<K, V> tmp = table[i];
            while (tmp != null) {
                if (Objects.equals(tmp.value, value)) {
                    return true;
                }
                tmp = tmp.next;
            }
        }

        return false;
    }

    /**
     * Метод {@link CustomHashMapImpl#keys()} возвращает массив всех ключей К
     * объекта {@link CustomHashMapImpl}.
     *
     * @return массив ключей Object[]
     */
    @Override
    public Object[] keys() {
        Object[] objects = new Object[size];
        int j = 0;

        for (int i = 0; i < table.length; ++i) {
            Node<K, V> tmp = table[i];
            while (tmp != null) {
                objects[j++] = tmp.key;
                tmp = tmp.next;
            }
        }

        return objects;
    }

    /**
     * Метод {@link CustomHashMapImpl#keys()} возвращает массив всех значений К
     * объекта {@link CustomHashMapImpl}.
     *
     * @return массив значений Object[]
     */
    @Override
    public Object[] values() {
        Object[] objects = new Object[size];
        int j = 0;

        for (int i = 0; i < table.length; ++i) {
            Node<K, V> tmp = table[i];
            while (tmp != null) {
                objects[j++] = tmp.value;
                tmp = tmp.next;
            }
        }

        return objects;
    }

    /**
     * Метод {@link CustomHashMapImpl<K, V>#toString()}
     * возвращает строковое представление дерева {@link CustomHashMapImpl<K, V>}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если дерево пустое.
     */
    @Override
    public String toString() {

        StringBuilder cb = new StringBuilder();

        cb.append("[ ");

        for (int i = 0; i < table.length; ++i) {

            Node<K, V> tmp = table[i];
            while (tmp != null) {

                cb.append("{ key=" + tmp.key + ";value=" + tmp.value + " } ");
                tmp = tmp.next;
            }
        }

        cb.append("]");
        return cb.toString();
    }
}