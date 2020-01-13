import ClientInfo from '../server/client-info'
import BusMessage from './bus-message'
import { BusChannel } from '../event-bus/bus-channel'
import MessageEventModel from './model/messageEventModel'



export default class MessageEventSender extends BusMessage {
    public client: ClientInfo
    public content: MessageEventModel

    constructor(client: ClientInfo, content: MessageEventModel) {
        super(client, BusChannel[BusChannel.MESSAGE_CONTENT])
        this.content = content

    }

}
