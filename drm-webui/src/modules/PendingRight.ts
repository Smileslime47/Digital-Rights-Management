import Constant from "~/constant/Constant.ts";

class PendingRight {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    title: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    owner: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    registrationNumber: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    issueTime:number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    expireTime:number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    description: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    status: string = Constant.Global.NULL_STRING_PLACEHOLDER;
}

export default PendingRight