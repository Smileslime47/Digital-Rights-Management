import Constant from "~/constant/Constant.ts";

class PendingRight {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    title: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    owner: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    registrationNumber: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    issueTime: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    expireTime: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    description: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    fileName: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    fileHash: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    status: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    estimatePrice: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    comment: string = "请耐心等待审核。";
    createTime: number = new Date().getTime();

    //all-args
    constructor(
        id: number,
        title: string,
        owner: string,
        registrationNumber: string,
        issueTime: number,
        expireTime: number,
        description: string,
        fileName: string,
        fileHash: string,
        status: string
    ) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.registrationNumber = registrationNumber;
        this.issueTime = issueTime;
        this.expireTime = expireTime;
        this.description = description;
        this.fileName = fileName;
        this.fileHash = fileHash;
        this.status = status;
    }
}

export const EmptyPendingRight = () => {
    return new PendingRight(
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
    )
}

export default PendingRight