import ClientInfo from '../server/client-info'
import BusMessage from './bus-message'
import { BusChannel } from '../event-bus/bus-channel'



export default class ConnectionEvent extends BusMessage {
    public type: String

    constructor(type: String, client: ClientInfo) {
        super(client, BusChannel[BusChannel.A])
        this.type = type
    }

}
