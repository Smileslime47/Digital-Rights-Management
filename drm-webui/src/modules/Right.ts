import Constant from "~/constant/Constant.ts";

class Right {
    addr: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    title: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    deployer: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    owner: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    registrationNumber: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    issueTime: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    expireTime: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    description: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    fileName: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    fileHash: string = Constant.Global.NULL_STRING_PLACEHOLDER;

    //all-args
    constructor(
        title: string,
        deployer: string,
        owner: string,
        registrationNumber: string,
        issueTime: number,
        expireTime: number,
        description: string,
        fileName: string,
        fileHash: string,
    ) {
        this.title = title;
        this.deployer = deployer;
        this.owner = owner;
        this.registrationNumber = registrationNumber;
        this.issueTime = issueTime;
        this.expireTime = expireTime;
        this.description = description;
        this.fileName = fileName;
        this.fileHash = fileHash;
    }
}

export const EmptyRight = () => {
    return new Right(
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_NUMBER_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
        Constant.Global.NULL_STRING_PLACEHOLDER,
    )
}

export default Right