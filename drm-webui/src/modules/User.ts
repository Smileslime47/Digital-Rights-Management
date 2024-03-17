import Constant from "~/constant/Constant.ts";

class User {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    permissionId: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    username: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    nickname: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    password: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    email: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    phoneNumber: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    chainAddress: string = Constant.Global.NULL_STRING_PLACEHOLDER
}

export default User