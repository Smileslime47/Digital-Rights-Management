// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./License.sol";

contract Right {
    //作品名称
    string private title;
    //版权所有者
    address private owner;
    //登记号
    string private registrationNumber;
    //发行时间
    uint32 private issueTime;
    //到期时间
    uint32 private expireTime;
    //版权描述
    string private description;
    //是否可用
    bool private available;
    //授权列表
    License[] private licenses;

    constructor(string memory _title, string memory _registrationNumber, uint32 _issueTime, uint32 _expireTime, string memory _description) {
        title = _title;
        owner = msg.sender;
        registrationNumber = _registrationNumber;
        issueTime = _issueTime;
        expireTime = _expireTime;
        description = _description;
        available = true;
    }

    //添加授权
    function addLicense(License _license) public {
        licenses.push(_license);
    }

    //获取版权名称
    function getTitle() view public returns (string memory){
        return title;
    }

    //序列化
    function serialize() view public returns (string memory){
        string memory result = "{";
        result = string(abi.encodePacked(result, "\"title\":\"", title, "\","));
        result = string(abi.encodePacked(result, "\"owner\":\"", addr2str(owner), "\","));
        result = string(abi.encodePacked(result, "\"registrationNumber\":\"", registrationNumber, "\","));
        result = string(abi.encodePacked(result, "\"issueTime\":\"", uint2str(issueTime), "\","));
        result = string(abi.encodePacked(result, "\"expireTime\":\"", uint2str(expireTime), "\","));
        result = string(abi.encodePacked(result, "\"description\":\"", description, "\","));
        result = string(abi.encodePacked(result, "\"available\":\"", available ? "true" : "false", "\","));
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

    function addr2str(address _addr) pure internal returns (string memory) {
        bytes32 value = bytes32(uint256(uint160(_addr)));
        bytes memory alphabet = "0123456789abcdef";

        bytes memory str = new bytes(42);
        str[0] = '0';
        str[1] = 'x';
        for (uint i = 0; i < 20; i++) {
            str[2+i*2] = alphabet[uint(uint8(value[i + 12] >> 4))];
            str[3+i*2] = alphabet[uint(uint8(value[i + 12] & 0x0f))];
        }
        return string(str);
    }

    function uint2str(uint _i) internal pure returns (string memory _uintAsString) {
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
            k = k-1;
            uint8 temp = (48 + uint8(_i - _i / 10 * 10));
            bytes1 b1 = bytes1(temp);
            bstr[k] = b1;
            _i /= 10;
        }
        return string(bstr);
    }
}