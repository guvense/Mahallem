import ClientInfo from '../server/client-info'
import { BusChannel } from '../event-bus/bus-channel'
import IApp from '../iapp'

export default abstract class EventHandler {
    public channel: BusChannel
    //  todo: main app'i geçir

    constructor(channel: BusChannel, app: IApp) {
        this.channel = channel
    }

    // tslint:disable-next-line:no-empty
    public async read<T>(client: ClientInfo, data: T) {
    }
}

//  client: ClientInfo
