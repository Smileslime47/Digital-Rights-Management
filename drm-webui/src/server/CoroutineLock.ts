export class CoroutineLock{
    private lock: boolean = false
    private queue: (() => void)[] = []

    public async lockCoroutine() {
        return new Promise<void>((resolve) => {
            if (this.lock) {
                this.queue.push(resolve)
            } else {
                this.lock = true
                resolve()
            }
        })
    }

    public unlockCoroutine() {
        if (this.queue.length > 0) {
            this.queue.shift()!()
        } else {
            this.lock = false
        }
    }
}