#!/bin/bash
build_path=./solidity-build

if test -d $build_path; then
  rm -r $build_path
fi

solc --evm-version paris ./src/main/solidity/DRManager.sol --bin --abi --optimize -o ./solidity-build

project_path=./src/main/java

web3j generate solidity \
    -b ./solidity-build/DRManager.bin \
    -a ./solidity-build/DRManager.abi \
    -o $project_path \
    -p moe._47saikyo.drm.blockchain.contract

web3j generate solidity \
    -b ./solidity-build/Right.bin \
    -a ./solidity-build/Right.abi \
    -o $project_path \
    -p moe._47saikyo.drm.blockchain.contract

web3j generate solidity \
    -b ./solidity-build/License.bin \
    -a ./solidity-build/License.abi \
    -o $project_path \
    -p moe._47saikyo.drm.blockchain.contract
