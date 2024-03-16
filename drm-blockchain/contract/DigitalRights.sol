// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

contract Account {
    bool private available;
    uint32 private dbId;
    Right[] private rights;
    License[] private licenses;

    function addRight(Right right) public {
        rights.push(right);
    }

    function addLicense(License license) public {
        licenses.push(license);
    }
}

contract Right {
    Account private owner;
    License[] private licenses;

    constructor(Account own){
        owner = own;

        owner.addRight(this);
    }

    function addLicense(License license) public {
        licenses.push(license);
    }
}

contract License {
    Account private author;
    Account private owner;
    Right private right;

    uint32 private expireTime;
    uint32 private issueTime;

    constructor(Account aut,Account own,Right rig,uint32 exp,uint32 iss){
        author=aut;
        owner=own;
        right=rig;
        expireTime=exp;
        issueTime=iss;

        owner.addLicense(this);
        right.addLicense(this);
    }
}