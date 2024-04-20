// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";

/**
 * 授权合约
 *
 * @author 刘一邦
 */
contract License {
    //合约地址
    address private addr;
    //版权地址
    address private right;
    //版权部署者
    address private deployer;
    //版权所有者
    string private owner;
    //授权时间
    uint64 private issueTime;
    //过期时间
    uint64 private expireTime;
    //授权描述
    string private description;

    /**
     * 构造器
     *
     * @param _right 版权地址
     * @param _issueTime 授权时间
     * @param _expireTime 过期时间
     */
    constructor(address _right, string memory _owner, uint64 _issueTime, uint64 _expireTime, string memory _description) {
        right = _right;
        owner = _owner;
        issueTime = _issueTime;
        expireTime = _expireTime;
        description = description;

        deployer = msg.sender;
        addr = address(this);
    }

    /**
     * 获取授权地址，用于生成主键
     *
     * @return 授权地址
     */
    function getKey() view public returns (string memory) {
        return addr2str(address(this));
    }

    /**
     * 序列化
     *
     * @return 序列化Json
     */
    function serialize() view public returns (string memory) {
        string memory result = "{";

        result = string(abi.encodePacked(result, "\"addr\":\"", addr2str(addr), "\","));
        result = string(abi.encodePacked(result, "\"right\":\"", addr2str(right), "\","));
        result = string(abi.encodePacked(result, "\"deployer\":\"", addr2str(deployer), "\","));
        result = string(abi.encodePacked(result, "\"owner\":\"", owner, "\","));
        result = string(abi.encodePacked(result, "\"issueTime\":\"", uint2str(issueTime), "\","));
        result = string(abi.encodePacked(result, "\"expireTime\":\"", uint2str(expireTime), "\","));
        result = string(abi.encodePacked(result, "\"description\":\"", description, "\""));

        result = string(abi.encodePacked(result, "}"));
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