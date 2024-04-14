// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

/**
 * 哈希表键值对结构体
 */
    struct Entry {
        string key;
        address value;
    }

/**
 * 可迭代哈希表结构体
 */
    struct IterableMap {
        mapping(string => uint) keys;
        Entry[] entries;
    }

library IterableMapLib {
    /**
     * 获取Map中指定key的value
     *
     * @param map Map
     * @param key key
     * @return value value
     */
    function get(IterableMap storage map, string memory key) view public returns (address value){
        return map.entries[map.keys[key]].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return value value
     */
    function get(IterableMap storage map, uint index) view public returns (address value){
        return map.entries[index].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return entry entry
     */
    function getEntry(IterableMap storage map, uint index) view public returns (Entry memory entry) {
        return map.entries[index];
    }

    /**
     * 添加元素
     *
     * @param map Map
     * @param key key
     * @param value value
     */
    function add(IterableMap storage map, string memory key, address value) public {
        uint keyLen = map.entries.length;

        map.entries.push(Entry(key, value));
        map.keys[key] = keyLen;
    }

    /**
     * 移除元素
     *
     * @param map Map
     * @param key key
     */
    function remove(IterableMap storage map, string memory key) public {
        uint keyIndex = map.keys[key];

        map.entries[keyIndex] = map.entries[map.entries.length - 1];
        map.entries.pop();
        delete map.keys[key];
    }

    /**
     * 获取Map长度
     *
     * @param map Map
     * @return 长度
     */
    function getLength(IterableMap storage map) view public returns (uint){
        return map.entries.length;
    }

    /**
     * 判断Map中是否存在指定key
     *
     * @param map Map
     * @param key key
     * @return 是否存在
     */
    function has(IterableMap storage map, string memory key) view public returns (bool) {
        if (map.keys[key] == 0) {
            return getLength(map) > 0 && equals(map.entries[0].key, key);
        } else {
            return true;
        }
    }

    /**
     * 判断两个字符串是否相等
      *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 是否相等
     */
    function equals(string memory s1, string memory s2) pure public returns (bool) {
        bytes memory b1 = bytes(s1);
        bytes memory b2 = bytes(s2);
        if (b1.length != b2.length) {
            return false;
        }
        for (uint i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}