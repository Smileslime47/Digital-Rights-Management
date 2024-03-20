// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

import "./Right.sol";

contract License {
    string private rightName;
    address private owner;

    bool private available;
    uint32 private issueTime;
    uint32 private expireTime;
}