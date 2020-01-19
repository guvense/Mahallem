import ClientInfo from '../server/client-info'
import Message from './message'
import { MessageType } from './message-type'

export default class MessageHelper extends Message {
    public static Create(type: MessageType, client: ClientInfo, content: object = {}, error: string[] = null): Message {
        return new MessageHelper({
            type,
            client,
            content,
            error,
            read: null,
        }, null)
    }
    public type: MessageType
    public content: any
}
