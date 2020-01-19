import ClientInfo from '../server/client-info'

export default abstract class BusMessage {
    public channel: string
    public client: ClientInfo

    constructor(client: ClientInfo, channel: string) {
        this.client = client
        this.channel = channel
    }

    public ToJson() {
        return JSON.stringify(this)
    }
}
