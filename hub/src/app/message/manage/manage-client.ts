import Message from '../message'
import { MessageType } from '../message-type'
import ClientInfo from '../../server/client-info'

export class ManageClientMessage extends Message {
    public type: MessageType = MessageType.stc_manage_connect
    public content: any

    constructor(content: any, client: ClientInfo) {
        super()
        this.content = content
        this.client = client
    }
}
