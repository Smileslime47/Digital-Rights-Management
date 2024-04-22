// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

/**
 * 版权管理合约
 *
 * @author 刘一邦
 */
contract DRManager {
    //------------------------------结构体定义------------------------------//

    struct Right {
        //数组下标
        uint index;
        //作品名称
        string title;
        //版权部署者
        address deployer;
        //版权所有者
        string owner;
        //登记号
        string registrationNumber;
        //发行时间
        uint64 issueTime;
        //到期时间
        uint64 expireTime;
        //版权描述
        string description;
        //文件名
        string fileName;
        //文件哈希
        string fileHash;
        //授权列表
        KeyPair[] licenses;
    }

    struct License {
        //数组下标
        uint index;
        //版权标题
        string rightTitle;
        //版权地址
        KeyPair rightKeyPair;
        //版权部署者
        address deployer;
        //版权所有者
        string owner;
        //授权时间
        uint64 issueTime;
        //过期时间
        uint64 expireTime;
        //授权描述
        string description;
    }

    /**
     * 用于在rightMap和licenseMap中唯一定位数据
     */
    struct KeyPair {
        address deployer;
        uint arrayIndex;
    }

    //------------------------------数据存储对象-----------------------------//

    //版权哈希表
    mapping(address => Right[]) private rightMap;
    //授权哈希表
    mapping(address => License[]) private licenseMap;

    //版权表Unique键映射
    mapping(string => KeyPair) private registrationNumberRightKeyMap;
    mapping(string => KeyPair) private fileHashRightKeyMap;

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
                    KeyPair memory keyPair = getEntry(titleRightKeyMap, i).value[j];

                    result[resultIndex++] = rightMap[keyPair.deployer][keyPair.arrayIndex];
                }
            }
        }

        return result;
    }

    //------------------------------以下为根据特殊键的相关操作------------------------------//

    /**
     * 根据登记号获取版权
     *
     * @param registrationNumber 登记号
     * @return 版权
     */
    function getRightByRegistrationNumber(string memory registrationNumber) view public returns (Right memory) {
        KeyPair memory pair = registrationNumberRightKeyMap[registrationNumber];
        return rightMap[pair.deployer][pair.arrayIndex];
    }

    /**
     * 根据文件哈希获取版权
     *
     * @param fileHash 文件哈希
     * @return 版权
     */
    function getRightByFileHash(string memory fileHash) view public returns (Right memory) {
        KeyPair memory pair = fileHashRightKeyMap[fileHash];
        return rightMap[pair.deployer][pair.arrayIndex];
    }

    /**
     * 根据登记号获取授权
     *
     * @param registrationNumber 登记号
     * @return 授权
     */
    function canInsertRight(string memory registrationNumber, string memory fileHash) view public returns (bool) {
        return !keyPairMapHas(registrationNumberRightKeyMap, registrationNumber) && !keyPairMapHas(fileHashRightKeyMap, fileHash);
    }

    //------------------------------以下为以地址为基础的插入操作------------------------------//

    /**
     * 添加版权
     *
     * @param owner 版权所有者
     * @param right 版权
     */
    function addRight(address owner, Right memory right) public {
        //检查特殊键不重复
        if (!canInsertRight(right.registrationNumber, right.fileHash)) {
            return;
        } else {
            uint index = rightMap[owner].length;
            Right storage storageRight = rightMap[owner].push();
            storageRight.index = index;
            storageRight.title = right.title;
            storageRight.deployer = right.deployer;
            storageRight.owner = right.owner;
            storageRight.registrationNumber = right.registrationNumber;
            storageRight.issueTime = right.issueTime;
            storageRight.expireTime = right.expireTime;
            storageRight.description = right.description;
            storageRight.fileName = right.fileName;
            storageRight.fileHash = right.fileHash;

            //添加到UniqueKey表

            KeyPair storage keyPair1 = registrationNumberRightKeyMap[storageRight.registrationNumber];
            KeyPair storage keyPair2 = fileHashRightKeyMap[storageRight.fileHash];

            keyPair1.deployer = owner;
            keyPair1.arrayIndex = index;

            keyPair2.deployer = owner;
            keyPair2.arrayIndex = index;

            //添加到Title表
            add(titleRightKeyMap, storageRight.title, owner, index);
        }
    }

    function addRight(Right memory right) public {
        addRight(msg.sender, right);
    }

    function addLicense(address owner, License memory license) public {
        if (rightMap[owner].length <= license.rightKeyPair.arrayIndex) {
            return;
        }

        if (rightMap[owner].length == 0 && license.rightKeyPair.arrayIndex == 0 && stringEquals(rightMap[owner][0].title, "")) {
            return;
        }

        uint index = licenseMap[owner].length;
        License storage storageLicense = licenseMap[owner].push();
        storageLicense.index = index;
        storageLicense.rightTitle = license.rightTitle;
        storageLicense.rightKeyPair = license.rightKeyPair;
        storageLicense.deployer = license.deployer;
        storageLicense.owner = license.owner;
        storageLicense.issueTime = license.issueTime;
        storageLicense.expireTime = license.expireTime;
        storageLicense.description = license.description;

        Right storage storageRight = rightMap[license.rightKeyPair.deployer][license.rightKeyPair.arrayIndex];
        KeyPair storage licenseKeyPair = storageRight.licenses.push();
        licenseKeyPair.deployer = owner;
        licenseKeyPair.arrayIndex = licenseMap[owner].length - 1;
    }

    function addLicense(License memory license) public {
        addLicense(msg.sender, license);
    }

    //------------------------------以下为以地址为基础的查询操作------------------------------//

    function getRight(address deployer, uint index) view public returns (Right memory) {
        return rightMap[deployer][index];
    }

    function getRight(uint index) view public returns (Right memory) {
        return getRight(msg.sender, index);
    }

    function getRights(address deployer) view public returns (Right[] memory) {
        return rightMap[deployer];
    }

    function getRights() view public returns (Right[] memory) {
        return getRights(msg.sender);
    }

    function getLastRight(address owner) view public returns (Right memory) {
        return rightMap[owner][rightMap[owner].length - 1];
    }

    function getLicense(address deployer, uint index) view public returns (License memory) {
        return licenseMap[deployer][index];
    }

    function getLicense(uint index) view public returns (License memory) {
        return getLicense(msg.sender, index);
    }

    function getLicenses(address deployer) view public returns (License[] memory) {
        return licenseMap[deployer];
    }

    function getLicenses() view public returns (License[] memory) {
        return getLicenses(msg.sender);
    }

    function getLastLicense(address owner) view public returns (License memory) {
        return licenseMap[owner][licenseMap[owner].length - 1];
    }

    //------------------------------Mapping基础函数------------------------------//

    function keyPairMapHas(mapping(string => KeyPair) storage map, string memory key) view internal returns (bool){
        KeyPair memory keyPair = map[key];
        if (keyPair.deployer == address(0) && keyPair.arrayIndex == 0) {
            return false;
        } else {
            return true;
        }
    }

    //------------------------------IterableMapping库------------------------------//

    /**
     * 哈希表键值对结构体
     */
    struct Entry {
        string key;
        KeyPair[] value;
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
    function get(IterableMap storage map, string memory key) view internal returns (KeyPair[] memory value){
        return map.entries[map.keys[key]].value;
    }

    /**
     * 获取Map中指定index的value
     *
     * @param map Map
     * @param index index
     * @return value value
     */
    function get(IterableMap storage map, uint index) view internal returns (KeyPair[] memory value){
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
     * @param _key key
     * @param _deployer 部署者
     * @param _arrayIndex 数组下标
     */
    function add(IterableMap storage map, string memory _key, address _deployer, uint _arrayIndex) internal {
        if (!has(map, _key)) {
            map.keys[_key] = map.entries.length;

            Entry storage entry = map.entries.push();
            entry.key = _key;
        }
        map.entries[map.keys[_key]].value.push(KeyPair(_deployer, _arrayIndex));
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

    //------------------------------类型转换------------------------------//

    /**
     * 地址转字符串
     *
     * @param _addr 地址
     * @return 地址字符串
     */
    function addr2str(address _addr) pure internal returns (string memory) {
        bytes32 value = bytes32(uint256(uint160(_addr)));
        bytes memory alphabet = "0123456789abcdef";

        bytes memory str = new bytes(42);
        str[0] = '0';
        str[1] = 'x';
        for (uint i = 0; i < 20; i++) {
            str[2 + i * 2] = alphabet[uint(uint8(value[i + 12] >> 4))];
            str[3 + i * 2] = alphabet[uint(uint8(value[i + 12] & 0x0f))];
        }
        return string(str);
    }

    /**
     * uint转字符串
     *
     * @param _i uint
     * @return _uintAsString uint字符串
     */
    function uint2str(uint _i) pure internal returns (string memory _uintAsString) {
        if (_i == 0) {
            return "0";
        }
        uint j = _i;
        uint len;
        while (j != 0) {
            len++;
            j /= 10;
        }
        bytes memory bstr = new bytes(len);
        uint k = len;
        while (_i != 0) {
            k = k - 1;
            uint8 temp = (48 + uint8(_i - _i / 10 * 10));
            bytes1 b1 = bytes1(temp);
            bstr[k] = b1;
            _i /= 10;
        }
        return string(bstr);
    }
}