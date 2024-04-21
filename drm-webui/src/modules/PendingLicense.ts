import Constant from "~/constant/Constant.ts";

class PendingLicense {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    rightTitle: string;
    rightAddr: string;
    deployer: string;
    owner: string;
    issueTime: number;
    expireTime: number;
    description: string;
    status: string = "PENDING";
    estimatePrice: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    comment: string = "请耐心等待审核。";
    createTime: number = new Date().getTime();

    //all-args
    constructor(
        rightTitle: string,
        rightAddr: string,
        deployer: string,
        owner: string,
        issueTime: number,
        expireTime: number,
        description: string,
    ) {
        this.rightTitle = rightTitle;
        this.rightAddr = rightAddr;
        this.deployer = deployer;
        this.owner = owner;
        this.issueTime = issueTime;
        this.expireTime = expireTime;
        this.description = description;
    }
}

export const EmptyPendingLicense = () => {
    return new PendingLicense(
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
    )
}

export default PendingLicense