import ClientInfo from '../server/client-info'
import { MessageType } from './message-type'

export default interface IMessage {
    type: MessageType
    client: ClientInfo
    content: object
    error: string[]
    read(fn: (param: any) => void): void
}
