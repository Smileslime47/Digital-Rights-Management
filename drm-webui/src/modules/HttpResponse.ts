interface HttpResponse {
    status: {
        code: number,
        msg: string
    },
    data: any
}
export default HttpResponse