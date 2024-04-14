// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";
import "./License.sol";

/**
 * 版权管理合约
 *
 * @author 刘一邦
 */
contract DRManager {
    //版权哈希表
    mapping(address => Right[]) private rightMap;
    //授权哈希表
    mapping(address => License[]) private licenseMap;

    //版权表Unique键映射
    mapping(string => address) private registrationNumberRightKeyMap;
    mapping(string => address) private fileHashRightKeyMap;

    //Title键可重名，用于做搜索功能
    IterableMap private titleRightKeyMap;

    //------------------------------以下为搜索的相关操作------------------------------//

    /**
     * 根据作品名称搜索版权
     *
     * @param title 作品名称
     * @return 版权列表
     */
    function searchByTitle(string memory title) view public returns (Right[] memory) {
        //如果存在完全匹配项直接返回
        if (has(titleRightKeyMap, title)) {
            Right[] memory exactResult = new Right[](get(titleRightKeyMap, title).length);
            for (uint i = 0; i < get(titleRightKeyMap, title).length; i++) {
                exactResult[i] = Right(get(titleRightKeyMap, title)[i]);
            }
            return exactResult;
        }

        //计算结果长度
        uint resultLen = 0;
        for (uint i = 0; i < getLength(titleRightKeyMap); i++) {
            if (kmpSearch(getEntry(titleRightKeyMap, i).key, title) != - 1) {
                resultLen += getEntry(titleRightKeyMap, i).value.length;
            }
        }

        //填充结果
        Right[] memory result = new Right[](resultLen);
        uint resultIndex = 0;
        for (uint i = 0; i < getLength(titleRightKeyMap); i++) {
            if (kmpSearch(getEntry(titleRightKeyMap, i).key, title) != - 1) {
                for (uint j = 0; j < getEntry(titleRightKeyMap, i).value.length; j++) {
                    result[resultIndex++] = Right(getEntry(titleRightKeyMap, i).value[j]);
                }
            }
        }

        return result;
    }

    //------------------------------以下为根据特殊键的相关操作------------------------------//

    function getRightByRegistrationNumber(string memory registrationNumber) view public returns (Right) {
        return Right(registrationNumberRightKeyMap[registrationNumber]);
    }

    function getRightByFileHash(string memory fileHash) view public returns (Right) {
        return Right(fileHashRightKeyMap[fileHash]);
    }

    function canInsertRight(string memory registrationNumber, string memory fileHash) view public returns (bool) {
        return !has(registrationNumberRightKeyMap, registrationNumber) && !has(fileHashRightKeyMap, fileHash);
    }

    //------------------------------以下为以地址为基础的插入操作------------------------------//

    function addRight(address owner, Right right) public returns (bool result) {
        //检查特殊键不重复
        if (!canInsertRight(right.getRegistrationNumber(), right.getFileHash())) {
            return false;
        } else {
            //添加到UniqueKey表
            registrationNumberRightKeyMap[right.getRegistrationNumber()] = address(right);
            fileHashRightKeyMap[right.getFileHash()] = address(right);

            //添加到Title表
            add(titleRightKeyMap, right.getTitle(), address(right));

            rightMap[owner].push(right);
            return true;
        }
    }

    function addRight(Right right) public returns (bool result) {
        return addRight(msg.sender, right);
    }

    function addLicense(address owner, License license) public {
        licenseMap[owner].push(license);
    }

    function addLicense(License license) public {
        addLicense(msg.sender, license);
    }

    //------------------------------以下为以地址为基础的查询操作------------------------------//

    function getRights(address owner) view public returns (Right[] memory) {
        return rightMap[owner];
    }

    function getRights() view public returns (Right[] memory) {
        return getRights(msg.sender);
    }

    function getLicenses(address owner) view public returns (License[] memory) {
        return licenseMap[owner];
    }

    function getLicenses() view public returns (License[] memory) {
        return getLicenses(msg.sender);
    }

    //------------------------------Mapping基础函数------------------------------//

    function has(mapping(string => address) storage map, string memory key) view internal returns (bool){
        return map[key] != address(0);
    }

    //------------------------------IterableMapping库------------------------------//

    /**
     * 哈希表键值对结构体
     */
    struct Entry {
        string key;
        address[] value;
    }

    /**
     * 可迭代哈希表结构体
     */
    struct IterableMap {
        mapping(string => uint) keys;
        Entry[] entries;
    }

    /**
     * 获取Map中指定key的value
     *
     * @param map Map
     * @param key key
     * @return value value
     */
    function get(IterableMap storage map, string memory key) view internal returns (address[] memory value){
        return map.entries[map.keys[key]].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return value value
     */
    function get(IterableMap storage map, uint index) view internal returns (address[] memory value){
        return map.entries[index].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return entry entry
     */
    function getEntry(IterableMap storage map, uint index) view internal returns (Entry memory entry) {
        return map.entries[index];
    }

    /**
     * 添加元素，在库基础上进行修改
     *
     * @param map Map
     * @param key key
     * @param value value
     */
    function add(IterableMap storage map, string memory key, address value) internal {
        uint keyIndex = map.keys[key];

        if (!has(map, key)) {
            uint keyLen = map.entries.length;

            map.entries.push(Entry(key, new address[](0)));
            map.keys[key] = keyLen;
            keyIndex = keyLen;
        }

        map.entries[keyIndex].value.push(value);
    }

    /**
     * 移除元素
     *
     * @param map Map
     * @param key key
     */
    function remove(IterableMap storage map, string memory key) internal {
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
    function getLength(IterableMap storage map) view internal returns (uint) {
        return map.entries.length;
    }

    /**
     * 判断Map中是否存在指定key
     *
     * @param map Map
     * @param key key
     * @return 是否存在
     */
    function has(IterableMap storage map, string memory key) view internal returns (bool) {
        if (map.keys[key] == 0) {
            return getLength(map) > 0 && stringEquals(map.entries[0].key, key);
        } else {
            return true;
        }
    }

    //------------------------------KMP算法------------------------------//

    /**
     * KMP算法
     *
     * @param s 主串
     * @param p 模式串
     * @return 位置
     */
    function kmpSearch(string memory s, string memory p) pure internal returns (int) {
        int n = int(bytes(s).length);
        int m = int(bytes(p).length);
        int[] memory next = kmpGetNext(p);
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (j == - 1 || stringCharAt(s, uint(i)) == stringCharAt(p, uint(j))) {
                i++;
                j++;
            } else {
                j = next[uint(j)];
            }
        }
        if (j == m) {
            return i - j;
        } else {
            return - 1;
        }
    }

    /**
     * 获取next数组
     *
     * @param p 模式串
     * @return next数组
     */
    function kmpGetNext(string memory p) pure internal returns (int[] memory) {
        int m = int(bytes(p).length);
        int[] memory next = new int[](uint(m));
        next[0] = - 1;
        int k = - 1;
        int j = 0;
        while (j < m - 1) {
            if (k == - 1 || stringCharAt(p, uint(j)) == stringCharAt(p, uint(k))) {
                k++;
                j++;
                next[uint(j)] = k;
            } else {
                k = next[uint(k)];
            }
        }
        return next;
    }

    //------------------------------字符串操作------------------------------//

    /**
     * 判断两个字符串是否相等
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 是否相等
     */
    function stringEquals(string memory s1, string memory s2) pure internal returns (bool) {
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

    /**
     * 获取字符串指定位置的字符
     *
     * @param s 字符串
     * @param index 位置
     * @return 字符
     */
    function stringCharAt(string memory s, uint index) pure internal returns (bytes1) {
        return bytes(s)[index];
    }
}