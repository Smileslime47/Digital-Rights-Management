// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./License.sol";

/**
 * 版权合约
 *
 * @author 刘一邦
 */
contract Right {
    //合约地址
    address private addr;
    //作品名称
    string private title;
    //版权所有者
    address private owner;
    //登记号
    string private registrationNumber;
    //发行时间
    uint64 private issueTime;
    //到期时间
    uint64 private expireTime;
    //版权描述
    string private description;
    //授权列表
    License[] private licenses;
    //文件名
    string private fileName;
    //文件哈希
    string private fileHash;

    /**
     * 构造器
     *
     * @param _title 作品名称
     * @param _registrationNumber 登记号
     * @param _issueTime 发行时间
     * @param _expireTime 到期时间
     * @param _description 版权描述
     * @param _fileName 文件名
     * @param _fileHash 文件哈希
     */
    constructor(
        string memory _title,
        string memory _registrationNumber,
        uint64 _issueTime,
        uint64 _expireTime,
        string memory _description,
        string memory _fileName,
        string memory _fileHash
    ) {
        addr = address(this);
        title = _title;
        registrationNumber = _registrationNumber;
        issueTime = _issueTime;
        expireTime = _expireTime;
        description = _description;
        fileName = _fileName;
        fileHash = _fileHash;

        owner = msg.sender;
        addr = address(this);
    }

    /**
     * 添加授权
     *
     * @param _license 授权合约
     */
    function addLicense(License _license) public {
        licenses.push(_license);
    }

    /**
     * 获取授权列表
     *
     * @return 授权列表
     */
    function getLicenses() view public returns (License[] memory) {
        return licenses;
    }

    /**
     * 获取文件哈希地址
     *
     * @return 哈希地址
     */
    function getFileHash() view public returns (string memory) {
        return fileHash;
    }

    /**
     * 获取合约登记号，用于生成主键
     *
     * @return 合约登记号
     */
    function getRegistrationNumber() view public returns (string memory) {
        return registrationNumber;
    }

    /**
     * 序列化
     *
     * @return 序列化Json
     */
    function serialize() view public returns (string memory) {
        string memory result = "{";

        result = string(abi.encodePacked(result, "\"addr\":\"", addr2str(addr), "\","));
        result = string(abi.encodePacked(result, "\"title\":\"", title, "\","));
        result = string(abi.encodePacked(result, "\"owner\":\"", addr2str(owner), "\","));
        result = string(abi.encodePacked(result, "\"registrationNumber\":\"", registrationNumber, "\","));
        result = string(abi.encodePacked(result, "\"issueTime\":\"", uint2str(issueTime), "\","));
        result = string(abi.encodePacked(result, "\"expireTime\":\"", uint2str(expireTime), "\","));
        result = string(abi.encodePacked(result, "\"description\":\"", description, "\","));
        result = string(abi.encodePacked(result, "\"fileName\":\"", fileName, "\","));
        result = string(abi.encodePacked(result, "\"fileHash\":\"", fileHash, "\","));
        result = string(abi.encodePacked(result, "\"licenses\":["));
        for (uint i = 0; i < licenses.length; i++) {
            result = string(abi.encodePacked(result, licenses[i].serialize()));
            if (i < licenses.length - 1) {
                result = string(abi.encodePacked(result, ","));
            }
        }

        result = string(abi.encodePacked(result, "]}"));
        return result;
    }

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