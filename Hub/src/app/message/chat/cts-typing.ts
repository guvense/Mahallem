import Message from '../message'
import { MessageType } from '../message-type'
import MessageHelper from '../message-helper'

export default class TypingMessage extends Message {
    public type = MessageType.cts_typing
    public content: any

    public async read(fn: (param: any) => void) {
        const toClient = this.app.getClientInfo(this.content)
        if (!toClient) {
            return super.read(fn)
        }

        this.app.send(MessageHelper.Create(MessageType.stc_typing, toClient))
        super.read(fn)
    }
}
