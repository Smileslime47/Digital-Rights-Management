// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";
import "./License.sol";

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

/**
 * 版权管理合约
 *
 * @author 刘一邦
 */
contract DRManager {
    //版权哈希表
    mapping(address => IterableMap) private rightMap;
    //授权哈希表
    mapping(address => IterableMap) private licenseMap;

    /**
     * 添加版权
     *
     * @param owner 所有者
     * @param right 版权
     */
    function addRight(address owner, Right right) public {
        add(rightMap[owner], right.getKey(), address(right));
    }

    /**
     * 添加版权，默认所有者为调用者
     *
     * @param right 版权
     */
    function addRight(Right right) public {
        addRight(msg.sender, right);
    }

    /**
     * 获取版权
     *
     * @param owner 所有者
     * @param key 主键
     */
    function getRight(address owner, string memory key) view public returns (Right){
        return Right(get(rightMap[owner], key));
    }

    /**
     * 获取版权，默认所有者为调用者
     *
     * @param key 主键
     * @return 版权
     */
    function getRight(string memory key) view public returns (Right){
        return getRight(msg.sender, key);
    }

    /**
     * 获取所有版权
     *
     * @param owner 所有者
     * @return 所有版权
     */
    function getRights(address owner) view public returns (Right[] memory){
        uint len = getLength(rightMap[owner]);
        Right[] memory res = new Right[](len);
        for (uint i = 0; i < len; i++) {
            res[i] = Right(get(rightMap[owner], i));
        }
        return res;
    }

    /**
     * 获取所有版权，默认所有者为调用者
     *
     * @return 所有版权
     */
    function getRights() view public returns (Right[] memory){
        return getRights(msg.sender);
    }

    /**
     * 添加授权
     *
     * @param owner 所有者
     * @param license 授权
     */
    function addLicense(address owner, License license) public {
        add(licenseMap[owner], license.getKey(), address(license));
    }

    /**
     * 添加授权，默认所有者为调用者
     *
     * @param license 授权
     */
    function addLicense(License license) public {
        add(licenseMap[msg.sender], license.getKey(), address(license));
    }

    /**
     * 获取版权
     *
     * @param owner 所有者
     * @param key 主键
     */
    function getLicense(address owner, string memory key) view public returns (License){
        return License(get(licenseMap[owner], key));
    }

    /**
     * 获取版权，默认所有者为调用者
     *
     * @param key 主键
     * @return 版权
     */
    function getLicense(string memory key) view public returns (License){
        return getLicense(msg.sender, key);
    }

    /**
     * 获取所有授权
     *
     * @param owner 所有者
     * @return 所有授权
     */
    function getLicenses(address owner) view public returns (License[] memory){
        uint len = getLength(licenseMap[owner]);
        License[] memory res = new License[](len);
        for (uint i = 0; i < len; i++) {
            res[i] = License(get(licenseMap[owner], i));
        }
        return res;
    }

    /**
     * 获取所有授权，默认所有者为调用者
     *
     * @return 所有授权
     */
    function getLicenses() view public returns (License[] memory){
        return getLicenses(msg.sender);
    }

    //------------------------------以下为Map结构的操作函数------------------------------

    /**
     * 获取Map中指定key的value
     *
     * @param map Map
     * @param key key
     * @return value
     */
    function get(IterableMap storage map, string memory key) view internal returns (address){
        return map.entries[map.keys[key]].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return value
     */
    function get(IterableMap storage map, uint index) view internal returns (address){
        return map.entries[index].value;
    }

    /**
     * 添加元素
     *
     * @param map Map
     * @param key key
     * @param value value
     */
    function add(IterableMap storage map, string memory key, address value) internal {
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
    function getLength(IterableMap storage map) view internal returns (uint){
        return map.entries.length;
    }
}