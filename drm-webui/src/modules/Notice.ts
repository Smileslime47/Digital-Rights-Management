import Constant from "~/constant/Constant.ts";

class Notice {
    id: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    title: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    content: string = Constant.Global.NULL_STRING_PLACEHOLDER;
    sentTime: number = Constant.Global.NULL_NUMBER_PLACEHOLDER;
    status: string = Constant.NoticeFilter.UNREAD;
}

export default Notice