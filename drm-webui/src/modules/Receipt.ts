import Constant from "~/constant/Constant.ts";

class Receipt {
    id: number;
    status: string;
    transactionHash: string;
    blockHash: string;
    blockNumber: number;
    gasUsed: number;
    deployer: string;
    deployIndex: number;
    createTime: number;

    //all-args
    constructor(
        id: number,
        status: string,
        transactionHash: string,
        blockHash: string,
        blockNumber: number,
        gasUsed: number,
        deployer: string,
        deployIndex: number,
        createTime: number,
    ) {
        this.id = id;
        this.status = status;
        this.transactionHash = transactionHash;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.gasUsed = gasUsed;
        this.deployer = deployer;
        this.deployIndex = deployIndex;
        this.createTime = createTime;
    }
}

export const EmptyReceipt = () => {
    return new Receipt(
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
    )
}

export default Receipt