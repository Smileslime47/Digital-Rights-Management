class LocalToken {
    expire_time: string;
    group_id: string;
    token: string;
    user_id: string;
    user_nickname: string;

    constructor(
        expire_time: string,
        group_id: string,
        token: string,
        user_id: string,
        user_nickname: string) {
        this.expire_time = expire_time
        this.group_id = group_id
        this.token = token
        this.user_id = user_id
        this.user_nickname = user_nickname
    }
}

export default LocalToken