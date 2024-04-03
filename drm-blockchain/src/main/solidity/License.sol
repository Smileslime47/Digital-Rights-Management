// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";

contract License {
    string private rightName;
    address private owner;
    bool private available;
    uint64 private issueTime;
    uint64 private expireTime;

    //constructor
    constructor(string memory _rightName, uint32 _issueTime, uint32 _expireTime) {
        rightName = _rightName;
        owner = msg.sender;
        available = true;
        issueTime = _issueTime;
        expireTime = _expireTime;
    }

    //serialize the whole contract
    function serialize() view public returns (string memory){
        return string(abi.encodePacked("{\"rightName\":\"", rightName, "\",\"owner\":\"", addr2str(owner), "\",\"available\":", available ? "true" : "false", ",\"issueTime\":", uint2str(issueTime), ",\"expireTime\":", uint2str(expireTime), "}"));
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