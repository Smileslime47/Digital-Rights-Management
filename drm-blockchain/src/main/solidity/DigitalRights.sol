// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

contract DRManager{
    mapping (address=>Right[]) private rights;
    mapping (address=>License[]) private licenses;
}

contract Right {
    address private owner;
    uint32 private issueTIme;
    string private rightName;

    License[] private licenses;

    constructor(uint32 iss,string memory name) {
        owner = msg.sender;
        issueTIme = iss;
        rightName = name;
    }
}

contract License {
    address private author;
    address private owner;

    Right private right;
    uint private index;

    bool private available;
    uint32 private issueTime;
    uint32 private expireTime;
}
