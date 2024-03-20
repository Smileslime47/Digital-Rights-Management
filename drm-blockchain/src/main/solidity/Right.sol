// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./License.sol";

contract Right {
    string private rightName;
    address private owner;

    bool private available;
    uint32 private issueTime;
    uint32 private expireTime;

    License[] private licenses;

    constructor(uint32 iss, string memory name) {
        owner = msg.sender;
        issueTime = iss;
        rightName = name;
    }

    function getRightName() view public returns (string memory){
        return rightName;
    }
}