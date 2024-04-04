// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";
import "./License.sol";

    struct Entry {
        string key;
        address value;
    }

    struct IterableMap {
        mapping(string => uint) keys;
        Entry[] entries;
    }

contract DRManager {
    mapping(address => IterableMap) private rightMap;
    mapping(address => IterableMap) private licenseMap;

    function addRight(address owner,Right right) public {
        add(rightMap[owner], right.getTitle(), address(right));
    }

    function addRight(Right right) public {
        addRight(msg.sender,right);
    }

    function getRight(address owner,string memory rightName) view public returns (Right){
        return Right(get(rightMap[owner], rightName));
    }

    function getRight(string memory rightName) view public returns (Right){
        return getRight(msg.sender,rightName);
    }

    function getRights(address owner) view public returns(Right[] memory){
        uint len = getLength(rightMap[owner]);
        Right[] memory res = new Right[](len);
        for (uint i = 0; i < len; i++) {
            res[i] = Right(get(rightMap[owner], i));
        }
        return res;
    }

    function getRights() view public returns (Right[] memory){
        return getRights(msg.sender);
    }

    /**
     * 以下为Map结构的操作函数
     */
    function get(IterableMap storage map, string memory key) view internal returns (address){
        return map.entries[map.keys[key]].value;
    }

    function get(IterableMap storage map, uint index) view internal returns (address){
        return map.entries[index].value;
    }

    function add(IterableMap storage map, string memory key, address value) internal {
        uint keyLen = map.entries.length;

        map.entries.push(Entry(key, value));
        map.keys[key] = keyLen;
    }

    function remove(IterableMap storage map, string memory key) internal {
        uint keyIndex = map.keys[key];

        map.entries[keyIndex] = map.entries[map.entries.length - 1];
        map.entries.pop();
        delete map.keys[key];
    }

    function getLength(IterableMap storage map) view internal returns (uint){
        return map.entries.length;
    }
}