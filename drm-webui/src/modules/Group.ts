import Constant from "~/constant/Constant.ts";

class Group {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    groupName: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    permissionLogin: boolean = false;
    permissionShowProfile: boolean = false;
    permissionCreateChainAccount: boolean = false;
    permissionCreateRight: boolean = false;
    permissionVerifyRight: boolean = false;
    permissionCreateLicense: boolean = false;
    permissionVerifyLicense: boolean = false;

}

export default Group